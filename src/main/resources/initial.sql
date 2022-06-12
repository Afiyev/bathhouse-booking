-- Create tables for spring security authentication
CREATE TABLE users(
                      id SERIAL NOT NULL ,
                      username VARCHAR(32),
                      mobile_number VARCHAR(12),
                      password VARCHAR(100),
                      PRIMARY KEY (id)
);

CREATE TABLE roles(
                      id SERIAL NOT NULL ,
                      name VARCHAR(32),
                      PRIMARY KEY (id)
);

CREATE TABLE user_roles(
                           user_id INT NOT NULL ,
                           role_id INT NOT NULL ,
                           FOREIGN KEY (user_id) REFERENCES users(id),
                           FOREIGN KEY (role_id) REFERENCES roles(id)
);


-- add 3 types of roles
INSERT INTO roles (name)
VALUES ('ADMIN'), ('USER'), ('MANAGER');

-- add user 'admin'
INSERT INTO users (username, mobile_number, password)
VALUES ('admin', 'foo', '$2a$12$WHxl.d.gEP0wVC4CcAuh5ulpz00IIywcrmjjmPOdFNdghGXPekj1.');

-- set role 'ADMIN' to user 'admin'
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);

-- Create tables for reservation
CREATE TABLE bathhouses(
                           id SERIAL NOT NULL,
                           name VARCHAR(32) NOT NULL,
                           address VARCHAR(100) NOT NULL,
                           phone_number VARCHAR(12) NOT NULL,
                           rating REAL,
                           PRIMARY KEY (id)
);

CREATE TABLE cabins(
                       id SERIAL NOT NULL,
                       bathhouse_id INT NOT NULL,
                       type VARCHAR(32) NOT NULL,
                       price INT NOT NULL,
                       schedule_id INT NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (bathhouse_id) REFERENCES bathhouses(id),
                       FOREIGN KEY (schedule_id) REFERENCES schedules(id)
);

CREATE TABLE schedules(
                          id SERIAL NOT NULL,
                          hours BOOLEAN[],
                          PRIMARY KEY (id)
);

CREATE TABLE reservations(
                             id SERIAL NOT NULL,
                             user_id INT NOT NULL,
                             cabin_id INT NOT NULL,
                             time TIME NOT NULL,
                             price INT NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (cabin_id) REFERENCES cabins(id)
);