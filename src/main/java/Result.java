import java.util.concurrent.Semaphore;

public class Result {
    int total = 0;
    int [] gabarito = new int[45];
    boolean finished = false;
    private final Semaphore available = new Semaphore(1,true);
    public int[] getResult()
    {
        if (finished) {
            return gabarito;
        }
        return null;
    }
    public void putResult(int[] respostas) throws InterruptedException
    {
        available.acquire();
        for (int i = 0; i < 45; i++) {
            gabarito[i] += respostas[i];
        }
        total++;
        available.release();
    }
    public void setFinished(){
        finished = true;
    }
    public void print(){
        for (int i = 0; i < 45; i++) {
            float p = (float) gabarito[i] / (float) total;
            //System.out.println("Q"+i+": "+gabarito[i]);
            System.out.println("Q"+(i+1)+": "+100*p+"%");
        }
    }
}
