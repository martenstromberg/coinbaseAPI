package Process;

import dto.CoinbasePriceEventHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CreateDatasetTest {


    @Test
    public void createFile() throws IOException {


        ReadInUnprocessedFiles readInUnprocessedFiles = new ReadInUnprocessedFiles("src/Test/fixtures/filesToBeZipped/");

        List<String> listOfUnProcessedFiles = readInUnprocessedFiles.populateFileDirectory();

        readInUnprocessedFiles.readAllUnprocessedFiles(listOfUnProcessedFiles);

        Map<String, String> map = readInUnprocessedFiles.getFilesAndContent();

        CreateDataset createDataset = new CreateDataset("src/Test/fixtures/filesToBeZipped/", map, "test10") ;

        createDataset.generateDataset();





    }
}
