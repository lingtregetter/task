-- liquibase formatted sql
-- changeset tregetter:9
CREATE TABLE pet
(
    id         BIGINT primary key NOT NULL auto_increment,
    name       VARCHAR(255)       NOT NULL,
    code       VARCHAR(255)       NOT NULL,
    type_id    BIGINT             NOT NULL,
    color_id   BIGINT             NOT NULL,
    country_id BIGINT             NOT NULL,
    account_id BIGINT             NOT NULL
);
