package springdemo;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//BeanFactory factory=new XmlBeanFactory(new FileSystemResource("spring.xml"));
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		context.registerShutdownHook();
		Shape triangle = (Triangle) context.getBean("triangle1");
		triangle.draw();
		
		Shape circle = (Circle) context.getBean("circle");
		circle.draw();
		
		
	}

}
