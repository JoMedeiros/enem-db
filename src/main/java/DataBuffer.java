import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class DataBuffer {
    volatile boolean finished = false; // OBS: Not a good idea
    private final ConcurrentLinkedQueue<String> lines = new ConcurrentLinkedQueue<>();
    private final Semaphore available = new Semaphore(0,true);

    public String getLine() throws InterruptedException
    {
        available.acquire();
        return lines.remove();
    }
    public void putLine(String line)
    {
        lines.add(line);
        available.release();
    }
    boolean isEmpty(){
        return lines.isEmpty();
    }

    void setFinished(){
        this.finished = true;
    }
    boolean isFinished(){
        return this.finished;
    }
}
