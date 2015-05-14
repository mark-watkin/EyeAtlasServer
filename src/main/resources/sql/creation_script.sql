/*
	1. Open up postgres shell
	2. Type \i [PATH.TO.SCRIPT]
*/

CREATE DATABASE eye_atlas;
\c eye_atlas

CREATE TABLE category (
	name VARCHAR(100) UNIQUE NOT NULL,
	description VARCHAR(500),
	parent VARCHAR(100) REFERENCES category(name),
	PRIMARY KEY (name)
);

CREATE TABLE tags (
	name VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY (name)
);

CREATE TABLE condition (
	title VARCHAR(100) UNIQUE NOT NULL,
	description VARCHAR(500) NOT NULL,
	category VARCHAR(100) REFERENCES category(name),
	PRIMARY KEY (title)
);

CREATE TABLE condition_tag (
	C_id VARCHAR(100) REFERENCES condition(title),
	T_id VARCHAR(100) REFERENCES tags(name),
	PRIMARY KEY (C_id, T_id)
);

CREATE TABLE image (
	resolution INTEGER NOT NULL,
	column_count INTEGER NOT NULL,
	row_count INTEGER NOT NULL,
	C_id VARCHAR(100) REFERENCES condition(title),
	PRIMARY KEY (resolution, C_id)
);

