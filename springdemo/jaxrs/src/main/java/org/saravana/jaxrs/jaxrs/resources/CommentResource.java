package org.saravana.jaxrs.jaxrs.resources;

import java.util.List;

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
import javax.ws.rs.core.UriInfo;

import org.saravana.jaxrs.jaxrs.model.Comment;
import org.saravana.jaxrs.jaxrs.service.CommentService;
import org.saravana.jaxrs.jaxrs.service.LinkService;

@Path("/")
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService=new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageid") long messageid, @Context UriInfo uriInfo){
		List<Comment> comments=commentService.getComments(messageid);
		
		for (Comment comment:comments){
			LinkService.updateCommentLinks(uriInfo, messageid, comment);
		}
		
		return comments;
	}
	
	@GET
	@Path("/{commentid}")
	public Comment getComment(@PathParam("commentid") long commentid, @PathParam("messageid") long messageid,@Context UriInfo uriInfo){
		Comment comment =commentService.getComment(messageid, commentid);
		LinkService.updateCommentLinks(uriInfo, messageid, comment);
		return comment;
	}
	
	@POST
	public Comment addComment(@PathParam("messageid") long messageid, Comment comment, @Context UriInfo uriInfo){
		//Comment newComment = commentService.addComment(messageid, comment);
		Comment newComment = commentService.addCommentDao(messageid, comment);
		LinkService.updateCommentLinks(uriInfo, messageid, newComment);
		return newComment;
	}
	
	@PUT
	@Path("/{commentid}")
	public Comment updateComment(@PathParam("messageid") long messageid, @PathParam("commentid") long id, 
			Comment comment,@Context UriInfo uriInfo){
	    comment.setId(id);
	    Comment newComment=commentService.updateComment(messageid, comment);
	    LinkService.updateCommentLinks(uriInfo, messageid, newComment);
		
		return newComment;
	}
	
	@DELETE
	@Path("/{commentid}")
	public Comment deleteComment(@PathParam("commentid") long commentid, @PathParam("messageid") long messageid){
		
		return commentService.deleteComment(messageid, commentid);
	}
	
}
