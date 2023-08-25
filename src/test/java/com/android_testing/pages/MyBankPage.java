package com.android_testing.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Getter
@Component
public class MyBankPage implements Page{

    public MyBankPage(){}

    private final String depositCurrencyId = "kz.tsb.app24.debug:id/";

    private final Map<String, SelenideElement> elements = new HashMap<String, SelenideElement>() {{
        // Buttons
        put("openDepositButton", $(MobileBy.id("kz.tsb.app24.debug:id/openButton")));
        put("continueButton", $(MobileBy.id("kz.tsb.app24.debug:id/nextButton")));
        put("confirmButton", $(MobileBy.id("kz.tsb.app24.debug:id/confirmButton")));
        put("continueAfterReceipt", $(MobileBy.id("kz.tsb.app24.debug:id/continueButton")));
        put("goToDashboard", $(MobileBy.id("kz.tsb.app24.debug:id/transferToDashboard")));

        // Fields
        put("amountSumEdit", $(MobileBy.id("kz.tsb.app24.debug:id/amountEditText")));
    }};

    private final Map<String, ElementsCollection> elementsCollections = new HashMap<String, ElementsCollection>() {{
        put("depositTypesContainer", $$(MobileBy.xpath("/hierarchy/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView" +
                "/android.widget.LinearLayout/android.widget.LinearLayout")));

        put("depositTypesTitle", $$(MobileBy.xpath("/hierarchy/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView" +
                "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]")));

        put("depositTypesOpenButton", $$(MobileBy.xpath("/hierarchy/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout" +
                "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView" +
                "/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]")));
    }};

    private final Map<String, String> elementLocators = new HashMap<String, String>() {{

    }};
}
