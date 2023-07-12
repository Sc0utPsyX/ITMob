create table events (id bigserial primary key, event varchar(255), description varchar(255), event_date timestamp, event_place varchar(255));
insert into events (event, description, event_date, event_place) values
('Discovery Day at Park', 'Discovery Day at Park', '1999-01-08 04:05:06', 'AT&T Park, Москва'), ('Loufer fest', 'Loufer fest', '1999-01-08 04:05:06', 'Asheville, Москва'), ('Jefferson Theater', 'Jefferson Theater', '1999-01-08 04:05:06', 'Charlottesville, Москва');

