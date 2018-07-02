package io.javabrains.courseapidata.course;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.courseapidata.topic.Topic;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@RequestMapping("topics/{topicname}/courses")
	public List<Course> getAllTopics(@PathVariable("topicname") String id){
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping("topics/{topicname}/courses/{coursename}")
	public Optional<Course> getCourse(@PathVariable("coursename") String id){
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="topics/{topicname}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable("topicname") String topicId){
		course.setTopic(new Topic(topicId,"",""));
		courseService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="topics/{topicname}/courses/{coursename}")
	public void updateCourse(@RequestBody Course course, @PathVariable("topicname") String topicId){
		course.setTopic(new Topic(topicId,"",""));
		courseService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="topics/{topicname}/courses/{coursename}")
	public void deleteCourse(@PathVariable("coursename") String id){
		courseService.deleteCourse(id);
	}
}
