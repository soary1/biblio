
-- ===============================
-- INSERTIONS D'EXEMPLE
-- ===============================
\! chcp 65001
-- 0. Catégories
INSERT INTO categorie_adherent (id, nom, description) VALUES
(1, 'Adulte', 'Personne adulte, quota normal'),
(2, 'Jeune', 'Étudiant ou jeune adulte'),
(3, 'Enfant', 'Mineur avec accès restreint');
SELECT setval('categorie_adherent_id_seq', 3);

-- 1. Personnes
INSERT INTO personne (id, nom, prenom, email, adresse, date_naissance) VALUES
(1, 'Dupont', 'Jean', 'jean.dupont@example.com', '10 rue des Lilas, Paris', '1980-05-15'),
(2, 'Martin', 'Sophie', 'sophie.martin@example.com', '22 avenue Victor Hugo, Lyon', '1995-08-30'),
(3, 'Bernard', 'Paul', 'paul.bernard@example.com', '45 boulevard Haussmann, Paris', '1970-01-20'),
(4, 'Durand', 'Marie', 'marie.durand@example.com', '12 place de la République, Marseille', '2003-12-02'),
(5, 'Petit', 'Luc', 'luc.petit@example.com', '8 rue de la Paix, Lille', '1988-07-10');
SELECT setval('personne_id_seq', 5);

-- 2. Adhérents
INSERT INTO adherent (id, identifiant, id_categorie_adherent, quota_max, est_bloque) VALUES
(1, 'A1001', 1, 5, FALSE),
(2, 'A1002', 1, 5, FALSE),
(4, 'A1003', 2, 3, FALSE),
(5, 'A1004', 1, 5, FALSE);

-- 3. Bibliothécaire
INSERT INTO bibliothecaire (id) VALUES (3);

-- 4. Livres
INSERT INTO livre (id, titre, auteur, genre, isbn, restriction_age) VALUES
(1, 'Le Petit Prince', 'Antoine de Saint-Exupéry', 'Conte', '978-0156012195', 6),
(2, '1984', 'George Orwell', 'Roman', '978-0451524935', 16),
(3, 'Les Misérables', 'Victor Hugo', 'Roman historique', '978-2070409184', 12),
(4, 'Harry Potter à l''école des sorciers', 'J.K. Rowling', 'Fantasy', '978-0747532699', 10);
SELECT setval('livre_id_seq', 4);

-- 5. Exemplaires
INSERT INTO exemplaire (id_livre, statut) VALUES
(1, 'disponible'),
(1, 'emprunte'),
(2, 'disponible'),
(3, 'disponible'),
(4, 'disponible'),
(4, 'emprunte');

-- 6. Prets
INSERT INTO pret (id_adherent, id_exemplaire, date_emprunt, date_retour_prevue, date_retour_reelle, est_prolonge) VALUES
(1, 2, '2025-06-15', '2025-07-15', NULL, FALSE),
(2, 6, '2025-06-20', '2025-07-20', NULL, FALSE);

-- 7. Réservations
INSERT INTO reservation (id_adherent, id_livre, date_reservation, expire_le, statut) VALUES
(4, 2, '2025-06-25', '2025-07-05', 'en_attente'),
(5, 3, '2025-06-28', '2025-07-08', 'confirmee');

-- 8. Types d'actions
INSERT INTO type_action (id, nom, description) VALUES
(1, 'inscription', 'Création d''un nouveau compte adhérent'),
(2, 'emprunt', 'Emprunt d''un livre'),
(3, 'reservation', 'Réservation d''un livre'),
(4, 'retour', 'Retour d''un livre'),
(5, 'annulation', 'Annulation d''une réservation');
SELECT setval('type_action_id_seq', 5);

-- 9. Historique
INSERT INTO historique_adherent (id_adherent, id_type_action, commentaire) VALUES
(1, 1, 'Inscription réussie le 2023-01-10'),
(1, 2, 'Emprunt du livre "Le Petit Prince" le 2025-06-15'),
(4, 3, 'Réservation du livre "1984" en attente'),
(2, 2, 'Emprunt du livre "Harry Potter à l''école des sorciers" le 2025-06-20');

-- 10. Quotas
INSERT INTO configuration_quota (id_categorie_adherent, quota_max) VALUES
(1, 5),
(2, 3),
(3, 2);


-- 11. Roles
INSERT INTO role (nom, description) VALUES
('ROLE_ADMIN', 'Administrateur avec tous les droits'),
('ROLE_BIBLIOTHECAIRE', 'Bibliothécaire avec droits de gestion des livres'),
('ROLE_ADHERENT', 'Utilisateur adhérent avec droits restreints');
SELECT setval('role_id_seq', 3);

-- 12. Employes (associés à personne)
INSERT INTO employe (id, poste, date_embauche) VALUES
(3, 'Bibliothécaire', '2020-01-15'),  -- correspond à la personne id=3 (Paul Bernard)
(5, 'Administrateur', '2019-05-10');  -- nouvelle personne à créer si besoin
SELECT setval('employe_id_seq', 5);

-- 13. Personne supplémentaire pour l'admin (id=6)
INSERT INTO personne (id, nom, prenom, email, adresse, date_naissance) VALUES
(6, 'Lemoine', 'Alice', 'alice.lemoine@example.com', '5 rue de la Liberté, Nantes', '1985-04-22');

-- 14. Utilisateurs (liés à employe)
INSERT INTO utilisateur (id, username, mot_de_passe, actif, id_employe) VALUES
(1, 'paul.bernard', 'encoded_password1', TRUE, 3),  -- bibliothécaire
(2, 'alice.lemoine', 'encoded_password2', TRUE, 6); -- administrateur

-- 15. Association Utilisateur-Role (N-N)
INSERT INTO utilisateur_role (id_utilisateur, id_role) VALUES
(1, 2), -- paul.bernard est bibliothécaire
(2, 1); -- alice.lemoine est admin
