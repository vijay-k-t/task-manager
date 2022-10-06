package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.UserProject;
import com.mygroup.repository.UserProjectRepository;



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
public class UserProjectService {
    
    private final UserProjectRepository repository;

    public List<UserProject> findAll() {
        return repository
            .findAll();
    }

    public void save(UserProject userProject) {
        repository
            .save(userProject);
    }

    public Optional<UserProject> findById(UUID id) {
        return repository
            .findById(id);
    }

    public List<UserProject> findByUserId(String userId) {
        return repository
            .findByUserId(userId);
    }

    public List<UserProject> findByProjectId(String projectId) {
        return repository
            .findByProjectId(projectId);
    }

    public void delete(UserProject userProject) {
        repository
            .delete(userProject);
    }

}
