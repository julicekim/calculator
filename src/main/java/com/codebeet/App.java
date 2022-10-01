package com.codebeet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("================================================================");
        logger.info("시작");
        logger.info("================================================================");

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "config.properties";

        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Path.of(appConfigPath))) {
            properties.load(input);
            logger.info("Version: {}", properties.getProperty("version"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
