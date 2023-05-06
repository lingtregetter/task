-- liquibase formatted sql
-- changeset tregetter:8
insert into users (username, password)
values ('user1', '1234'),
       ('user2', '1234'),
       ('user3', '1234');