package com.WebService.WebServiceTesting;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Assert;

public class Test {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		//String restURL_JSON = "http://restapi.demoqa.com/utilities/weather/city/Hyderabad";
		String restURL_JSON = "http://restapi.demoqa.com/utilities/weather/city/Bangalore";
		
		HttpUriRequest request = new HttpGet(restURL_JSON);
		
		//So you can add header like cookies,ID...input
		//request.addHeader("cookies", "fgfdgfd");
		
		// send response or execute query
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		 
		System.out.println("Verify the HTTP status code "+httpResponse.getStatusLine().getStatusCode());
		  Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
		System.out.println("Verify the response content type "+ContentType.getOrDefault(httpResponse.getEntity()).getMimeType());
		  
		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());
		 System.out.println("print result "+result);
		// Convert the result as a String to a JSON object
		JSONObject jo = new JSONObject(result);
		 System.out.println("Verify content:"+jo.getString("City"));
		// Verify content
		//Assert.assertEquals(expectedValue, jo.getString(element));

	}

}
