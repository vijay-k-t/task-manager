package com.mygroup.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.mygroup.data.Project;
import com.mygroup.data.UserProject;
import com.mygroup.repository.ProjectRepository;
import com.mygroup.service.UserProjectService;



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
public class ProjectService {
    
    private final ProjectRepository repository;

    private final UserProjectService userProjectService;

    public List<Project> findAll() {
        return repository
            .findAll();
    }

    public Project save(Project task) {
        return repository
            .save(task);
    }

    public Project findById(UUID id) {
        List<UserProject> userProjects = userProjectService.findByProjectId(id.toString());
        Project result = repository
            .findById(id).get();
            result.setUserProjects(userProjects);
        return result;
    }

    public List<Project> findByTeamId(String teamId) {
        return repository
            .findByTeamId(teamId);
    }

    public List<Project> findByUserId(String id) {
        List<UserProject> userProject = userProjectService.findByUserId(id);
        List<Project> result = new ArrayList();
        userProject.stream().forEach((c) -> result.add(repository
            .findById(UUID.fromString(c.getProjectId())).get()));
        return result;
    }

    public void delete(Project task) {
        repository
            .delete(task);
    }

}
