-- liquibase formatted sql
-- changeset tregetter:10
insert into pet (name, code, type_id, color_id, country_id, account_id)
values ('Mustikas', '202211151535', 1, 1, 1, 1),
       ('Roy', '202211151536', 2, 3, 2, 1),
       ('Charles', '202211151537', 4, 2, 6, 1);