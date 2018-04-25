package com.restful.messenger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.restful.commonstep.GlobalStepDefinition;
import com.restful.util.RestfulUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Message {
	private static final Logger LOGGER = LogManager.getLogger(Message.class);
	private static HttpResponse httpResponse;
	ObjectMapper mapper = new ObjectMapper().setVisibility(JsonMethod.FIELD, Visibility.ANY);
	@Given("^the Rest API messenger is up an runing$")
	public void the_Rest_API_messenger_is_up_an_runing() throws Throwable {
	   
	}

	@When("^user call \"([^\"]*)\"service with method \"([^\"]*)\" and argument \"([^\"]*)\"$")
	public void user_call_service_with_method_and_argument(String service, String method, String argument) throws Throwable {
		httpResponse=null;
		
		String URL=RestfulUtil.getConfigProperties().getProperty("BaseUrlMessage")+service;
	
		HttpUriRequest request = new HttpGet(URL);
		
		//So you can add header like cookies,ID...input
		//request.addHeader("cookies", "fgfdgfd");
		
		// send response or execute query
        httpResponse = HttpClientBuilder.create().build().execute(request);
        GlobalStepDefinition.setHttpResponse(httpResponse);
	}

	@Then("^verify that service response contain all messages$")
	public void verify_that_service_response_containall_messages() throws Throwable {
	/*	ResponseHandler<String> responsehandler=new BasicResponseHandler();
		String jsonResponse=responsehandler.handleResponse(httpResponse);
		
		System.out.println("Json Response "+jsonResponse);
		JsonNode root=mapper.readTree(jsonResponse);*/
		
		String result = EntityUtils.toString(httpResponse.getEntity());
		System.out.println("Json Response "+result);
		JsonNode root=mapper.readTree(result);
		System.out.println("print id value "+root.get(0).get("id"));
		//Read this website
		//http://tutorials.jenkov.com/java-json/jackson-objectmapper.html
		//http://www.baeldung.com/jackson-object-mapper-tutorial
	}
	
	
	@When("^user call \"([^\"]*)\"service with method \"([^\"]*)\" and new massage$")
	public void user_call_service_with_method_and_new_massage(String service, String method) throws Throwable {
httpResponse=null;
		
		String URL=RestfulUtil.getConfigProperties().getProperty("BaseUrlMessage")+service;
	
		HttpPost request = new HttpPost(URL);
		request.addHeader("Content-type", "application/json");
		
		HashMap<String,String> requestMap= new HashMap<>();
		requestMap.put("author", "koushik");
		requestMap.put("created", "2018-04-24T22:12:10.926-07:00");
		requestMap.put("message", "Hello  niraj gupta");
		
		String requestStr=mapper.writeValueAsString(requestMap);
		StringEntity entity= new StringEntity(requestStr);
		
		
		request.setEntity(entity);
		//So you can add header like cookies,ID...input
		//request.addHeader("cookies", "fgfdgfd");
		
		// send response or execute query
	
        httpResponse = HttpClientBuilder.create().build().execute(request);
        GlobalStepDefinition.setHttpResponse(httpResponse);
	}

	@Then("^verify that service response contain newly added message$")
	public void verify_that_service_response_contain_newly_added_message() throws Throwable {
		
		String result = EntityUtils.toString(httpResponse.getEntity());
		System.out.println("Json Response "+result);
		JsonNode root=mapper.readTree(result);
		System.out.println("print id value "+root.get("id"));
	  
	}

}
