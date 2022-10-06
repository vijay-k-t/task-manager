package com.mygroup.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.Account;
import com.mygroup.data.UserProject;

import com.mygroup.service.AccountService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp; 

@RequestMapping("/api/account")
@Controller
@RestController
public class AccountController {

    @Autowired
	AccountService service;

	@Autowired
	UserProjectService userProjectService;
    

	@GetMapping("/findAll")
	public List<Account> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Optional<Account> findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		return service.findById(UUID.fromString(id));
    }

	@GetMapping("/findByProjectId/{projectId}")
	public List<Account> findByProjectId(@PathVariable("projectId") String projectId) {
		System.out.println("Find By Project ID" + projectId);
		
		List<UserProject> userProject = userProjectService.findByProjectId(projectId);
        List<Account> result = new ArrayList();
        userProject.stream().forEach((c) -> result.add(service
            .findById(UUID.fromString(c.getUserId())).get()));
        return result;

    }

	@PostMapping("/save")
	public void save(@RequestBody Account account) {
		System.out.println("save" + account);
		service.save(account);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody Account account) {
		System.out.println("save" + account);
		service.delete(account);
    }

	@PostMapping("/login")
	public Account login(@RequestBody Account account) {
		System.out.println("login Email->" + account.getEmail());
		Account result = service.findByEmail(account.getEmail()).get();
		result.setToken(result.getId().toString());
		//account.setId(UUID.fromString("62522ac8-7226-4797-b88b-464976b45967"));	
		return result;
    }

	@PostMapping("/register")
	public void register(@RequestBody Account account) {
		System.out.println("save" + account);
		account.setCreated_at(new Timestamp(new Date().getTime()));
		service.save(account);
    }

}
