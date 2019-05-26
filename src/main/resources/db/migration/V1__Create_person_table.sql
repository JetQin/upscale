--  drop the schema if exist;
-- drop schema upscale cascade ;
-- create schema upscale;

create table PERSON (
    ID serial,
    NAME varchar(100) not null,
    constraint person_pk PRIMARY KEY (ID)
);