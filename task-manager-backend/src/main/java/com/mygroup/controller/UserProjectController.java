package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.UserProject;

import com.mygroup.service.UserProjectService;

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

@RequestMapping("/api/userproject")
@Controller
@RestController
public class UserProjectController {

    @Autowired
	UserProjectService service;
    

	@GetMapping("/findAll")
	public List<UserProject> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Optional<UserProject> findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		return service.findById(UUID.fromString(id));
    }

	@GetMapping("/findByProjectId/{projectId}")
	public List<UserProject> findByProjectId(@PathVariable("projectId") String projectId) {
		System.out.println("Find By Project ID" + projectId);
		return service.findByProjectId(projectId);
    }

	@PostMapping("/save")
	public void save(@RequestBody UserProject userProject) {
		System.out.println("save" + userProject);
		service.save(userProject);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody UserProject userProject) {
		System.out.println("save" + userProject);
		service.delete(userProject);
    }

}
