package com.restful.dto;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestDTO {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		Student abc= new Student();
		abc.setCollege("bit");
		abc.setId("12345");
		
		System.out.println( abc);
		
		ObjectMapper mapper = new ObjectMapper();
		
		 String jsonStr = mapper.writeValueAsString(abc); 
		  
         // Displaying JSON String 
         System.out.println("print json"+jsonStr); 
         
         Student student = mapper.readValue(jsonStr, Student.class);
         
         System.out.println("print the id of stucent"+student.getId());
         
         
         abc.setCollege("bit");
 		abc.setId("12345");
 		abc.setName("Niraj");
 		
 		String jsonStr1 = mapper.writeValueAsString(abc); 
		  
        // Displaying JSON String 
        System.out.println("print json2"+jsonStr1); 
         
         
		 

	}

}
