create table ADDRESS(
    ID serial,
    STREET varchar(200),
    CITY varchar(200),
    PROVINCE varchar(200),
    PERSON_ID serial,
    constraint address_pk PRIMARY KEY(ID),
    constraint address_person_fk FOREIGN KEY(PERSON_ID) REFERENCES PERSON(ID)
)