package com.android_testing.config;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.test.annotation.DirtiesContext;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


@Configuration
@ComponentScan("com.android_testing")
@Import({PropertiesConfig.class})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class AndroidDriverConfig {

    @Autowired
    private PropertiesConfig propertiesConfig;

    public AndroidDriver<AndroidElement> driver;

    @Qualifier("android")
    @Bean
    @Scope("prototype")
    public AndroidDriver<AndroidElement> androidDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();


        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, propertiesConfig.getVd().getPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, propertiesConfig.getVd().getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, propertiesConfig.getVd().getDeviceName());
        capabilities.setCapability(MobileCapabilityType.APP, propertiesConfig.getApp().getAppSource());
        capabilities.setCapability("appPackage", propertiesConfig.getApp().getAppPackage());
        capabilities.setCapability("appActivity", propertiesConfig.getApp().getAppActivity());


        driver = new AndroidDriver<>(new URL(propertiesConfig.getWeb().getBaseUrl()), capabilities);
        WebDriverRunner.setWebDriver(driver);

        return driver;
    }
}