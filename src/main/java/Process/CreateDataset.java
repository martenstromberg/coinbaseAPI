package Process;

import dto.CoinbasePriceEventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateDataset {

    CoinbasePriceEventHandler coinbasePriceEventHandler;
    Map<String, String> unprocessedFiles;
    String outputFile;

    public CreateDataset(
        String directory,
        Map<String, String> unprocessedFiles,
        String outputFile
    ) {
        this.coinbasePriceEventHandler = new CoinbasePriceEventHandler(directory);
        this.unprocessedFiles = unprocessedFiles;
        this.outputFile = outputFile;
    }

    /**
     * @return CSV file with one row per unprocessed file
     * @throws IOException
     */

    public  File generateDataset() throws IOException {
        File file = new java.io.File(outputFile);
        FileWriter fr = null;
        fr = new FileWriter(file, true);

        for (String unprocessedfile : unprocessedFiles.keySet()) {
            try {

                String row = coinbasePriceEventHandler.handle(unprocessedfile);
                fr.write(row);
                fr.write("\n");
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try {
            fr.flush();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
        }

}


