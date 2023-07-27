CREATE SCHEMA IF NOT EXISTS itmob;

CREATE DOMAIN itmob.gender CHAR(1)
CHECK (value IN ('F','M'));;

CREATE  TABLE itmob.event_types ( 
	id                   serial  NOT NULL  ,
	name                 varchar(300)    ,
	description          text  NOT NULL  ,
	create_date          date DEFAULT CURRENT_DATE NOT NULL  ,
	is_default           boolean DEFAULT False NOT NULL  ,
	CONSTRAINT pk_event_types PRIMARY KEY ( id ),
	CONSTRAINT unq_event_types UNIQUE ( name ) 
 );

CREATE  TABLE itmob.user_right_types ( 
	id                   serial  NOT NULL  ,
	name                 varchar(255)  NOT NULL  ,
	active               boolean DEFAULT False NOT NULL  ,
	CONSTRAINT pk_dic_rights PRIMARY KEY ( id ),
	CONSTRAINT unq_right_types UNIQUE ( name ) 
 );

CREATE  TABLE itmob.users ( 
	id                   bigserial  NOT NULL  ,
	right_id             int  NOT NULL  ,
	login                varchar(100)  NOT NULL  ,
	"password"           varchar(100)  NOT NULL  ,
	displayname          varchar(100)  NOT NULL  ,
	create_date          timestamp DEFAULT NOW() NOT NULL  ,
	register_date        timestamp    ,
	reg_confirmed        boolean DEFAULT False NOT NULL  ,
	active               boolean DEFAULT True NOT NULL  ,
	CONSTRAINT pk_users PRIMARY KEY ( id ),
	CONSTRAINT unq_users UNIQUE ( login ) 
 );

CREATE  TABLE itmob.events ( 
	id                   bigserial  NOT NULL  ,
	event                varchar(250)  NOT NULL  ,
	description          text  NOT NULL  ,
	create_date          timestamp DEFAULT NOW NOT NULL  ,
	update_date			 timestamp DEFAULT NOW NOT NULL  ,
	owner_id             bigint  NOT NULL  ,
	event_date           timestamp  NOT NULL  ,
	event_type           int  NOT NULL  ,
	CONSTRAINT pk_events PRIMARY KEY ( id )
 );

CREATE  TABLE itmob.user_details ( 
	user_id              bigint  NOT NULL  ,
	about                varchar(4000)    ,
	address              varchar(1000)    ,
	city                 varchar(255)    ,
	sex                  itmob.gender    ,
	birth_date           date    ,
	email                varchar(200)  NOT NULL  ,
	CONSTRAINT pk_user_details PRIMARY KEY ( user_id )
 );

CREATE  TABLE itmob.user_messages ( 
	id                   bigserial  NOT NULL  ,
	recipient_id         bigint  NOT NULL  ,
	sender_id            bigint  NOT NULL  ,
	message              varchar(4000)  NOT NULL  ,
	create_date          timestamp DEFAULT NOW() NOT NULL  ,
	CONSTRAINT pk_user_sent_messages PRIMARY KEY ( id )
 );

CREATE  TABLE itmob.event_comments ( 
	id                   bigserial  NOT NULL  ,
	event_id             bigint  NOT NULL  ,
	"comment"            varchar(4000)  NOT NULL  ,
	create_date          timestamp DEFAULT NOW() NOT NULL  ,
	owner_id             bigint  NOT NULL  ,
	parent_id            bigint    ,
	CONSTRAINT pk_event_comments PRIMARY KEY ( id )
 );

CREATE  TABLE itmob.event_location ( 
	event_id             bigserial  NOT NULL  ,
	address              varchar(250)    ,
	coordinates          point    ,
	detail               varchar(2000)    ,
	CONSTRAINT pk_event_location PRIMARY KEY ( event_id )
 );

CREATE  TABLE itmob.event_members ( 
	event_id             bigint  NOT NULL  ,
	user_id              bigint  NOT NULL  ,
	CONSTRAINT pk_event_members PRIMARY KEY ( event_id, user_id )
 );

ALTER TABLE itmob.event_comments ADD CONSTRAINT fk_event_comments_event_id FOREIGN KEY ( event_id ) REFERENCES itmob.events( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.event_comments ADD CONSTRAINT fk_event_comments_owner_id FOREIGN KEY ( owner_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.event_location ADD CONSTRAINT fk_event_location_event_id FOREIGN KEY ( event_id ) REFERENCES itmob.events( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.event_members ADD CONSTRAINT fk_event_member_id FOREIGN KEY ( user_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.event_members ADD CONSTRAINT fk_event_members_event FOREIGN KEY ( event_id ) REFERENCES itmob.events( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.events ADD CONSTRAINT fk_event_user_id FOREIGN KEY ( owner_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.events ADD CONSTRAINT fk_event_type_id FOREIGN KEY ( event_type ) REFERENCES itmob.event_types( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.user_details ADD CONSTRAINT fk_user_details_users FOREIGN KEY ( user_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.user_messages ADD CONSTRAINT fk_user_messages_users_recipient FOREIGN KEY ( recipient_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.user_messages ADD CONSTRAINT fk_user_messages_users_sender FOREIGN KEY ( sender_id ) REFERENCES itmob.users( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE itmob.users ADD CONSTRAINT fk_user_right_id FOREIGN KEY ( right_id ) REFERENCES itmob.user_right_types( id ) ON DELETE RESTRICT ON UPDATE RESTRICT;

COMMENT ON CONSTRAINT fk_event_comments_owner_id ON itmob.event_comments IS 'Владелец комментария';

COMMENT ON CONSTRAINT fk_event_location_event_id ON itmob.event_location IS 'Идентификатор события';

COMMENT ON CONSTRAINT fk_event_member_id ON itmob.event_members IS 'Участник';

COMMENT ON CONSTRAINT fk_event_members_event ON itmob.event_members IS 'Событие';

COMMENT ON CONSTRAINT fk_event_user_id ON itmob.events IS 'Идентификатор пользователя (владельца)';

COMMENT ON CONSTRAINT fk_event_type_id ON itmob.events IS 'Тип события';

COMMENT ON CONSTRAINT fk_user_details_users ON itmob.user_details IS 'Идентификатор пользователя';

COMMENT ON CONSTRAINT fk_user_messages_users_recipient ON itmob.user_messages IS 'Получатель сообщения';

COMMENT ON CONSTRAINT fk_user_messages_users_sender ON itmob.user_messages IS 'Отправитель сообщения';

COMMENT ON DOMAIN itmob.gender IS 'Пол пользователя';

COMMENT ON CONSTRAINT unq_event_types ON itmob.event_types IS 'Уникальное имя типа события';

COMMENT ON TABLE itmob.event_types IS 'Справочник типов событий';

COMMENT ON COLUMN itmob.event_types.id IS 'Идентификатор';

COMMENT ON COLUMN itmob.event_types.name IS 'Наименование';

COMMENT ON COLUMN itmob.event_types.description IS 'Описание';

COMMENT ON COLUMN itmob.event_types.create_date IS 'Дата создания';

COMMENT ON COLUMN itmob.event_types.is_default IS 'Признак по умолчанию';

COMMENT ON CONSTRAINT unq_right_types ON itmob.user_right_types IS 'Уникальное имя права';

COMMENT ON TABLE itmob.user_right_types IS 'Справочник видов прав';

COMMENT ON COLUMN itmob.user_right_types.id IS 'Идентификатор права';

COMMENT ON COLUMN itmob.user_right_types.name IS 'Наименование права';

COMMENT ON COLUMN itmob.user_right_types.active IS 'Признак возможности использования права';

COMMENT ON CONSTRAINT unq_users ON itmob.users IS 'Уникальный логин';

COMMENT ON TABLE itmob.users IS 'Таблица пользователей';

COMMENT ON COLUMN itmob.users.id IS 'Уникальный идентификатор пользователя';

COMMENT ON COLUMN itmob.users.right_id IS 'Идентификатор права';

COMMENT ON COLUMN itmob.users.login IS 'Логин пользователя';

COMMENT ON COLUMN itmob.users."password" IS 'Пароль пользователя';

COMMENT ON COLUMN itmob.users.displayname IS 'Отображаемое имя на сайте';

COMMENT ON COLUMN itmob.users.create_date IS 'Дата создания';

COMMENT ON COLUMN itmob.users.register_date IS 'Дата регистрации пользователя';

COMMENT ON COLUMN itmob.users.reg_confirmed IS 'Факт подтверждения регистрации';

COMMENT ON COLUMN itmob.users.active IS 'Активный пользователь';

COMMENT ON TABLE itmob.events IS 'Таблица событий';

COMMENT ON COLUMN itmob.events.id IS 'Идентификатор';

COMMENT ON COLUMN itmob.events.event IS 'Наименование события';

COMMENT ON COLUMN itmob.events.description IS 'Содержанание';

COMMENT ON COLUMN itmob.events.create_date IS 'Дата создания';

COMMENT ON COLUMN itmob.events.update_date IS 'Дата редактирования';

COMMENT ON COLUMN itmob.events.owner_id IS 'Владелец (кто создал)';

COMMENT ON COLUMN itmob.events.event_date IS 'Дата, на которую назначено событие';

COMMENT ON COLUMN itmob.events.event_type IS 'Тип события';

COMMENT ON TABLE itmob.user_details IS 'Таблица расширенной информации по пользователе';

COMMENT ON COLUMN itmob.user_details.user_id IS 'Идентификатор пользователя';

COMMENT ON COLUMN itmob.user_details.about IS 'О себе';

COMMENT ON COLUMN itmob.user_details.address IS 'Адрес пользователя';

COMMENT ON COLUMN itmob.user_details.city IS 'Город, посёлок, деревня, пгт пользователя';

COMMENT ON COLUMN itmob.user_details.sex IS 'Пол пользователя';

COMMENT ON COLUMN itmob.user_details.birth_date IS 'Дата рождения пользователя';

COMMENT ON COLUMN itmob.user_details.email IS 'Адрес электронной почты';

COMMENT ON CONSTRAINT pk_user_sent_messages ON itmob.user_messages IS 'Первичный ключ по отправленному сообщению';

COMMENT ON TABLE itmob.user_messages IS 'Отправленные личные сообщения';

COMMENT ON COLUMN itmob.user_messages.id IS 'Идентификатор';

COMMENT ON COLUMN itmob.user_messages.recipient_id IS 'Пользователь, которому отправлено сообщение';

COMMENT ON COLUMN itmob.user_messages.sender_id IS 'Отправитель сообщения';

COMMENT ON COLUMN itmob.user_messages.message IS 'Текст сообщения';

COMMENT ON COLUMN itmob.user_messages.create_date IS 'Дата создания сообщения';

COMMENT ON TABLE itmob.event_comments IS 'Комментарии к событиям';

COMMENT ON COLUMN itmob.event_comments.id IS 'Идентификатор комментария';

COMMENT ON COLUMN itmob.event_comments.event_id IS 'Идентификатор события';

COMMENT ON COLUMN itmob.event_comments."comment" IS 'Текст комментария';

COMMENT ON COLUMN itmob.event_comments.create_date IS 'Дата создания комментария';

COMMENT ON COLUMN itmob.event_comments.owner_id IS 'Автор комментария';

COMMENT ON COLUMN itmob.event_comments.parent_id IS 'Идентификатор предыдущего комментария (только для ответов на комментарий) для построения дерева';

COMMENT ON TABLE itmob.event_location IS 'Местонахождение события';

COMMENT ON COLUMN itmob.event_location.event_id IS 'Идентификатор события';

COMMENT ON COLUMN itmob.event_location.address IS 'Адрес события';

COMMENT ON COLUMN itmob.event_location.coordinates IS 'Координаты широты и долготы';

COMMENT ON COLUMN itmob.event_location.detail IS 'Дополнительная информация о локации';

COMMENT ON CONSTRAINT pk_event_members ON itmob.event_members IS 'Первичный ключ';

COMMENT ON TABLE itmob.event_members IS 'Участники события';

COMMENT ON COLUMN itmob.event_members.event_id IS 'Событие';

COMMENT ON COLUMN itmob.event_members.user_id IS 'Участник';

