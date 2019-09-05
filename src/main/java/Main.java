import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        long startTime = System.currentTimeMillis();
        int numberOfThreads = 10;
        DataBuffer df = new DataBuffer();
        String fileName = "MICRODADOS_ENEM_2018.csv";
        FileReader fileReader = new FileReader(fileName);
        Result resultado = new Result();

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            // Read column names
            line = bufferedReader.readLine();
            String[] cells = line.split(";");
            for (int i = 0; i < cells.length; i++) {
                //System.out.println("Coluna " + i);
                //System.out.println(cells[i]);
            }
            // Read values
            int numLines = 2000;
            for (int i = 0; i < numLines; i++) {
                line = bufferedReader.readLine();
                df.putLine(line);
            }
            //while((line = bufferedReader.readLine()) != null) {
            //    line = bufferedReader.readLine();
            //    df.putLine(line);
            //}
            df.setFinished();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Thread [] tds = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            tds[i] = (new Thread (new DataConsumer(df, resultado)));
            tds[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            try {
                tds[i].join();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        resultado.print();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time in milliseconds: " + elapsedTime);
    }
}
