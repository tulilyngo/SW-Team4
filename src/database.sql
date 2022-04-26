DROP TABLE client;
DROP TABLE answer;
DROP TABLE question;

-- Relations
CREATE TABLE client
(
	username 		varchar(25),
	password 		varbinary(25)
);

CREATE TABLE question
(
	questionID 	integer,
	question 	varchar(512),
	correctAns 	varchar(128)
);

CREATE TABLE answer
(
	questionID 	integer,
	answer      varchar(128)
);

-- Primary keys
ALTER TABLE client
ADD CONSTRAINT client_username_pk PRIMARY KEY(username);

ALTER TABLE question
ADD CONSTRAINT question_ID_pk PRIMARY KEY(questionID);

ALTER TABLE answer
ADD CONSTRAINT answer_questionsID_answer_pk PRIMARY KEY(questionID, answer);

-- Foreign keys
ALTER TABLE answer
ADD CONSTRAINT answer_questionID_fk FOREIGN KEY (questionID)
REFERENCES question(questionID);

-- Data for client
INSERT INTO client
VALUES
    ('testUser', aes_encrypt('hello123', 'key')),
    ('testUser2', aes_encrypt('bye123', 'key'));

-- Data for question
INSERT INTO question
VALUES
    (1, 'How long does it take sunlight to reach the Earth?', '8 minutes'),
    (2, 'How fast does the Earth move in its orbit about the sun?', '18 miles per second'),
    (3, 'The Moon\'s density is what percentage of the Earth\'s?', '60 %'),
    (4, 'What region of the Earth experiences the least seasonal variation?', 'The equatorial region'),
    (5, 'What is the largest living organism visible from Earth\'s orbit?', 'The Great Barrier Reef'),
    (6, 'What percentage of Earth\'s surface is land?', '~ 30 %'),
    (7, 'Which planet other than Earth was the first to be studied by a satellite launched from Earth?', 'Venus'),
    (8, 'What is the average distance from Earth to its Moon?', '239,000 miles'),
    (9, 'How long does it take for radio signals to travel from Earth to the Moon (and vice versa)?', '1.28 seconds'),
    (10, 'What is the brightest star in the sky (excluding our Sun and novas)?', 'Sirius');

-- Data for answer
INSERT INTO answer
VALUES
    (1, '8 minutes'),
    (1, '1 minute'),
    (1, '36 minutes'),
    (1, '15 seconds'),
    (2, '18 miles per minute'),
    (2, '18 miles per second'),
    (2, '18 miles per hour'),
    (2, '18 miles per day'),
    (3, '60 %'),
    (3, '30 %'),
    (3, '90 %'),
    (3, '1 %'),
    (4, 'North of the equator'),
    (4, 'South of the equator'),
    (4, 'The equatorial region'),
    (4, 'All of the above'),
    (5, 'The Great Lakes'),
    (5, 'Mount Everest'),
    (5, 'The Great Barrier Reef'),
    (5, 'All of the above'),
    (6, '~ 10 %'),
    (6, '~ 70 %'),
    (6, '~ 30 %'),
    (6, '~ 50 %'),
    (7, 'Mercury'),
    (7, 'Venus'),
    (7, 'Mars'),
    (7, 'Pluto'),
    (8, '10 miles'),
    (8, '597,000 miles'),
    (8, '143,213 miles'),
    (8, '239,000 miles'),
    (9, '50.43 seconds'),
    (9, '1 minute'),
    (9, '1.28 seconds'),
    (9, '2 minutes'),
    (10, 'Vega'),
    (10, 'Sirius'),
    (10, 'Canopus'),
    (10, 'All of the above');
