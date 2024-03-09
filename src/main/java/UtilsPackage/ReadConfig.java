package UtilsPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties prop;

    public ReadConfig() {
    }

    public String getProperty(String property) throws IOException {
        File file= new File("Config.properties");
        FileInputStream fis = new FileInputStream(file);
        prop=new Properties();
        prop.load(fis);
        return prop.getProperty(property);
    }
}
