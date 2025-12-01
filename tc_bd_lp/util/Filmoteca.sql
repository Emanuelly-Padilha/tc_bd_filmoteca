
CREATE DATABASE filmoteca;

USE filmoteca;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE filmes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    diretor VARCHAR(100),
    duracao INT,
    classificacao VARCHAR(10)
);

CREATE TABLE avaliacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    nota INT NOT NULL,
    review TEXT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_filme) REFERENCES filmes(id)
);

CREATE TABLE filmes_assistidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    data_assistido DATE,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_filme) REFERENCES filmes(id)
);

CREATE TABLE watchlist (
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    PRIMARY KEY (id_usuario, id_filme),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_filme) REFERENCES filmes(id)
);