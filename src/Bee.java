import java.util.concurrent.Semaphore;

public class Bee implements Runnable{

    private int count_portions;
    private Pot pot;
    public Bee( int count_portions, Pot pot){
        this.count_portions = count_portions;
        this.pot = pot;
    }
    @Override
    public void run(){
        try {
            while (true) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (pot.get_current_portions() < count_portions) {
                        pot.getLocker().acquire();
                        int curr = pot.get_current_portions();
                        curr++;
                        pot.set_current_portions(curr);
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " " +  curr);
                        pot.getLocker().release();
                    }
                }
                else{
                    throw new InterruptedException();
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}