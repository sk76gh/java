package org.saravana.mockito.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.saravana.mockito.data.api.TodoServiceStub;
import org.saravana.mockito.data.api.service.TodoService;

public class TodoBusinessImplStubTest {

	@Test
	public void test() {
		TodoService todoService= new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl= new TodoBusinessImpl(todoService);
		
		List<String> filteredList=todoBusinessImpl.retrieveTodoRelatedtoSpring("saravana");
		assertEquals(2, filteredList.size());
	}

}
