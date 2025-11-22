package SeleniumFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadFromPropertyFile {
    public static String readFromPropertyFile(String key) throws IOException {
            FileInputStream fis=null;
            try {
                fis = new FileInputStream("C:\\Users\\Shekhar\\IdeaProjects\\SeleniumDemo\\PropertyFile");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Properties prop = new Properties();
            prop.load(fis);
            String value=prop.getProperty(key);
             //System.out.println("URL is: "+URL);
            return value;
            }
        }



