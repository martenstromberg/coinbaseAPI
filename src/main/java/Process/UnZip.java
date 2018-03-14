package Process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip {


    public void unZipIt(String fileDirectory, String zipFile, String outputFolder){

        byte[] buffer = new byte[1024];

        try{

            //create output directory is not exists
            File folder = new File(outputFolder);
            if(!folder.exists()){
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                new ZipInputStream(new FileInputStream(fileDirectory + zipFile + ".zip"));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while(ze!=null){

                String fileName = ze.getName();
                if(fileName.equals(".DS_Store")) {
                    System.out.println("Ignore file .DS_STORE");
                } else {
                    File newFile = new File(outputFolder + File.separator + fileName);

                    System.out.println("file unzip : " + newFile.getAbsoluteFile());

                    //create all non exists folders
                    //else you will hit FileNotFoundException for compressed folder
                    new File(newFile.getParent()).mkdirs();

                    FileOutputStream fos = new FileOutputStream(newFile);

                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();
                }
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();

            System.out.println("Done");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void removeParentFolder(String outputDirectory, File fileName){

        if(fileName.delete()){
            System.out.println("Parentfolder successfully deleted");
        } else {
            System.out.println("Not able to delete file");
        }
    }

}