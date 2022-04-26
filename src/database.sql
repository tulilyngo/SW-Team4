DROP TABLE players;
DROP TABLE questions;

-- Relations
CREATE TABLE players
(
	playerID 		integer,
	username 		varchar(25),
	password 		varbinary(25),
	playerScore 	integer
);


CREATE TABLE questions 
(
	questionID 	integer,
	question 		varchar(512),
	option1		varchar(128),
	option2		varchar(128),
	option3		varchar(128),
	option4 		varchar(128),
	correctAns 	integer
);

-- Primary keys
ALTER TABLE players
ADD CONSTRAINT players_ID_pk PRIMARY KEY(playerID);

ALTER TABLE questions
ADD CONSTRAINT questions_ID_pk PRIMARY KEY(questionID);

-- Data for players
INSERT INTO players
VALUES (1, 'testUser', aes_encrypt('hello123', 'key'), 0);

INSERT INTO players
VALUES (2, 'testUser2', aes_encrypt('bye123', 'key'), 0);

-- Data for questions
INSERT INTO questions
VALUES (1, 'What plant on Earth is able to grow quickly, similar to Groot�s ability to rapidly grow taller and longer at will?', 'Redwood tree', 'Oak tree', 'Cactus', 'Bamboo', 4);

INSERT INTO questions
VALUES (2, 'The fastest growing plant on Earth (clumping bamboo) can grow how quickly?', '91 cm (35 in) per day', '15 cm (6 in) per day', '61 cm (24 in) per day', '304 cm (120 in) per day', 1);

INSERT INTO questions
VALUES (3, 'If Groot is made of typical plant cells, how would they differ from typical animal cells? They would____.', 'not contain a nucleus', 'contain a cell membrane and nucleus', 'contain a cell wall and cytoplasm', 'have mitochondria and vacuoles', 3);

INSERT INTO questions
VALUES (4, 'The hereditary information that passed down to Groot was contained in Groot�s ___.', 'blood cells', 'DNA', 'xylem', 'chloroplasts', 2);

INSERT INTO questions
VALUES (5, 'While protecting his friends, Groot often yess loudly. Those sound waves would create what?', 'Magnetism', 'Light energy', 'Vibrations in the air', 'Electromagnetic energy', 3);