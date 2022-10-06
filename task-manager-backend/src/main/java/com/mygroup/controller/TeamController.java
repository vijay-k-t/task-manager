package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.Team;
import com.mygroup.data.UserTeam;
import com.mygroup.data.Account;

import com.mygroup.service.AccountService;
import com.mygroup.service.TeamService;
import com.mygroup.service.UserTeamService;
import com.mygroup.service.ProjectService;

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
import java.util.ArrayList;

@RequestMapping("/api/team")
@Controller
@RestController
public class TeamController {

    @Autowired
	TeamService service;

    @Autowired
	UserTeamService userTeamService;

	@Autowired
	ProjectService projectService;

	@Autowired
	AccountService accountService;

	@GetMapping("/findAll")
	public List<Team> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Team findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		Team team = service
            .findById(UUID.fromString(id)).get();
		team.setProjects(projectService.findByTeamId(id));

		List<UserTeam> userTeam = userTeamService.findByTeamId(id);
        List<Account> result = new ArrayList();
        userTeam.stream().forEach((c) -> result.add(accountService
            .findById(UUID.fromString(c.getUserId())).get()));
		team.setAccounts(result);
		return team;
    }

	@GetMapping("/findByName/{name}")
	public List<Team> findByName(@PathVariable("name") String name) {
		System.out.println("Find By name" + name);
		return service.findByName(name);
    }

	@GetMapping("/findByUserId/{userId}")
	public List<Team> findByUserId(@PathVariable("userId") String userId) {
		System.out.println("Find By userId:" + userId);

		List<UserTeam> userTeams = userTeamService.findByUserId(userId);
		List<Team> result = new ArrayList<>();
        userTeams.stream().forEach((c) -> {
			Team team = service
            .findById(UUID.fromString(c.getTeamId())).get();
			team.setProjects(projectService.findByTeamId(c.getTeamId()));
			result.add(team);
			}
			);

		return result;
    }

	@PostMapping("/save/{userId}")
	public void save(@RequestBody Team team, @PathVariable("userId") String userId) {
		System.out.println("save" + team);
		Team result = service.save(team);
		System.out.println("Team ID created ->"+ result.getId());
		UserTeam userTeam = new UserTeam();
		userTeam.setUserId(userId);
		userTeam.setTeamId(result.getId().toString());
		userTeamService.save(userTeam);
    }

	@PostMapping("/save")
	public void save(@RequestBody Team team) {
		System.out.println("save" + team);
		service.save(team);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody Team team) {
		System.out.println("save" + team);
		service.delete(team);
    }

}
