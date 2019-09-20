package ucr.ac.ecci.ci1340.InputSimulator.FileManagement;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

/**
 * Redundant class
 */
public class CSVManager {

    private CSVReader csvReader;

    public CSVManager(String path) throws IOException {
        csvReader = new CSVReader(new FileReader("src/main/resources/"+path));
        csvReader.readNext();
    }

    public String[] readNext() throws IOException {
        String[] row;
        if((row = csvReader.readNext()) != null){
            return row;
        }else{
            csvReader.close();
            return null;
        }
    }
}
