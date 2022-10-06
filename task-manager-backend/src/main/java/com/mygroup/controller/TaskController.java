package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.Task;
import com.mygroup.data.Account;
import com.mygroup.data.Comment;

import com.mygroup.service.TaskService;
import com.mygroup.service.ProjectService;
import com.mygroup.service.AccountService;
import com.mygroup.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;  

@RequestMapping("/api/task")
@Controller
@RestController
public class TaskController {

    @Autowired
	TaskService service;

	@Autowired
	ProjectService projectService;

	@Autowired
	AccountService accountService;

	@Autowired
	CommentService commentService;
    

	@GetMapping("/findAll")
	public List<Task> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Task findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		
		Task result = service.findById(UUID.fromString(id)).get();
        	result.setProject(projectService
            .findById(UUID.fromString(result.getProjectId())));
			result.setAccount(accountService.findById(UUID.fromString(result.getAssigneeId())).get());
			result.setComments(commentService
            .findByTaskId(result.getId().toString()));
		return result;
    }

	

	@GetMapping("/findByAssigneeId/{assigneeId}")
	public List<Task> findByAssigneeId(@PathVariable("assigneeId") String assigneeId) {
		System.out.println("Find By User ID" + assigneeId);
	
		Account user = accountService.findById(UUID.fromString(assigneeId)).get();
		List<Task> result = service.findByAssigneeId(assigneeId);
         result.stream().forEach((c) -> {
		 	c.setProject(projectService
             .findById(UUID.fromString(c.getProjectId())));
		 	c.setAccount(user);
		 	c.setComments(commentService
             .findByTaskId(c.getId().toString()));
		 	});
        return result;
    }

	@GetMapping("/findByProjectId/{projectId}")
	public List<Task> findByProjectId(@PathVariable("projectId") String projectId) {
		System.out.println("Find By User ID" + projectId);
		return service.findByProjectId(projectId);
    }

	@PostMapping("/save")
	public void save(@RequestBody Task task) {
		System.out.println("save" + task);
		task.setCreated_at(new Timestamp(new Date().getTime()));
		service.save(task);
    }

	@PostMapping("/completeTask/{id}")
	public void save(@PathVariable("id") String id) {
		Task task = service.findById(UUID.fromString(id)).get();
		if(!task.isCompleted()) {
			task.setCompleted(true);
			task.setCompleted_at(new Timestamp(new Date().getTime()));
			task.setUpdated_at(new Timestamp(new Date().getTime()));
		} else {
			task.setCompleted(false);
			task.setCompleted_at(null);
			task.setUpdated_at(new Timestamp(new Date().getTime()));
		}
		service.save(task);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody Task task) {
		System.out.println("save" + task);
		service.delete(task);
    }

}
