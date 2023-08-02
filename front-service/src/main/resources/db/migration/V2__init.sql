DROP TABLE menu_list;

create table menu_list(id bigserial primary key, name varchar(255), photo_link text, href text, visibility boolean);
insert into menu_list (name, photo_link, href, visibility) values
('Главная страница', 'images_menu/558900.PNG', '#!/', true),
('Поиск события', 'images_menu/459947.PNG', '#!/events', true),
('Добавить событие', 'images_menu/117885.PNG', '#!/events-card', false),
('Личный кабинет', 'images_menu/12126177.PNG', '#!/profile', false);