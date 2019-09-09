import java.util.concurrent.Semaphore;

public class Result {
    int total = 0;
    int [] acertosCN = new int[45];
    int [] acertosLC = new int[45];
    boolean finished = false;
    private final Semaphore available = new Semaphore(1,true);
    public int[] getResult()
    {
        if (finished) {
            return acertosLC;
        }
        return null;
    }

    /**
     * @brief Incrementa o contador de acertos na prova de Ciências Naturais
     * @param respostas
     * @throws InterruptedException
     */
    public void putResultCN(byte[] respostas) throws InterruptedException
    {
        available.acquire();
        for (int i = 0; i < 45; i++) {
            acertosCN[i] += respostas[i];
        }
        total++;
        available.release();
    }
    public void putResultLC(byte[] respostas) throws InterruptedException
    {
        available.acquire();
        for (int i = 0; i < 45; i++) {
            acertosLC[i] += respostas[i];
        }
        total++;
        available.release();
    }
    public void setFinished(){
        finished = true;
    }
    public void print(){
        System.out.println("Acertos em Ciências Naturais");
        for (int i = 0; i < 45; i+=5) {
            float p1 = (float) acertosCN[i] / (float) total;
            float p2 = (float) acertosCN[i+1] / (float) total;
            float p3 = (float) acertosCN[i+2] / (float) total;
            float p4 = (float) acertosCN[i+3] / (float) total;
            float p5 = (float) acertosCN[i+4] / (float) total;
            System.out.print("Q"+(i+1)+": "+100*p1+"%,\t");
            System.out.print("Q"+(i+2)+": "+100*p2+"%,\t");
            System.out.print("Q"+(i+3)+": "+100*p3+"%,\t");
            System.out.print("Q"+(i+4)+": "+100*p4+"%,\t");
            System.out.print("Q"+(i+5)+": "+100*p5+"%,\n");
        }
        System.out.println("Acertos em Linguagens e Códigos");
        for (int i = 0; i < 45; i+=5) {
            float p1 = (float) acertosLC[i] / (float) total;
            float p2 = (float) acertosLC[i+1] / (float) total;
            float p3 = (float) acertosLC[i+2] / (float) total;
            float p4 = (float) acertosLC[i+3] / (float) total;
            float p5 = (float) acertosLC[i+4] / (float) total;
            System.out.print("Q"+(i+1)+": "+100*p1+"%,\t ");
            System.out.print("Q"+(i+2)+": "+100*p2+"%,\t");
            System.out.print("Q"+(i+3)+": "+100*p3+"%,\t");
            System.out.print("Q"+(i+4)+": "+100*p4+"%,\t");
            System.out.print("Q"+(i+5)+": "+100*p5+"%,\n");
        }
    }
}
