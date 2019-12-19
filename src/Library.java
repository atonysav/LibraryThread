import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Library {
    private static int maxAmount;
    private static boolean[] libraryPlaces;
    private static Semaphore semaphore;
    private static final Door door = new Door();

    static void setMaxAmount(int amount) {
        maxAmount = amount;
        libraryPlaces = new boolean[amount];
        semaphore = new Semaphore(maxAmount, true);
    }

    public static class Human implements Runnable{
        private int humanNumber;

        public Human(int humanNumber) {
            this.humanNumber = humanNumber;
        }

        @Override
        public void run() {
            System.out.println("человек: " + humanNumber + " пришел ко входу в библиотеку" + "   - " + Thread.currentThread().getName());
            door.comeToDoorOutside();

            try {

                semaphore.acquire();
                int libraryNumber = -1;

                synchronized (libraryPlaces) {
                    for (int i = 0; i <= libraryPlaces.length; i++) {
                        if (!libraryPlaces[i]) {
                            libraryPlaces[i] = true;
                            door.moveInside();
                            libraryNumber = i;
                            System.out.println("человек: " + humanNumber + " вошел в библиотеку" + "   - " + Thread.currentThread().getName());
                            door.movedThruDoorInside();
                            System.out.println("человек: " + humanNumber + " читает книгу" + "   - " + Thread.currentThread().getName());
                            break;
                        }
                        else {
                            System.out.println("человек: " + humanNumber + " ждет входа в библиотеку" + "   - " + Thread.currentThread().getName());
                        }
                    }
                }
                int waitTime = new Random().nextInt(4)+1;
                TimeUnit.SECONDS.sleep(waitTime);

                synchronized (libraryPlaces) {
                    libraryPlaces[libraryNumber] = false;
                }
                semaphore.release();
                door.comeToDoorInside();
                door.moveOutside();
                System.out.println("человек: " + humanNumber + " вышел из библиотеки" + "   - " + Thread.currentThread().getName());
                door.movedThruDoorOutside();
            }catch (InterruptedException e){
                //
            }
        }
    }
}
