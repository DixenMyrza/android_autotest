package com.android_testing.services;

public interface MyBankService {

    void scrollDownUntilVisibleAndClickByText(String buttonsText);

    void chooseCurrencyAndOpenDeposit(String currency);

    void chooseCurrency(String currency);

    void chooseDepositTerm(String term);

    void inputAmountAndConfirm(String depositType);

    void chooseDepositType(String depositType);

    void checkStatusInColvir(String depositStatus);
}
