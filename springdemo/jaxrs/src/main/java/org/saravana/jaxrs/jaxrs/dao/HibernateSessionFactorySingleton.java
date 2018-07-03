package org.saravana.jaxrs.jaxrs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactorySingleton {

	public static SessionFactory factory;
	public static Session session;
	private HibernateSessionFactorySingleton() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static synchronized Session getHibernateSession(){
		
		if(factory==null){
			factory=new Configuration().configure().buildSessionFactory();
			session=factory.openSession();
		}
		return session;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		session.close();
		factory.close();
		super.finalize();
	
	}
}
