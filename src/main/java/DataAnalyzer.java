public class DataAnalyzer implements Runnable {
    DataBuffer db;
    Result result;
    DataAnalyzer(DataBuffer dataBuffer, Result res){
        this.db = dataBuffer;
        result = res;
    }

    @Override
    public void run() {
        // @TODO Process String
        int c = 5;
        while (!this.db.isFinished() || !this.db.isEmpty()){
            try {
                String l = db.getLine();
                String[] values;
                if (l != null) {
                    values = l.split(";");
                    // Respostas
                    String TX_RESPOSTAS_CN = values[94].replaceAll("[^A-E|a-e]", "");
                    String TX_RESPOSTAS_CH = values[95].replaceAll("[^A-E|a-e]", "");
                    String TX_RESPOSTAS_LC = values[96].replaceAll("[^A-E|a-e]", "");
                    String TX_RESPOSTAS_MT = values[97].replaceAll("[^A-E|a-e]", "");
                    //String TP_LINGUA = values[98];
                    // Gabaritos:
                    String TX_GABARITO_CN = values[99];
                    String TX_GABARITO_CH = values[100];
                    String TX_GABARITO_LC = values[101];
                    String TX_GABARITO_MT = values[102];
                    // Resultado
                    gerarResultado(TX_RESPOSTAS_CN, TX_GABARITO_CN, "CN");
                    gerarResultado(TX_RESPOSTAS_CH, TX_GABARITO_CH, "CH");
                    gerarResultado(TX_RESPOSTAS_LC, TX_GABARITO_LC, "LC");
                    gerarResultado(TX_RESPOSTAS_MT, TX_GABARITO_MT, "MT");
                }
                // After setting the string to null
                l = null;
                values = null;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void gerarResultado(String respostas, String gabarito, String prova) throws InterruptedException {
        byte[] resultado = new byte[45];
        int numQ = prova.equals("LC") ? 50 : 45;
        if (prova.equals("LC")){
            System.out.println("PEGA\n FOGO\n CABARÉ");
        }
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