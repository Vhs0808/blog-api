CREATE TABLE comentario (
    id BINARY(16) NOT NULL,
    data DATE NOT NULL,
    comentario LONGTEXT NOT NULL,
    post BINARY(16) NOT NULL,
    PRIMARY KEY (id),
    INDEX idx_comentario_post (post),
    CONSTRAINT fk_comentario_post FOREIGN KEY (post) REFERENCES post (id)
);
