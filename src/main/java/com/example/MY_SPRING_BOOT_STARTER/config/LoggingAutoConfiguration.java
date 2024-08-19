package com.example.MY_SPRING_BOOT_STARTER.config;

import com.example.MY_SPRING_BOOT_STARTER.interceptors.HttpLoggingInterceptor;
import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingAutoConfiguration implements WebMvcConfigurer {

    private final LogService logService;

    public LoggingAutoConfiguration(LogService logService) {
        this.logService = logService;
    }

    @Bean
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(this.logService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpLoggingInterceptor());
    }
}

