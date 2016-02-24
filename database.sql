DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `age` int NOT NULL,
  `is_admin` bit NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT now(),
  PRIMARY KEY (`id`)
)DEFAULT CHARSET=utf8;

INSERT INTO `user`(`name`, `age`, `is_admin`)
VALUES ('Admin',34, 1),('helper',32, 1);

INSERT INTO `user`(`name`, `age`) 
VALUES ('Владислав', 10), ('Окорок', 52), ('Йоко Оно', 0), ('Каджубей', 115),
('Оно', 32), ('Ид', 2),('Орк', 36), ('Квак', 8),('Эл', 100), ('Alex', 22),
('Lama', 99), ('Iren', 43), ('Сеня', 10), ('Константин Владимирович', 10),
('Владислав Князев', 10), ('Пилагея Васильевна', 10),('Mickle Jackson', 10), 
('Jowy', 10),('Mama Lama', 10), ('Alex Koh', 10);

