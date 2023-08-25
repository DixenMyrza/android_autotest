package com.android_testing.services;

public interface TransfersService {

    void goToTransfersPage();

    void chooseTransferType(String type);

    void chooseFromAccount(String account);

    void chooseToAccount(String account);

    void makeTransfer();

    void closeReceipt();

    void checkTransferStatus(String transferStatus);

    void inputAccountNum(String accountNum);

    void inputClientId(String clientId);

    void chooseClientTypeAndConfirm(String clientType);

    void chooseKnpType(String knpType);

    void chooseKbeType(String kbeType);
}
