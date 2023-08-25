package com.android_testing.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(ignoreInvalidFields = true)
@PropertySource({
        "classpath:properties/app.properties",
        "classpath:properties/auth.properties",
        "classpath:properties/emulator.properties",
        "classpath:properties/database.properties",
})

public class PropertiesConfig {

    private User user;

    private App app;

    private Vd vd;

    private Web web;

    private Datasource datasource;


    @Data
    @Configuration
    @ConfigurationProperties(prefix = "datasource")
    public static class Datasource {
        private String url;
        private String username;
        private String password;
    }


    @Data
    @Configuration
    @ConfigurationProperties(prefix = "user")
    public static class User {
        private String phoneNumber;
        private String smsCode;
        private String passCode;
        private String clientId;
    }


    @Data
    @Configuration
    @ConfigurationProperties(prefix = "app")
    public static class App {
        private String appSource;
        private String appPackage;
        private String appActivity;
    }


    @Data
    @Configuration
    @ConfigurationProperties(prefix = "vd")
    public static class Vd {
        private String deviceName;
        private String platformName;
        private String platformVersion;
    }


    @Data
    @Configuration
    @ConfigurationProperties(prefix = "web")
    public static class Web {
        private String browser;
        private String baseUrl;
    }
}