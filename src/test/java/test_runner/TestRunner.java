package test_runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { ".//Features/" }, // for one Feature file .//Features/Customers.feature
              
// .//Features/ it executes all feature files in the Features folder
// We Can also specify the multiple feature files
//{ ".//Features/Login.feature",  ".//Features/Customers.feature"} similar approach can be followed for the step definitions as well
glue = "step_definitions", 
              dryRun = false, 
              monochrome = true, // Removes unnecessary charataters
              plugin = {"pretty", "html:test-output/index" },
              tags = "@Smoke")
// dryRun = check steps definitions are there or not)
//plug-in = for html report


public class TestRunner {

}
