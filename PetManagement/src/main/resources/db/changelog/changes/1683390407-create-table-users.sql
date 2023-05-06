-- liquibase formatted sql
-- changeset tregetter:7
CREATE TABLE users
(
    id       BIGINT primary key NOT NULL auto_increment,
    username VARCHAR(255)       NOT NULL,
    password VARCHAR(255)       NOT NULL
);