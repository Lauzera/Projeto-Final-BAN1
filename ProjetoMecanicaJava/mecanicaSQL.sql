CREATE TABLE Oficina (
	cnpj VARCHAR(14) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	email VARCHAR(60)
);

CREATE TABLE Cliente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	telefone VARCHAR(15)
);

CREATE TABLE Veiculo (
	placa VARCHAR(10) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	cor VARCHAR(15),
	id_cliente INT NOT NULL,
	
	FOREIGN KEY(id_cliente) REFERENCES Cliente(id)
);

CREATE TABLE Mecanico(
	cpf VARCHAR(11) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	salario NUMERIC(10, 2) NOT NULL,
	cnpj_oficina VARCHAR(14) NOT NULL,
	
	FOREIGN KEY(cnpj_oficina) REFERENCES Oficina(cnpj)
);

CREATE TABLE Fornecedor(
	cnpj VARCHAR(14) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	telefone VARCHAR(20)
);

CREATE TABLE Fornecimento(
	id SERIAL PRIMARY KEY,
	cnpj_oficina VARCHAR(14) NOT NULL,
	cnpj_fornecedor VARCHAR(14) NOT NULL,
	produto VARCHAR(100),

	
	FOREIGN KEY(cnpj_oficina) REFERENCES Oficina(cnpj),
	FOREIGN KEY(cnpj_fornecedor) REFERENCES Fornecedor(cnpj)
);

CREATE TABLE Servico(
	id SERIAL PRIMARY KEY,
    cnpj_oficina VARCHAR(14) NOT NULL,
    id_cliente INT NOT NULL,
    placa_veiculo VARCHAR(10) NOT NULL,

    FOREIGN KEY(cnpj_oficina) REFERENCES Oficina(cnpj),
    FOREIGN KEY(id_cliente) REFERENCES Cliente(id),
    FOREIGN KEY(placa_veiculo) REFERENCES Veiculo(placa)
);

CREATE TABLE Conserto (
	id SERIAL PRIMARY KEY,
    descricao TEXT,
    valor NUMERIC(10,2),
    data_inicio VARCHAR(15),
    data_fim VARCHAR(15),
    cpf_mecanico VARCHAR(11) NOT NULL,
    placa_veiculo VARCHAR(10) NOT NULL,
    id_servico INT NOT NULL,

    FOREIGN KEY(cpf_mecanico) REFERENCES Mecanico(cpf),
    FOREIGN KEY(placa_veiculo) REFERENCES Veiculo(placa),
    FOREIGN KEY(id_servico) REFERENCES Servico(id)
);