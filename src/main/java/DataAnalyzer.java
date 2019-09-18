import java.util.concurrent.Callable;

public class DataAnalyzer implements Callable {
    DataBase data;
    Result result;
    public DataAnalyzer(DataBase dataBase, Result result){
        this.data = dataBase;
        this.result = result;
    }

    @Override
    public Object call() {
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
        return null;
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
