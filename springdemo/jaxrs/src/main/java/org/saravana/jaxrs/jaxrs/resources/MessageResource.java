package org.saravana.jaxrs.jaxrs.resources;

import java.net.URI;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.saravana.jaxrs.jaxrs.model.Message;
import org.saravana.jaxrs.jaxrs.resources.beans.MessageFilterBean;
import org.saravana.jaxrs.jaxrs.service.LinkService;
import org.saravana.jaxrs.jaxrs.service.MessageService;

@Path("/messages")
@Consumes(value={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class MessageResource {

	private MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean, @Context UriInfo uriInfo){
		if (filterBean.getYear()>0){
			//return messageService.getAllMessagesForYear(filterBean.getYear());
			return messageService.getAllMessagesForYearDao(filterBean.getYear());
		}
		if (filterBean.getStart()>=0 && filterBean.getSize()>0){
			//return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
			return messageService.getAllMessagesPaginatedDao(filterBean.getStart(), filterBean.getSize());
		}
		//List<Message> messages=messageService.getAllMessages();
		List<Message> messages=messageService.getAllMessagesDao();
		for(Message message:messages){
			LinkService.updateMessageLinks(uriInfo, message);
		}
		return messages;
	}
	
	@GET
	@Path("/{messageid}")
	public Message getMessage(@PathParam("messageid") long id, @Context UriInfo uriInfo){
		//Message message= messageService.getMessage(id);
		Message message= messageService.getMessageDao(id);
		LinkService.updateMessageLinks(uriInfo, message);
		return message;
	}
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo){
		//Message newMessage=messageService.addMessage(message);
		Message newMessage=messageService.addMessageDao(message);
		URI uri=uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		LinkService.updateMessageLinks(uriInfo, newMessage);
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageid}")
	public Message updateMessage(@PathParam("messageid") long id,Message message,@Context UriInfo uriInfo){
		message.setId(id);
		//return messageService.updateMessage(message);
		LinkService.updateMessageLinks(uriInfo, message);
		return messageService.updateMessageDao(message);
	}
	
	@DELETE
	@Path("/{messageid}")
	public Message deleteMessage(@PathParam("messageid") long id){
		
		//return messageService.removeMessage(id);
		return messageService.removeMessageDao(id);
	}
	
	@Path("/{messageid}/comments")
	public CommentResource getComments(){
		return new CommentResource();
	}
}
