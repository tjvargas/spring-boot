INSERT INTO CLINICA (id, nome, cnpj, telefone) VALUES (1, 'Juca Pet', '24950568000131', '48991680107');

INSERT INTO USUARIO (login, senha) values ('funcionario1', '$2a$10$yOntx7leM6T6FQoEPVJ4uuZlpuKyvtXFf95dUFVUHZP..m/9xGffC');
INSERT INTO USUARIO (login, senha) values ('veterinario1', '$2a$10$yOntx7leM6T6FQoEPVJ4uuZlpuKyvtXFf95dUFVUHZP..m/9xGffC');

INSERT INTO CLIENTE_DETALHE (id, nome, telefone, cpf, email) values (1, 'thiago', '489999999',  '59682641012', 'thiago@vargastechs.com');
INSERT INTO CLIENTE (id, clinica_id, cliente_detalhe_id) values (1, 1,1);

INSERT INTO PET (id, cliente_id, nome, raca, data_nascimento) values (1, 1, 'kioko', 'cocker', '1995-05-05');

INSERT INTO AGENDAMENTO (id, pet_id, data_agendamento, data_termino) values (1, 1, '2021-05-05 05:00:00', '2021-05-05 05:50:00');


INSERT INTO RECURSO (id, clinica_id, nome, descricao) values (1,1, 'Jumberlâncio', 'Veterinário gastrointestinal');
INSERT INTO RECURSO_HUMANO (id, cpf, funcao ) values (1, '07455286945', 'VETERINARIO');
INSERT INTO AGENDAMENTO_RECURSOS (agendamento_id, recursos_id) values (1,1);


