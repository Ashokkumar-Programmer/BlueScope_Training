create database BlueScope;

use BlueScope;

create table student(rollno bigint primary key, studentname varchar(50), dept varchar(50), phonenumber bigint);

select * from student;




create table bank (id int primary key auto_increment, name varchar(30), party_code varchar(30), parent_code varchar(30), entity_code varchar(30), status varchar(40));

insert into bank(name, party_code, parent_code, entity_code, status) values("ashok", "kotak01", "kotak", "bank", "approved");
insert into bank(name, party_code, parent_code, entity_code, status) values("kishore", "bsit01", "bsit", "corporate", "new");
insert into bank(name, party_code, parent_code, entity_code, status) values("karthik", "bsit02", "bsit", "corporate_user", "new");
insert into bank(name, party_code, parent_code, entity_code, status) values("logash", "bsit03", "bsit", "corporate_user", "pending");
insert into bank(name, party_code, parent_code, entity_code, status) values("hari", "kotak02", "kotak", "bank_user", "pending");

alter table bank auto_increment=1;

create table files(userrole varchar(30) primary key, upload_file MEDIUMBLOB);

#C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/student.csv

insert into files values("maker", load_file(''));

select * from bank;

delete from files;

drop table files;