-- liquibase formatted sql
-- changeset tregetter:3
CREATE TABLE color
(
    id    BIGINT PRIMARY KEY NOT NULL auto_increment,
    color VARCHAR(255)       NOT NULL
);