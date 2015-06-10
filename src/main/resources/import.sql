create database study character set utf8 collate utf8_general_ci;

grant select, insert, update, delete, create, drop on study.* to 'study'@'localhost' identified by 'study';
grant select, insert, update, delete, create, drop on study .* to 'study'@'%' identified by 'study';

insert into sequence values ('comment', 0);