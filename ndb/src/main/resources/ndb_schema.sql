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
alter table state add constraint fk_country_code foreign key 
(country_code) references country(code);

insert into state values (1, 'TN', 'Tamil Nadu', 'IN');
insert into state values (2, 'KL', 'Kerala', 'IN' );
insert into state values (3, 'AP', 'Andhara Pradesh', 'IN');
insert into state values (4, 'KA', 'Karnataka', 'IN');