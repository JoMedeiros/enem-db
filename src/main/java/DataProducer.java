import java.io.BufferedReader;
import java.io.FileReader;

public class DataProducer extends Thread {
    private FileReader fileReader;
    private DataBuffer dataBuffer;
    public DataProducer(FileReader fileReader, DataBuffer dataBuffer){
        this.fileReader = fileReader;
        this.dataBuffer = dataBuffer;
    }
    @Override
    public void run() {

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            System.out.println("Loading file...");
            String line;
            // Read column names
            line = bufferedReader.readLine();
            String[] cells = line.split(";");
            for (int i = 0; i < cells.length; i++) {
                //System.out.println("Coluna " + i);
                //System.out.println(cells[i]);
            }
            // Read values
            int numLines = 1_000;
            //line = bufferedReader.readLine();
            //while( line != null ) {
            for (int i = 0; i < numLines; i++) {
                dataBuffer.putLine(line);
                line = bufferedReader.readLine();
            }
            System.out.println("Finished loading");
            dataBuffer.setFinished();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
