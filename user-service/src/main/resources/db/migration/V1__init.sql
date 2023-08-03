CREATE TABLE privacy_settings
(
    id                       bigserial PRIMARY KEY,
    show_age                 bool not null default true,
    open_profile             bool not null default true,
    invitation_subscribers   bool not null default true,
    invitation_subscriptions bool not null default true
);

CREATE TABLE users
(
    id            bigserial    NOT NULL,
    "login"       varchar(250) NOT NULL UNIQUE,
    "password"    varchar(100) NOT NULL,
    username      varchar(100) NOT NULL UNIQUE,
    create_date   timestamp    NOT NULL,
    modify_date   timestamp    NOT NULL,
    reg_confirmed boolean      NOT NULL,
    active        boolean      NOT NULL,
    privacy_setting_id bigint REFERENCES privacy_settings(id),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE user_details
(
    user_id    bigserial    NOT NULL,
    about      varchar(4000),
    address    varchar(1000),
    city       varchar(255),
    sex        varchar(1),
    birth_date date,
    email      varchar(200) NOT NULL UNIQUE,
    CONSTRAINT pk_user_details PRIMARY KEY (user_id)
);

CREATE TABLE dic_rights
(
    id     bigserial       NOT NULL,
    "name" varchar(255) NOT NULL,
    active boolean      NOT NULL,
    CONSTRAINT pk_dic_rights PRIMARY KEY (id)
);

CREATE TABLE user_rights
(
    user_id  bigint NOT NULL,
    right_id bigint NOT NULL,
    CONSTRAINT pk_user_rights PRIMARY KEY (user_id, right_id)
);

ALTER TABLE user_rights
    ADD CONSTRAINT fk_user_rights_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE user_rights
    ADD CONSTRAINT fk_user_right_id FOREIGN KEY (right_id) REFERENCES dic_rights (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE user_details
    ADD CONSTRAINT fk_user_details_user_id FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

insert into dic_rights
("name", active)
values ('user', true),
       ('admin', true);