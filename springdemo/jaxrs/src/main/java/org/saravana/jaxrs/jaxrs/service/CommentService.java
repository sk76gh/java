package org.saravana.jaxrs.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.saravana.jaxrs.jaxrs.dao.CommentDao;
import org.saravana.jaxrs.jaxrs.database.DatabaseClass;
import org.saravana.jaxrs.jaxrs.exception.CommentNotFoundException;
import org.saravana.jaxrs.jaxrs.exception.MessageNotFoundException;
import org.saravana.jaxrs.jaxrs.model.Comment;
import org.saravana.jaxrs.jaxrs.model.Message;

public class CommentService {

	private Map<Long, Message> messages=DatabaseClass.getMessages();
	private CommentDao commentDao=new CommentDao();
	
	public List<Comment> getComments(long messageid){
		//Map<Long, Comment> comments=messages.get(messageid).getComments();
		Map<Long, Comment> comments=commentDao.getMessage(messageid).getComments();
		return  new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageid, long commentid){
		
		//Message message = messages.get(messageid);
		//Message message=commentDao.getMessage(messageid);
		
		//if (message==null){
		//	throw new WebApplicationException(response);
		//}
		//Map<Long, Comment> comments=message.getComments();
		//Comment comment = comments.get(commentid);
		
		Message message=this.commentDao.getMessage(messageid);
		
		if(message==null){
			throw new MessageNotFoundException("Message with id:"+messageid+" not found");
		}

		Comment comment =commentDao.getComment(messageid,commentid);
		if (comment==null){
			throw new CommentNotFoundException("Comment with id:"+commentid+" not found for message id:"+messageid);
		}
		return comment;
	}
	public Comment addComment(long messageid, Comment comment){
		
		Message message=messages.get(messageid);
		Map<Long,Comment> comments=message.getComments();
		comment.setId(comments.size()+1L);
		comments.put(comment.getId(), comment);
	
		return comment;
		
	}
	
	public Comment addCommentDao(long messageid, Comment comment){		
		Message message=this.commentDao.getMessage(messageid);
	
		if(message==null){
			throw new MessageNotFoundException("Messagse with id:"+messageid+" not found");
		}

		Map<Long,Comment>comments=message.getComments();
		//comment.setId(comments.size()+1L);
		comments.put(comments.size()+1L, comment);
		
		this.commentDao.addComment(comment);
		return comment;
	}
	public Comment updateComment(long messageid, Comment comment){

		//***************verify if comment id is valid*************************************

		Comment verifyComment=this.commentDao.getComment(messageid, comment.getId());
		
	
		
		if (verifyComment==null){
			throw new CommentNotFoundException("Comment with id:"+comment.getId()+" not found");
		}
		
		//*********************update the comment********************************************
		this.commentDao.updateComment(comment);
		return comment;
	}
	
	public Comment deleteComment(long messageid, long commentid){

		//***************verify if message id is valid*************************************
		Message messageVerify=this.commentDao.getMessage(messageid);
		
		
		if(messageVerify==null){
			throw new MessageNotFoundException("Messagse with id:"+messageid+" not found");
		}

		//***************verify if comment id is valid*************************************
		Comment verifyComment=this.commentDao.getComment(messageid, commentid);
		
		
		
		if (verifyComment==null){
			throw new CommentNotFoundException("Comment with id:"+commentid+" not found");
		}
		
		//******************remove the comment************************************************
		return this.commentDao.removeComment(messageid, commentid);
	}
	
}