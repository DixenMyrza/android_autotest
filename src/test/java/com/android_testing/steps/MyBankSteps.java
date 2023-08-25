package com.android_testing.steps;

import com.android_testing.services.MyBankService;
import cucumber.api.java.ru.И;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBankSteps {

    @Autowired
    private MyBankService myBankService;

    @И("Нажимаем на кнопку {string}")
    public void scrollDownUntilVisibleAndClickByText(String buttonsText){
        myBankService.scrollDownUntilVisibleAndClickByText(buttonsText);
    }

    @И ("Выбираем валюту депозита {string} и подтверждаем")
    public void chooseCurrencyAndOpenDeposit(String currency){
        myBankService.chooseCurrencyAndOpenDeposit(currency);
    }

    @И ("Выбираем валюту вклада {string}")
    public void chooseCurrency(String currency){
        myBankService.chooseCurrency(currency);
    }

    @И ("Выбираем срок депозита {string} если он есть")
    public void chooseDepositTerm(String term){
        myBankService.chooseDepositTerm(term);
    }

    @И ("Вводим случайную сумму вклада в {string} и подтверждаем")
    public void inputAmountAndConfirm(String depositType){
        myBankService.inputAmountAndConfirm(depositType);
    }

    @И ("Выбираем тип депозита {string}")
    public void chooseDepositType(String depositType){
        myBankService.chooseDepositType(depositType);
    }

    @И ("Проверяем статус депозита {string} в Colvir")
    public void checkStatusInColvir(String depositStatus){
        myBankService.checkStatusInColvir(depositStatus);
    }
}
