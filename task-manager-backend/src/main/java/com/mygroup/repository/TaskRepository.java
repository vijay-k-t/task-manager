package com.mygroup.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mygroup.data.Task;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAll();
    Optional<Task> findById(UUID id);
    List<Task> findByAssigneeId(String assigneeId);
    List<Task> findByProjectId(String projectId);
    List<Task> findByTasklistId(String tasklistId);
    
    
}
