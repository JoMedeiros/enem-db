import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveAction;

public class DataAnalyzer extends RecursiveAction {
    private static final int SEQUENTIAL_THRESHOLD = 5000;
    DataBase data;
    Result result;
    public DataAnalyzer(DataBase dataBase, Result result){
        this.data = dataBase;
        this.result = result;
    }

    protected void compute() {
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
            computeResult();
        }
        else { // recursive case
            // Calcuate new range
            int mid = data.size() / 2;
            DataAnalyzer firstSubtask =
                    new DataAnalyzer(data.subList(0, mid), new Result());
            DataAnalyzer secondSubtask = new DataAnalyzer(data.subList(mid, data.size()), new Result());
            firstSubtask.fork(); // queue the first task
            secondSubtask.compute(); // compute the second task
            firstSubtask.join(); // wait for the first task result
            secondSubtask.result.sum(firstSubtask.result);
            this.result.sum(secondSubtask.result);
            // invokeAll(firstSubtask, secondSubtask);
        }
    }
    public void computeResult() {
        while (!data.isFinished()) {
            String [] line = data.getLine();
            // Respostas
            String TX_RESPOSTAS_CN = line[94];
            String TX_RESPOSTAS_CH = line[95];
            String TX_RESPOSTAS_LC = line[96];
            String TX_RESPOSTAS_MT = line[97];
            //String TP_LINGUA = line[98];
            // Gabaritos:
            String TX_GABARITO_CN = line[99];
            String TX_GABARITO_CH = line[100];
            String TX_GABARITO_LC = line[101];
            String TX_GABARITO_MT = line[102];
            // Resultado
            try {
                gerarResultado(TX_RESPOSTAS_CN, TX_GABARITO_CN, "CN");
                gerarResultado(TX_RESPOSTAS_CH, TX_GABARITO_CH, "CH");
                gerarResultado(TX_RESPOSTAS_LC, TX_GABARITO_LC, "LC");
                gerarResultado(TX_RESPOSTAS_MT, TX_GABARITO_MT, "MT");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void gerarResultado(String respostas, String gabarito, String prova) throws InterruptedException {
        byte[] resultado = new byte[45];
        int numQ = 45;
        // TODO: resolver os casos em que o gabarito está faltando
        if (respostas.length() == numQ && gabarito.length() == numQ) {
            for (int i = 0; i < numQ; i++) {
                if (gabarito.charAt(i) == respostas.charAt(i)) {
                    resultado[i] = 1;
                } else {
                    resultado[i] = 0;
                }
            }
            this.result.putResult(resultado, prova);
        }
    }
}
