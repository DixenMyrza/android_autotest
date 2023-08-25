package com.android_testing;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.android_testing.steps",
        plugin = {"pretty"},
        monochrome = true
//        tags = {"@Accounts"}
)
public class CucumberRunTest {

    @AfterClass
    public static void afterClass(){

    }
}