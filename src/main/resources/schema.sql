CREATE TABLE IF NOT EXISTS investidor (
    codigo           INTEGER PRIMARY KEY,
    nome             VARCHAR(255) NOT NULL,
    cpf              VARCHAR(11) NOT NULL,
    apta_negociacao  BOOLEAN NOT NULL DEFAULT TRUE,
    data_criacao     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
