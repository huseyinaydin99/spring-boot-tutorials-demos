## Terminal
docker run -d -p 3306:3306 -p 33060:33060  --name mysql-standalone -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=huseyin_aydin_db -e MYSQL_USER=root  -e MYSQL_PASSWORD=toor -d mysql:latest


mysql -u root -p
toor

SHOW VARIABLES WHERE Variable_name = 'port';

SHOW VARIABLES WHERE Variable_name = 'hostname';
SHOW VARIABLES LIKE "%port%";

//If you want to get user, you need start query in your mysql:
SELECT user(); // output your user: root@localhost
SELECT system_user(); // --

//If you want to get port your "mysql://user:pass@hostname:port/db"
SELECT @@port; //3306 is default

//If you want hostname your db, you can execute query
SELECT @@hostname;

SELECT host, user FROM mysql.user;


CREATE USER 'root'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'toor'@'%' WITH GRANT OPTION;


CREATE USER 'huseyin'@'%' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON huseyin.* TO '123'@'%' WITH GRANT OPTION;

##### MySQL Practical Command Examples #######

SHOW DATABASES;

CREATE DATABASE `huseyin_aydin_db`;
SHOW DATABASES;

DROP DATABASE `huseyin_aydin_db`;
SHOW DATABASES;

USE huseyin_aydin_db;

SHOW TABLES; 

CREATE TABLE product (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
name VARCHAR(20),
food VARCHAR(30),
confirmed CHAR(1), 
signup_date DATE);

SHOW TABLES;

DESCRIBE product;

INSERT INTO `product` (`id`,`name`,`food`,`confirmed`,`signup_date`) VALUES (NULL, "aaa", "ttt","Y", '2020-04-11');
INSERT INTO `product` (`id`,`name`,`food`,`confirmed`,`signup_date`) VALUES (NULL, "bbb", "yyy","N", '2020-04-14');
INSERT INTO `product` (`id`,`name`,`food`,`confirmed`,`signup_date`) VALUES (NULL, "ccc", "uuu","Y", '2020-04-18');
INSERT INTO `product` (`id`,`name`,`food`,`confirmed`,`signup_date`) VALUES (NULL, "ddd", "mmm","Y", '2020-04-10');

SELECT * FROM product;

UPDATE `product` 
SET 
`confirmed` = 'Y' 
WHERE `product`.`name` ='bbb';
SELECT * FROM product;

ALTER TABLE product ADD email VARCHAR(40);
DESCRIBE product;

ALTER TABLE product DROP email;
DESCRIBE product;

ALTER TABLE product ADD email VARCHAR(40) AFTER name; 
DESCRIBE product;

ALTER TABLE product DROP email;
DESCRIBE product;

DELETE from product where name='bbb';
SELECT * FROM product;

exit;
##### MySQL Practical Command Examples #######


## TERMINAL 2
cd C:\yol\spring-boot-docker-mysql-crud-example-demo
docker build -t spring-boot-docker-mysql-crud .

docker run -p 8085:8085 -t --name spring-boot-docker-mysql-crud-example-demo --link mysql-standalone:mysql spring-boot-docker-mysql-crud-example-demo


## Docker CLI - Cheat Sheet (Kopya Ka????d??) Komut ve	A????klamas??
docker images	
Lokal registry???de mevcut bulunan Image???lar?? listeler

docker ps	
Halihaz??rda ??al????makta olan Container???lar?? listeler

docker ps -a	
Docker Daemon ??zerindeki b??t??n Container???lar?? listeler

docker ps -aq	
Docker Daemon ??zerindeki b??t??n Container???lar??n ID???lerini listeler

docker pull <repository_name>/<image_name>:<image_tag>	
Belirtilen Image????? lokal registry???ye indirir. ??rnek: docker pull gsengun/jmeter3.0:1.7

docker top <container_id>	
??lgili Container???da top komutunu ??al????t??rarak ????kt??s??n?? g??sterir

docker run -it <image_id|image_name> CMD	
Verilen Image???dan terminal???i attach ederek bir Container olu??turur

docker pause <container_id>	
??lgili Container????? duraklat??r

docker unpause <container_id>	
??lgili Container pause ile duraklat??lm???? ise ??al????mas??na devam ettirilir

docker stop <container_id>	
??lgili Container????? durdurur

docker start <container_id>	
??lgili Container????? durdurulmu??sa tekrar ba??lat??r

docker rm <container_id>	
??lgili Container????? kald??r??r fakat ili??kili Volume???lara dokunmaz

docker rm -v <container_id>	
??lgili Container????? ili??kili Volume???lar ile birlikte kald??r??r

docker rm -f <container_id>	
??lgili Container????? zorlayarak kald??r??r. ??al????an bir Container ancak -f ile kald??r??labilir

docker rmi <image_id|image_name>	
??lgili Image????? siler

docker rmi -f <image_id|image_name>	
??lgili Image????? zorlayarak kald??r??r, ba??ka isimlerle Tag???lenmi?? Image???lar -f ile kald??r??labilir

docker info	
Docker Daemon???la ilgili ??zet bilgiler verir

docker inspect <container_id>	
??lgili Container???la ilgili detayl?? bilgiler verir

docker inspect <image_id|image_name>	
??lgili Image???la ilgili detayl?? bilgiler verir

docker rm $(docker ps -aq)	
B??t??n Container???lar?? kald??r??r

docker stop $(docker ps -aq)	
??al????an b??t??n Container???lar?? durdurur

docker rmi $(docker images -aq)	
B??t??n Image???lar?? kald??r??r

docker images -q -f dangling=true	
Dangling (taglenmemi?? ve bir Container ile ili??kilendirilmemi??) Image???lar?? listeler

docker rmi $(docker images -q -f dangling=true)	
Dangling Image???lar?? kald??r??r

docker volume ls -f dangling=true	
Dangling Volume???lar?? listeler

docker volume rm $(docker volume ls -f dangling=true -q)	
Danling Volume???lar?? kald??r??r

docker logs <container_id>	
??lgili Container?????n terminalinde o ana kadar olu??an ????kt??y?? g??sterir

docker logs -f <container_id>	
??lgili Container?????n terminalinde o ana kadar olu??an ????kt??y?? g??sterir ve -f follow parametresi ile o andan sonra olu??an loglar?? da g??stermeye devam eder

docker exec <container_id> <command>	
??al????an bir Container i??inde bir komut ko??turmak i??in kullan??l??r

docker exec -it <container_id> /bin/bash	
??al????an bir Container i??inde terminal a??mak i??in kullan??l??r. ??lgili Image???da /bin/bash bulundu??u varsay??m?? ile

docker attach <container_id>	
??nceden detached modda -d ba??lat??lan bir Container???a attach olmak i??in kullan??l??r
