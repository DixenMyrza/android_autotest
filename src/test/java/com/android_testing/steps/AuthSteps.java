package com.android_testing.steps;

import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import org.springframework.beans.factory.annotation.Autowired;
import com.android_testing.services.AuthService;

public class AuthSteps {
    @Autowired
    private AuthService authService;

    @Если("Появляется страница приветствия, то закрыть его")
    public void closeWelcomePage(){
        authService.closeWelcomePage();
    }

    @И("Войти в учетную запись с данными в auth.properties файле")
    public void signIn(){
        authService.signIn();
    }

    @И ("Задать код пароль для входа в приложение")
    public void setPassCode(){
        authService.setPassCode();
    }
}