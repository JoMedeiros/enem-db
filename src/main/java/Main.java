import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException, InterruptedException {
        // Creating objects
        long startTime = System.currentTimeMillis();
        int numberOfThreads = 1;
        DataBuffer dataBuffer = new DataBuffer();
        FileReader fileReader = new FileReader( "MICRODADOS_ENEM_2018.csv");
        DataProducer dataProducer = new DataProducer(fileReader, dataBuffer);
        Result resultado = new Result();
        Thread [] consumers = new Thread[numberOfThreads];
        Thread tui = new Thread( TUI.getINSTANCE() );

        // Running Threads
        dataProducer.start();
        for (int i = 0; i < numberOfThreads; i++) {
            consumers[i] = (new Thread (new DataConsumer(dataBuffer, resultado)));
            consumers[i].start();
        }

        // Finishing Threands
        dataProducer.join();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time to load file in milliseconds: " + elapsedTime);

        for (int i = 0; i < numberOfThreads; i++) {
            consumers[i].join();
        }

        tui.start();
        tui.join();

        System.out.println("Resultado de Ciências Humanas");
        resultado.print();

    }
}
