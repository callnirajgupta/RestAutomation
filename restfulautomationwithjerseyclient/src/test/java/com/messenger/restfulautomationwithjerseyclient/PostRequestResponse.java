package com.messenger.restfulautomationwithjerseyclient;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class PostRequestResponse {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		 ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget service = client.target(getBaseURI());
		   
		    HashMap<String,String> requestMap= new HashMap<String,String>();
			requestMap.put("author", "Niraj");
			requestMap.put("id", "3");
			requestMap.put("created", "2018-04-24T22:12:10.926-07:00");
			requestMap.put("message", "Hello  niraj gupta");
			
			
			/*ObjectMapper mapper= new ObjectMapper();
			String str=mapper.writeValueAsString(requestMap);
			System.out.println("print input string"+str);
			*/ // this anthor way to convert java object to  json string
						
			Gson gson = new Gson();
			String json = gson.toJson(requestMap);
			System.out.println("print json"+json);
			Response  serviceResponse=service.request(MediaType.APPLICATION_JSON).post(Entity.entity(json, MediaType.APPLICATION_JSON));
			
			 System.out.println("Print status "+serviceResponse.getStatus());
			 String response=serviceResponse.readEntity(String.class);
			 
			System.out.println("Print response"+response);
		
		ObjectMapper mapper1= new ObjectMapper();
		JsonNode root=mapper1.readTree(response);
			
System.out.println("printvalue"+root.get("author"));

		 }
			  private static URI getBaseURI() {
			    return UriBuilder.fromUri("http://localhost:8081/messenger/webapi/messages").build();
			  }
}
