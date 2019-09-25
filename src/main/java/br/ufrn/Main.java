package br.ufrn;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        DataBase dataBase = new DataBase();
        Thread loader = new Thread(new DataBaseLoader(dataBase));
        loader.start();
        loader.join();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time to load file in milliseconds: " + elapsedTime);

        //Runnable [] analyzers = new Runnable[2];
        TUI.setDataBase(dataBase);
        TUI.setResult( new Result() );
        //br.ufrn.TUI.setAnalyzers(analyzers);
        (new Thread(TUI.getINSTANCE())).start();
    }
}
