package com.android_testing.services;

public interface AuthService {

    void closeWelcomePage();

    void signIn();

    void setPassCode();

    void goToMainPage();

    void goToAuthPage();

    void navigateByBottomButtons(String pageButton);
}
