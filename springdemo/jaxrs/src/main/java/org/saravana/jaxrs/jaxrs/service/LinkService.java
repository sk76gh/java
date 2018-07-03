package org.saravana.jaxrs.jaxrs.service;

import javax.ws.rs.core.UriInfo;

import org.saravana.jaxrs.jaxrs.model.Comment;
import org.saravana.jaxrs.jaxrs.model.Message;
import org.saravana.jaxrs.jaxrs.model.Profile;
import org.saravana.jaxrs.jaxrs.resources.CommentResource;
import org.saravana.jaxrs.jaxrs.resources.MessageResource;
import org.saravana.jaxrs.jaxrs.resources.ProfileResource;

public class LinkService {

	public static String getUriForComments(UriInfo uriInfo, Message message) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getComments")
				.path(CommentResource.class)
				.resolveTemplate("messageid", message.getId())
				.build()
				.toString();
		
		return uri;
	}

	public static String getUriForProfile(UriInfo uriInfo, Message message) {
		// TODO Auto-generated method stub
		String uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
		return uri;
	}

	public static String getUriForSelf(UriInfo uriInfo, Message message) {
		// TODO Auto-generated method stub
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(String.valueOf(message.getId()))
				.build()
				.toString();
		return uri;
	}

	public static String getUriForSelf(UriInfo uriInfo, Profile profile) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(profile.getFirstName())
				.build()
				.toString();
		return uri;
	}

	public static String getUriForComment(UriInfo uriInfo, long messageid, long commentid) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getComments")
				.path(CommentResource.class)
				.path(String.valueOf(commentid))
				.resolveTemplate("messageid", messageid)
				.build()
				.toString();
		
		return uri;
	}

	public static String getUriForCommentParent(UriInfo uriInfo, long messageid, long commentid) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				//.path(MessageResource.class,"getComments")
				//.path(CommentResource.class)
				.resolveTemplate("messageid", messageid)
				.build()
				.toString();
		
		return uri;
	}
	
	public static String getUriForCommentCollection(UriInfo uriInfo, long messageid, long commentid) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getComments")
				//.path(CommentResource.class)
				.resolveTemplate("messageid", messageid)
				.build()
				.toString();
		
		return uri;
	}

	public static String getUriForProfile(UriInfo uriInfo, String author) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(author)
				.build()
				.toString();
		return uri;
	}

 
	public static Message updateMessageLinks(UriInfo uriInfo, Message message){
		if(message.getLinks().size()==0){
			message.addLink(getUriForSelf(uriInfo, message), "self");
			message.addLink(getUriForProfile(uriInfo, message),"author");
			message.addLink(getUriForComments(uriInfo,message), "comments");
		}
		return message;
	}
	
	public static Comment updateCommentLinks(UriInfo uriInfo, long messageid, Comment comment){
		if(comment.getLinks().size()==0){
			comment.addLink(LinkService.getUriForComment(uriInfo, messageid, comment.getId()), "self");
			comment.addLink(LinkService.getUriForProfile(uriInfo, comment.getAuthor()), "author");
			comment.addLink(LinkService.getUriForCommentParent(uriInfo, messageid, comment.getId()), "parent");
			comment.addLink(LinkService.getUriForCommentCollection(uriInfo, messageid, comment.getId()), "comments");
		}
		return comment;
	}
}