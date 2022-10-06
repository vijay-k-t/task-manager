package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.Team;
import com.mygroup.repository.TeamRepository;



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
public class TeamService {
    
    private final TeamRepository repository;

    public List<Team> findAll() {
        return repository
            .findAll();
    }

    public Team save(Team team) {
        return repository
            .save(team);
    }

    public Optional<Team> findById(UUID id) {
        return repository
            .findById(id);
    }

    public List<Team> findByName(String name) {
        return repository
            .findByName(name);
    }

    public void delete(Team team) {
        repository
            .delete(team);
    }

}
