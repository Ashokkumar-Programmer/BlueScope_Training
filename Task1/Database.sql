create database BlueScope;

use BlueScope;

create table student(rollno bigint primary key, studentname varchar(50), dept varchar(50), phonenumber bigint);

alter table student drop column batch;

select * from student;

delete from student;