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

create table files(userrole varchar(30) primary key, upload_file LONGBLOB);

#C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/student.csv

insert into files values("checker", load_file(''));

update files set upload_file=load_file("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Checker.xlsx") where userrole="checker";

update files set upload_file=load_file("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Maker.xlsx") where userrole="maker";

SET GLOBAL local_infile = 1;

SELECT userrole, LENGTH(upload_file) FROM files WHERE userrole = 'maker';

select * from files;

select * from bank;
