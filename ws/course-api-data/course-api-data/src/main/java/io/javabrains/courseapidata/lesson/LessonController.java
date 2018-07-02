package io.javabrains.courseapidata.lesson;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.courseapidata.course.Course;
import io.javabrains.courseapidata.topic.Topic;

@RestController
public class LessonController {

	@Autowired
	private LessonService lessonService;
	
	@RequestMapping("topics/{topicname}/courses/{coursename}/lessons")
	public List<Lesson> getAllLessons(@PathVariable("coursename") String id){
		return lessonService.getAllLessons(id);
	}
	
	@RequestMapping("topics/{topicname}/courses/{coursename}/lesson/{lessonname}")
	public Optional<Lesson> getLesson(@PathVariable("lessonname") String id){
		return lessonService.getLesson(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="topics/{topicname}/courses/{coursename}/lessons")
	public void addLesson(@RequestBody Lesson lesson, @PathVariable("topicname") String topicId, @PathVariable("coursename") String courseId){
		lesson.setCourse(new Course(courseId,"","",topicId));
		lessonService.addLesson(lesson);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="topics/{topicname}/courses/{coursename}/lessons/{lessonname}")
	public void updateCourse(@RequestBody Lesson lesson, @PathVariable("topicname") String topicId,@PathVariable("coursename") String courseId){
		lesson.setCourse(new Course(courseId,"","",topicId));
		lessonService.updateLesson(lesson);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="topics/{topicname}/courses/{coursename}/lessons/{lessonname}")
	public void deleteLesson(@PathVariable("lessonname") String id){
		lessonService.deleteLesson(id);
	}
}
