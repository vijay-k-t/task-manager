package com.mygroup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mygroup.data.UserProject;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, UUID> {
    List<UserProject> findAll();
    Optional<UserProject> findById(UUID id);
    List<UserProject> findByProjectId(String projectId);
    List<UserProject> findByUserId(String userId);
}
