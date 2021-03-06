package edu.epam.task6.util;

import edu.epam.task6.exception.LocalPropertyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final Logger logger = LogManager.getLogger();

    public Properties read (String path) throws LocalPropertyException {
        ClassLoader classLoader =Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = classLoader.getResourceAsStream(path)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            logger.error("Error in reading properties: ", e);
            throw new LocalPropertyException("Error in reading properties: ", e);
        }
    }
}
