package com.android_testing.services;

import com.android_testing.config.AndroidDriverConfig;
import com.android_testing.pages.MainPage;
import com.android_testing.services.impl.ScrollNavigation;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.android_testing.pages.Page;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Service
public abstract class CommonServices {

    @Autowired
    private AndroidDriverConfig androidDriverConfig;

    @Autowired
    private MainPage mainPage;

    private final int centralXCord = 535;
    private final int midYCord = 1350;
    private final int botYCord = 2090;
    private final int scrollLimit = 50;


    protected SelenideElement getFromMap(Map<String, SelenideElement> map, String name){
        return map.get(name);
    }


    protected TouchAction touchAction(){
        return new TouchAction(androidDriverConfig.driver);
    }


    protected ElementsCollection getFromCollectionMap(Map<String, ElementsCollection> map, String name){
        return map.get(name);
    }


    public void clickButton(Page obj, String button){

        try {
            getFromMap(obj.getElements(), button).click();
        }

        catch (Throwable e){
            if (isElementDisplayed(mainPage, "errorMessage")){
                Assert.fail(textOf(mainPage, "errorMessage"));
            }
            else {
                Assert.fail("Элемент \"" + button + "\" страницы " + obj + " не найден:\n" + e.getMessage());
            }
        }
    }


    public boolean isElementDisplayed(Page obj, String element){
        return getFromMap(obj.getElements(), element).isDisplayed();
    }


    public SelenideElement findByText(String text){

        SelenideElement element = null;

        try {
            element = $(MobileBy.xpath("//*[contains(@text, '" + text + "')]"));
        }

        catch (Throwable e){
            if (isElementDisplayed(mainPage, "errorMessage")){
                Assert.fail(textOf(mainPage, "errorMessage"));
            }
            else {
                Assert.fail("Элементы с текстом \"" + text +  "\" на странице не найдены:\n" + e.getMessage());
            }
        }

        return element;
    }


    public ElementsCollection findCollectionByText(String text){

        ElementsCollection elements = null;

        try {
            elements = $$(MobileBy.xpath("//*[contains(@text, '" + text + "')]"));
        }

        catch (Throwable e){
            if (isElementDisplayed(mainPage, "errorMessage")){
                Assert.fail(textOf(mainPage, "errorMessage"));
            }
            else {
                Assert.fail("Элементы с текстом \"" + text +  "\" на странице не найдены:\n" + e.getMessage());
            }
        }

        return elements;
    }


    public void clickByText(String text){
        findByText(text).click();
    }


    public void inputText(Page obj, String field, String text){

        try {
            getFromMap(obj.getElements(), field).sendKeys(text);
        }

        catch (Throwable e){
            if (isElementDisplayed(mainPage, "errorMessage")){
                Assert.fail(textOf(mainPage, "errorMessage"));
            }
            else {
                Assert.fail("Элемент \"" + field + "\" страницы " + obj + " не найден:\n" + e.getMessage());
            }
        }
    }


    public String textOf(Page obj, String element){

        String elementText = "";

        try {
            elementText = getFromMap(obj.getElements(), element).getText();
        }
        catch (Throwable e){
            if (isElementDisplayed(mainPage, "errorMessage")){
                Assert.fail(textOf(mainPage, "errorMessage"));
            }
            else {
                Assert.fail("Элемент \"" + element + "\" страницы " + obj + " не найден:\n" + e.getMessage());
            }
        }

        return elementText;
    }


    public void inputNumByPressingKeys(String number) {
        for (char digit : number.toCharArray()) {
            AndroidKey key = getAndroidKeyForDigit(digit);
            androidDriverConfig.driver.pressKey(new KeyEvent(key));
        }
    }


    public void inputNumByPressingKeys(Page obj, String keyboard, String number){
        for (char digit : number.toCharArray()) {
            SelenideElement key = getAppKeyForDigit(obj, keyboard, digit);
            key.click();
        }
    }


    public MobileElement SelenideElementToMobileElement(Page obj, String element){
        return (MobileElement) getFromMap(obj.getElements(), element).toWebElement();
    }


    public int getElementYCord (MobileElement element){
        return element.getLocation().getY() + element.getSize().getHeight()/2;
    }


    public void scrollUntilDisplayed(Page obj, String element, String navigation){
        int startY = 0, endY = 0, scrollCount = 0;

        if (navigation.equals("down")){
            startY = botYCord;
            endY = midYCord;
        }
        else if (navigation.equals("up")){
            startY = midYCord;
            endY = botYCord;
        }
        else Assert.fail("Неизвестное направление!");

        while (!isElementDisplayed(obj, element)){
            if (scrollCount>scrollLimit)
                Assert.fail("Элемент " + element + " отсутствует на странице!");

            touchAction()
                    .press(PointOption.point(centralXCord, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(centralXCord, endY))
                    .release()
                    .perform();

            scrollCount+=1;
        }
    }


    public void scrollUntilDisplayed(SelenideElement element, ScrollNavigation scrollNavigation){
        int startY = 0, endY = 0, scrollCount = 0;

        switch (scrollNavigation){
            case DOWN:
                startY = botYCord;
                endY = midYCord;
                break;

            case UP:
                startY = midYCord;
                endY = botYCord;
                break;

            default:
                Assert.fail("Неизвестное направление скролла!");

        }

        while (!element.isDisplayed()){
            if (scrollCount>scrollLimit)
                Assert.fail("Элемент " + element + " отсутствует на странице!");

            touchAction()
                    .press(PointOption.point(centralXCord, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                    .moveTo(PointOption.point(centralXCord, endY))
                    .release()
                    .perform();

            scrollCount+=1;
        }
    }


    public void waitUntilDisplayed(Page obj, String element, int milliseconds){
        getFromMap(obj.getElements(), element).waitUntil(Condition.visible, milliseconds);
    }


    public SelenideElement findElementFromCollectionBySiblingText(String elementText, String siblingText) {
        ElementsCollection collection = findCollectionByText(elementText);

        return collection.findBy(Condition.text(siblingText));
    }


    private AndroidKey getAndroidKeyForDigit(char digit) {
        switch (digit) {
            case '0': return AndroidKey.DIGIT_0;
            case '1': return AndroidKey.DIGIT_1;
            case '2': return AndroidKey.DIGIT_2;
            case '3': return AndroidKey.DIGIT_3;
            case '4': return AndroidKey.DIGIT_4;
            case '5': return AndroidKey.DIGIT_5;
            case '6': return AndroidKey.DIGIT_6;
            case '7': return AndroidKey.DIGIT_7;
            case '8': return AndroidKey.DIGIT_8;
            case '9': return AndroidKey.DIGIT_9;
            default: throw new IllegalArgumentException("Invalid digit: " + digit);
        }
    }


    private SelenideElement getAppKeyForDigit(Page obj, String keyboard, char digit){
        int index = obj.getElementLocators().get(keyboard).indexOf('0');
        String oldId = obj.getElementLocators().get(keyboard);

        char[] newId = oldId.toCharArray();
        newId[index] = digit;

        return $(MobileBy.id(new String(newId)));
    }
}
