package de.slfw.io;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CSVLine {

    List<String> values = new CopyOnWriteArrayList<>();

    public CSVLine(String line, String separator) {
        initCsvLine(line, separator);
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
}
