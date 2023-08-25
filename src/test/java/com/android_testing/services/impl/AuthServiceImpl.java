package com.android_testing.services.impl;

import com.android_testing.config.PropertiesConfig;
import com.android_testing.dao.ColvirDAO;
import com.android_testing.pages.AuthPage;
import com.android_testing.pages.MainPage;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.android_testing.services.CommonServices;
import com.android_testing.services.AuthService;
import com.android_testing.pages.WelcomePage;

import static com.codeborne.selenide.Selenide.sleep;

@Service
public class AuthServiceImpl extends CommonServices implements AuthService {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private WelcomePage welcomePage;

    @Autowired
    private AuthPage authPage;

    @Autowired
    private ColvirDAO colvirDAO;

    @Autowired
    private MainPage mainPage;

    @Override
    public void closeWelcomePage(){
        clickButton(welcomePage, "closeWelcomeWindow");
    }

    @Override
    public void signIn(){
        goToAuthPage();

        Assert.assertTrue("Не отображается кнопка входа/выхода на странице Авторизации!",
                isElementDisplayed(authPage, "logInOutButton"));
        clickButton(authPage, "logInOutButton");

        String phoneNumber = propertiesConfig.getUser().getPhoneNumber();
        String smsCode = propertiesConfig.getUser().getSmsCode();

        Assert.assertTrue("Не отображается поле для номера телефона на странице Авторизации!",
                isElementDisplayed(authPage, "phoneNumberEdit"));
        inputText(authPage, "phoneNumberEdit", phoneNumber);
        clickButton(authPage, "acceptPhoneNumber");

        if (isElementDisplayed(authPage, "authErrorLog")){
            // Нужно сообщить об ошибке
            Assert.fail("Не удается войти: " + textOf(authPage, "errorMessage"));

            // Временная функция повтора действия
            clickButton(authPage, "closeErrorLog");
            Assert.assertTrue("Не отображается поле для номера телефона на странице Авторизации!",
                    isElementDisplayed(authPage, "phoneNumberEdit"));
            inputText(authPage, "phoneNumberEdit", phoneNumber);
            clickButton(authPage, "acceptPhoneNumber");
        }

//        Assert.assertTrue("Не отображается поле для СМС кода на странице Авторизации!",
//                isElementDisplayed(authPage, "smsCodeEdit"));
        inputText(authPage, "smsCodeEdit", smsCode);
    }

    @Override
    public void setPassCode(){
        String passCode = propertiesConfig.getUser().getPassCode();

        inputNumByPressingKeys(authPage, "passCodeKeyboard", passCode);
        sleep(300);
        inputNumByPressingKeys(authPage, "passCodeKeyboard", passCode);

        sleep(5000);

//        Assert.assertTrue("Не отображается главная страница после того как задан код-пароль!",
//                isElementDisplayed(mainPage, "mainPage"));
    }

    @Override
    public void goToMainPage(){
        clickButton(authPage, "goBackButton");
    }

    @Override
    public void goToAuthPage(){
        clickButton(mainPage, "profileButton");
    }

    @Override
    public void navigateByBottomButtons(String pageButton){

        if (findByText(pageButton).isDisplayed())
            clickByText(pageButton);
        else
            Assert.fail("После авторизации не видна главная страница!");
    }
}
