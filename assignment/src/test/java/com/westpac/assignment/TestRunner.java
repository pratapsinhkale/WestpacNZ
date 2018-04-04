package com.westpac.assignment;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features", glue = { "com.westpac.assignment.stepslibrary" }, tags = { "@Req1,@Req2,@Req4" })
public class TestRunner {

}
	