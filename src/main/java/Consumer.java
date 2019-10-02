import java.util.concurrent.Flow;

public class Consumer implements Flow.Subscriber<String> {
    private Flow.Subscription subscription;
    private Result result;

    public Consumer(Result result){
        this.result = result;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String item) {
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

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
