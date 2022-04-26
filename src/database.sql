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
	question 	varchar(512),
	correctAns 	integer
);

CREATE TABLE answers
(
	questionID 	integer,
	answerOrder integer,
	answer      varchar(128)
);

-- Primary keys
ALTER TABLE players
ADD CONSTRAINT players_ID_pk PRIMARY KEY(playerID);

ALTER TABLE questions
ADD CONSTRAINT questions_ID_pk PRIMARY KEY(questionID);

ALTER TABLE answers
ADD CONSTRAINT answers_questionsID_answerOrder_pk PRIMARY KEY(questionID, answerOrder);

-- Foreign keys
ALTER TABLE answers
ADD CONSTRAINT answers_questionID_fk FOREIGN KEY (questionID)
REFERENCES questions(questionID);

-- Data for players
INSERT INTO players
VALUES
    (1, 'testUser', aes_encrypt('hello123', 'key'), 0),
    (2, 'testUser2', aes_encrypt('bye123', 'key'), 0);

-- Data for questions
INSERT INTO questions
VALUES
    (1, 'How long does it take sunlight to reach the Earth?', 1),
    (2, 'How fast does the Earth move in its orbit about the sun?', 2),
    (3, 'The Moon\'s density is what percentage of the Earth\'s?', 1),
    (4, 'What region of the Earth experiences the least seasonal variation?', 3),
    (5, 'What is the largest living organism visible from Earth\'s orbit?', 3),
    (6, 'What percentage of Earth\'s surface is land?', 3),
    (7, 'Which planet other than Earth was the first to be studied by a satellite launched from Earth?', 2),
    (8, 'What is the average distance from Earth to its Moon?', 4),
    (9, 'How long does it take for radio signals to travel from Earth to the Moon (and vice versa)?', 3),
    (10, 'What is the brightest star in the sky (excluding our Sun and novas)?', 2);

INSERT INTO answers
VALUES
    (1, 1, '8 minutes'),
    (1, 2, '1 minute'),
    (1, 3, '36 minutes'),
    (1, 4, '15 seconds'),
    (2, 1, '18 miles per minute'),
    (2, 2, '18 miles per second'),
    (2, 3, '18 miles per hour'),
    (2, 4, '18 miles per day'),
    (3, 1, '60 %'),
    (3, 2, '30 %'),
    (3, 3, '90 %'),
    (3, 4, '1 %'),
    (4, 1, 'North of the equator'),
    (4, 2, 'South of the equator'),
    (4, 3, 'The equatorial region'),
    (4, 4, 'All of the above'),
    (5, 1, 'The Great Lakes'),
    (5, 2, 'Mount Everest'),
    (5, 3, 'The Great Barrier Reef'),
    (5, 4, 'All of the above'),
    (6, 1, '~ 10 %'),
    (6, 2, '~ 70 %'),
    (6, 3, '~ 30 %'),
    (6, 4, '~ 50 %'),
    (7, 1, 'Mercury'),
    (7, 2, 'Venus'),
    (7, 3, 'Mars'),
    (7, 4, 'Pluto'),
    (8, 1, '10 miles'),
    (8, 2, '597,000 miles'),
    (8, 3, '143,213 miles'),
    (8, 4, '239,000 miles'),
    (9, 1, '50.43 seconds'),
    (9, 2, '1 minute'),
    (9, 3, '1.28 seconds'),
    (9, 4, '2 minutes'),
    (10, 1, 'Vega'),
    (10, 2, 'Sirius'),
    (10, 3, 'Canopus'),
    (10, 4, 'All of the above');