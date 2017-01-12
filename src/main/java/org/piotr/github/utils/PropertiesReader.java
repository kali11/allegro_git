package org.piotr.github.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Wrapper for configuration properties
 */
@Singleton
public class PropertiesReader {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private final Properties properties;

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
