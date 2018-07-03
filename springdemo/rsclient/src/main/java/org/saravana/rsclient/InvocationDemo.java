package org.saravana.rsclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saravana.jaxrs.jaxrs.model.Message;

public class InvocationDemo {
	
	public static void main(String[] args){
		Client client=ClientBuilder.newClient();
		WebTarget baseTarget= client.target("http://localhost:8080/jaxrs/webapi/");
		WebTarget messageTarget=baseTarget.path("messages");
		WebTarget singleMessageTarget=messageTarget.path("{messageId}");
		
		
		Invocation invocation=singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON).buildGet();
		
		
		
		Response response=invocation.invoke();
		Message message=response.readEntity(Message.class);	
		System.out.println(message.getMessage());
		
	}

}
