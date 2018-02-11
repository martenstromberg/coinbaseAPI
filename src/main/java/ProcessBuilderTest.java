import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import org.apache.commons.io.*;
import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ProcessBuilderTest {

    public static void main(String[] args) throws IOException {

        while(true) {


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));

            ProcessBuilder pb = new ProcessBuilder("curl", "-o", date + ".json", "https://api.coinbase.com/v2/prices/BTC-USD/spot");
            pb.directory(new File("/Users/martenstromberg/IdeaProjects/CoinBase/src/main/Messages"));
            pb.redirectErrorStream(true);

            Process p = pb.start();
            InputStream is = p.getInputStream();
            String test = FileUtils.readFileToString(new File("map.json"));
            System.out.println(test);    //is.read()
            //String theString = IOUtils.toString(is, "utf-8");
        }
    //System.out.println(theString);

    /*
        FileOutputStream outputStream = new FileOutputStream(
                "/Users/martenstromberg/IdeaProjects/CoinBase/src/main/Messages/message.json");

        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] bytes = new byte[10000];
        int numberByteReader;
        while ((numberByteReader = bis.read(bytes, 0, 100)) != -1) {

            outputStream.write(bytes, 0, numberByteReader);
            Arrays.fill(bytes, (byte) 0);

        }

        outputStream.flush();
        outputStream.close();
*/


    }
}
