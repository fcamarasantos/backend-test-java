INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('VOLKSWAGEN','GOLF GTI','PRETO','ABC1D231','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('VOLKSWAGEN','JETTA GLI','BRANCO','FGH5J231','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('VOLKSWAGEN','FUSCA','VERMELHO','TYU1D456','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('VOLKSWAGEN','TIGUAN','BRANCO','KIL1D331','CARRO');

INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('HONDA','CIVIC SI','PRETO','ATE1Y234','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('HONDA','FIT','BRANCO','JUK8L487','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('HONDA','ACCORD','VERMELHO','RFV1V123','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('HONDA','WRX','BRANCO','KIQ8H587','CARRO');

INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('TOYOTA','COROLLA','PRATA','POI7P897','CARRO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('TOYOTA','ETIOS','BRANCO','USX8Z512','CARRO');

INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('KAWAZAKI','H2R','CARBONO','UJZ8S258','MOTO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('YAMAHA','MT-09','PRETO','URV5U167','MOTO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('YAMAHA','MT-07','AZUL','PLM9O514','MOTO');
INSERT INTO veiculo(marca, modelo, cor, placa, tipo) VALUES('YAMAHA','MT-03','BRANCO','UHV7Y158','MOTO');

INSERT INTO estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro) VALUES('Estacionamento do Rafael', '14253647586941','Rua Teodoro Sampaio, 12, SP', '11985634578',10,40);
INSERT INTO estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro) VALUES('Estacionamento do Leonardo', '45678912336985','Rua Washington Luiz, 456, SP', '11985624761',20,80);
INSERT INTO estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro) VALUES('Estacionamento do Michelangelo', '14725836985214','Rua Marechal Kleber, 87, SP', '11982514376',50,15);
INSERT INTO estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro) VALUES('Estacionamento do Donatelo', '75315985245625','Rua São Lucas Tadeu, 5, SP', '11988996475',100,70);
INSERT INTO estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro) VALUES('Estacionamento do Splinter', '75123455245625','Rua Padre Afonso, 78, SP', '11988997410',0,0);

INSERT INTO controle(datahora_entrada, datahora_saida, estacionamento_cnpj, veiculo_placa) VALUES(CURRENT_TIMESTAMP, null, '14253647586941', 'ABC1D231');
INSERT INTO controle(datahora_entrada, datahora_saida, estacionamento_cnpj, veiculo_placa) VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '14253647586941', 'ABC1D231');