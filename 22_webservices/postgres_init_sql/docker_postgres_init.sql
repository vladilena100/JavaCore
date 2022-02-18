DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

CREATE TABLE IF NOT EXISTS role
(
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(64) NOT NULL
    );

CREATE TABLE IF NOT EXISTS "user"
(
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(64) NOT NULL UNIQUE,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    birthday TIMESTAMP NOT NULL,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(role_id)
    );

INSERT INTO role(role_name) VALUES ('ADMIN');
INSERT INTO role(role_name) VALUES ('USER');

INSERT INTO "user"(login, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', '$2a$12$k.ohrb6nF.r62vHKueUQyO0aFTez/MSqR2WCuD4B3GOqeIHMBHMpy', 'admin@gmail.com', 'AdminName', 'AdminLastName', '1990-02-09', 1);

INSERT INTO "user"(login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', '$2a$12$NrqoZ8.obiqH.K0reEkPq.WiDemgjeSUMJ1m.mkwFgfHzZglDJn0K', 'user@gmail.com', 'UserName', 'UserLastName', '1990-02-09', 2);