package com.example.MY_SPRING_BOOT_STARTER.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http.logging")
public class LoggingProperties {

    private String level = "INFO";

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
