alter table contas_receber add constraint FK_clienteconta FOREIGN key (idcliente) references cliente(id);
