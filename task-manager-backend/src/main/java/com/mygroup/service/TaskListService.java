package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.TaskList;
import com.mygroup.repository.TaskListRepository;



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
public class TaskListService {
    
    private final TaskListRepository repository;

    public List<TaskList> findAll() {
        return repository
            .findAll();
    }

    public void save(TaskList taskList) {
        repository
            .save(taskList);
    }

    public Optional<TaskList> findById(UUID id) {
        return repository
            .findById(id);
    }

    public List<TaskList> findByProjectId(String projectId) {
        return repository
            .findByProjectId(projectId);
    }

    public void delete(TaskList taskList) {
        repository
            .delete(taskList);
    }

}
