package dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CoinbasePrice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CoinbasePriceEventHandler {

    final String directory;

    public CoinbasePriceEventHandler(String directory){
        this.directory = directory;
    }

    /** Method extracts date and time for data point, which is stored in the name of the file rater than the content of file
     *
     * @param filename Requires filename to be in specificed format
     * @return String of the date and time in format ready for sql
     */

    protected String getDateInRedshiftFormat(String filename){

        final Calendar cal = Calendar.getInstance();

        if(filename.length() > 25) {

            final Integer year = Integer.parseInt(filename.substring(4, 8));
            final Integer month = Integer.parseInt(filename.substring(10, 11));
            final Integer day = Integer.parseInt(filename.substring(13, 14));
            final Integer hour = Integer.parseInt(filename.substring(15, 17));
            final Integer minute = Integer.parseInt(filename.substring(17, 19));

            cal.set(year, month-1, day, hour, 11);
        } else {

            final Integer year = Integer.parseInt(filename.substring(0, 4));
            final Integer month = Integer.parseInt(filename.substring(6, 7));
            final Integer day = Integer.parseInt(filename.substring(9, 10));
            final Integer hour = Integer.parseInt(filename.substring(11, 13));
            final Integer minute = Integer.parseInt(filename.substring(13, 15));

            cal.set(year, month-1, day, hour, 11);
        }

        final Date date = cal.getTime();

        final SimpleDateFormat ft =
                new SimpleDateFormat ("YYYY-MM-dd HH:mm");
        final String dateString = ft.format(date).toString();

        return dateString;
    }

    /**
     *
     * @param fileName - name of file you want to extract data from. Must be in a format that is readable by FileReader.class
     * @return Contents of file as a String
     * @throws IOException
     */

    protected String readFile(String fileName) throws IOException {

        final FileReader file = new FileReader(directory + fileName);
        final BufferedReader reader = new BufferedReader(file);

        StringBuilder fileContent = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            fileContent.append(line);
            line = reader.readLine();
        }

        return fileContent.toString();
    }

    public String handle(String fileName) throws IOException {

        String dateString = getDateInRedshiftFormat(fileName);

        String fileContent = readFile(fileName);

        ObjectMapper objectMapper = new ObjectMapper();

        CoinbasePrice coinbasePrice = objectMapper.readValue(fileContent, CoinbasePrice.class);

        Map<String, String> mappedEvent = coinbasePrice.getData();

        addDateTimeToEvent(mappedEvent, dateString);

        //String cleanedContent = removeWarningsFromApiInFileContent(mappedEvent.get(fileName));

        //mappedEvent.remove(fileName);

        //mappedEvent.put(fileName, cleanedContent);

        String eventRow = createRow(mappedEvent);

        System.out.println("event row is " + eventRow);

        return eventRow;
    }

    public String handleFromS3(String fileName, String fileContentRaw) throws IOException {


        String dateString = getDateInRedshiftFormat(fileName);

        //String fileContent = readFile(fileName);
        String fileContent = removeWarningsFromApiInFileContent(fileContentRaw);

        ObjectMapper objectMapper = new ObjectMapper();



        CoinbasePrice coinbasePrice = objectMapper.readValue(fileContent, CoinbasePrice.class);

        Map<String, String> mappedEvent = coinbasePrice.getData();

        addDateTimeToEvent(mappedEvent, dateString);

        String eventRow = createRow(mappedEvent);
        System.out.println("event row is " + eventRow);

        return eventRow;
    }

    protected void addDateTimeToEvent(Map<String, String> map, String dateString) {

        map.put("Datetime", dateString);
    }


    protected String createRow(Map<String, String> mapEvent){

        StringBuilder sb = new StringBuilder();

        for (String s : mapEvent.keySet()) {
            if(sb.length()!= 0) {
                sb.append(",");
            }
            System.out.println(mapEvent.get(s));
            sb.append(mapEvent.get(s));
        }

        return sb.toString();
    }

    protected String removeWarningsFromApiInFileContent(String fileContent){

        int indexOfWarning = fileContent.indexOf("\"warnings\"");
        if(indexOfWarning!= -1) {
            System.out.println("Warning present");

            String cleanedFileContent = fileContent.substring(0, indexOfWarning-1) + "}";
            return  cleanedFileContent;
        }

        System.out.println("No warning present - returns unchanged fileContant");

        return fileContent;

    }



}

