package org.saravana.derby.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int getCircleCount(){
		String hql="select count(*) from Circle";
		System.out.println("inside hibernate dao "+hql);
		Query<Integer> query=sessionFactory.openSession().createQuery(hql);
		return query.uniqueResult();
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
