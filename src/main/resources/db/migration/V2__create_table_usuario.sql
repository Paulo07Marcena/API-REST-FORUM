CREATE TABLE usuario(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

INSERT INTO usuario (id, nome, email) VALUES (1, 'ana', 'ana@gmail.com');