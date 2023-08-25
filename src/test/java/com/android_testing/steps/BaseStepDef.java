package com.android_testing.steps;

import com.android_testing.config.AndroidDriverConfig;
import com.android_testing.config.PropertiesConfig;
import com.android_testing.services.AuthService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Допустим;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;

@ContextConfiguration(
        classes = {
                AndroidDriverConfig.class,
                PropertiesConfig.class
        })
public class BaseStepDef {
    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private AndroidDriverConfig androidDriverConfig;

    @Autowired
    private AuthService authService;

    public AndroidDriver<AndroidElement> driver;


    @Before
    public void beforeRunning() throws MalformedURLException {
        com.codeborne.selenide.Configuration.browser = propertiesConfig.getWeb().getBrowser();
        com.codeborne.selenide.Configuration.baseUrl = propertiesConfig.getWeb().getBaseUrl();
        com.codeborne.selenide.Configuration.timeout = 30000;
        com.codeborne.selenide.Configuration.startMaximized = true;

        this.driver = androidDriverConfig.androidDriver();

        authService.closeWelcomePage();
        authService.signIn();
        authService.setPassCode();
        authService.goToMainPage();
    }

    @After
    public void afterRunning() {
        if (this.driver != null) {
            try {
                this.driver.close();
                this.driver.quit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Допустим ("Зашли в страницу {string}")
    public void navigateByBottomButtons(String pageButton){
        authService.navigateByBottomButtons(pageButton);
    }
}
