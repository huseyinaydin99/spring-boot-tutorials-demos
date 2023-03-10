CREATE DATABASE IF NOT EXISTS huseyin_aydin_db DEFAULT CHARACTER SET utf8;

SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'huseyin_aydin_db';

SHOW DATABASES LIKE 'huseyin_aydin_db';

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `huseyin_aydin_db`.`users` (`id`, `created_at`, `created_by`, `email_address`, `first_name`, `last_name`, `updated_at`, `updated_by`) VALUES ('1', '2023-03-03', '2023-03-03', 'xxx@xxx.com', 'Hüseyin', 'Aydın', '2023-03-03', '2023-03-03');

SELECT * FROM huseyin_aydin_db.users;