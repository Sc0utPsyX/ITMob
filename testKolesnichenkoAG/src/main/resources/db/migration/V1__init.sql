create SCHEMA IF NOT EXISTS itmob;

create TABLE itmob.event_types (
	id                   serial  NOT NULL  ,
	name                 varchar(300)    ,
	description          text  NOT NULL  ,
	create_date          date DEFAULT CURRENT_DATE NOT NULL  ,
	is_default           boolean DEFAULT False NOT NULL  ,
	CONSTRAINT pk_event_types PRIMARY KEY ( id ),
	CONSTRAINT unq_event_types UNIQUE ( name )
 );

create TABLE itmob.events (
	id                   bigserial  NOT NULL  ,
	title                varchar(250)  NOT NULL  ,
	description          text  NOT NULL  ,
	create_date          date DEFAULT CURRENT_DATE NOT NULL  ,
	owner_id             bigint  NOT NULL  ,
	event_date           timestamp  NOT NULL  ,
	type_id              int  NOT NULL  ,
	event_location       int  NOT NULL  ,
	CONSTRAINT pk_events PRIMARY KEY ( id )
 );

create  TABLE itmob.event_location (
	id                   bigserial NOT NULL  ,
	address              varchar(250)    ,
	detail               varchar(2000)    ,
	CONSTRAINT pk_event_location PRIMARY KEY ( id )
 );


insert into itmob.event_types ( id, name, description, create_date, is_default ) values ( 0, '3FAYA98Y14J351761', 'Anne is walking. Anne bought new car. Tony bought new car. John is shopping. John has free time. ', '2014-08-15', false );
INSERT INTO itmob.event_types ( id, name, description, create_date, is_default ) VALUES ( 1, '3WSA8BS215Y220644', 'Anne is walking. Anne is walking. John bought new car. Tony has free time. Anne has free time. ', '2011-04-01', false );

insert into itmob.events ( id, title, description, create_date, owner_id, event_date, type_id, event_location) values ( 0, 'Rapzapin Direct Company', 'Anne is walking. Anne bought new car. Tony bought new car. John is shopping. John has free time. ', '2014-08-15', 73, '2014-08-11', 0, 0 );
insert into itmob.events ( id, title, description, create_date, owner_id, event_date, type_id, event_location) values ( 1, 'Klibanollover WorldWide Company', 'Anne is walking. Anne is walking. John bought new car. Tony has free time. Anne has free time. ', '2011-04-01', 91, '2003-07-14', 1, 1 );
insert into itmob.events ( id, title, description, create_date, owner_id, event_date, type_id, event_location) values ( 2, 'Klierplar WorldWide Group', 'John bought new car. John bought new car. John bought new car. Tony has free time. Anne has free time. ', '2001-06-02', 91, '2001-10-06', 0, 2 );

insert into itmob.event_location ( id, address, detail ) values ( 0, '423 South White Cowley Parkway', 'Anne bought new car. Anne is shopping. Tony bought new car. Tony is walking. Tony has free time. ' );
insert into itmob.event_location ( id, address, detail ) values ( 1, '164 South Rocky Hague Blvd.', 'John is shopping. John is shopping. John bought new car. John is walking. ' );
insert into itmob.event_location ( id, address, detail ) values ( 2, '543 North Green Hague Way', 'Anne bought new car. Anne is walking. John bought new car. John has free time. Tony is walking. ' );
