create table country (
	code varchar(10) primary key,
    name varchar(50) 
);
create table state (
	id int primary key,
	code varchar(10),
    name varchar(50) ,
    country_code varchar(50) 
);
alter table state add constraint fk_country_code foreign key 
(country_code) references country(code);