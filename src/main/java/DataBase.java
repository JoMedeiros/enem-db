import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DataBase {
    //private int i = 0;
    private AtomicInteger i = new AtomicInteger(0);
    private CopyOnWriteArrayList lines = new CopyOnWriteArrayList();

    public void add(String[] line){
        lines.add(line);
    }
    public boolean isFinished(){
        return i.get()+1 == lines.size();
    }
    public String[] getLine(){
        return (String[]) lines.get(i.getAndIncrement());
    }
    public void reset(){
        i.set(0);
    }
}
