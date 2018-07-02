package io.javabrains.courseapidata.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
 
	@Autowired
	private CourseRepository courseRepository;
		
	public List<Course> getAllCourses(String topicid){
		
		List<Course> courses = new ArrayList<>();
		courseRepository.findByTopicId(topicid).forEach(courses::add);
		return courses;
		
	}
	
	public Optional<Course> getCourse(String id){
		//return courses.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return courseRepository.findById(id);
	}
	
	public void addCourse(Course course){
		//courses.add(course);
		courseRepository.save(course);
	}
	
	public void updateCourse(Course course){
		courseRepository.save(course);
	}

	public void deleteCourse(String id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
	}
}
