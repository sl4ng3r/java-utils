package de.slfw.io;

import de.slfw.util.StringUtil;

import java.io.*;
import java.util.List;

public class CSVReader {


    private  File csvFile;

    public CSVReader(File csvFile) {
        this.csvFile = csvFile;
    }


    public  CSVLine readLine(Integer index) throws IOException {
        return readLine(this.csvFile, index, ";");
    }

    public  CSVLine readLine(Integer index, String separator) throws IOException {
        return readLine(this.csvFile, index, separator);
    }
    public static CSVLine readLine(File csvFile, Integer index) throws IOException {
        return readLine(csvFile, index, ";");


    }

    public static CSVLine readLine(File csvFile, Integer index, String separator) throws IOException {
        long counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line;
        while((line = reader.readLine())!= null){
            if(counter == index){
                CSVLine csvLine = new CSVLine(line, separator);
                return csvLine;
            }
            counter++;

        }
        reader.close();

       return null;
    }

}
