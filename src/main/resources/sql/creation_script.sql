/*
	1. Open up postgres shell
	2. Type \i [PATH.TO.SCRIPT]
*/

CREATE DATABASE eye_atlas;
\c eye_atlas

CREATE TABLE entity (
	id SERIAL PRIMARY KEY,
	title VARCHAR(100) UNIQUE NOT NULL,
	description VARCHAR(500) NOT NULL
);

CREATE TABLE tags (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE entity_tag (
	E_id INTEGER REFERENCES entity(id),
	T_id INTEGER REFERENCES tags(id)
);


CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE entity_category (
	E_id INTEGER REFERENCES entity(id),
	C_id INTEGER REFERENCES category(id)
);

CREATE TABLE category_subcategory (
	C_id INTEGER REFERENCES category(id),
	S_id INTEGER REFERENCES category(id)
);

CREATE TABLE image (
	id SERIAL PRIMARY KEY,
	resolution INTEGER NOT NULL,
	column_count INTEGER NOT NULL,
	row_count INTEGER NOT NULL,
	E_id INTEGER REFERENCES entity(id)
);

