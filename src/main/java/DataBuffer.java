import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class DataBuffer {
    volatile boolean finished = false; // OBS: Not a good idea

    LinkedList<String> lines = new LinkedList();
    private final Semaphore available = new Semaphore(0,true);
    public String getLine() throws InterruptedException
    {
        available.acquire();
        return lines.pop();
    }
    public void putLine(String line)
    {
        lines.push(line);
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
