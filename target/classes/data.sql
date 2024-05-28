DROP ALL OBJECTS DELETE FILES;

CREATE TABLE subject (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO subject (name) VALUES
  ('Math'),
  ('Physics'),
  ('History'),
  ('Geography'),
  ('Chemistry');

CREATE TABLE student (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(200) NOT NULL
);

INSERT INTO student (name) VALUES
  ('Marco'),
  ('Juan'),
  ('Rosa'),
  ('Hector'),
  ('Sahra');
  
CREATE TABLE subject_student (
  student_id int(6) unsigned NOT NULL,
  subject_id int(3) unsigned NOT NULL,
   PRIMARY KEY (student_id, subject_id),
   FOREIGN KEY (student_id)
      REFERENCES student(id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (subject_id)
      REFERENCES subject(id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

INSERT INTO subject_student (student_id, subject_id) VALUES
  ('1', '2'),
  ('2', '2'),
  ('3', '2'),
  ('3', '1'),
  ('4', '1'),
  ('4', '2'),
  ('4', '3'),
  ('4', '4'),
  ('5', '5');
