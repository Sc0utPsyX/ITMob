CREATE SCHEMA IF NOT EXISTS itmob;

CREATE TABLE itmob.event_types (
	id                   serial  NOT NULL  ,
	name                 varchar(300)    ,
	description          text  NOT NULL  ,
	create_date          date DEFAULT CURRENT_DATE NOT NULL  ,
	is_default           boolean DEFAULT False NOT NULL  ,
	CONSTRAINT pk_event_types PRIMARY KEY ( id ),
	CONSTRAINT unq_event_types UNIQUE ( name )
 );

CREATE TABLE itmob.events (
	id                   bigserial  NOT NULL  ,
	event                varchar(250)  NOT NULL  ,
	description          text  NOT NULL  ,
	create_date          date DEFAULT CURRENT_DATE NOT NULL  ,
	owner_id             bigint  NOT NULL  ,
	event_date           timestamp  NOT NULL  ,
	event_type           int  NOT NULL  ,
	CONSTRAINT pk_events PRIMARY KEY ( id )
 );

CREATE  TABLE itmob.event_location (
	id                   serial  NOT NULL  ,
	address              varchar(250)    ,
	detail               varchar(2000)    ,
	CONSTRAINT pk_event_location PRIMARY KEY ( id )
 );


INSERT INTO itmob.event_types ( id, name, description, create_date, is_default ) VALUES ( 0, '3FAYA98Y14J351761', 'Anne is walking. Anne bought new car. Tony bought new car. John is shopping. John has free time. ', '2014-08-15', false );
INSERT INTO itmob.event_types ( id, name, description, create_date, is_default ) VALUES ( 1, '3WSA8BS215Y220644', 'Anne is walking. Anne is walking. John bought new car. Tony has free time. Anne has free time. ', '2011-04-01', false );
INSERT INTO itmob.event_types ( id, name, description, create_date, is_default ) VALUES ( 2, '5GAF4GW016D692343', 'John bought new car. John bought new car. John bought new car. Tony has free time. Anne has free time. ', '2001-06-02', false );
INSERT INTO itmob.event_types ( id, name, description, create_date, is_default ) VALUES ( 3, '1FXHJH1114A327155', 'John bought new car. Tony is walking. John is walking. John has free time. Tony has free time. ', '2016-02-08', false );

INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 0, 'Rapzapin Direct Company', 'Anne is walking. Anne bought new car. Tony bought new car. John is shopping. John has free time. ', '2014-08-15', 73, '2014-08-11', 7 );
INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 1, 'Klibanollover WorldWide Company', 'Anne is walking. Anne is walking. John bought new car. Tony has free time. Anne has free time. ', '2011-04-01', 91, '2003-07-14', 5 );
INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 2, 'Klierplar WorldWide Group', 'John bought new car. John bought new car. John bought new car. Tony has free time. Anne has free time. ', '2001-06-02', 91, '2001-10-06', 7 );
INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 3, 'Unkiledax Holdings ', 'John bought new car. Tony is walking. John is walking. John has free time. Tony has free time. ', '2016-02-08', 67, '2012-03-27', 1 );
INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 4, 'Frozapanor Holdings Corp.', 'Anne has free time. Anne bought new car. John is walking. John has free time. ', '2001-05-05', 7, '2009-10-11', 3 );
INSERT INTO itmob.events ( id, event, description, create_date, owner_id, event_date, event_type ) VALUES ( 5, 'Zeerobedor WorldWide Corp.', 'Anne bought new car. Anne is shopping. Tony is shopping. John is walking. Anne is walking. ', '2011-08-21', 2, '2018-11-25', 6 );

INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 0, '423 South White Cowley Parkway', 'Anne bought new car. Anne is shopping. Tony bought new car. Tony is walking. Tony has free time. ' );
INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 1, '164 South Rocky Hague Blvd.', 'John is shopping. John is shopping. John bought new car. John is walking. ' );
INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 2, '543 North Green Hague Way', 'Anne bought new car. Anne is walking. John bought new car. John has free time. Tony is walking. ' );
INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 3, '74 East Green Clarendon Way', 'John bought new car. Anne is walking. Anne is walking. John is shopping. John is shopping. ' );
INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 4, '78 North Green Old Way', 'Tony bought new car. Tony has free time. Tony bought new car. ' );
INSERT INTO itmob.event_location ( id, address, detail ) VALUES ( 5, '351 South Rocky Milton Freeway', 'Anne bought new car. Tony is shopping. Tony is shopping. Tony has free time. Anne bought new car. ' );
