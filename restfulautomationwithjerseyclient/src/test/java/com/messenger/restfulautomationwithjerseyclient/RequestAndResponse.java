package com.messenger.restfulautomationwithjerseyclient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;




public class RequestAndResponse {
	
	String request = "{\"author\":\"koushik\",\"created\":\"2018-04-22T11:15:38.91-07:00\",\"message\":\"Hello Niraj\"}";// if request argument is fixed, then can create the request string like this..instead of JSON object...
  //String response={"author":"koushik","created":"2018-04-22T11:15:38.91-07:00","id":3,"links":[],"message":"Hello Niraj"}
	
	 public static void main(String[] args) throws JsonProcessingException, IOException {
		    
		 ClientConfig config = new ClientConfig();
	    Client client = ClientBuilder.newClient(config);
	    WebTarget service = client.target(getBaseURI());
		 Response  serviceResponse=service.request(MediaType.APPLICATION_JSON).get();//GET Request...no argument passed...
		
		 
		 System.out.println("Print status "+serviceResponse.getStatus());
		 String response=serviceResponse.readEntity(String.class);
		 
		System.out.println("Print response"+response);
	//List<HashMap<String,String>>	responsemap= new Gson().fromJson(response, new TypeToken<List<HashMap<String, String>>>(){}.getType());
	ObjectMapper mapper= new ObjectMapper();
	JsonNode root=mapper.readTree(response);
		
		System.out.println("printvalue"+root.get(0).get("author"));

	 }
		  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8081/messenger/webapi/messages").build();
		  }
}
