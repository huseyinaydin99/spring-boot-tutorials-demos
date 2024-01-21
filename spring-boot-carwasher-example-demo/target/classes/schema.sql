create table car(
	id bigint not null, 
	model varchar(255),
	color varchar(255),
	driver_id bigint,
	year int
);

create table driver(
	id bigint not null,
	name varchar(255),
	surname varchar(255),
	phone varchar(255),
	address varchar(255)
);

create table carWash(
	id bigint not null,
	price bigint,
	date Date,
	car_id bigint
);

create table users(
	username varchar(128) primary key not null,
	password varchar(512) not null,
	enabled boolean not null
);

create table authorities(
	username varchar(128) not null,
	authority varchar(128) not null
);

alter table car add constraint constraint1 primary key(id);
alter table driver add constraint constraint2 primary key(id);
alter table car add constraint constraint3 foreign key(driver_id) references driver;
alter table carWash add constraint constraint4 foreign key(car_id) references car;
	
