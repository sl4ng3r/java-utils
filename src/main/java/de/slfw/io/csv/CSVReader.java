package de.slfw.io.csv;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVReader {


    private  File csvFile;
    private  Map<String, Integer> headers = new HashMap<>();
    private String seperator;

    private CSVReader(File csvFile) {
        this(csvFile, ";");
    }

    private CSVReader(File csvFile, String seperator) {
        this.csvFile = csvFile;
        this.seperator = seperator;
        fillHeaders();
    }
    private void fillHeaders() {
        try {
            final CSVLine headerLine = readLine(0);
            this.headers =
                    IntStream.range(0,headerLine.values.size())
                            .boxed()
                            .collect(Collectors.toMap ( i -> headerLine.values.get(i), i -> i));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CSVReader build(File csvFile){
        return new CSVReader(csvFile, ";");
    }

    public static CSVReader build(File csvFile, String seperator){
        return new CSVReader(csvFile, seperator);
    }

    public  CSVLine readLine(Integer index) throws IOException {
        long counter = 0;
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line;
        while((line = reader.readLine())!= null){
            if(counter == index){
                CSVLine csvLine = new CSVLine(line, this.seperator, headers);
                return csvLine;
            }
            counter++;

        }
        reader.close();

        return null;


        //return readLine(this.csvFile, index, this.seperator);
    }


//    public  CSVLine readLine(Integer index, String separator) throws IOException {
//        return readLine(this.csvFile, index, separator);
//    }
//    public static CSVLine readLine(File csvFile, Integer index) throws IOException {
//        return readLine(csvFile, index, ";");
//
//
//    }
//
//    public static CSVLine readLine(File csvFile, Integer index, String separator) throws IOException {
//        long counter = 0;
//        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
//        String line;
//        while((line = reader.readLine())!= null){
//            if(counter == index){
//                CSVLine csvLine = new CSVLine(line, separator);
//                return csvLine;
//            }
//            counter++;
//
//        }
//        reader.close();
//
//       return null;
//    }



}
