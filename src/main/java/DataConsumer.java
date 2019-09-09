public class DataConsumer implements Runnable {
    DataBuffer db;
    Result result;
    DataConsumer(DataBuffer dataBuffer, Result res){
        this.db = dataBuffer;
        result = res;
    }

    @Override
    public void run() {
        // @TODO Process String
        int c = 5;
        //while (!this.db.isEmpty()){
        while (!this.db.isFinished() || !this.db.isEmpty()){
            try {
                String l = db.getLine();
                String[] values;
                if (l != null) {
                    values = l.split(";");
                    // Respostas
                    String TX_RESPOSTAS_CN = values[94].replaceAll("[^A-E|a-e]", "");
                    //String TX_RESPOSTAS_CH = values[95].replaceAll("[^A-E|a-e]", "");
                    String TX_RESPOSTAS_LC = values[96].replaceAll("[^A-E|a-e]", "");
                    //String TX_RESPOSTAS_MT = values[97].replaceAll("[^A-E|a-e]", "");
                    //String TP_LINGUA = values[98];
                    // Gabaritos:
                    String TX_GABARITO_CN = values[99];
                    //String TX_GABARITO_CH = values[100];
                    String TX_GABARITO_LC = values[101];
                    //String TX_GABARITO_MT = values[101];
                    // Resultado
                    byte[] resultadoCN = new byte[45];
                    if (TX_RESPOSTAS_CN.length() == 45) {
                        for (int i = 0; i < 45; i++) {
                            if (TX_GABARITO_CN.charAt(i) == TX_RESPOSTAS_CN.charAt(i)) {
                                resultadoCN[i] = 1;
                            } else {
                                resultadoCN[i] = 0;
                            }
                        }
                        this.result.putResultCN(resultadoCN);
                    }
                    byte[] resultadoLC = new byte[45];
                    if (TX_RESPOSTAS_LC.length() == 45) {
                        for (int i = 0; i < 45; i++) {
                            if (TX_GABARITO_LC.charAt(i) == TX_RESPOSTAS_LC.charAt(i)) {
                                resultadoLC[i] = 1;
                            } else {
                                resultadoLC[i] = 0;
                            }
                        }
                        this.result.putResultLC(resultadoLC);
                    }
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
}