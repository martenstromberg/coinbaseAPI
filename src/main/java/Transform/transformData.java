package Transform;

import Load.GetObject;
import Load.ListKeys;
import dto.CoinbasePriceEventHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class transformData {

    /*
    protected void addRow(String filename2) {
    try
    {
        String filename= "src/main/Messages/data1.csv";
        FileWriter fw = new FileWriter(filename,true); //the true will append the new data
        fw.write("\n");//appends the string to the file
        fw.close();
    }
    catch(IOException ioe)
    {
        System.err.println("IOException: " + ioe.getMessage());
    }

    }
    */


    //public List<String> getUnprocessedFiles(String bucketName) throws IOException {

    public static void main(String[] args) throws IOException {

        // get list of files in the unprocessed folder

        ListKeys listKeys = new ListKeys();

        List<String> files = listKeys.getFiles("crypto-unprocessed-data");

        System.out.println(files.size());




    // download the content of the unprocessed files

        GetObject getObject = new GetObject();

        Map<String, String> fileContents = getObject.getContentOfFiles("crypto-unprocessed-data", files.get(5));

        for(String s : fileContents.keySet()) {

            System.out.println("File: " + s + " contains: " + fileContents.get(s));
        }



    //handle the unprocessed files and make them into CSV file

        CoinbasePriceEventHandler coinbasePriceEventHandler = new CoinbasePriceEventHandler("fixtures/filesToBeZipped/");

        String test = coinbasePriceEventHandler.handleFromS3("BTC_2018_03_10_102701.json", fileContents.get("BTC_2018_03_10_102701.json"));

        System.out.println("handled file: " + test);


    //post CSV file to database


    }
}