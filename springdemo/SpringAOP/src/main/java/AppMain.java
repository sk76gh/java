

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aspect.model.ShapeService;

public class AppMain {

	public static void main(String[] args){
	
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService = ctx.getBean("shapeService",ShapeService.class);
		System.out.println(shapeService.getTriangle().getName());
	}
}
