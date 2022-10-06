package com.mygroup.service;

import java.util.List;
import java.util.Map;

import com.mygroup.data.Account;
import com.mygroup.repository.AccountRepository;



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
public class AccountService {
    
    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository
            .findAll();
    }

    public void save(Account account) {
        repository
            .save(account);
    }

    public Optional<Account> findById(UUID id) {
        return repository
            .findById(id);
    }

    public Optional<Account> findByEmail(String email) {
        return repository
            .findByEmail(email);
    }

    public void delete(Account account) {
        repository
            .delete(account);
    }

}
