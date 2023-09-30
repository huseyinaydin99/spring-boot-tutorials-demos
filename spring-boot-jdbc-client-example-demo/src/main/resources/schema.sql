DROP TABLE IF EXISTS Book;

CREATE TABLE `Book`(
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);