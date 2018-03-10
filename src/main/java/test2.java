import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test2 {

    /** Method extracts date and time for data point, which is stored in the name of the file rater than the content of file
     *
     * @param filename Requires filename to be in specificed format
     * @return String of the date and time in format ready for sql
     */

    private static String getDateInRedshiftFormat(String filename){

        final Integer year = Integer.parseInt(filename.substring(4,8));
        final Integer month = Integer.parseInt(filename.substring(10,11));
        final Integer day = Integer.parseInt(filename.substring(13,14));
        final Integer hour = Integer.parseInt(filename.substring(15,17));
        final Integer minute = Integer.parseInt(filename.substring(17,19));

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month-1, day, hour, minute);
        final Date date = cal.getTime();


        final SimpleDateFormat ft =
                new SimpleDateFormat ("YYYY-MM-dd HH:MM:SS");
        final String dateString = ft.format(date).toString();

        return dateString;
    }

    /**
     *
     * @param fileName - name of file you want to extract data from. Must be in a format that is readable by FileReader.class
     * @return Contents of file as a String
     * @throws IOException
     */

    private static String readFile(String fileName) throws IOException {

        final FileReader file = new FileReader(fileName);
        final BufferedReader reader = new BufferedReader(file);

        StringBuilder fileContent = new StringBuilder();
        String line = reader.readLine();

        while (line != null) {
            fileContent.append(line);
            line = reader.readLine();
        }

        return fileContent.toString();
    }

    public test2()  {

        String test = "test";
    }



/*
        System.out.println(key); // so key works

        String title2 = "LTC_2018_03_04_121102";
        String title = "Sat Feb 03 17:39:55 CET 2018";


        String dateString = getDateInRedshiftFormat(title2);

        ObjectMapper test = new ObjectMapper();
        CoinbasePrice test2 = test.readValue(key, CoinbasePrice.class);

        Map<String, String> jsonData = test2.getData();
        jsonData.put("Datetime", dateString);
        StringBuilder sb = new StringBuilder();

        for (String s : jsonData.keySet()) {
            if(sb.length()!= 0) {
                sb.append(",");
            }
            System.out.println(jsonData.get(s));
            sb.append(jsonData.get(s));

        }

        System.out.println(sb.toString());

*/
  //  }
}

