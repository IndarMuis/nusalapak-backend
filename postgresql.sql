SELECT * FROM accounts;

INSERT INTO roles(name)
VALUES ('ADMIN'),
       ('SELLER'),
       ('CONSUMER');

INSERT INTO accounts(id, email, password, role_id)
VALUES (gen_random_uuid(), 'indar@gmail.com', '123', 2),
       (gen_random_uuid(), 'admin@gmail.com', '123', 1);