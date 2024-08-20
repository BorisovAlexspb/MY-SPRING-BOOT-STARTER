package com.example.MY_SPRING_BOOT_STARTER;

import com.example.MY_SPRING_BOOT_STARTER.config.LoggingProperties;
import com.example.MY_SPRING_BOOT_STARTER.interceptors.HttpLoggingInterceptor;
import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Collections;

import static org.mockito.Mockito.*;

@SpringBootTest
public class HttpLoggingInterceptorTest {

    private LogService logService;
    private HttpLoggingInterceptor interceptor;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        LoggingProperties loggingProperties = new LoggingProperties();
        loggingProperties.setLevel("INFO");

        logService = new LogService(loggingProperties);
        interceptor = new HttpLoggingInterceptor(logService);
        logger = logService.createLogger(HttpLoggingInterceptor.class);
    }

    @Test
    public void testPreHandle() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test");
        when(request.getHeaderNames()).thenReturn(Collections.emptyEnumeration());

        interceptor.preHandle(request, response, new Object());
    }

    @Test
    public void testAfterCompletion() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getAttribute("startTime")).thenReturn(Instant.now());
        when(response.getStatus()).thenReturn(200);
        when(response.getHeaderNames()).thenReturn(Collections.emptyList());

        interceptor.afterCompletion(request, response, new Object(), null);
    }
}
