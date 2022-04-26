DROP TABLE players;
DROP TABLE answers;
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
	correctAns 	integer
);

CREATE TABLE answers
(
	questionID 	integer,
	answerOrder integer,
	answer   varchar(128)
);

-- Primary keys
ALTER TABLE players
ADD CONSTRAINT players_ID_pk PRIMARY KEY(playerID);

ALTER TABLE questions
ADD CONSTRAINT questions_ID_pk PRIMARY KEY(questionID);

-- Data for players
INSERT INTO players
VALUES (1, 'testUser', aes_encrypt('hello123', 'key'), 0);
VALUES (2, 'testUser2', aes_encrypt('bye123', 'key'), 0);

-- Data for questions
INSERT INTO questions VALUES
(1, 'What plant on Earth is able to grow quickly, similar to Groot\'s ability to rapidly grow taller and longer at will?', 4)
(2, 'The fastest growing plant on Earth (clumping bamboo) can grow how quickly?', 1)
(3, 'If Groot is made of typical plant cells, how would they differ from typical animal cells? They would____.', 'not contain a nucleus', 'contain a cell membrane and nucleus', 'contain a cell wall and cytoplasm', 'have mitochondria and vacuoles', 3)
(4, 'The hereditary information that passed down to Groot was contained in Groot\'s ___.', 'blood cells', 'DNA', 'xylem', 'chloroplasts', 2)
(5, 'While protecting his friends, Groot often yess loudly. Those sound waves would create what?', 'Magnetism', 'Light energy', 'Vibrations in the air', 'Electromagnetic energy', 3)
(6, 'How long does it take sunlight to reach the Earth?', '8 minutes', '1 minute', '36 minutes', '15 seconds', 1)
(7, 'How fast does the Earth move in its orbit about the sun?', '18 miles/minute', '18 miles/second', '18 miles/hour', '18 miles/day', 2)
(8, 'The Moon\'s density is what percentage of the Earth\'s?', '60 percent', '30 percent', '90 percent', '1 percent', 1)
(9, 'What region of the Earth experiences the least seasonal variation?', 'North of the Equator', 'South of the Equator', 'The Equatorial region', 'All of the above', 3)
(10, 'What is the average distance from Earth to its Moon?', '10 miles', '597,000 miles', '143,213 miles', '239,000 miles', 4);

INSERT INTO answers VALUES
(1, 'Redwood tree')
(1, 'Oak tree')
(1, 'Cactus')
(1, 'Bamboo')
(2, '91 cm (35 in) per day')
(2, '15 cm (6 in) per day')
(2, '61 cm (24 in) per day')
(2, '304 cm (120 in) per day')
(3, )