create table menu_list(id bigserial primary key, name varchar(255), photo_link text, href text);
insert into menu_list (name, photo_link, href) values
('Главная страница', 'images_menu/558900.PNG', '#!/'),
('Поиск события', 'images_menu/459947.PNG', '#!/events'),
('Добавить событие', 'images_menu/117885.PNG', '#!/events-card'),
('Сообщения', 'images_menu/456501.PNG', ''),
('Личный кабинет', 'images_menu/12126177.PNG', '');