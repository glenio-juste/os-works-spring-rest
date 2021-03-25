create table ordem_servico (
	id bigserial not null,
    cliente_id bigint not null,
    descricao character varying not null,
    preco numeric(10,2) not null,
    status character varying(20) not null,
    data_abertura DATE not null,
    data_finalizacao DATE,
    
    primary key (id)
);

alter table ordem_servico add constraint fk_ordem_servico_cliente  
foreign key (cliente_id)  references cliente (id);