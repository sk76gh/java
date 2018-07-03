package org.saravana.jaxrs.jaxrs.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.saravana.jaxrs.jaxrs.dao.MessageDao;
import org.saravana.jaxrs.jaxrs.database.DatabaseClass;
import org.saravana.jaxrs.jaxrs.exception.DataNotFoundException;
import org.saravana.jaxrs.jaxrs.exception.MessageNotFoundException;
import org.saravana.jaxrs.jaxrs.model.Message;


public class MessageService {

	private Map<Long, Message> messages= DatabaseClass.getMessages();
	private MessageDao messageDao=new MessageDao();
	
	public MessageService(){
		messages.put(1L, new Message(1,"first message","saravana"));
		messages.put(2L, new Message(2,"second message","saravana"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesDao(){
		return messageDao.getAllMessages();
	}
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageForYear=new ArrayList<>();
		Calendar cal= Calendar.getInstance();
		for(Message message:messages.values()){
			cal.setTime(message.getDate());
			if(cal.get(Calendar.YEAR)==year){
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}
	
	public List<Message> getAllMessagesForYearDao(int year){
		List<Message> messagesForYear=messageDao.getAllMessagesForYear(year);
		
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if((start+size)>list.size()) {
			return list;
		}
		return list.subList(start, start+size);
	}
	
	public List<Message> getAllMessagesPaginatedDao(int start, int size){
		List<Message> messages=messageDao.getAllMessagesPaginatedDao(start, size);
		
		return messages;
	}
		
	
	public Message getMessage(long id){
		Message message=messages.get(id);
		if (message==null){
			throw new DataNotFoundException("Message with id "+id+" is not found");
		}
		return message;
	}
	
	public Message getMessageDao(long id){
		Message message=messageDao.getMessage(id);
		if (message==null){
			throw new DataNotFoundException("Message with id "+id+" is not found");
		}
		return message;
	}
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message addMessageDao(Message message){
		
		messageDao.addMessage(message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if (message.getId()==0){
			return null;
		}
		messages.put(message.getId(), message);
		//System.out.println(messages.toString());
		return message; 
	}
	
	public Message updateMessageDao(Message message){
		if (message.getId()==0){
			throw new MessageNotFoundException("Messagse id:"+message.getId()+" not valid ");
		}
		
		Message messageVerify=messageDao.getMessage(message.getId());
		
		
		if(messageVerify==null){
			throw new MessageNotFoundException("Messagse with id:"+message.getId()+" not found");
		}
		
		messageDao.update(message);
		
		return message;
	}
	
	
	public Message removeMessage(long id){
		
		Message messageVerify=messageDao.getMessage(id);
		if(messageVerify==null){
			throw new MessageNotFoundException("Messagse with id:"+id+" not found");
		}
		
		return messages.remove(id);
	}
	public Message removeMessageDao(long id){
		
		//return messages.remove(id);
		return messageDao.remove(id);
	}	
	public MessageDao getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
