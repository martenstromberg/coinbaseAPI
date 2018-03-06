import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class testObjectMapper {

    public static String getDateInRedshiftFormat(String filename){

        Integer year = Integer.parseInt(filename.substring(4,8));
        Integer month = Integer.parseInt(filename.substring(10,11));
        Integer day = Integer.parseInt(filename.substring(13,14));
        Integer hour = Integer.parseInt(filename.substring(15,17));
        Integer minute = Integer.parseInt(filename.substring(17,19));

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(year, month-1, day, hour, minute);
        Date date = cal.getTime();


        SimpleDateFormat ft =
                new SimpleDateFormat ("YYYY-MM-dd HH:MM:SS");
        String dateString = ft.format(date).toString();
        return dateString;

    }


    public static void main(String[] args) throws IOException {


        FileReader file = new FileReader("src/main/Messages/Sat Feb 03 17:39:55 CET 2018.json");
        BufferedReader reader = new BufferedReader(file);


        // **** key is declared here in this block of code
        String key = "";
        String line = reader.readLine();

        while (line != null) {
            key += line;
            line = reader.readLine();
        }
        System.out.println(key); // so key works

        String title2 = "LTC_2018_03_04_121102";
        String title = "Sat Feb 03 17:39:55 CET 2018";


        String dateString = getDateInRedshiftFormat(title2);

        ObjectMapper test = new ObjectMapper();
        BitCoinBase test2 = test.readValue(key, BitCoinBase.class);

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


    }
}

