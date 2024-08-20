package com.example.MY_SPRING_BOOT_STARTER;

import com.example.MY_SPRING_BOOT_STARTER.interceptors.HttpLoggingInterceptor;
import com.example.MY_SPRING_BOOT_STARTER.service.LogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HttpLoggingAutoConfigurationTest {

    @MockBean
    private LogService logService;

    @Test
    public void testHttpLoggingInterceptorBeanExists(ApplicationContext context) {
        HttpLoggingInterceptor interceptor = context.getBean(HttpLoggingInterceptor.class);
        assertThat(interceptor).isNotNull();
    }
}

