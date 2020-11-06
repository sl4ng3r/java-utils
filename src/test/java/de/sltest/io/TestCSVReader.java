package de.sltest.io;

import de.slfw.io.CSVLine;
import de.slfw.io.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class TestCSVReader {


    private CSVReader csvReader;

    @BeforeEach
    void init(){
        csvReader = new CSVReader(new File(Thread.currentThread().getContextClassLoader().getResource("testfolder/test.csv").getFile()));
    }

    @Test
    public void testReadSingeLine() throws IOException {
        final CSVLine csvLine = csvReader.readLine(1);

        assertEquals("", csvLine.getValue(0), "emptyValue");
        assertEquals("ccc", csvLine.getValue(2), "midValue");
        assertEquals("asdasdf", csvLine.getValue(3), "lastValue");


        try {
            csvLine.getValue(4);
            fail("Should throw Exception");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    @Test
    public void testReadInvalidLine() throws IOException {
        final CSVLine csvLine = csvReader.readLine(11, ",");

        assertEquals(null, csvLine, "emptyValue");

    }

}
