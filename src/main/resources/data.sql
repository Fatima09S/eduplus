-- Données de test EduPlus
-- À exécuter après le premier démarrage (tables déjà créées par Hibernate)

-- Utilisateurs (mots de passe = "Test1234" hashés BCrypt)
INSERT IGNORE INTO users (email, fullname, password, role) VALUES
('admin@eduplus.sn', 'Admin EduPlus', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN'),
('prof.diop@eduplus.sn', 'Professeur Diop', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ENSEIGNANT'),
('etudiant1@eduplus.sn', 'Fatou Ndiaye', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ETUDIANT');

-- Étudiants
INSERT IGNORE INTO etudiants (nom, prenom, email, telephone, numero_etudiant, date_naissance) VALUES
('Ndiaye', 'Fatou', 'fatou.ndiaye@eduplus.sn', '771234001', 'ETU001', '2002-03-15'),
('Diallo', 'Moussa', 'moussa.diallo@eduplus.sn', '771234002', 'ETU002', '2001-07-22'),
('Sarr', 'Aminata', 'aminata.sarr@eduplus.sn', '771234003', 'ETU003', '2002-11-08'),
('Ba', 'Ibrahima', 'ibrahima.ba@eduplus.sn', '771234004', 'ETU004', '2001-05-30'),
('Diouf', 'Rokhaya', 'rokhaya.diouf@eduplus.sn', '771234005', 'ETU005', '2003-01-18');

-- Enseignants
INSERT IGNORE INTO enseignants (nom, prenom, email, telephone, specialite, date_embauche) VALUES
('Diop', 'Mamadou', 'prof.diop@eduplus.sn', '775001001', 'Développement Web', '2020-09-01'),
('Fall', 'Aissatou', 'prof.fall@eduplus.sn', '775001002', 'Cybersécurité', '2019-09-01'),
('Sow', 'Cheikh', 'prof.sow@eduplus.sn', '775001003', 'Base de données', '2021-09-01');

-- Cours
INSERT IGNORE INTO cours (titre, description, capacite, code) VALUES
('Développement Web Avancé', 'API REST, Spring Boot, JWT, Swagger', 30, 'PDWA-L3'),
('Cybersécurité & Cryptographie', 'Pentest, Metasploit, DVWA, chiffrement', 25, 'SECU-L3'),
('Bases de Données Avancées', 'PostgreSQL, optimisation, transactions, JPA', 35, 'BDA-L3');

-- Inscriptions
INSERT IGNORE INTO inscription (etudiant_id, cours_id, date_inscription) VALUES
(1, 1, NOW()), (2, 1, NOW()), (3, 1, NOW()),
(1, 2, NOW()), (4, 2, NOW()),
(5, 3, NOW()), (2, 3, NOW());