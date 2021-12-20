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
VALUES ('admin', 'admin', 'admin@gmail.com', 'AdminName', 'AdminLastName', '1990-02-09', 1);

INSERT INTO "user"(login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', 'user', 'user@gmail.com', 'UserName', 'UserLastName', '1990-02-09', 2);