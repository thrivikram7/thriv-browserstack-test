package com.equalExperts.runner;

import com.equalExperts.webutility.BaseDriverPage;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}, features = {
        "./src/test/resources/com/equalExperts/featurefiles"}, glue = {
        "com/equalExperts/teststeps/"})
public class RunnerTest {

    @AfterClass
    public static void tearDown() {
        try {
            BaseDriverPage.getInstance().sharedDriver().quit();
        } catch (Throwable t) {}
    }
}