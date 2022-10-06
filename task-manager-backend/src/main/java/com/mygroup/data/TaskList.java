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


import java.util.UUID;
import java.util.List;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskList {
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
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "column_index")
    private int columnIndex;
    @Column(name = "owner_id")
    private String userId;
    private Timestamp created_at;
    private Timestamp updated_at;

    @Transient
    private List<Task> tasks;

 }