CREATE TABLE curso(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    categoria varchar(50) not null,
    primary key (id)
);

INSERT INTO curso (id, nome, categoria) VALUES (1, 'Java', 'Programação');