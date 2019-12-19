import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Door extends Thread{

    private static final Semaphore moveThruDoor = new Semaphore(1, true);

    public void comeToDoorOutside(){
        System.out.println(Thread.currentThread().getName() + " подошел к двери с улицы");
    }

    public void comeToDoorInside(){
        System.out.println(Thread.currentThread().getName() + " подошел к двери изнутри");
    }

    public void moveInside() throws InterruptedException {
        try {
            moveThruDoor.acquire();
            System.out.println(Thread.currentThread().getName() + " проходит через дверь внутрь");
            int waitTime = 500;
            TimeUnit.MILLISECONDS.sleep(waitTime);
            moveThruDoor.release();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void moveOutside() throws InterruptedException {
        try{
            moveThruDoor.acquire();
            System.out.println(Thread.currentThread().getName() + " проходит через дверь наружу");
            int waitTime = 500;
            TimeUnit.MILLISECONDS.sleep(waitTime);
            moveThruDoor.release();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void movedThruDoorInside(){
        System.out.println(Thread.currentThread().getName() + " прошел через дверь внутрь");
    }

    public void movedThruDoorOutside(){
        System.out.println(Thread.currentThread().getName() + " прошел через дверь наружу");
    }

}
