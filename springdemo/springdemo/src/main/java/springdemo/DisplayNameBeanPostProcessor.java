package springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DisplayNameBeanPostProcessor implements BeanPostProcessor{

	public Object postProcessAfterInitialization(Object arg0, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("Bean Post Processing: AFter initialization method "+beanName);
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		
		System.out.println("Bean Post Processing: Before initialization method "+beanName);
		return arg0;
	}
	

}
