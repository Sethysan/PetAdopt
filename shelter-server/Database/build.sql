START TRANSACTION;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS pet;
DROP TABLE IF EXISTS parent;
DROP TABLE IF EXISTS pet_details;

-- Create the parent table
CREATE TABLE parent (
    id SERIAL,
    name VARCHAR(50),
    CONSTRAINT PK_parent PRIMARY KEY(id)
);

-- Create the pet table with a foreign key reference to the pet_details table
CREATE TABLE pet (
    id SERIAL,
    name VARCHAR(50),
    weight INT,
    species VARCHAR(50),
    paper_trained BOOLEAN,
    parent_id INT,
    pet_details_id INT,
    CONSTRAINT PK_pet PRIMARY KEY(id),
    CONSTRAINT FK_pet_parent FOREIGN KEY(parent_id) REFERENCES parent(id),
    CONSTRAINT FK_pet_details FOREIGN KEY(pet_details_id) REFERENCES pet_details(id)
);

-- Create the pet_details table with an image_url column
CREATE TABLE pet_details (
    id SERIAL,
    age INT,
    breed VARCHAR(50),
    description TEXT,
    image_url VARCHAR(255),  -- Store the URL or file path to the pet's image
    CONSTRAINT PK_pet_details PRIMARY KEY(id)
);

-- Insert data into the parent table
INSERT INTO parent(name) VALUES ('Looking for Home!');

-- Insert data into the pet_details table with an image URL
INSERT INTO pet_details (age, breed, description, image_url)
VALUES (3, 'Siamese', 'A playful and curious cat.', '/images/pets/dogerina.jpg');

INSERT INTO pet_details (age, breed, description, image_url)
VALUES (5, 'Husky', 'A loyal and energetic dog.', '/images/pets/moon-moon.jpg');

INSERT INTO pet_details (age, breed, description, image_url)
VALUES (4, 'Shiba Inu', 'A calm and wise dog.', '/images/pets/cheems.jpg');

INSERT INTO pet_details (age, breed, description, image_url)
VALUES (2, 'Persian', 'A quiet and affectionate cat.', '/images/pets/sally.jpg');

INSERT INTO pet_details (age, breed, description, image_url)
VALUES (6, 'Bulldog', 'A gentle and strong-willed dog.', '/images/pets/gustavo.jpg');

-- Insert data into the pet table with reference to the pet_details table
INSERT INTO pet (name, weight, species, paper_trained, parent_id, pet_details_id)
VALUES ('Dogerina', 8, 'Cat', TRUE, 1, 1);

INSERT INTO pet (name, weight, species, paper_trained, parent_id, pet_details_id)
VALUES ('Moon-Moon', 15, 'Dog', TRUE, 1, 2);

INSERT INTO pet (name, weight, species, paper_trained, parent_id, pet_details_id)
VALUES ('Cheems', 10, 'Dog', FALSE, 1, 3);

INSERT INTO pet (name, weight, species, paper_trained, parent_id, pet_details_id)
VALUES ('Sally', 9, 'Cat', TRUE, 1, 4);

INSERT INTO pet (name, weight, species, paper_trained, parent_id, pet_details_id)
VALUES ('Gustavo Flexington III', 14, 'Dog', FALSE, 1, 5);

COMMIT;
