package com.android_testing.pages;

import com.codeborne.selenide.Condition;
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
public class TransfersPage implements Page{

    public TransfersPage(){}
    private final Map<String, SelenideElement> elements = new HashMap<String, SelenideElement>() {{
        // Buttons
        put("betweenYourAccountsButton", $(MobileBy.xpath("//*[@text = 'Между своими счетами']")));
        put("chooseFromAccount", $(MobileBy.id("kz.tsb.app24.debug:id/productArrowImageview")));
        put("chooseToAccount", $(MobileBy.id("kz.tsb.app24.debug:id/placeholderFrameLayout")));
        put("transferButton", $(MobileBy.id("kz.tsb.app24.debug:id/transferButton")));
        put("confirmTransferButton", $(MobileBy.id("kz.tsb.app24.debug:id/confirmButton")));
        put("receiptButton", $(MobileBy.id("kz.tsb.app24.debug:id/receiptButton")));
        put("closeReceiptButton", $(MobileBy.id("kz.tsb.app24.debug:id/closeButton")));

        put("findAccountButton", $(MobileBy.id("kz.tsb.app24.debug:id/findButton")));
        put("editKnpButton", $(MobileBy.id("kz.tsb.app24.debug:id/knpEditText")));
        put("editKbeButton", $(MobileBy.id("kz.tsb.app24.debug:id/kbeEditText")));

        // Accounts locators
        put("depositKZT", $(MobileBy.xpath("//*[contains(@text, '«Jusan депозит» •4716')]")));
        put("paymentCardKZT", $(MobileBy.xpath(("//*[@text='Платежная карта •2108']"))));

        // Fields
        put("transfersSumEdit", $(MobileBy.id("kz.tsb.app24.debug:id/amountEditText")));

        put("accountNumberEdit", $(MobileBy.id("kz.tsb.app24.debug:id/contractNumberEditText")));
        put("idEdit", $(MobileBy.id("kz.tsb.app24.debug:id/iinEditText")));
    }};

    private final Map<String, ElementsCollection> elementsCollections = new HashMap<String, ElementsCollection>() {{

    }};

    private final Map<String, String> elementLocators = new HashMap<String, String>() {{

    }};
}
