create table event_types
(
    id               bigserial primary key,
    name             varchar(255),
    photo_link       text,
    description      text,
    description_full text,
    create_date      timestamp,
    is_default       boolean
 );

create table events
(
    id               bigserial primary key,
    event_types_id   bigint not null references event_types (id),
    event_types_name varchar(255),
    title            varchar(255),
    author           varchar(255) not null,
    description      text,
    event_date       timestamp,
    event_place      varchar(255),
    created_at       timestamp default current_timestamp,
    updated_at       timestamp default current_timestamp
);

create table event_members
(
    id              bigserial primary key,
    event_id        bigint not null references events (id),
    title           varchar(255) not null,
    username        varchar(255) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table menu_list
(
    id           bigserial primary key,
    name         varchar(255),
    photo_link   text,
    href         text
 );

insert into event_types (id, name, photo_link, description, description_full, create_date, is_default) values
(1, 'Стартап-проекты', 'events/images_event_types/event_types_1.JPG', 'Здесь люди ищат Стартап-проекты и создают свои команды.', 'Здесь люди ищат Стартап-проекты и создают свои команды. Создатели Стартапов уже позаботились о тебе и разместили свои проекты здесь. Его видят другие люди и присоединяются. Создатель события сам фильтрует участников и общается с ними в чате, присоединенным к событию – договаривается и классно создает свой Стартапчик.', '1999-01-08 04:05:06', true),
(2, 'Стартап-встречи', 'events/images_event_types/event_types_2.JPG', 'Здесь люди общаются. Создатели стартапов уже позаботились о тебе и разместили свои проекты на этом сайте, собрали команду и хотят с ней встретиться.', 'Здесь люди общаются. Создатели стартапов уже позаботились о тебе и разместили свои проекты на этом сайте, собрали команду и хотят с ней встретиться. Это событие видят другие люди и тоже присоединяются. Создатель события сам фильтрует участников и общается с ним в чате, присоединенным к событию – договаривается и классно проводит рабочее время с командой и новыми участниками встречи.', '1999-01-08 04:05:06', false),
(3, 'Стартап-демонстрации', 'events/images_event_types/event_types_3.JPG', 'Здесь люди проводят демонстрации своих проектов и вебинары по ним. Создатели стартапов уже позаботились о тебе и разместили свои проекты на этом сайте, собрали команду и даже создали продукт и уже хотят поделиться тем что сделали.', 'Здесь люди проводят демонстрации своих проектов и вебинары по ним. Создатели стартапов уже позаботились о тебе и разместили свои проекты на этом сайте, собрали команду и даже создали продукт и уже хотят поделиться тем что сделали. Это событие видят другие люди и тоже присоединяются. Создатель события сам фильтрует участников и общается с ним в чате, присоединенным к событию – договаривается и организует участникам события демонстрацию проекта.', '1999-01-08 04:05:06', false);

insert into events (event_types_id, title, author, description, event_date, event_place) values
(3, 'Test', 'Test', 'Здесь будет описание Discovery Test', '2023-09-30 04:05:06', 'AT&T Park, Москва'),
(2, 'Discovery Day at Park', 'Park', 'Здесь будет описание Discovery Day at Park', '2023-09-30 04:05:06', 'AT&T Park, Москва'),
(2, 'Loufer fest', 'Loufer', 'Здесь будет описание Loufer fest', '2023-09-08 14:30:06', 'Asheville, Москва'),
(2, 'Lincoln Hall', 'Lincoln', 'Здесь будет описание Lincoln Hall', '2023-10-08 04:15:06', 'Lincoln Hall, Москва'),
(2, 'The Orange Peel', 'Peel', 'Здесь будет описание The Orange Peel', '2023-09-12 10:00:06', 'The Orange Peel, Москва'),
(2, 'Jefferson Theater', 'Jefferson', 'Здесь будет описание Jefferson Theater', '2023-09-14 04:05:06', 'Charlottesville, Москва'),
(1, 'Merriweather', 'Jefferson', 'Здесь будеь описание Merriweather', '2023-08-31 11:00:06', 'Merriweather, Москва'),
(1, 'Jeffersons Startup', 'Jefferson', 'Здесь будет описание Jefferson Theater', '2023-08-30 12:30:06', 'Jeffersons, Москва'),
(1, 'ITMob Startup', 'ITMob', 'Здесь будет описание Jefferson Theater', '2023-08-27 17:05:06', 'ITMob, Москва');

