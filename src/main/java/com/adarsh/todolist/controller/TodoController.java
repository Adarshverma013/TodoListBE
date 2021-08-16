package com.adarsh.todolist.controller;

import java.util.List;

import com.adarsh.todolist.model.Users;
import com.adarsh.todolist.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.adarsh.todolist.model.TodoItem;
import com.adarsh.todolist.repo.TodoItemRepo;
import com.sun.istack.NotNull;

@RestController()
@RequestMapping("/api")
public class TodoController {

	@Autowired
	private TodoItemRepo todoRepo;

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/user/{user_id}/todo/{todo_id}")
	public TodoItem getTodoById(@PathVariable int user_id,@PathVariable int todo_id)
	{
		return todoRepo.findByUserIdAndTodoId(user_id,todo_id);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/user/{user_id}/todo")
	public List<TodoItem> getAllTodo(@PathVariable int user_id)
	{
		return todoRepo.findByUserId(user_id);
	}

	@PostMapping(value = "/user/{user_id}/todo")
	public TodoItem saveTodo(@Validated @NotNull @RequestBody TodoItem todoItem)
	{
		return todoRepo.save(todoItem);
	}

	@DeleteMapping("/user/{user_id}/todo/{todo_id}")
	public void deleteTodo(@PathVariable int user_id,@PathVariable int todo_id){
		todoRepo.deleteByUserIdAndTodoId(user_id,todo_id);
	}
}
