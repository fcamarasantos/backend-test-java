create table veiculos
(

    id     SERIAL       not null,
    marca  varchar(100) not null,
    placa  varchar(100) not null,
    cor    varchar(10)  not null,
    modelo varchar(100) not null,
    tipo   varchar(20)  not null,
    primary key (id)
);