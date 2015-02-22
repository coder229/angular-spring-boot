package com.github.coder229.todo.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.coder229.todo.storage.Task;
import com.github.coder229.todo.storage.TaskRepository;

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepository repository;

	@RequestMapping(value="/task",method=RequestMethod.GET)
	public List<Task> list() {
		return repository.findAll();
	}

	@RequestMapping(value="/task/{id}",method=RequestMethod.GET)
	public Task show(@PathVariable("id") String id) {
		return repository.findOne(id);
	}

	@RequestMapping(value="/task",method=RequestMethod.POST)
	public Task create(@RequestBody Task task) {
		task.setCreatedAt(new Date());
		return repository.save(task);
	}

	@RequestMapping(value="/task/{id}",method=RequestMethod.PUT)
	public Task update(@PathVariable("id") String id, @RequestBody Task updated) {
		Date now = new Date();
		Task task = repository.findOne(id);
		task.setName(updated.getName());
		if (task.getCreatedAt() == null) {
			task.setCreatedAt(now);
		}
		task.setUpdatedAt(now);
		return repository.save(task);
	}

	@RequestMapping(value="/task/{id}",method=RequestMethod.DELETE)
	public Task delete(@PathVariable("id") String id) {
		Date now = new Date();
		Task task = repository.findOne(id);
		repository.delete(id);
		task.setUpdatedAt(now);
		return task;
	}
}
