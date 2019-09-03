import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String [] args) throws IOException {
        long startTime = System.currentTimeMillis();
        int numberOfThreads = 2;
        DataBuffer df = new DataBuffer();
        String fileName = "MICRODADOS_ENEM_2018_parte1.csv";
        FileReader fileReader = new FileReader(fileName);
        Result resultado = new Result();

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            //for (int i = 0; i < numLines; i++) {
            //    line = bufferedReader.readLine();
            //    df.putLine(line);
            //    //System.out.println(line);
            //}
            line = bufferedReader.readLine();
            String[] cells = line.split(",");
            //for (int i = 0; i < cells.length; i++) {
            //    //System.out.println("Coluna " + i);
            //    //System.out.println(cells[i]);
            //}
            while((line = bufferedReader.readLine()) != null) {
                line = bufferedReader.readLine();
                df.putLine(line);
            //    System.out.println(line);
            }
            df.setFinished();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            (new Thread (new DataConsumer(df, resultado))).run();
        }

        resultado.print();

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time in milliseconds: " + elapsedTime);
    }
}
