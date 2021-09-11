#drop database Preguntas
create database Preguntas;
use Preguntas;
#---------------------usuario-----------
create table HISTORIAL(
ID int primary key auto_increment not null,
Player varchar(100) not null,
Date date not null,
Score integer not null,
Level integer not null
);
select * from HISTORIAL;