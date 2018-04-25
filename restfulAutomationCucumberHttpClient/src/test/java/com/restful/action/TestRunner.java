
package com.restful.action;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.restful.util.RestfulUtil;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/featurefile/", tags = {
		"@abc" }, plugin = { "pretty", "html:target/cucumber-html-report",
				"json:target/cucumber.json", "junit:target/cucumber.xml",
				"rerun:target/rerun.txt" }, glue = { "com.restful" })

public class TestRunner {

	@BeforeClass
	public static void beforeClass() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		System.setProperty("currentdate", dateFormat.format(new Date()));
		RestfulUtil.cleanFolder("Logs");// delete all logs files
		

	}

	@AfterClass
	public static void afterClass() {
		
		

	}

}
