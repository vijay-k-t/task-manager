package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.UserTeam;

import com.mygroup.service.UserTeamService;
import com.mygroup.service.TeamService;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp; 

@RequestMapping("/api/userteam")
@Controller
@RestController
public class UserTeamController {

    @Autowired
	UserTeamService service;

	@Autowired
	TeamService teamService;
    

	@GetMapping("/findAll")
	public List<UserTeam> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Optional<UserTeam> findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		return service.findById(UUID.fromString(id));
    }

	@GetMapping("/findByUserId/{userId}")
	public List<UserTeam> findByUserId(@PathVariable("userId") String userId) {
		System.out.println("Find By User ID" + userId);
		List<UserTeam> result = service.findByUserId(userId);
        result.stream().forEach((c) -> c.setName(teamService
            .findById(UUID.fromString(c.getTeamId())).get().getName()));
        return result;
    }

	@PostMapping("/save")
	public void save(@RequestBody UserTeam userTeam) {
		System.out.println("save" + userTeam);
		if(userTeam.getId() == null) {
			userTeam.setCreated_at(new Timestamp(new Date().getTime()));
		}else {
			userTeam.setUpdated_at(new Timestamp(new Date().getTime()));
		}
		service.save(userTeam);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody UserTeam userTeam) {
		System.out.println("save" + userTeam);
		service.delete(userTeam);
    }

}
