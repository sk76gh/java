package org.saravana.hibernate.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.saravana.hibernate.hib.dto.two.EntityChild;
import org.saravana.hibernate.hib.dto.two.EntityOne;

public class HibEntityPersistence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		EntityChild ec=new EntityChild();
		ec.setChildName("child name");
		EntityChild ec2=new EntityChild();
		ec2.setChildName("child2 name");
		
		EntityOne e1=new EntityOne();
		e1.setName("E1 Name");
		
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(e1);
		session.getTransaction().commit();
		session.close();
		//sessionFactory.close();

		

		System.out.println("starting new session");
		//sessionFactory=new Configuration().configure().buildSessionFactory();
		session=sessionFactory.openSession();
		
		session.beginTransaction();
		EntityOne newE1=(EntityOne)session.get(EntityOne.class, 1);
		System.out.println(newE1);
		newE1.getChild().add(ec);
		
		session.save(ec);
		
		session.getTransaction().commit();
		session.close();
		
		
		session=sessionFactory.openSession();
		
		session.beginTransaction();
		EntityOne newE2=(EntityOne)session.get(EntityOne.class, 1);
		System.out.println(newE2);
		
		newE2.getChild().add(ec2);
		
		session.save(ec2);
		
		session.getTransaction().commit();
		session.close();
		
       //deleting child object
		session=sessionFactory.openSession();
		
		session.beginTransaction();
		
		session.delete(ec2);
		
		session.getTransaction().commit();
		session.close();
		
		
		sessionFactory.close();
		
	}

}
