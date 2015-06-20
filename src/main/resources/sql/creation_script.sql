/*
	1. Open up postgres shell
	2. Type \i [PATH.TO.SCRIPT]
*/

CREATE DATABASE eye_atlas;
\c eye_atlas

CREATE TABLE category (
  id VARCHAR(500) UNIQUE NOT NULL,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(500),
	parent VARCHAR(500) REFERENCES category(id),
	PRIMARY KEY (id)
);

CREATE TABLE tag (
	name VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY (name)
);

CREATE TABLE condition (
  id SERIAL UNIQUE NOT NULL,
	title VARCHAR(100) NOT NULL,
	description VARCHAR(500) NOT NULL,
	category VARCHAR(100) REFERENCES category(id),
	PRIMARY KEY (id)
);

CREATE TABLE condition_tag (
	C_id INTEGER REFERENCES condition(id),
	T_id VARCHAR(100) REFERENCES tag(name),
	PRIMARY KEY (C_id, T_id)
);

CREATE TABLE image (
	resolution INTEGER NOT NULL,
	column_count INTEGER NOT NULL,
	row_count INTEGER NOT NULL,
	C_id INTEGER REFERENCES condition(id),
	PRIMARY KEY (C_id, resolution)
);

