package io.javabrains.courseapidata.lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
 
	@Autowired
	private LessonRepository lessonRepository;
		
	public List<Lesson> getAllLessons(String courseid){
		
		List<Lesson> lessons = new ArrayList<>();
		lessonRepository.findByCourseId(courseid).forEach(lessons::add);
		return lessons;
		
	}
	
	public Optional<Lesson> getLesson(String id){
		//return courses.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return lessonRepository.findById(id);
	}
	
	public void addLesson(Lesson lesson){
		//courses.add(course);
		lessonRepository.save(lesson);
	}
	
	public void updateLesson(Lesson lesson){
		lessonRepository.save(lesson);
	}

	public void deleteLesson(String id) {
		// TODO Auto-generated method stub
		lessonRepository.deleteById(id);
	}
}
