package com.example.MY_SPRING_BOOT_STARTER.interceptors;

import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;

@Component
public class HttpLoggingInterceptor implements HandlerInterceptor {

    private final LogService logService;
    private final Logger logger;

    public HttpLoggingInterceptor(LogService logService) {
        this.logService = logService;
        this.logger = logService.createLogger(this.getClass());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        request.setAttribute("startTime", Instant.now());
        logService.log(logger, getRequestMessage(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        Instant startTime = (Instant) request.getAttribute("startTime");
        long duration = Instant.now().toEpochMilli() - startTime.toEpochMilli();

        logService.log(logger, getResponseMessage(response, duration));
    }

    private String getRequestMessage(HttpServletRequest request) {
        return String.format("method = %s URI = %s headers = %s",
                request.getMethod(),
                request.getRequestURI(),
                request.getHeaderNames());
    }

    private String getResponseMessage(HttpServletResponse response, long duration) {
        return String.format("status = %d duration = %dms headers = %s",
                response.getStatus(),
                duration,
                response.getHeaderNames());
    }
}
