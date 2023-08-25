package com.android_testing.steps;

import com.android_testing.services.TransfersService;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.И;
import org.springframework.beans.factory.annotation.Autowired;

public class TransfersSteps {

    @Autowired
    private TransfersService transfersService;

    @Допустим("Зашли в страницу Переводы")
    public void goToTransfersPage(){
        transfersService.goToTransfersPage();
    }

    @И("Выбираем перевод {string}")
    public void chooseTransferType(String type){
        transfersService.chooseTransferType(type);
    }

    @И ("Выбираем откуда переводить, например со счета {string}")
    public void chooseFromAccount(String account){
        transfersService.chooseFromAccount(account);
    }

    @И ("Выбираем куда переводить, например на счет {string}")
    public void chooseToAccount(String account){
        transfersService.chooseToAccount(account);
    }

    @И ("Вводим случайную сумму перевода и подтверждаем")
    public void makeTransfer(){
        transfersService.makeTransfer();
    }

    @И ("Закрыть квитанцию")
    public void closeReceipt(){
        transfersService.closeReceipt();
    }

    @И ("Проверяем статус перевода {string} в Colvir")
    public void checkTransferStatus(String transferStatus){
        transfersService.checkTransferStatus(transferStatus);
    }

    @И ("Вводим номер счета {string}")
    public void inputAccountNum(String accountNum){
        transfersService.inputAccountNum(accountNum);
    }

    @И ("Вводим ИИН\\БИН клиента {string}")
    public void inputClientId(String clientId){
        transfersService.inputClientId(clientId);
    }

    @И ("Выбираем {string} и подтверждаем")
    public void chooseClientTypeAndConfirm(String clientType){
        transfersService.chooseClientTypeAndConfirm(clientType);
    }

    @И ("Выбираем тип КНП {string}")
    public void chooseKnpType(String knpType){
        transfersService.chooseKnpType(knpType);
    }

    @И ("Выбираем тип КБЕ {string}")
    public void chooseKbeType(String kbeType){
        transfersService.chooseKbeType(kbeType);
    }
}
