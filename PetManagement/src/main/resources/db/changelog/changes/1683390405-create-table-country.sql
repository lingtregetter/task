-- liquibase formatted sql
-- changeset tregetter:5
CREATE TABLE country
(
    id           BIGINT PRIMARY KEY NOT NULL auto_increment,
    country VARCHAR(255)       NOT NULL
);