CREATE TABLE ESTADO(
                       ID BIGINT NOT NULL AUTO_INCREMENT,
                       NOME VARCHAR(80) NOT NULL,
                       primary key (ID)
);

insert into ESTADO (nome) select distinct CIDADE.nome_estado from CIDADE;
alter table CIDADE add column estado_id bigint not null ;
update CIDADE c set c.estado_id = (select e.id from estado e where e.nome = c.nome_estado);

alter table CIDADE add constraint FK_CIDADE_ESTADO foreign key (estado_id) references ESTADO(ID);

alter table CIDADE drop column nome_estado;

alter table CIDADE alter column nome_cidade RENAME to "nome";