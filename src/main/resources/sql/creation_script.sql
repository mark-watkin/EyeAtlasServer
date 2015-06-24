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
  id SERIAL UNIQUE NOT NULL,
	name VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE condition (
  id SERIAL UNIQUE NOT NULL,
	title VARCHAR(100) NOT NULL,
	description VARCHAR(500) NOT NULL,
	category VARCHAR(100) REFERENCES category(id),
	img_res_width INTEGER NOT NULL,
	img_res_height INTEGER NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE condition_tag (
	C_id INTEGER REFERENCES condition(id),
	T_id INTEGER REFERENCES tag(id),
	PRIMARY KEY (C_id, T_id)
);

