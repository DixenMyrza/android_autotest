package com.android_testing.services.impl;

import com.android_testing.config.AndroidDriverConfig;
import com.android_testing.dao.ColvirDAO;
import com.android_testing.pages.AuthPage;
import com.android_testing.pages.MyBankPage;
import com.android_testing.services.CommonServices;
import com.android_testing.services.MyBankService;
import static com.codeborne.selenide.Condition.*;

import com.android_testing.steps.BaseStepDef;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en_scouse.An;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

@Service
public class MyBankServiceImpl extends CommonServices implements MyBankService {

    private List<Integer> transactionAmountList = new ArrayList<>();

    private List<String> depositTypeList = new ArrayList<>();

    @Autowired
    private MyBankPage myBankPage;

    @Autowired
    private AuthPage authPage;

    @Autowired
    private AndroidDriverConfig androidDriverConfig;

    private Random random = new Random();

    @Autowired
    private ColvirDAO colvirDAO;


    @Override
    public void scrollDownUntilVisibleAndClickByText(String buttonsText){
        scrollUntilDisplayed(findByText(buttonsText), ScrollNavigation.DOWN);

        clickByText(buttonsText);
    }


    @Override
    public void chooseCurrencyAndOpenDeposit(String currency){
        chooseCurrency(currency);

        clickButton(myBankPage, "openDepositButton");
    }


    @Override
    public void chooseCurrency(String currency){
        SelenideElement currencyButton = $(MobileBy.id(myBankPage.getDepositCurrencyId()
                + currency.toLowerCase() + "Layout"));

        currencyButton.click();
    }


    @Override
    public void chooseDepositTerm(String term){
        if (findByText(term).isDisplayed()){
            clickByText(term);
        }
    }


    @Override
    public void inputAmountAndConfirm(String depositType){

        int max = 17000, min = 12000;

        int transactionAmount = random.nextInt(max - min + 1) + min;

        transactionAmountList.add(transactionAmount);

        depositTypeList.add(depositType);

        inputText(myBankPage, "amountSumEdit", String.valueOf(transactionAmount));

        clickButton(myBankPage, "continueButton");

        sleep(1000);

        clickButton(myBankPage, "confirmButton");

        if (isElementDisplayed(authPage, "smsCodeEdit")){
            inputText(authPage, "smsCodeEdit", "0000");
        }

        clickButton(myBankPage, "continueAfterReceipt");

        clickButton(myBankPage, "goToDashboard");
    }


    @Override
    public void chooseDepositType(String depositType){

        ElementsCollection depositTypesTitle = getFromCollectionMap(myBankPage.getElementsCollections(), "depositTypesTitle");

        int index = 0;

        for (int i=0; i<depositTypesTitle.size();i++){
            if (depositTypesTitle.get(i).has(text(depositType)))
                index = i;
        }

        getFromCollectionMap(myBankPage.getElementsCollections(), "depositTypesOpenButton").get(index).click();
    }


    @Override
    public void checkStatusInColvir(String depositStatus){

        String assertionText = "Статус нижеперечисленных депозитов в базе данных не обновился или же неверный:\n";

        boolean isStatusWrong = false;

        FluentWait<AndroidDriver<AndroidElement>> wait = new FluentWait<>(androidDriverConfig.driver)
                .withTimeout(Duration.ofMinutes(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        System.out.println(transactionAmountList.size());

        System.out.println(transactionAmountList);

        for (int i=0; i< transactionAmountList.size(); i++){

            try{
                final int index = i;

                System.out.println(i);
                wait.until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        String actualStatus = colvirDAO.getDepositStatus(transactionAmountList.get(index));
                        return !actualStatus.isEmpty() && actualStatus.equals(depositStatus);
                    }
                });
            } catch (Exception e){
                isStatusWrong = true;

                assertionText += depositTypeList.get(i) + " - Статус: '" +
                        colvirDAO.getDepositStatus(transactionAmountList.get(i)) + "'\n";
            }
        }

        if (isStatusWrong){
            Assert.fail(assertionText);
        }
    }
}
