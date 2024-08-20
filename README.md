# Отчёт по проекту SPRING BOOT STARTER
## Описание
Это Spring Boot Starter, который предоставляет возможность логирования HTTP запросов в вашем приложении на базе Spring Boot.
## Запуск приложения 
Для запуска приложения выполните команду
```
mvn install
mvn spring-boot:run
```
Для запуска тестов выполните команду 
```
mvn test
```
## Логирование 
Приложение позволяет настаивать уровень логирования через файл `application.properties`
Для этого,  укажите нужный вам уровень логирования. Пример
```
http.logging.level=INFO
```
## Примеры логов 
```
2024-08-20T14:44:57.152+03:00  INFO 15572 --- [MY-SPRING-BOOT-STARTER] [           main] c.e.M.i.HttpLoggingInterceptor           : method = GET URI = /test headers = java.util.Collections$EmptyEnumeration@6f0a4e30
2024-08-20T14:30:37.537+03:00  INFO 15041 --- [MY-SPRING-BOOT-STARTER] [           main] c.e.M.i.HttpLoggingInterceptor           : status = 200 duration = 4ms headers = []
```

## Примеры тестов 
Тест на проверку работы класса LogService
```
@Test
    public void testLogInfo() {
        LoggingProperties loggingProperties = new LoggingProperties();
        loggingProperties.setLevel("INFO");
        logService = new LogService(loggingProperties);
        logService.log(logger, "Info message");
        verify(logger, times(1)).info("Info message");
    }
```
Тест на проверку работы интерцептора
```
 @Test
    public void testPreHandle() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test");
        when(request.getHeaderNames()).thenReturn(Collections.emptyEnumeration());

        interceptor.preHandle(request, response, new Object());
    }
```


