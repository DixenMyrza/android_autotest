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
public class WelcomePage implements Page{

    public WelcomePage(){}

    private final Map<String, SelenideElement> elements = new HashMap<String, SelenideElement>() {{
        put("closeWelcomeWindow", $(MobileBy.id("kz.tsb.app24.debug:id/closeButton")));
    }};

    private final Map<String, ElementsCollection> elementsCollections = new HashMap<String, ElementsCollection>() {{

    }};

    private final Map<String, String> elementLocators = new HashMap<String, String>() {{

    }};
}
