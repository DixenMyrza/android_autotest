package com.android_testing.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

@Getter
@Component
public class AuthPage implements Page {

    public AuthPage(){}

    private final Map<String, SelenideElement> elements = new HashMap<String, SelenideElement>() {{
        // Buttons
        put("logInOutButton", $(MobileBy.id("kz.tsb.app24.debug:id/authButton")));
        put("acceptPhoneNumber", $(MobileBy.id("kz.tsb.app24.debug:id/nextButton")));
        put("goBackButton", $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]")));

        // Fields
        put("phoneNumberEdit", $(MobileBy.id("kz.tsb.app24.debug:id/phoneNumberEditText")));
        put("smsCodeEdit", $(MobileBy.id("kz.tsb.app24.debug:id/smsCodeEditText")));

        // Error log
        put("authErrorLog", $(MobileBy.id("kz.tsb.app24.debug:id/title_template")));
        put("closeErrorLog", $(MobileBy.id("android:id/button1")));
        put("errorMessage", $(MobileBy.id("android:id/message")));
    }};

    private final Map<String, ElementsCollection> elementsCollections = new HashMap<String, ElementsCollection>() {{

    }};

    private final Map<String, String> elementLocators = new HashMap<String, String>() {{
        put("passCodeKeyboard", "kz.tsb.app24.debug:id/number0TextView");
    }};
}
