CREATE DATABASE IF NOT EXISTS huseyin_aydin_db DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `huseyin_aydin_db`.`user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'huseyin_aydin_db';
SHOW DATABASES LIKE 'huseyin_aydin_db';
INSERT INTO `huseyin_aydin_db`.`user` (`id`, `name`) VALUES ('1', 'Hüseyin');
INSERT INTO `huseyin_aydin_db`.`user` (`id`, `name`) VALUES ('2', 'Hamzulahettin');
INSERT INTO `huseyin_aydin_db`.`user` (`id`, `name`) VALUES ('3', 'Zımzımettin');
SELECT * FROM huseyin_aydin_db.user;