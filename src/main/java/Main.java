import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws InterruptedException {
        try {
            // Creating objects
            long startTime = System.currentTimeMillis();
            int numberOfThreads = 4;
            DataBuffer dataBuffer = new DataBuffer();
            DataProducer dataProducer = new DataProducer("MICRODADOS_ENEM_2018.csv", dataBuffer);
            Result resultado = new Result();
            Thread[] consumers = new Thread[numberOfThreads];
            Thread tui = new Thread(TUI.getINSTANCE());

            // Running Threads
            dataProducer.start();
            long conumerStartTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfThreads; i++) {
                consumers[i] = (new Thread(new DataConsumer(dataBuffer, resultado)));
                consumers[i].start();
            }

            // Finishing Threands
            dataProducer.join();
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Time to load file in milliseconds: " + elapsedTime);

            tui.start();

            for (int i = 0; i < numberOfThreads; i++) {
                consumers[i].join();
            }
            long consumerStopTime = System.currentTimeMillis();
            long consumerElapsedTime = consumerStopTime - conumerStartTime;

            System.out.println("Tempo de consumo das Strings:" + consumerElapsedTime);
            tui.join();
            resultado.print();
        }
        catch (FileNotFoundException e) {
            System.out.println("O arquivo não foi encontrado. Saindo do programa...");
        }

    }
}
