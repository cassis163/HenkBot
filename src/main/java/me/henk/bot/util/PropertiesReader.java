package me.henk.bot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    Properties properties;

    public PropertiesReader(String fileDirectory) throws IOException {
        InputStream inputStream = PropertiesReader.class.getResourceAsStream(fileDirectory);

        properties = new Properties();
        properties.load(inputStream);
    }


    public String get(String key) {
        return properties.getProperty(key);
    }

}
