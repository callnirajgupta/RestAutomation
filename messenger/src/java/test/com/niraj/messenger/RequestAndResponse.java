package com.niraj.messenger;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.niraj.messenger_kaushik.model.Message;
import com.niraj.messenger_kaushik.service.MessageService;

public class RequestAndResponse {
	
	String request = "{\"author\":\"koushik\",\"created\":\"2018-04-22T11:15:38.91-07:00\",\"message\":\"Hello Niraj\"}";
  //String response={"author":"koushik","created":"2018-04-22T11:15:38.91-07:00","id":3,"links":[],"message":"Hello Niraj"}
	
	 public static void main(String[] args) {
		    
		 ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget service = client.target(getBaseURI());
		 Response  serviceResponse=service.request(MediaType.APPLICATION_JSON).get();
		    
		 System.out.println("Print status "+serviceResponse.getStatus());
		// List<Message> list=(List<Message>)serviceResponse.getEntity();
		 
		 //serviceResponse.getEntity();
		 //System.out.println("Print Author"+list.get(0).getAuthor());

	 }
		  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://localhost:8080/messenger/webapi/messages").build();
		  }
}
