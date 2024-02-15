--liquibase formatted sql

--changeset skavysh_ilya:1
CREATE TABLE IF NOT EXISTS templates(
id SERIAL PRIMARY KEY,
tittle VARCHAR(64) NOT NULL,
template VARCHAR(2048) NOT NULL,
dateOfCreation TIMESTAMP
);

--changeset skavysh_ilya:2
CREATE TABLE IF NOT EXISTS groups(
id SERIAL PRIMARY KEY,
name VARCHAR(64) NOT NULL,
dateOfCreation TIMESTAMP
);

--changeset skavysh_ilya:3
CREATE TABLE IF NOT EXISTS users(
id SERIAL PRIMARY KEY,
fullname VARCHAR(64) NOT NULL,
email VARCHAR(64) NOT NULL,
groupId INT REFERENCES groups (id),
dateOfCreation TIMESTAMP,
FOREIGN KEY (groupId) REFERENCES groups(id)
);

--changeset skavysh_ilya:4
CREATE TABLE IF NOT EXISTS mails(
id SERIAL PRIMARY KEY,
uniqueMessage VARCHAR(64) UNIQUE,
groupUser INT,
templateId INT,
file VARCHAR(512),
typeFile VARCHAR(8),
data VARCHAR(2048)
);

--changeset skavysh_ilya:5
CREATE TABLE IF NOT EXISTS errors(
id SERIAL PRIMARY KEY,
message VARCHAR(64),
dateTime TIMESTAMP,
status VARCHAR(16),
idMail INT,
FOREIGN KEY (idMail) REFERENCES mails(id)
);