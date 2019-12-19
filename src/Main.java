import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int peopleCount;
        int libraryMaxAmount;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите кол-во людей");
        peopleCount = Integer.parseInt(reader.readLine());

        System.out.println("Введите лимит кол-ва людей которые одновременно могут находиться в библиотеке");
        libraryMaxAmount = Integer.parseInt(reader.readLine());


        Library.setMaxAmount(libraryMaxAmount);
        for (int i =0; i<peopleCount; i++){
            new Thread(new Library.Human(i)).start();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
