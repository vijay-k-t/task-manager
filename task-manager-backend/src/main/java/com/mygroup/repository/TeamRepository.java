package com.mygroup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mygroup.data.Team;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findAll();
    Optional<Team> findById(UUID id);
    List<Team> findByName(String name);
}
