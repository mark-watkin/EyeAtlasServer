/*
	1. Open up postgres shell
	2. Type \i [PATH.TO.SCRIPT]
*/

CREATE DATABASE eye_atlas;
\c eye_atlas





CREATE TABLE entity (
	id int NOT NULL PRIMARY KEY,
	title varchar(100) NOT NULL,
	description varchar(500) NOT NULL
);

CREATE TABLE tags(
	id int NOT NULL PRIMARY KEY,
	name varchar(100) NOT NULL
);

CREATE TABLE entity_tag (
	E_id int REFERENCES entity(id),
	T_id int REFERENCES tags(id)
);


CREATE TABLE category(
	id int NOT NULL PRIMARY KEY,
	name varchar(100) NOT NULL
);

CREATE TABLE entity_category (
	E_id int REFERENCES entity(id),
	C_id int REFERENCES category(id)
);

CREATE TABLE category_subcategory (
	C_id int REFERENCES category(id),
	S_id int REFERENCES category(id)
);






CREATE TABLE image (
	id int NOT NULL PRIMARY KEY,
	resolution int NOT NULL,
	column_count int NOT NULL,
	row_count int NOT NULL,
	E_id int REFERENCES entity(id)
);

