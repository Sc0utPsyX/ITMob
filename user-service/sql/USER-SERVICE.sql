CREATE SCHEMA IF NOT EXISTS itmob;

CREATE DOMAIN itmob.gender CHAR(1)
    CHECK (value IN ('F', 'M'));

CREATE TABLE itmob.privacy_settings
(
    id                       bigserial PRIMARY KEY,
    show_age                 bool not null default true,
    open_profile             bool not null default true,
    invitation_subscribers   bool not null default true,
    invitation_subscriptions bool not null default true
);

CREATE TABLE itmob.users
(
    id            bigserial    NOT NULL,
    "login"       varchar(250) NOT NULL UNIQUE,
    "password"    varchar(100) NOT NULL,
    username      varchar(100) NOT NULL UNIQUE,
    create_date   timestamp    NOT NULL,
    modify_date   timestamp    NOT NULL,
    reg_confirmed boolean      NOT NULL,
    active        boolean      NOT NULL,
    privacy_setting_id bigint REFERENCES itmob.privacy_settings(id),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE itmob.user_details
(
    user_id    bigserial    NOT NULL,
    about      varchar(4000),
    address    varchar(1000),
    city       varchar(255),
    sex        gender,
    birth_date date,
    email      varchar(200) NOT NULL UNIQUE,
    CONSTRAINT pk_user_details PRIMARY KEY (user_id)
);

CREATE TABLE itmob.user_rights
(
    user_id  bigserial NOT NULL,
    right_id serial    NOT NULL,
    CONSTRAINT pk_user_rights PRIMARY KEY (user_id, right_id)
);

CREATE TABLE itmob.dic_rights
(
    id     serial       NOT NULL,
    "name" varchar(255) NOT NULL,
    active boolean      NOT NULL,
    CONSTRAINT pk_dic_rights PRIMARY KEY (id)
);


ALTER TABLE itmob.user_rights
    ADD CONSTRAINT fk_user_rights_user_id FOREIGN KEY (user_id) REFERENCES itmob.users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE itmob.user_rights
    ADD CONSTRAINT fk_user_right_id FOREIGN KEY (right_id) REFERENCES itmob.dic_rights (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE itmob.user_details
    ADD CONSTRAINT fk_user_details_user_id FOREIGN KEY (user_id) REFERENCES itmob.users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- insert into itmob.users
-- 	(id,"login","password",username,create_date,modify_date,reg_confirmed,active)
-- values
-- 	(1,'log1','$2a$12$YO0TqUHXZijRTxIxfoBnl.CZF/gigBS00eMLMsRa4e9iGn1lcoBF2','log1',now(),now(),true,false); --password=pass
--
-- insert into itmob.user_details
-- 	(user_id,about,address,city,sex,birth_date,email)
-- values
-- 	('1','about1','address1','city1','F','01.01.2010','email1');


insert into itmob.dic_rights
    (id, "name", active)
values (1, 'user', true),
       (2, 'admin', true);

-- insert into itmob.user_rights
--     (user_id,right_id)
-- values
--     (1,1),
--     (1,2);


select *
from itmob.users;
select *
from itmob.user_details;
select *
from itmob.dic_rights;
select *
from itmob.user_rights;

