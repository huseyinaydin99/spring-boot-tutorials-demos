CREATE DATABASE IF NOT EXISTS huseyin_aydin_db;

USE huseyin_aydin_db;

CREATE TABLE employee (
	id INT(50) NOT NULL AUTO_INCREMENT, 
	name VARCHAR(200) DEFAULT NULL, 
	position VARCHAR(200) DEFAULT NULL,
	department VARCHAR(100) DEFAULT NULL,
	salary INT(100) DEFAULT NULL,
	PRIMARY KEY (id)
);

INSERT INTO employee (id, name, position, department, salary) VALUES (1, 'Hüseyin Aydın', 'Lead', 'Administration', 13000);

INSERT INTO employee (id, name, position, department, salary) VALUES (2, 'Hasan Aydın', 'Associate', 'Human Resource', 11000);

INSERT INTO employee (id, name, position, department, salary) VALUES (3, 'Yusuf', 'Associate', 'Software', 9000);

INSERT INTO employee (id, name, position, department, salary) VALUES (4, 'Ahmet', 'Manager', 'Software', 12000);

INSERT INTO employee (id, name, position, department, salary) VALUES (5, 'Süleyman', 'Senior Manager', 'Human Resource', 16000);

INSERT INTO employee (id, name, position, department, salary) VALUES (6, 'Yavuz', 'Lead', 'Software', 14000);

SELECT * FROM employee;


CALL findAllEmployees();

CALL findEmployeeByDepartment("Software");

CALL findEmployeeCountByPosition('Lead');