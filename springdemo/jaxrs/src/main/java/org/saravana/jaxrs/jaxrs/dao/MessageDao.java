package org.saravana.jaxrs.jaxrs.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.saravana.jaxrs.jaxrs.model.Message;

public class MessageDao {

	private Session session;


	public MessageDao(Session session) {
		this.session = session;
	}

	public MessageDao() {
		
		this.session=HibernateSessionFactorySingleton.getHibernateSession();
		
		//System.out.println("messagedao :"+this);
		//System.out.println("messagedao session :"+session);
		
	}
	
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}

	public List<Message> getAllMessages() {
		// TODO Auto-generated method stub
		Query query = session.createQuery("from Message");
		List<Message> messages=(List<Message>)query.list();
		//session.close();
		return messages;
	}

	public Message getMessage(long id) {
		// TODO Auto-generated method stub
		
		Message message=(Message)session.get(Message.class, id);
		//System.out.println("message id in getMessage dao "+message);
		//session.close();
		return message;
	}

	public void addMessage(Message message) {
		// TODO Auto-generated method stub
		
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
		
		//session.close();
	}

	public Message remove(long id) {
		// TODO Auto-generated method stub
		Message message=(Message)session.get(Message.class, id);
		session.beginTransaction();
		session.remove(message);
		session.getTransaction().commit();
		//session.close();
		return message;
	}

	public void update(Message message) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.update(message);
		session.getTransaction().commit();
		//session.close();	
	}

	public List<Message> getAllMessagesForYear(int year) {
		// TODO Auto-generated method stub
		Criteria criteria=session.createCriteria(Message.class);
		Calendar calendar=Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, year-1);
		Date fromDate=calendar.getTime();
		//System.out.println(fromDate.toString());
		calendar.set(Calendar.YEAR,year);
		Date toDate=calendar.getTime();
		//System.out.println(toDate.toString());
		criteria.add(Restrictions.between("date",fromDate,toDate));
		List<Message> filteredMessages=criteria.list();
		 
		return filteredMessages;
	}
	
	public List<Message> getAllMessagesPaginatedDao(int start, int size){
		Criteria criteria=session.createCriteria(Message.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(size);
		List<Message> filteredMessages=criteria.list();
		
		return filteredMessages;
	}

}


