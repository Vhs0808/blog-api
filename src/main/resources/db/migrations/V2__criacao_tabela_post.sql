-- IF NOT EXISTS permite executar depois da V1 em um banco novo.
-- Em um banco com a V1 aplicada e a tabela removida, recria post.
CREATE TABLE IF NOT EXISTS post (
    id BINARY(16) NOT NULL,
    autor VARCHAR(70) NOT NULL,
    data DATE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    texto TEXT NOT NULL,
    PRIMARY KEY (id)
);
