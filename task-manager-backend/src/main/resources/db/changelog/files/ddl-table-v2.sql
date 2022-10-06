--liquibase formatted sql
--changeset Vijay:V2.0
CREATE TABLE IF NOT EXISTS Account (
    ID UUID,
    email VARCHAR (355),
    hashed_password VARCHAR (355),
    name VARCHAR (355),
    image VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE User

--changeset Vijay:V2.1
CREATE TABLE IF NOT EXISTS Team (
    ID UUID,
    NAME VARCHAR (355),
    DESCRIPTION VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE Team

--changeset Vijay:V2.2
CREATE TABLE IF NOT EXISTS User_Team (
    ID UUID,
    User_id VARCHAR (355),
    team_id VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE UserTeam

--changeset Vijay:V2.3
CREATE TABLE IF NOT EXISTS Project (
    ID UUID,
    NAME VARCHAR (355),
    team_id VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE Project

--changeset Vijay:V2.4
CREATE TABLE IF NOT EXISTS Task_List (
    ID UUID,
    NAME VARCHAR (355),
    project_id VARCHAR (355),
    column_index Integer,
    owner_id VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE TaskList

--changeset Vijay:V2.5
CREATE TABLE IF NOT EXISTS Task (
    ID UUID,
    NAME VARCHAR (355),
    tasklist_id VARCHAR (355),
    assignee_id VARCHAR (355),
    project_id VARCHAR (355),
    task_index Integer,
    description VARCHAR (355),
    due_date TIMESTAMP,
    completed boolean,
    completed_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE Task

--changeset Vijay:V2.6
CREATE TABLE IF NOT EXISTS Comment (
    ID UUID,
    text VARCHAR (355),
    task_id VARCHAR (355),
    user_id VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE Comment

--changeset Vijay:V2.7
CREATE TABLE IF NOT EXISTS User_Project (
    ID UUID,
    user_id VARCHAR (355),
    project_id VARCHAR (355),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
--rollback DROP TABLE UserProject