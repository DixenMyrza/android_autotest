package com.android_testing.services.impl;

import com.android_testing.config.AndroidDriverConfig;
import com.android_testing.config.PropertiesConfig;
import com.android_testing.dao.ColvirDAO;
import com.android_testing.pages.MainPage;
import com.android_testing.pages.TransfersPage;
import com.android_testing.services.CommonServices;
import com.android_testing.services.TransfersService;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.sleep;

@Service
public class TransfersServceImpl extends CommonServices implements TransfersService {

    @Autowired
    private TransfersPage transfersPage;

    @Autowired
    private ColvirDAO colvirDAO;

    @Autowired
    private AndroidDriverConfig androidDriverConfig;

    @Autowired
    private PropertiesConfig propertiesConfig;

    private Random random = new Random();

    private int transactionAmount;

    private String receiverClientId;

    private String receiverAcc;

    @Autowired
    private MainPage mainPage;


    @Override
    public void goToTransfersPage(){
        clickButton(mainPage, "transferPage");
    }


    @Override
    public void chooseTransferType(String type){

        if (type.equals("Между своими счетами"))
            receiverClientId = propertiesConfig.getUser().getClientId();

        clickByText(type);
    }


    @Override
    public void chooseFromAccount(String account){
        clickButton(transfersPage, "chooseFromAccount");

        scrollUntilDisplayed(findByText(account), ScrollNavigation.DOWN);

        clickByText(account);
    }


    @Override
    public void chooseToAccount(String account){

        receiverAcc = account.substring(account.length()-4);

        clickButton(transfersPage, "chooseToAccount");

        scrollUntilDisplayed(findByText(account), ScrollNavigation.DOWN);

        clickByText(account);
    }


    @Override
    public void makeTransfer(){
        clickButton(transfersPage, "transfersSumEdit");

        int max = 3000, min = 500;

        transactionAmount = random.nextInt(max - min + 1) + min;

        inputText(transfersPage, "transfersSumEdit", String.valueOf(transactionAmount));

        androidDriverConfig.driver.hideKeyboard();

        clickButton(transfersPage, "transferButton");
        clickButton(transfersPage, "confirmTransferButton");
    }


    @Override
    public void closeReceipt(){
        clickButton(transfersPage, "receiptButton");
        // Можно проверить квитанцию

        clickButton(transfersPage, "closeReceiptButton");

        sleep(6000);
    }


    @Override
    public void checkTransferStatus(String transferStatus) {

        FluentWait<AndroidDriver<AndroidElement>> wait = new FluentWait<>(androidDriverConfig.driver)
                .withTimeout(Duration.ofMinutes(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    String actualStatus = colvirDAO.getTransferStatus(transactionAmount, receiverAcc, receiverClientId);
                    return !actualStatus.isEmpty() && actualStatus.equals(transferStatus);
                }
            });
        } catch (TimeoutException e) {
            Assert.fail("Статус перевода в базе данных не обновился или неверный");
        }
    }


    @Override
    public void inputAccountNum(String accountNum){
        receiverAcc = accountNum.substring(accountNum.length()-4);

        inputText(transfersPage, "accountNumberEdit", accountNum);
    }


    @Override
    public void inputClientId(String clientId){

        receiverClientId = clientId;

        inputText(transfersPage, "idEdit", clientId);
    }


    @Override
    public void chooseClientTypeAndConfirm(String clientType){
        clickByText(clientType);

        clickButton(transfersPage, "findAccountButton");
    }


    @Override
    public void chooseKnpType(String knpType){
        clickButton(transfersPage, "editKnpButton");

        clickByText(knpType);
    }


    @Override
    public void chooseKbeType(String kbeType){
        clickButton(transfersPage, "editKbeButton");

        clickByText(kbeType);

        clickButton(transfersPage, "transferButton");
    }
}
