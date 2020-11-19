package de.slfw.io.csv;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CSVLine {

    List<String> values = new CopyOnWriteArrayList<>();
    private Map<String, Integer> headers = new HashMap<>();


    public CSVLine(String line, String separator) {
        initCsvLine(line, separator);
    }

    public CSVLine(String line, String separator,Map<String, Integer> headers ) {
        initCsvLine(line, separator);
        this.headers = headers;
    }

    private void initCsvLine(String line, String separator) {
        while (line.indexOf(separator) != -1){
            String value = line.substring(0,line.indexOf(separator));
            line = line.substring(line.indexOf(separator)+1);
            values.add(value);
        }
        values.add(line);
    }

    public String getValue(Integer index){
        return values.get(index);
    }

    public String getValue(String headerName) {
        if (headers.containsKey(headerName)){
            return values.get(headers.get(headerName));
        }
        throw new StringIndexOutOfBoundsException("headername " + headerName + " not found");

    }
}
