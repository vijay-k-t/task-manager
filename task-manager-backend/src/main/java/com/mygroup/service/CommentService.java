package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.Comment;
import com.mygroup.repository.CommentRepository;

import com.mygroup.data.Account;

import com.mygroup.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentRepository repository;

    @Autowired
	AccountService accountService;

    public List<Comment> findAll() {
        return repository
            .findAll();
    }

    public void save(Comment comment) {
        repository
            .save(comment);
    }

    public Optional<Comment> findById(UUID id) {
        return repository
            .findById(id);
    }

    public List<Comment> findByTaskId(String taskId) {
        List<Comment> result = repository.findByTaskId(taskId);
			result.stream().forEach((c) -> {
			c.setAccount(accountService.findById(UUID.fromString(c.getUserId())).get());
		});
		return result;
    }

    public void delete(Comment comment) {
        repository
            .delete(comment);
    }

}
