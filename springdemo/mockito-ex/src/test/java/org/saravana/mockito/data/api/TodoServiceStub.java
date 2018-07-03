package org.saravana.mockito.data.api;

import java.util.Arrays;
import java.util.List;

import org.saravana.mockito.data.api.service.TodoService;

public class TodoServiceStub implements TodoService {

	public List<String> retrieveTools(String user) {
		// TODO Auto-generated method stub

		return Arrays.asList("Lear Spring MVC","Learn Dance","Learn Spring Boot","Learn Python");
	}

}
