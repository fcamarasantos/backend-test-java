create table estabelecimentos
(

    id               SERIAL       not null,
    nome             varchar(100) not null,
    cnpj             varchar(14)  not null UNIQUE,
    telefone         varchar(15),
    num_vagas_motos  integer      not null,
    num_vagas_carros integer      not null,


--  Endereco embutido no objeto
    logradouro       varchar(100) not null,
    bairro           varchar(100) not null,
    cep              varchar(8)   not null,
    complemento      varchar(100),
    numero           varchar(20),
    uf               char(2)      not null,
    cidade           varchar(100) not null,

    primary key (id)
);