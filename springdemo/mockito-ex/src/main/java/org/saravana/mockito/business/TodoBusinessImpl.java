package org.saravana.mockito.business;

import java.util.ArrayList;
import java.util.List;

import org.saravana.mockito.data.api.service.TodoService;

public class TodoBusinessImpl  {

	private TodoService todoService;

	public TodoBusinessImpl(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	public List<String> retrieveTodoRelatedtoSpring(String user){
		List<String> filteredToDos=new ArrayList<String>();
		List<String> toDos=getTodoService().retrieveTools(user);
		
		for(String todo:toDos){
			if(todo.contains("Spring")){
				filteredToDos.add(todo);
			}
		}
		return filteredToDos;
	}
	public TodoService getTodoService() {
		return todoService;
	}

	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}

}
