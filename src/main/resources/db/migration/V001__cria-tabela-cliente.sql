create table cliente (
	id bigserial not null,
	nome character varying(60) not null,
	email character varying(255) not null, 
	telefone character varying(20) not null,
	
	primary key (id)
);