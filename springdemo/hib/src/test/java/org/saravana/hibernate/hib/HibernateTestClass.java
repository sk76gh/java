package org.saravana.hibernate.hib;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.saravana.hibernate.hib.dto.Address;
import org.saravana.hibernate.hib.dto.FourWheeler;
import org.saravana.hibernate.hib.dto.Pojo;
import org.saravana.hibernate.hib.dto.TwoWheeler;
import org.saravana.hibernate.hib.dto.UserDetails;
import org.saravana.hibernate.hib.dto.UserVehicle;

public class HibernateTestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDetails user= new UserDetails();
		//user.setUserId(3);
		user.setUserName("Kiran");
		
		Address addr = new Address();
		addr.setCity("bangalore");
		addr.setState("kar");
		addr.setStreet("itpl road");
		addr.setPincode("560048");
		
		Address addr2 = new Address();
		addr2.setCity("bangalore");
		addr2.setState("kar");
		addr2.setStreet("itpl road");
		addr2.setPincode("560048");
		user.getAddressList().add(addr);
		user.getAddressList().add(addr2);
		user.setJoinedDate(new Date());
		user.setDescription("description of user");
		
		
	    UserVehicle vehicle= new UserVehicle();
	    vehicle.setVehicleName("Cycle");
		
	    user.setVehicle(vehicle);
	    
	    
	    UserVehicle car1= new UserVehicle();
	    car1.setVehicleName("car1");
		
	    UserVehicle car2= new UserVehicle();
	    car2.setVehicleName("car2");
		
	    ArrayList<UserVehicle> vehicles=(ArrayList<UserVehicle>) user.getVehicles();
	    vehicles.add(car1);
	    vehicles.add(car2);
	    user.setVehicles(vehicles);
	    
	    //Vehicle inheritance
	    
	    UserVehicle vehicle1=new UserVehicle();
	    vehicle1.setVehicleName("Mercedes");
	    vehicle1.setUser(user);
	    
	    TwoWheeler bike=new TwoWheeler();
	    bike.setVehicleName("Bajaj");
	    bike.setSteeringHandle("Bike steering handle");
	    bike.setUser(user);
	    
	    FourWheeler car=new FourWheeler();
	    car.setVehicleName("Porche");
	    car.setSteeringWheel("steering wheel");
	    car.setUser(user);
	    
	    //**************************Pojo********************************
	 
	    
		System.out.println("************creating session factory");
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		System.out.println("************creating transaction");
		
		session.beginTransaction();
		session.persist(user);
		session.save(vehicle);
		session.save(car1);
		session.save(car2);
		session.save(vehicle1);//inheritance
		session.save(car);
		session.save(bike);
		for(int i =0;i<10;i++){
		   Pojo curdPojo=new Pojo();
		   curdPojo.setFieldA("my name is saravana "+i);
           session.save(curdPojo);	
		   }
		//**************************************************************

		session.getTransaction().commit();
		session.close();
		System.out.println("************committing transaction");
		
		user=null;
		session=sessionFactory.openSession();
		session.beginTransaction();
		
		
		//first level cache (session cache )demo - watch for just one sql call
		System.out.println("1st level cache: first get");
		user = (UserDetails)session.get(UserDetails.class,1);
		
		UserDetails user2=new UserDetails();
		System.out.println("1st level cache: second get");
		user = (UserDetails)session.get(UserDetails.class,1);
		System.out.println("************************************************");
		//***********************************************************
		
		System.out.println(user.toString());
		System.out.println("****************lazy loading");
	    System.out.println(user.getAddressList().size());
	    System.out.println(user.getVehicles().size());
	    
	    Pojo pojoObj=(Pojo)session.get(Pojo.class, 10);
	    System.out.println(pojoObj.getFieldA());
	    
	    Pojo pojoObj1=(Pojo)session.get(Pojo.class, 11);
	    System.out.println(pojoObj.getFieldA());
	    pojoObj1.setFieldA("updated field");
	    session.update(pojoObj1);
	    
	    session.delete(pojoObj);
	    
	    //Query query=session.createQuery("select fieldA from Pojo");
	    //query.setFirstResult(5);
	    //query.setMaxResults(3);
	  //List<String> usersList=(List<String>)query.list();
	    //criteria api projections
	    
	    Criteria criteria1=session.createCriteria(Pojo.class)
	    		.setProjection(Projections.property("fieldA"))
	    		.addOrder(Order.desc("id"));
	    
	    //named query
	    //Query query1=session.getNamedQuery("Pojo.byId");
	    //query1.setInteger("id", 12);
	    //**************************
	    
	    //List<String> usersList=(List<String>)query1.list();
	    List<String> usersList=(List<String>)criteria1.list();
	    //named native query
	    Query query2=session.getNamedQuery("Pojo.byName");
	    query2.setParameter(1,"updated field" );
	    
	    List<Pojo> outPojo=(List<Pojo>) query2.list();
	    
	    //Criteria api
	    //Criteria criteria=session.createCriteria(Pojo.class);
	    //criteria.add(Restrictions.like("fieldA", "%saravana%"))
	    //		.add(Restrictions.or(Restrictions.between("id", 11,12),Restrictions.between("id", 14, 16)));
	    
	    //Query by Example
	    Pojo pojo=new Pojo();
	    pojo.setFieldA("updated field");
	    
	    
	    Example example=Example.create(pojo).ignoreCase();
	    
	    Criteria criteria=session.createCriteria(Pojo.class)
	    						.add(example);
	    List<Pojo> outPojo2=(List<Pojo>) criteria.list();
	    
	    session.getTransaction().commit();
	    //verify if pojo 10 is available
	    //pojoObj=null;
	    //pojoObj=(Pojo)session.get(Pojo.class, 10);
	    //System.out.println(pojoObj.getFieldA());
	    session.close();
	    
	    
		for(String p:usersList)
			System.out.println(p);
	
		for(Pojo op:outPojo)
			System.out.println(op.getId() +" "+op.getFieldA());
		
		for(Pojo op2:outPojo2)
			System.out.println(op2.getId() +" "+op2.getFieldA());
		
		
		System.out.println("************creating second session");
		Session session1=sessionFactory.openSession();
		session1.beginTransaction();
		System.out.println("2nd level cache: second get withe new session");
		user = (UserDetails)session1.get(UserDetails.class,1);
		System.out.println("************************************************");
		//named native query
	    query2=session1.getNamedQuery("Pojo.byName");
	    query2.setParameter(1,"updated field" );
	    
	    outPojo=(List<Pojo>) query2.list();
		//***********************************************************
		session1.getTransaction().commit();
		session1.close();
	}
	}