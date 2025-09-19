CREATE DATABASE course;

USE course;

CREATE TABLE Courses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  teacheremail VARCHAR(255)
);

INSERT INTO Courses (name, teacheremail) VALUES
('Security', 'teacher1@unige.it'),
('Networks', 'teacher2@unige.it');
