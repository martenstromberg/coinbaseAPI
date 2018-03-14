package Process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadInUnprocessedFiles {

    final String fileDirectoryOfUnprocessedFiles;
    final Map<String, String> filesAndContent;

    public ReadInUnprocessedFiles(String fileDirectoryOfUnprocessedFiles){

        this.filesAndContent = new HashMap<String, String>();
        this.fileDirectoryOfUnprocessedFiles = fileDirectoryOfUnprocessedFiles;
    }

    /**
     * Method collects the files included in the provided directory. This list provides the basis for which will later be read
     * @return List<String> with all files. Excluding the .DS_store file
     */

    public List<String> populateFileDirectory() {

        final File directory = new File(fileDirectoryOfUnprocessedFiles);
        final List<String> listOfUnprocessedFiles = new ArrayList<String>();

        final File[] listOfFiles = directory.listFiles();

        for (File f : listOfFiles) {

            if (f.getName().equals(".DS_Store")) {
                System.out.println("Ignore .dot file");

            } else {
                listOfUnprocessedFiles.add(f.getName());
            }

            }
        return listOfUnprocessedFiles;
    }

    /**
     * The methods reads the content of a file and puts the filename + content into a map
     * @param String filename of unprocessed file. T
     * @throws IOException
     */

    public void addFileToMap(String file) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(fileDirectoryOfUnprocessedFiles + file));
        String         line = null;
        final StringBuilder  stringBuilder = new StringBuilder();
        final String  ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                //stringBuilder.append(ls);
            }

            filesAndContent.put(file,stringBuilder.toString());
        } finally {
            reader.close();
        }
    }

    /**
     * Reads in all unprocessed files
     * @param unprocessedFiles
     */

    public void readAllUnprocessedFiles(List<String> unprocessedFiles){

        for(String s : unprocessedFiles){
            try {
                addFileToMap(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the list and reads in all files
     */
    public void readInUnprocessedFiles(){

        List<String> listOfUnprocessedFiles = populateFileDirectory();
        readAllUnprocessedFiles(listOfUnprocessedFiles);
    }

    public Map<String, String> getFilesAndContent() {
        return filesAndContent;

    }

}
