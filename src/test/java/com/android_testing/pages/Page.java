package com.android_testing.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

public interface Page {

    Map<String, SelenideElement> getElements();
    Map<String, ElementsCollection> getElementsCollections();

    Map<String, String> getElementLocators();
}
