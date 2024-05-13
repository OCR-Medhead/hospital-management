CREATE TABLE IF NOT EXISTS hospital(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(200) NOT NULL,
    latitude float,
    longitude float
);

CREATE TABLE IF NOT EXISTS specialization(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS hospital_specialization(
    hospital_id INT,
    specialization_id INT,
    FOREIGN KEY (hospital_id) REFERENCES hospital(id),
    FOREIGN KEY (specialization_id) REFERENCES specialization(id)
);

CREATE TABLE IF NOT EXISTS customUser(
    id INT AUTO_INCREMENT PRIMARY KEY,
    email varchar(200) NOT NULL,
    passwordhash varchar(200) NOT NULL,
    first_name varchar(200) NOT NULL,
    last_name varchar(200) NOT NULL
);

INSERT INTO hospital(name, latitude, longitude) VALUES
('Paris', 48.85, 1.44),
('Toulouse', 43.6, 1.44),
('Lyon', 45.75, 7.83),
('Marseille', 43.29, 5.37),
('Lille', 50.63, 3.06),
('Strasbourg', 48.58, 7.75),
('Biarritz', 43.48, -1.55),
('Brest', 48.39, -4.48);

INSERT INTO specialization (name) VALUES
('Cardiologie'),
('Immunologie'),
('Neuropathologie'),
('Diagnostic');

INSERT INTO hospital_specialization(hospital_id, specialization_id) VALUES
(1,1),
(1,2),
(2,1),
(3,2),
(3,3),
(4,1),
(4,4),
(5,4),
(5,3),
(6,2),
(6,1),
(7,4),
(7,3),
(8,1);

INSERT INTO customUser (id, email, passwordhash, first_name, last_name) VALUES
(32, 'dev@dev.fr', '123', 'dev', 'dev'),
(33, 'admin@admin.fr', '123', 'admin', 'admin'),
(34, 'user@user.fr', '123', 'user', 'user');
