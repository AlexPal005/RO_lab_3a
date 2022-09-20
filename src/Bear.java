
public class Bear implements Runnable {
    private int count_sips;
    private Pot pot;
    public Bear ( int count_sips, Pot pot){
        this.count_sips = count_sips;
        this.pot = pot;
    }
    @Override
    public void run(){
        try {
            while (true) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (pot.get_current_sips() == count_sips) {
                        pot.getLocker().acquire();
                        int curr = pot.get_current_sips();
                        while(curr != 0) {
                            Thread.sleep(1500);
                            pot.set_current_sips(curr);
                            System.out.println(Thread.currentThread().getName() + " Bear drink " + curr );
                            curr--;
                        }
                        pot.set_current_portions(0);
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
