-- Inserting RobotUsers
INSERT INTO users (id, username, password, role, created_by, updated_by, created_at, updated_at)
VALUES
  (1, 'victor', 'password', 0, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, 'hugo', 'password', 0, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (3, 'admin', 'password123', 1, 'Admin', 'Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
