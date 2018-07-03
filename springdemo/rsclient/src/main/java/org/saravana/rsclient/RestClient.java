package org.saravana.rsclient;

import java.util.Calendar;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saravana.jaxrs.jaxrs.model.Comment;
import org.saravana.jaxrs.jaxrs.model.Message;


public class RestClient{
	
	public static void main(String[] args){
		
		Client client=ClientBuilder.newClient();
		WebTarget baseTarget= client.target("http://localhost:8080/jaxrs/webapi/");
		WebTarget messageTarget=baseTarget.path("messages");
		WebTarget singleMessageTarget=messageTarget.path("{messageId}");
		
		
		Response response=singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON).get();
		Message message=response.readEntity(Message.class);	
		System.out.println(message.getMessage());
		System.out.println(response.getHeaderString("X-Powered-By"));
		/*
		Message newMessage=new Message(50,"second new message from jax-rs client","saravana");
		Response postResponse=messageTarget.request().post(Entity.json(newMessage));
		if (postResponse.getStatus()!=201){
			System.out.println("resource not created");
		}
	
		System.out.println(postResponse.readEntity(Message.class).getMessage());
		System.out.println(response.getHeaderString("X-Powered-By"));
		*/
		int messageId=1;
		Comment comment =new Comment(messageId,"first comment to first message",Calendar.getInstance().getTime(),"saravana");
		WebTarget commentTarget=singleMessageTarget.path("comments");
		Response postResponse=commentTarget.resolveTemplate("messageId", messageId).request().post(Entity.json(comment));
	
	}
}