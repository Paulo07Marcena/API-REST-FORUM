CREATE TABLE resposta(
    id bigint not null,
    mensagem varchar(300) not null,
    data_criacao datetime not null,
    topico_id bigint not null,
    usuario_id bigint not null,
    solucao boolean not null,
    primary key (id),
    foreign key (topico_id) references topico(id),
    foreign key (usuario_id) references usuario(id)
);

