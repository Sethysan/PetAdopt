START TRANSACTION;

DROP TABLE if exists pet;
DROP TABLE IF exists parent;

create table parent (
	id serial,
	name VARCHAR(50),
	CONSTRAINT PK_parent PRIMARY KEY(id)
);

create table pet (
	id serial,
	name VARCHAR(50),
	weight INT,
	species VARCHAR(50),
	paper_trained BOOLEAN,
	parent_id INT,
	CONSTRAINT PK_puppies PRIMARY KEY(id),
	CONSTRAINT FK_pet_parent FOREIGN KEY(parent_id) REFERENCES parent(id)
);

insert into parent(name) values ('Looking for Home!');
insert into pet (name, weight, species, paper_trained, parent_id) values ('Dogerina', 8, 'Cat', true,1);
insert into pet (name, weight, species, paper_trained, parent_id) values ('Moon-Moon', 15, 'Dog', true,1);
insert into pet (name, weight, species, paper_trained, parent_id) values ('Cheems', 10, 'Dog', false,1);
insert into pet (name, weight, species, paper_trained, parent_id) values ('Sally', 9, 'Cat', true,1);
insert into pet (name, weight, species, paper_trained, parent_id) values ('Gustavo Flexington III', 14, 'Dog', false,1);

COMMIT;