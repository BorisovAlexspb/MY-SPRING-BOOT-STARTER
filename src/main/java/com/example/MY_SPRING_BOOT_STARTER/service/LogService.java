package com.example.MY_SPRING_BOOT_STARTER.service;

import com.example.MY_SPRING_BOOT_STARTER.config.LoggingProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private final String level;


    public LogService(LoggingProperties loggingProperties) {
        this.level = loggingProperties.getLevel().toUpperCase();
    }

    public Logger createLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public void log(Logger logger, String message) {
        switch (level) {
            case "ERROR":
                logger.error(message);
                break;
            case "WARN":
                logger.warn(message);
                break;
            case "INFO":
                logger.info(message);
                break;
            case "DEBUG":
                logger.debug(message);
                break;
            default:
                logger.trace(message);
        }
    }
}
