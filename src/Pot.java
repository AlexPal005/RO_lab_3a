import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Pot {

    private int current_sips = 0 ;
    private int current_portions = 0;
    private Semaphore locker ;
    public Pot(Semaphore locker){
        this.locker = locker;
    }
    public void set_current_sips(int sips){
        this.current_sips = sips;
    }
    public void set_current_portions(int portions){
        this.current_portions = portions;
        this.current_sips = portions / 3;
    }
    public int get_current_sips(){
        return this.current_sips;
    }
    public int get_current_portions(){
       return  this.current_portions;
    }

    public Semaphore getLocker() {
        return this.locker;
    }
}
