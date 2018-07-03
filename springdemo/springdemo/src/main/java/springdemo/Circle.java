package springdemo;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Shape, ApplicationEventPublisherAware{


	private Point center;
	private ApplicationEventPublisher publisher;
	@Autowired
	private MessageSource messageSource;
	
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println(this.messageSource.getMessage("drawing.circle", null, "default circle", null));
	System.out.println(this.messageSource.getMessage("circle.point", new Object[]{center.getX(),center.getY()}, "default circle", null));
	System.out.println(this.messageSource.getMessage("greeting", null, "default circle", null));
	DrawEvent drawEvent=new DrawEvent(this);
	publisher.publishEvent(drawEvent);
	}

	public Point getCenter() {
		return center;
	}

	@Resource(name="pointC")
	public void setCenter(Point center) {
		this.center = center;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher appEventPublisher) {
		// TODO Auto-generated method stub
		this.publisher=appEventPublisher;
	}

}
