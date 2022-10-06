package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mygroup.data.Comment;
import com.mygroup.data.Account;

import com.mygroup.service.CommentService;
import com.mygroup.service.AccountService;

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
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;
import java.util.Date;

@RequestMapping("/api/comment")
@Controller
@RestController
public class CommentController {

    @Autowired
	CommentService service;


	@GetMapping("/findAll")
	public List<Comment> findAll() {
		System.out.println("Find All");
		return service.findAll();
    }

	@GetMapping("/findById/{id}")
	public Optional<Comment> findById(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);
		return service.findById(UUID.fromString(id));
    }

	@GetMapping("/findByTaskId/{id}")
	public List<Comment> findByTaskId(@PathVariable("id") String id) {
		System.out.println("Find By ID" + id);

		return service.findByTaskId(id);
    }

	@PostMapping("/save")
	public void save(@RequestBody Comment comment) {
		System.out.println("save" + comment);
		if(comment.getCreated_at() == null) {
            comment.setCreated_at(new Timestamp(new Date().getTime()));
        }

        if(comment.getId()!=null && comment.getUpdated_at() == null) {
            comment.setUpdated_at(new Timestamp(new Date().getTime()));
        }
		service.save(comment);
    }

	@DeleteMapping("/delete")
	public void delete(@RequestBody Comment comment) {
		System.out.println("save" + comment);
		service.delete(comment);
    }

}
