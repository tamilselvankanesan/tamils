create table country (
	code varchar(10) primary key,
    name varchar(50) 
);
create table state (
	id int primary key auto_increment,
	code varchar(10) not null,
    name varchar(50) not null,
    country_code varchar(50) not null
);

create table district (
	id int primary key auto_increment,
    name varchar(50) not null,
    state_id integer not null
);

create table city (
	id int primary key auto_increment,
    name varchar(50) not null,
    state_id varchar(50) not null
);

create table person (
	person_id int primary key auto_increment,
	first_name varchar(50) not null,
    last_name varchar(50),
	address1 varchar(100),
	address2 varchar(100),
    zip_code integer(6),
	village varchar(50),
	city_id integer,
	district varchar(50),
	state_id integer,
	about varchar(5000),
	country_code varchar(10) default 'IN'
);

create table timeline (
	id int primary key auto_increment,
	person_id int not null,
	description varchar (5000),
	created_datetime datetime
);

create table review (
	id int primary key auto_increment,
	person_id int ,
	description varchar (5000),
	created_datetime datetime,
	rating int (1),
	review_type varchar(50)
);

alter table state add constraint fk_country_code foreign key 
(country_code) references country(code);

alter table district add constraint fk_dist_state foreign key 
(state_id) references state(id);

alter table person add constraint fk_pr_country_code foreign key 
(country_code) references country(code);

alter table person add constraint fk_pr_state foreign key 
(state_id) references state(id);

alter table person add constraint fk_pr_city foreign key 
(city_id) references city(id);

alter table timeline add constraint fk_tl_person_id foreign key 
(person_id) references person(person_id);



insert into state values (1, 'TN', 'Tamil Nadu', 'IN');
insert into state values (2, 'KL', 'Kerala', 'IN' );
insert into state values (3, 'AP', 'Andhara Pradesh', 'IN');
insert into state values (4, 'KA', 'Karnataka', 'IN');