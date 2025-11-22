package SeleniumFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteToPropertyFile {
    public static void writeToPropertyFile(String key,String value) throws IOException {
        FileInputStream  fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("C:\\Users\\Shekhar\\IdeaProjects\\SeleniumDemo\\PropertyFile");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties prop = new Properties();
        prop.load(fis);
        prop.setProperty(key,value);

        try{
            fos=new FileOutputStream("C:\\Users\\Shekhar\\IdeaProjects\\SeleniumDemo\\PropertyFile");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        prop.store(fos,"File written successfully to Property File!!");

    }
}
