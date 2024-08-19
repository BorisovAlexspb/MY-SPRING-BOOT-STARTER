package com.example.MY_SPRING_BOOT_STARTER;

import com.example.MY_SPRING_BOOT_STARTER.interceptors.HttpLoggingInterceptor;
import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class HttpLoggingInterceptorTest {

    private LogService logService;
    private HttpLoggingInterceptor interceptor;
    private Logger logger;

    @BeforeEach
    public void setUp() {
        logService = mock(LogService.class);
        logger = mock(Logger.class);
        when(logService.createLogger(HttpLoggingInterceptor.class)).thenReturn(logger);
        interceptor = new HttpLoggingInterceptor(logService);
    }

    @Test
    public void testPreHandle() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test");
        when(request.getHeaderNames()).thenReturn(Collections.emptyEnumeration());

        interceptor.preHandle(request, response, new Object());
        verify(logService, times(1)).log(any(), contains("method = GET URI = /test"));
    }

    @Test
    public void testAfterCompletion() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getAttribute("startTime")).thenReturn(Instant.now());
        when(response.getStatus()).thenReturn(200);
        when(response.getHeaderNames()).thenReturn(Collections.emptyList());

        interceptor.afterCompletion(request, response, new Object(), null);
        verify(logService, times(1)).log(any(), contains("status = 200"));
    }
}
