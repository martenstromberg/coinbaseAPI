package Transform;

import Process.UnZip;
import java.io.IOException;

public class LoadFileAndUnzip {

    public static void main(String[] args) throws IOException {


        String zipFile = "filesToBeZipped.zip";
        String fileDirectory = "src/main/Messages/";
        String outputFolder = "src/main/Messages/unzippedFiles/";

        UnZip unZip = new UnZip();

        unZip.unZipIt(zipFile, fileDirectory, outputFolder);

        /*

        // get list of files in the unprocessed folder

        ListKeys listKeys = new ListKeys();

        List<String> files = listKeys.getFiles("crypto-unprocessed-data");

        System.out.println(files.size());

        // download the content of the unprocessed files

        GetObject getObject = new GetObject();

        Map<String, String> fileContents = getObject.getContentOfFiles("crypto-unprocessed-data", files.get(5));

        for (String s : fileContents.keySet()) {

            System.out.println("File: " + s + " contains: " + fileContents.get(s));

        }
        */
    }
}
