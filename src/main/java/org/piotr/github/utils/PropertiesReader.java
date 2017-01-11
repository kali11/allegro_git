package org.piotr.github.utils;


import org.apache.log4j.Logger;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Singleton
public class PropertiesReader {
    private final Properties properties;
    private final Logger logger = Logger.getLogger(getClass().getName());

    public PropertiesReader(InputStream in) {
        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error("cannot read properties file");
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
