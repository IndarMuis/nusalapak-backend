SELECT *
FROM accounts;

INSERT INTO accounts(id, email, password, role_id)
VALUES (gen_random_uuid(), 'indar@gmail.com', '123', 2),
       (gen_random_uuid(), 'admin@gmail.com', '123', 1);

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_SELLER'),
       ('ROLE_CUSTOMER');

INSERT INTO product_categories(name)
VALUES ('Fashion'),
       ('Electronic'),
       ('Sport'),
       ('Book');

INSERT INTO payments(name)
VALUES ('Transfer Bank'),
       ('E-Wallet'),
       ('Credit Card'),
       ('Mobile Banking'),
       ('Virtual Account');

INSERT INTO couriers(name)
VALUES ('JNE Express'),
       ('Post Indonesia'),
       ('J&T Express'),
       ('TIKI'),
       ('Sicepat');