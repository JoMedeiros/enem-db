import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class Result {
    private Hashtable<String, Integer> totais = new Hashtable<>();
    private String [] provas = {"CN","CH","LC","MT"};
    private String [] provasExtenso = {"Ciências da Natureza","Ciências Humanas","Linguagens e Códigos","Matemática"};
    private Hashtable<String, int[]> acertos = new Hashtable<>();
    private boolean finished = false;
    private final Semaphore available = new Semaphore(1,true);

    public Result(){
        for (String prova : provas) {
            acertos.put(prova, new int[45]);
            totais.put(prova, 0);
        }
    }
    /**
     * @brief Incrementa o contador de acertos na prova.
     * @param respostas Um vetor que representa os acertos de um aluno. Cada valor representa acerto com 1 e erro com 0.
     * @param prova O assunto da prova (Ciências da Natureza, Ciências Humanas, etc).
     * @throws InterruptedException
     */
    public void putResult(byte[] respostas, String prova) throws InterruptedException
    {
        available.acquire();
        int [] it = acertos.get(prova);
        for (int i = 0; i < respostas.length; i++) {
            it[i] += respostas[i];
        }
        totais.put(prova, totais.get(prova) + 1);
        available.release();
    }
    public void setFinished(){
        finished = true;
    }
    public void print(){
        int j = 0;
        for (String pv : provas) {
            System.out.println("Acertos em " + provasExtenso[j++]);
            int[] acertosPV = acertos.get(pv);
            for (int i = 0; i < acertosPV.length; i += 5) {
                float p1 = (float) acertosPV[i] / (float) totais.get(pv);
                float p2 = (float) acertosPV[i + 1] / (float) totais.get(pv);
                float p3 = (float) acertosPV[i + 2] / (float) totais.get(pv);
                float p4 = (float) acertosPV[i + 3] / (float) totais.get(pv);
                float p5 = (float) acertosPV[i + 4] / (float) totais.get(pv);
                System.out.print("Q" + (i + 1) + ": " + 100 * p1 + "%,\t");
                System.out.print("Q" + (i + 2) + ": " + 100 * p2 + "%,\t");
                System.out.print("Q" + (i + 3) + ": " + 100 * p3 + "%,\t");
                System.out.print("Q" + (i + 4) + ": " + 100 * p4 + "%,\t");
                System.out.print("Q" + (i + 5) + ": " + 100 * p5 + "%,\n");
            }
        }
    }
}
