package org.saravana.rsclient;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saravana.jaxrs.jaxrs.model.Message;

public class GenericDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Client client=ClientBuilder.newClient();
		WebTarget baseTarget= client.target("http://localhost:8080/jaxrs/webapi/");
		WebTarget messageTarget=baseTarget.path("messages").queryParam("year", 2018);
	
		
		
		List<Message> response=messageTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Message>>() {});
	
		System.out.println(response);
	}

}
