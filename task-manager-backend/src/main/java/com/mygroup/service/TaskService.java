package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.Task;
import com.mygroup.repository.TaskRepository;



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
public class TaskService {
    
    private final TaskRepository repository;

    public List<Task> findAll() {
        return repository
            .findAll();
    }

    public void save(Task task) {
        repository
            .save(task);
    }

    public Optional<Task> findById(UUID id) {
        return repository
            .findById(id);
    }

    public List<Task> findByAssigneeId(String assigneeId) {
        return repository
            .findByAssigneeId(assigneeId);
    }
    
    

    public List<Task> findByTasklistId(String tasklistId) {
        return repository
            .findByTasklistId(tasklistId);
    }

    public List<Task> findByProjectId(String projectId) {
        return repository
            .findByProjectId(projectId);
    }

    public void delete(Task task) {
        repository
            .delete(task);
    }

}
