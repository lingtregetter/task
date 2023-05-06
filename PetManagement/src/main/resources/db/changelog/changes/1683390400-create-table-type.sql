-- liquibase formatted sql
-- changeset tregetter:1
CREATE TABLE type
(
    id        BIGINT PRIMARY KEY NOT NULL auto_increment,
    type VARCHAR(255)       NOT NULL
);