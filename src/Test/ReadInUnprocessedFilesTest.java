import Process.ReadInUnprocessedFiles;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ReadInUnprocessedFilesTest {

    @Test
    public void itShouldReadInRightAmountOfFiles() {
        ReadInUnprocessedFiles readInUnprocessedFiles = new ReadInUnprocessedFiles("src/Test/fixtures/filesToBeZipped/");

        List<String> listOfUnProcessedFiles = readInUnprocessedFiles.populateFileDirectory();

        assertThat(listOfUnProcessedFiles.size()).isEqualTo(6);
    }

    @Test
    public void itShouldGetRightContent() throws IOException {

        ReadInUnprocessedFiles readInUnprocessedFiles = new ReadInUnprocessedFiles("src/Test/fixtures/filesToBeZipped/");

        readInUnprocessedFiles.readInUnprocessedFiles();
        Map<String, String> map = readInUnprocessedFiles.getFilesAndContent();
        System.out.println("nr of files in map:" + map.size());

        assertThat(map.get("BTC_2018_03_10_102401.json")).isEqualTo("{\"data\":{\"base\":\"BTC\",\"currency\":\"USD\",\"amount\":\"9263.81\"}}");

    }
}
