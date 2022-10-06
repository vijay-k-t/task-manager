package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.Project;
import com.mygroup.data.UserProject;

import com.mygroup.service.ProjectService;
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

@RequestMapping("/api/project")
@Controller
@RestController
public class ProjectController {

    @Autowired
	ProjectService service;

	@Autowired
	UserProjectService userProjectService;
    

	@GetMapping("/findAll")
	public List<Project> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Project findById(@PathVariable("id") String id) {
		System.out.println("Find By Project ID" + id);
		Project result = service.findById(UUID.fromString(id));
		if(result!=null) {
			return result;
		}else {
			return new Project();
		}
    }

	@GetMapping("/findByUserId/{id}")
	public List<Project> findByUserId(@PathVariable("id") String id) {
		System.out.println("Find By User ID" + id);
		return service.findByUserId(id);
    }

	@GetMapping("/findByTeamId/{teamId}")
	public List<Project> findByTeamId(@PathVariable("teamId") String teamId) {
		System.out.println("Find By Team ID" + teamId);
		return service.findByTeamId(teamId);
    }

	@PostMapping("/save")
	public void save(@RequestBody Project project) {
		System.out.println("save" + project);
		service.save(project);
    }

	@PostMapping("/save/{userId}")
	public void save(@RequestBody Project project, @PathVariable("userId") String userId) {
		System.out.println("save" + project);
		Project result = service.save(project);
		System.out.println("Project ID created ->"+ result.getId());
		UserProject userProject = new UserProject();
		userProject.setUserId(userId);
		userProject.setProjectId(result.getId().toString());
		userProjectService.save(userProject);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody Project project) {
		System.out.println("save" + project);
		service.delete(project);
    }

}
