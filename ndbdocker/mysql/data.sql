/*CREATE SCHEMA  IF NOT EXISTS `ndb_dev` ;
CREATE USER  IF NOT EXISTS 'ndb_app'@'localhost' IDENTIFIED BY 'Ndb_test1';
GRANT SELECT, INSERT, UPDATE, DELETE ON ndb_dev.* TO 'ndb_app'@'localhost'; */
GRANT SELECT, INSERT, UPDATE, DELETE ON ndb_dev.* TO 'ndb_app'@'%'; 
create table IF NOT EXISTS ndb_dev.country (
	code varchar(10) primary key,
    name varchar(50) 
);
create table IF NOT EXISTS ndb_dev.state (
	id int primary key auto_increment,
	code varchar(10) not null,
    name varchar(50) not null,
    country_code varchar(50) not null
);

create table IF NOT EXISTS ndb_dev.district (
	id int primary key auto_increment,
    name varchar(50) not null,
    state_id integer not null
);

create table IF NOT EXISTS ndb_dev.city (
	id int primary key auto_increment,
    name varchar(50) not null,
    district_id int
);

create table IF NOT EXISTS ndb_dev.person (
	person_id int primary key auto_increment,
	first_name varchar(50) not null,
    last_name varchar(50),
	address1 varchar(100),
	address2 varchar(100),
    zip_code integer(6),
	village varchar(50),
	city varchar(50),
	district varchar(50),
	state varchar(50),
	about varchar(5000),
	country varchar(50) default 'India'
);

create table IF NOT EXISTS ndb_dev.timeline (
	id int primary key auto_increment,
	person_id int not null,
	description varchar (5000),
	created_datetime datetime
);

create table IF NOT EXISTS ndb_dev.review (
	id int primary key auto_increment,
	person_id int ,
	description varchar (5000),
	created_datetime datetime,
	rating int (1),
	review_type varchar(50)
);

alter table ndb_dev.state add constraint fk_country_code foreign key 
(country_code) references ndb_dev.country(code);

alter table ndb_dev.district add constraint fk_dist_state foreign key 
(state_id) references ndb_dev.state(id);

alter table ndb_dev.city add constraint fk_city_district foreign key 
(district_id) references ndb_dev.district(id);

alter table ndb_dev.timeline add constraint fk_tl_person_id foreign key 
(person_id) references ndb_dev.person(person_id);

alter table ndb_dev.review add constraint fk_rw_person_id foreign key 
(person_id) references ndb_dev.person(person_id);

INSERT INTO ndb_dev.country VALUES ('IN','INDIA');
