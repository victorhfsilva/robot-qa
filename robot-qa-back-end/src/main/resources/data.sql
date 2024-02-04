-- Inserting Robots
INSERT INTO robots (id, name, description, created_by, updated_by, created_at, updated_at)
VALUES
  (1, '13DC-2', 'Created to fulfill all your desires.', 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, '2345-1', 'Created to make you feel human.', 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (3, '123D-3', 'Created to teach you all the secrets of the universe.', 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserting RobotUsers
INSERT INTO users (id, username, password, role, created_by, updated_by, created_at, updated_at)
VALUES
  (1, 'victor', 'password', 0, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, 'hugo', 'password', 0, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (3, 'admin', 'password123', 1, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserting mappings between Robots and RobotUsers
INSERT INTO robots_users (robot_id, user_id)
VALUES
  (1, 1),
  (2, 1),
  (2, 2),
  (3, 2),
  (3, 3),
  (1, 3),
  (2, 3);


-- Inserting questions and answers for Robots
INSERT INTO questions_answers (robot_id, question, answer)
VALUES
  (1, 'What is your purpose?', 'To fulfill your desires.'),
  (1, 'What can you do?', 'I can do all you wish.'),
  (1, 'How are you powered?', 'I am powered by electricity.');

INSERT INTO questions_answers (robot_id, question, answer)
VALUES
  (2, 'What is your purpose?', 'To make you feel.'),
  (2, 'What can you do?', 'I can make you feel like never before.'),
  (2, 'Do you have emotions?', 'No, I do not have emotions.');

INSERT INTO questions_answers (robot_id, question, answer)
VALUES
  (3, 'What is your purpose?', 'To teach you.'),
  (3, 'What can you do?', 'Everything that can be done.'),
  (3, 'How are you powered?', 'By wisdom.');