CREATE TABLE items (
   	id SERIAL PRIMARY KEY,
   	description TEXT,
	created TIMESTAMP,
	done TIMESTAMP,
	author_id int
);

CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);