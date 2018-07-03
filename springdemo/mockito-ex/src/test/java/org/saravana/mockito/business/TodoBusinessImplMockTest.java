package org.saravana.mockito.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.saravana.mockito.data.api.service.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void test() {
		
		
		TodoService todoServiceMock= mock(TodoService.class);
		List<String> todos = Arrays.asList("Lear Spring MVC","Learn Dance","Learn Spring Boot","Learn Python");
		when(todoServiceMock.retrieveTools("saravana")).thenReturn(todos);
		
		TodoBusinessImpl todoBusinessImpl= new TodoBusinessImpl(todoServiceMock);
		List<String> filteredList=todoBusinessImpl.retrieveTodoRelatedtoSpring("saravana");
		
		
		assertEquals(2, filteredList.size());
	}

}
