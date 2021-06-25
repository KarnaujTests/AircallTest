package com.aircall.es.testscucumber.Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:target/cucumber"},
        glue = "com.aircall.es.testscucumber",
        features = "src/test/resources/com/aircall/es/testscucumber",
        tags = "@phone")

public class RunDebug {
}
