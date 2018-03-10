package dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.Test;



public class CoinbasePriceEventHandlerTest {

    @Test
    public  void itShouldConvertFilenameToDate() throws Exception{

        String filename = "LTC_2018_03_04_121102";

        CoinbasePriceEventHandler cp = new CoinbasePriceEventHandler();

        String dateFormat = cp.getDateInRedshiftFormat(filename);
        assertThat(dateFormat).isEqualTo("2018-03-04 12:11");
    }

    @Test
    public void itShouldOnlyTakeFilesInRightFormat() throws Exception {

        CoinbasePriceEventHandler cp = new CoinbasePriceEventHandler();

        String filename = "src/main/Messages/BTC_2018_03_04_121102.json";

        String fileContent = cp.readFile(filename);

        System.out.println(fileContent);
        assertThat(fileContent).isEqualTo("{\"data\":{\"base\":\"BTC\",\"currency\":\"USD\",\"amount\":\"9200.18\"}}");

    }

    @Test
    public void itShouldMapEvents() throws Exception {

        CoinbasePriceEventHandler cp = new CoinbasePriceEventHandler();

        String filename = "BTC_2018_03_04_121102.json";



        String fileContent = cp.readFile(filename);

        System.out.println(fileContent);


    }


    @Test
    public void itShouldOnlyHandleWrongFormatOfValuesInFile() throws Exception {

        CoinbasePriceEventHandler cp = new CoinbasePriceEventHandler();


        String filename = "BTC_2018_03_04_121102.json";
        String output = cp.handle(filename);

        System.out.println(output);

        assertThat(output).isEqualTo("BTC,USD,9200.18,2018-03-04 12:11");

    }

    @Test
    public void itShouldOnlyHandleWrongFormatOfValuesInFile2() throws Exception {

        CoinbasePriceEventHandler cp = new CoinbasePriceEventHandler();


        cp.addRow("test");

    }

}
