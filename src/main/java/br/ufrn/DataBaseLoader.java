package br.ufrn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataBaseLoader implements Runnable {
    DataBase data = new DataBase();

    public DataBaseLoader(DataBase dataBase) {
        this.data = dataBase;
    }

    public void run() {
        try {
            FileReader fileReader = new FileReader("MICRODADOS_ENEM_2018.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("Loading file...");
            String line;
            // Read column names
            line = bufferedReader.readLine();
            String[] cells = line.split(";");
            // Read values
            int numLines = 100000;
            line = bufferedReader.readLine();
            //while( line != null ) {
            for (int i = 0; i < numLines; i++) {
                cells = line.split(";");
                cleanData(cells);
                //data.add(new ArrayList<String>(Arrays.asList(cells)));
                data.add(cells);

                //dataBuffer.putLine(line);
                line = bufferedReader.readLine();
                if (i%10000 == 0){
                    System.out.print(i/10000 + 1 + " ");
                }
            }
            System.out.println();
            System.out.println("Finished loading");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cleanData(String[] cells) {
        // Verifica o idioma escolhido para a prova de Linguagens
        if (cells[101].length() != 0) {
            if (cells[101].charAt(0) == '9') {
                cells[96] = cells[96].substring(5);
                cells[101] = cells[101].substring(5);
            }
            else {
                cells[96] = cells[96].substring(0,5) + cells[96].substring(10);
                cells[101] = cells[101].substring(0,5) + cells[101].substring(10);
            }
        }
        // Respostas
        cells[94] = cells[94].replaceAll("[^A-E|a-e|*]", "");
        cells[95] = cells[95].replaceAll("[^A-E|a-e|*]", "");
        cells[96] = cells[96].replaceAll("[^A-E|a-e|*]", "");
        cells[97] = cells[97].replaceAll("[^A-E|a-e|*]", "");
    }
}
