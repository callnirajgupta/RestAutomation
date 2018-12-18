package com.rest.RestAssuredAutomation.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rest.RestAssuredAutomation.ExampleTest1;
import com.rest.RestAssuredAutomation.ExampleTest2;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	    ExampleTest1.class,
	    ExampleTest2.class,
})
public class TestRunner {
	
	

}
