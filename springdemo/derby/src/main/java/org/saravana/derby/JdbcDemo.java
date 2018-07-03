package org.saravana.derby;

import org.saravana.derby.dao.HibernateDaoImpl;
import org.saravana.derby.dao.SimpleJdbcDaoImpl;
import org.saravana.derby.dao.SpringJDBC;
import org.saravana.derby.model.Circle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Circle circle=new JdbcDaoImpl().getCircle(1);
		//System.out.println(circle.getName());
		
		//using spring component
		ApplicationContext ctx= new ClassPathXmlApplicationContext("spring.xml");
		SpringJDBC dao=ctx.getBean("springJDBC",SpringJDBC.class);
		
		Circle circle=dao.getCircle(1);
		System.out.println(circle.getName());
		System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(1));
		System.out.println(dao.getAllCircles().size());
		
		/*
		int id=1;
		Circle circle1=new Circle(id,"second circle");
		dao.insertCricle(circle1);
		System.out.println("insert done");
		circle1=dao.getCircle(id);
		System.out.println(circle1.getName());
		System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(id));
		System.out.println(dao.getAllCircles().size());
		
		//dao.creatTriangleTable();
		/*
		
		//named query
		id=id+1;
		circle1=new Circle(id,"second circle");
		dao.insertCircl(circle1);
		System.out.println("insert done");
		circle=dao.getCircle(id);
		System.out.println(circle.getName());
		System.out.println(dao.getCircleCount());
		System.out.println(dao.getCircleName(id));
		System.out.println(dao.getAllCircles().size());
		
		*/
		
		//using DAO support classes
		SimpleJdbcDaoImpl simpleJdbcDao=ctx.getBean("simpleJdbcDaoImpl",SimpleJdbcDaoImpl.class);
		System.out.println("using SimpleJDBCDaoSupport "+simpleJdbcDao.getCircleCount());
		
		//using spring hibernate
		HibernateDaoImpl hibernateDao=ctx.getBean("hibernateDaoImpl",HibernateDaoImpl.class);
		System.out.println(hibernateDao);
		System.out.println("from Hibernate"+hibernateDao.getCircleCount());
	}

}
