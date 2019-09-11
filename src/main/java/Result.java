import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Result {
    //private Hashtable<String, Integer> totais = new Hashtable<>(); // Número de provas analisadas
    private  Hashtable<String, AtomicInteger> totais = new Hashtable<>(); // Número de provas analisadas
    private Hashtable<String, AtomicInteger[]> acertos = new Hashtable<>(); // Totais de acerto por questão
    private String [] provas = {"CN","CH","LC","MT"};
    private String [] provasExtenso = {"Ciências da Natureza","Ciências Humanas","Linguagens e Códigos","Matemática"};
    private boolean finished = false;
    private final Semaphore available = new Semaphore(1,true);

    public Result(){
        initialize();
    }
    public void initialize(){
        for (String prova : provas) {
            acertos.put(prova, new AtomicInteger[45]);
            for (int i = 0; i < 45; i++) {
                acertos.get(prova)[i] = new AtomicInteger(0);
            }
            totais.put(prova, new AtomicInteger(0));
        }
    }
    /**
     * @brief Incrementa o contador de acertos na prova.
     * @param respostas Um vetor que representa os acertos de um aluno. Cada valor representa acerto com 1 e erro com 0.
     * @param prova O assunto da prova (Ciências da Natureza, Ciências Humanas, etc).
     * @throws InterruptedException
     * @comments Essa Função é crítica uma vez que pode gerar problemas de visibilidade.
     */
    public void putResult(byte[] respostas, String prova) throws InterruptedException
    {
        //available.acquire();
        AtomicInteger [] it = acertos.get(prova);
        for (int i = 0; i < respostas.length; i++) {
            it[i].addAndGet(respostas[i]);
        }
        //totais.put(prova, totais.get(prova).incrementAndGet());
        totais.get(prova).incrementAndGet();
        //available.release();
    }
    public void setFinished(){
        finished = true;
    }
    public void print(){
        int j = 0;
        for (String pv : provas) {
            System.out.println("Acertos em " + provasExtenso[j++]);
            AtomicInteger[] acertosPV = acertos.get(pv);
            for (int i = 0; i < acertosPV.length; i++) {
                for (int k = 0;k < 4 && i < acertosPV.length ;k++) {
                    String p =  String.format("%.2f", 100 * ((float) acertosPV[i++].get() / (float) totais.get(pv).get()));
                    System.out.print("Q" + String.format("%02d",i) + ": " + p + "%,\t");
                }
                String p =  String.format("%.2f", 100 * ((float) acertosPV[i].get() / (float) totais.get(pv).get()));
                System.out.print("Q" + String.format("%02d",(i + 1)) + ": " + p + "%,\n");
            }
        }
    }
    public void reset() {
        totais = new Hashtable<>();
        acertos = new Hashtable<>();
        initialize();
    }
}
