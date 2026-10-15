-- UUIDs fixos, convertidos para BINARY(16) sem alterar a ordem dos bytes.
INSERT INTO post (id, autor, data, titulo, texto) VALUES
    (UNHEX(REPLACE('11111111-1111-4111-8111-111111111111', '-', '')), 'Ana Silva', '2026-10-01',
     'Bem-vindo ao blog', 'Este blog foi criado para compartilhar conhecimentos e trocar ideias.'),
    (UNHEX(REPLACE('22222222-2222-4222-8222-222222222222', '-', '')), 'Bruno Souza', '2026-10-02',
     'Primeiros passos com Spring Boot', 'O Spring Boot facilita a criacao de APIs Java e a integracao com bancos de dados.'),
    (UNHEX(REPLACE('33333333-3333-4333-8333-333333333333', '-', '')), 'Carla Santos', '2026-10-03',
     'Organizando migrations com Flyway', 'As migrations permitem versionar a estrutura e os dados iniciais do banco.');

INSERT INTO comentario (id, data, comentario, post) VALUES
    (UNHEX(REPLACE('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa1', '-', '')), '2026-10-01',
     'Gostei da proposta do blog!', UNHEX(REPLACE('11111111-1111-4111-8111-111111111111', '-', ''))),
    (UNHEX(REPLACE('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa2', '-', '')), '2026-10-02',
     'Estou acompanhando os proximos posts.', UNHEX(REPLACE('11111111-1111-4111-8111-111111111111', '-', ''))),
    (UNHEX(REPLACE('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa3', '-', '')), '2026-10-02',
     'Esse conteudo ajudou nos meus primeiros passos.', UNHEX(REPLACE('22222222-2222-4222-8222-222222222222', '-', ''))),
    (UNHEX(REPLACE('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa4', '-', '')), '2026-10-03',
     'Seria interessante um exemplo de API com comentarios.', UNHEX(REPLACE('22222222-2222-4222-8222-222222222222', '-', ''))),
    (UNHEX(REPLACE('aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaa5', '-', '')), '2026-10-03',
     'Versionar o banco facilita o trabalho em equipe.', UNHEX(REPLACE('33333333-3333-4333-8333-333333333333', '-', '')));
