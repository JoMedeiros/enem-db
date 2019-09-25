package br.ufrn;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DataBase {
    //private int i = 0;
    private AtomicInteger i = new AtomicInteger(0);
    private CopyOnWriteArrayList lines = new CopyOnWriteArrayList();

    public DataBase(){
    }
    /**
     * Constructor to use with Fork
     * @param subLines
     */
    DataBase (List subLines) {
        this.lines = new CopyOnWriteArrayList();
        this.lines.addAll(subLines);
    }
    DataBase subList(int begin, int end){
        DataBase db = new DataBase(lines.subList(begin, end));
        return db;
    }
    public void add(String[] line){
        lines.add(line);
    }
    public boolean isFinished(){
        return i.get()+1 == lines.size();
    }
    public String[] getLine(){
        return (String[]) lines.get(i.getAndIncrement());
    }

    /**
     * Polimorphic method to use with Fork and Join
     * @return
     */
    public String[] getLine(int index){
        return (String[]) lines.get(index);
    }
    public int size(){
        return lines.size();
    }
    public void reset(){
        i.set(0);
    }
}
