/*
    1. Open psql
    2. \i [PATH.TO.SCRIPT]
*/

\c eye_atlas

DROP TABLE entity_category;
DROP TABLE entity_tag;
DROP TABLE image;
DROP TABLE tags;
DROP TABLE entity;
DROP TABLE category;


\c postgres

DROP DATABASE eye_atlas;
