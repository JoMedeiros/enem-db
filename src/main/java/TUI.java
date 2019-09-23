import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * @brief Terminal User Interface.
 */
public class TUI implements Runnable {
    private static DataBase dataBase;
    private static Result result;
    private final Scanner sc = new Scanner(System.in);
    private static final TUI INSTANCE = new TUI();
    private Filter filter = new Filter();
    //private static Runnable[] dataAnalyzers;
    private static final ExecutorService exec = Executors.newFixedThreadPool(100);

    private TUI(){}

    public static TUI getINSTANCE() {
        return INSTANCE;
    }

    public static void setDataBase(DataBase dataBase) {
        TUI.dataBase = dataBase;
    }

    public static void setResult(Result result) {
        TUI.result = result;
    }

    //public static void setAnalyzers(Runnable[] analyzers) {
    //    //dataAnalyzers = analyzers;
    //}

    @Override
    public void run() {
        try {
            handleInput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleInput() throws InterruptedException {
        int option = 0;
        while (option != 4) {
            System.out.println("Aplicando filtros...");
            System.out.println("1 - Selecione os filtros");
            System.out.println("2 - Remover fitros");
            System.out.println("3 - Aplicar Consulta");
            System.out.println("4 - Sair");
            option = this.sc.nextInt();
            if (option == 1) {
                addFilter();
            }
            else if (option == 3) {
                runAnalyzers();
            }
            else if (option == 4){
                exec.shutdown();
                System.out.println("Fechando programa...");
            }
            else {
                System.out.println("Opção inválida");
            }
        }
    }

    private void runAnalyzers() throws InterruptedException {
        System.out.println("Gerando resultados...");
        long startTime = System.currentTimeMillis();
        //for (int i = 0; i < 2; i++) {
        //    exec.submit(new DataAnalyzer(dataBase, result));
        //}
        ForkJoinPool pool = ForkJoinPool.commonPool();
        DataAnalyzer task = new DataAnalyzer(dataBase, result);
        pool.invoke(task);
        result.print();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Tempo para analisar os dados em milisegundos: " + elapsedTime);
        dataBase.reset();
        result.reset();
    }

    private void addFilter(){
        boolean out = false;
        while (!out) {
            System.out.println("1 - Estado");
            int option = this.sc.nextInt();
            if (option == 1) {

            }
        }
    }
}