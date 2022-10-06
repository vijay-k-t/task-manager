package com.mygroup.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.ColumnDefault;


import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Getter
    @Setter
    private UUID id;
    private String name;
    @Column(name = "tasklist_id")
    private String tasklistId;
    @Column(name = "assignee_id")
    private String assigneeId;
    @Column(name = "project_id")
    private String projectId;
    private int task_index;
    private String description;
    private Timestamp due_date;
    private boolean completed;
    private Timestamp completed_at;
    private Timestamp created_at;
    private Timestamp updated_at;

    @Transient
    private Project project;

    @Transient
    private Account account;    

    @Transient
    private List<Comment> comments;

 }
