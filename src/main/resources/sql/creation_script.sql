/*
	1. Open up postgres shell
	2. Type \i [PATH.TO.SCRIPT]
*/

CREATE DATABASE eye_atlas;
\c eye_atlas

CREATE TABLE entity (
	id SERIAL,
	title VARCHAR(100) UNIQUE NOT NULL,
	description VARCHAR(500) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE tags (
	id SERIAL,
	name VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE entity_tag (
	E_id INTEGER REFERENCES entity(id),
	T_id INTEGER REFERENCES tags(id),
	PRIMARY KEY (E_id, T_id)
);

CREATE TABLE category (
	id SERIAL,
	name VARCHAR(100) UNIQUE NOT NULL,
	parent INTEGER REFERENCES category(id),
	PRIMARY KEY (id)
);

CREATE TABLE entity_category (
	E_id INTEGER REFERENCES entity(id),
	C_id INTEGER REFERENCES category(id),
	PRIMARY KEY (E_id)
);

CREATE TABLE image (
	id SERIAL,
	resolution INTEGER NOT NULL,
	column_count INTEGER NOT NULL,
	row_count INTEGER NOT NULL,
	E_id INTEGER REFERENCES entity(id),
	PRIMARY KEY (id)
);

