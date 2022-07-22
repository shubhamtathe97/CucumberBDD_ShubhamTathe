package pom.cucumberBDD.framework.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "classpath:features",                 //to tell cucumber where is ur feature file
		glue     ="pom.cucumberBDD.framework.stepdefs",  // to tell cucumber where is ur step def code
		tags     ="@endtoend",                           // to tell which tagged feature file to execute
		plugin   = {"pretty",                            // to generate reports
				"html:target/html/htmlreport.html",
				"json:target/json/jsonreport.json"
		},
		monochrome=true,
		publish   =true,
		dryRun    =false                                // to tell whether to test run(true) or actual run(false)
		)


public class TestRunner {

}