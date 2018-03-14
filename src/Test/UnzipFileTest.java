import Process.UnZip;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class UnzipFileTest {

    @Test
    public  void itShouldPrintGenerateSixFiles() throws IOException {

        UnZip unZip = new UnZip();

        String fileDirectory = "src/Test/fixtures/";

        String zipFile = "filesToBeZipped";

        String outputFolder = "src/main/Messages/outPutZip3/";

        unZip.unZipIt(fileDirectory, zipFile, outputFolder);


        File test = new File(outputFolder + zipFile);
        unZip.removeParentFolder(outputFolder, test);

        int nrOfUnzippedFiles = new File("src/main/Messages/outPutZip3/").list().length;

        assertThat(nrOfUnzippedFiles).isEqualTo(7);
    }


    //It should handle case where no files


    // It should handle case where no zip file

}
