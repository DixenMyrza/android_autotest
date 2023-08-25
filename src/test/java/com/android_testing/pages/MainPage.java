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
public class MainPage implements Page{
    public MainPage(){}

    private final Map<String, SelenideElement> elements = new HashMap<String, SelenideElement>() {{
        // Buttons
        put("profileButton", $(MobileBy.id("kz.tsb.app24.debug:id/profileButton")));

        // Bottom navigation buttons
        put("mainPage", $(MobileBy.id("kz.tsb.app24.debug:id/mainTextView")));
        put("myBankPage", $(MobileBy.id("kz.tsb.app24.debug:id/dashboardTextView")));
        put("transferPage", $(MobileBy.id("kz.tsb.app24.debug:id/transferTextView")));
        put("paymentsPage", $(MobileBy.id("kz.tsb.app24.debug:id/paymentTextView")));
        put("servicesPage", $(MobileBy.id("kz.tsb.app24.debug:id/menuTextView")));

        // Error
        put("closeErrorLog", $(MobileBy.id("android:id/button1")));
        put("errorMessage", $(MobileBy.id("android:id/message")));
    }};

    private final Map<String, ElementsCollection> elementsCollections = new HashMap<String, ElementsCollection>() {{

    }};

    private final Map<String, String> elementLocators = new HashMap<String, String>() {{

    }};
}
