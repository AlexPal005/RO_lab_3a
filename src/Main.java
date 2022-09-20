import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
public class Main {
    public static void main(String[] args) {
        new Main().go();
    }
    private Semaphore locker;
    public void go(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter count of bees:");
        int count_bees = in.nextInt();
        System.out.println("Enter count of sips:");
        int count_sips = in.nextInt();
        // нехай 1 ковток = 100 порцій меду, що принесуть бджоли
        int count_portions = count_sips * 3;
        locker = new Semaphore(count_bees, true);
        Pot pot = new Pot( locker);
        new Thread (new Bear (count_sips, pot)).start();
        for( int i = 0 ; i < count_bees ; i++ ){
            new Thread ( new Bee(count_portions, pot)).start();
        }
    }
}