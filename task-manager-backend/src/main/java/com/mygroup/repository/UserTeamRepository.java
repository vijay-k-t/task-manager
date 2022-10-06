package com.mygroup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mygroup.data.UserTeam;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, UUID> {
    List<UserTeam> findAll();
    Optional<UserTeam> findById(UUID id);
    List<UserTeam> findByUserId(String userId);
    List<UserTeam> findByTeamId(String teamId);
}
