package com.github.coder229;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepository repository;

	@RequestMapping(value="/task",method=RequestMethod.GET)
	public @ResponseBody List<Task> list() {
		return repository.findAll();
	}

	@RequestMapping(value="/task/{id}",method=RequestMethod.GET)
	public @ResponseBody Task show(@PathVariable("id") String id) {
		return repository.findOne(id);
	}

	@RequestMapping(value="/task",method=RequestMethod.POST)
	public @ResponseBody Task create(@RequestBody Task task) {
		return repository.save(task);
	}

	@RequestMapping(value="/task",method=RequestMethod.PUT)
	public @ResponseBody Task update(@RequestBody Task task) {
		return repository.save(task);
	}

	@RequestMapping(value="/task/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Task delete(@PathVariable("id") String id) {
		Task task = repository.findOne(id);
		repository.delete(id);
		return task;
	}
}
