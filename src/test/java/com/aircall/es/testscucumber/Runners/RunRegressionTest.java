package com.aircall.es.testscucumber.Runners;

import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 30,
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = true,
        rerunAttempts = 2,
        showTestOutput = true,
        reportTitle = "Regression report of Aircall phone",
        reportTargetDir = "Report/HtmlReport",
        plugin = { "extentreports" },
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/com/aircall/es/testscucumber",
                tags = "@phone",
                plugin = {"pretty",
                        "json:Report/HtmlReport/cucumber-report/cucumber.json",
                        "html:Report/HtmlReport/cucumber-report/cucumber.html"}
        ))
public class RunRegressionTest {
}
