create table registros_entrada_saida
(

    id                        SERIAL   not null,
    estabelecimento_id        bigint   not null,
    veiculo_id                bigint   not null,
    data_entrada              timestamp not null,
    data_saida                timestamp,


    primary key (id),
    constraint fk_registros_entrada_saida_estabelecimento_id foreign key (estabelecimento_id) references estabelecimentos (id),
    constraint fk_registros_entrada_saida_veiculo_id foreign key (veiculo_id) references veiculos (id)
);