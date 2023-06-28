Create table cliente(
id int not null primary key auto_increment,
nome_cliente varchar(45)
);
insert into cliente(id,nome_cliente) values (1,'gabriel');
insert into cliente(id,nome_cliente) values (2,'raul');
insert into cliente(id,nome_cliente) values (3,'vinicius');


create table contas_receber(
id int not null primary key auto_increment,
idcliente int not null,
dataconta date,
valor_conte decimal(12,2)

);
insert into contas_receber(id,idcliente,dataconta,valor_conte) values (10,1,'2005-07-16',79.4);
insert into contas_receber(id,idcliente,dataconta,valor_conte) values (20,2,'2005-07-17',80.4);
insert into contas_receber(id,idcliente,dataconta,valor_conte) values (30,3,'2005-07-18',90.4);


