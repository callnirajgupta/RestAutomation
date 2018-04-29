package com.restful.commonstep;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import com.restful.util.RestfulUtil;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GlobalStepDefinition {
	
	private static final Logger LOGGER = LogManager.getLogger(GlobalStepDefinition.class);
	private static HttpResponse httpResponse;
	private static String city;

	@Before()
	public void Setup(Scenario scenario) {
		LOGGER.info("Inside Setup method");
		

	}
	@Given("^the Rest API City temperature is up an runing$")
	public void the_Rest_API_City_temperature_is_up_an_runing() throws Throwable {
		
		//String restURL_JSON = "http://restapi.demoqa.com/utilities/weather/city/Hyderabad";
				//String restURL_JSON = "http://restapi.demoqa.com/utilities/weather/city/Bangalore";
				
				//HttpUriRequest request = new HttpGet(restURL_JSON);
				
				//So you can add header like cookies,ID...input
				//request.addHeader("cookies", "fgfdgfd");
				
				// send response or execute query
				//HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
				 
				//System.out.println("Verify the HTTP status code "+httpResponse.getStatusLine().getStatusCode());
				//  Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
				/*System.out.println("Verify the response content type "+ContentType.getOrDefault(httpResponse.getEntity()).getMimeType());
				  
				// Convert the response to a String format
				String result = EntityUtils.toString(httpResponse.getEntity());
				 System.out.println("print result "+result);
				// Convert the result as a String to a JSON object
				JSONObject jo = new JSONObject(result);
				 System.out.println("Verify content:"+jo.getString("City"));*/
	   
	}

	@When("^user call the service with argument city as \"([^\"]*)\"$")
	public void user_call_the_service_with_argument_city_as(String cityTemp) throws Throwable {
		httpResponse=null;
		city=cityTemp;
		String URL=RestfulUtil.getConfigProperties().getProperty("BaseUrlCityTemp")+city;
		
		HttpUriRequest request = new HttpGet(URL);
		
		//So you can add header like cookies,ID...input
		//request.addHeader("cookies", "fgfdgfd");
		
		// send response or execute query
        httpResponse = HttpClientBuilder.create().build().execute(request);
	   
		
	}

	@Then("^verify that response contain statuscode as \"([^\"]*)\"$")
	public void verify_that_response_contain_statuscode_as(String statusCode) throws Throwable {
		 Assert.assertEquals(Integer.parseInt(statusCode),httpResponse.getStatusLine().getStatusCode());
	    
	}

	@Then("^verify that service response contain the following attribute$")
	public void verify_that_service_response_contain_the_following_attribute(DataTable table) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		List<List<String>>  responseAttributeList= table.raw();
		String result = EntityUtils.toString(httpResponse.getEntity());
		 System.out.println("print result "+result);
		// Convert the result as a String to a JSON object
		JSONObject jo = new JSONObject(result);//this will be determined the the response structure...if [] then as in "mapper.readTree" line 70-message.java...infact if mapper is used it can read both [] and {}...
		 System.out.println("Verify content:"+jo.getString("City"));
		 Assert.assertEquals("The Attribute not present", city, jo.get("City"));
		 for(int i=0;i<responseAttributeList.size();i++){ 
		Assert.assertNotNull("the values doesnot display", responseAttributeList.get(i).get(0));
		 }
	   
	}

	
	public static HttpResponse getHttpResponse() {
		return httpResponse;
	}
	public static void setHttpResponse(HttpResponse httpResponse) {
		GlobalStepDefinition.httpResponse = httpResponse;
	}
	@After()
	public void TearDown(Scenario scenario) {
		LOGGER.info("Inside Teardown method");

		if (scenario.isFailed()) {
			
			

		}

		

	}
}
