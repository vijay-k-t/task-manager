package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.TaskList;
import com.mygroup.data.Task;

import com.mygroup.service.TaskListService;
import com.mygroup.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/tasklist")
@Controller
@RestController
public class TaskListController {

    @Autowired
	TaskListService service;

	@Autowired
	TaskService taskService;
    

	@GetMapping("/findAll")
	public List<TaskList> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Optional<TaskList> findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		return service.findById(UUID.fromString(id));
    }

	@GetMapping("/findByProjectId/{projectId}")
	public List<TaskList> findByProjectId(@PathVariable("projectId") String projectId) {
		System.out.println("Find By Project ID" + projectId);
		
		List<TaskList> result = service.findByProjectId(projectId);
        result.stream().forEach((c) -> c.setTasks(taskService
            .findByTasklistId(c.getId().toString())));
        return result;

    }

	@PostMapping("/save")
	public void save(@RequestBody TaskList taskList) {
		System.out.println("save" + taskList);
		service.save(taskList);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody TaskList taskList) {
		System.out.println("save" + taskList);
		service.delete(taskList);
    }

}
