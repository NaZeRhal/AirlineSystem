/*insert into `new-airlines`.user_type*/
INSERT INTO `new-airlines`.user_type (id, user_type_name)
VALUES (1, 'MODERATOR');
INSERT INTO `new-airlines`.user_type (id, user_type_name)
VALUES (2, 'ADMINISTRATOR');
INSERT INTO `new-airlines`.user_type (id, user_type_name)
VALUES (3, 'DISPATCHER');

/*insert into `new-airlines`.user*/
INSERT INTO `new-airlines`.user (id, first_name, last_name, login, password, user_type_id)
VALUES (1, 'moderator', 'moderator', 'moderator', 'moderator', 1);
INSERT INTO `new-airlines`.user (id, first_name, last_name, login, password, user_type_id)
VALUES (2, 'admin', 'admin', 'admin', 'admin', 2);
INSERT INTO `new-airlines`.user (id, first_name, last_name, login, password, user_type_id)
VALUES (3, 'dispatcher', 'dispatcher', 'dispatcher', 'dispatcher', 3);

/*insert into `new-airlines`.professions*/
INSERT INTO `new-airlines`.professions (id, profession_name)
VALUES (1, 'PILOT');
INSERT INTO `new-airlines`.professions (id, profession_name)
VALUES (2, 'NAVIGATOR');
INSERT INTO `new-airlines`.professions (id, profession_name)
VALUES (3, 'RADIOMAN');
INSERT INTO `new-airlines`.professions (id, profession_name)
VALUES (4, 'STEWARD');

/*insert into `new-airlines`.airport*/
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (1, 'Moscow', 'VKO');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (2, 'Minsk', 'MSQ');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (3, 'Kiev', 'IEV');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (4, 'Krasnodar', 'KRR');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (5, 'Smolensk', 'LNX');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (6, 'St. Petersburg', 'LED');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (7, 'Rostov', 'ROV');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (8, 'Simferopol', 'SIP');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (9, 'Vologda', 'VLG');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (10, 'Moscow', 'DME');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (11, 'Grozniy', 'GRV');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (12, 'Murmansk', 'MUR');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (13, 'Vitebsk', 'VTB');
INSERT INTO `new-airlines`.airport (id, city, airport_code)
VALUES (14, 'Mogilev', 'MGL');

/*insert into `new-airlines`.crew_man*/
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (1, 'Darius', 'Rosiello', '1977-04-21', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (2, 'Tabina', 'Trenouth', '1985-12-06', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (3, 'Bette-ann', 'Flecknoe', '1989-12-25', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (4, 'Jenine', 'Redman', '1978-05-31', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (5, 'Борис', 'Годунов', '1982-07-01', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (6, 'Mario', 'Stroban', '1985-08-11', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (7, 'Mommy', 'Albert', '1986-07-13', 3);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (8, 'Chrissy', 'Skoggings', '1990-03-15', 2);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (9, 'Romonda', 'Wake', '1978-04-07', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (10, 'Susanna', 'Cilintano', '1988-04-10', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (11, 'Milana', 'Davinci', '1994-06-13', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (12, 'Григорий', 'Малевич', '1989-12-12', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (13, 'Marilyn', 'Mans', '1979-07-11', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (14, 'Bear', 'Grylls', '1976-07-20', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (15, 'Natali', 'Kumamonovich', '1991-09-08', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (16, 'Петр', 'Петров', '1984-05-12', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (17, 'Maksim', 'Rzhevutski', '1985-06-12', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (18, 'Анастасия', 'Кумамонова', '1994-02-12', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (19, 'Петр', 'Мамонов', '1983-05-12', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (20, 'Анастасия', 'Ржевуцкая', '1995-11-05', 2);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (21, 'Денис', 'Денисов', '1991-06-13', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (22, 'Иван', 'Смыслов', '1988-03-25', 2);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (23, 'Михаил', 'Светлов', '1989-06-15', 3);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (24, 'Светлана', 'Летучая', '1989-06-15', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (25, 'Валентин', 'Чадовой', '1990-12-13', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (26, 'Михаил', 'Додиков', '1980-12-12', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (27, 'Петр', 'Новиков', '1989-03-15', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (28, 'Мария', 'Голубкина', '1992-02-25', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (29, 'Илья', 'Привальный', '1987-12-12', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (30, 'Мирон', 'Миронов', '1990-06-23', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (31, 'Клара', 'Захарова', '1987-06-13', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (32, 'Павел', 'Стройный', '1985-03-23', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (33, 'Мария', 'Зеленая', '1990-07-15', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (34, 'Петр', 'Малинов', '1983-06-16', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (35, 'Валентина', 'Буханкина', '1991-02-03', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (36, 'Ольга', 'Радужная', '1994-09-06', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (37, 'Регина', 'Толстая', '1996-06-06', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (38, 'Сара', 'Блюм', '1992-09-16', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (39, 'Джон', 'Сноу', '1984-11-23', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (40, 'Макар', 'Иванов', '1981-04-15', 3);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (41, 'Константин', 'Ермолов', '1986-02-12', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (42, 'Андрей', 'Колобов', '1989-12-16', 1);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (43, 'Андрей', 'Курсоков', '1985-11-15', 4);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (44, 'Петр', 'Петриков', '1988-02-02', 3);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (45, 'Григорий', 'Колбаскин', '1979-09-07', 2);
INSERT INTO `new-airlines`.crew_man (id, first_name, last_name, date_of_birth, profession_id)
VALUES (46, 'Pirog', 'Pirogov', '1985-12-26', 4);
