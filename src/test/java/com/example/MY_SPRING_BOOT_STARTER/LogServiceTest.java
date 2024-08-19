package com.example.MY_SPRING_BOOT_STARTER;

import com.example.MY_SPRING_BOOT_STARTER.config.LoggingProperties;
import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class LogServiceTest {

    private LogService logService;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        LoggingProperties loggingProperties = new LoggingProperties();
        loggingProperties.setLevel("DEBUG");
        logService = new LogService(loggingProperties);
        logger = mock(Logger.class);
    }

    @Test
    public void testLogDebug() {
        logService.log(logger, "Debug message");
        verify(logger, times(1)).debug("Debug message");
    }

    @Test
    public void testLogInfo() {
        LoggingProperties loggingProperties = new LoggingProperties();
        loggingProperties.setLevel("INFO");
        logService = new LogService(loggingProperties);
        logService.log(logger, "Info message");
        verify(logger, times(1)).info("Info message");
    }

}
