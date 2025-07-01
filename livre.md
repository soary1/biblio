

- Livre

- Exemplaire

- Pret
	lecture sur place
	emporter
	reserver

- Adherant 
	etudiant
	Professionnel
	professeur

- Penalite

- cotisation

- inscription

- Reservation 
	apres reservation , le livre n est plus disponible
	
- prolongement
	combien de fois/date
	
- gestion de jour ferrier
	pas de penalite si c est un jour ferrier le depot 
	avant ou apres 

le prolongement ou le reservation qui arrive en premier est en priorite

validation par le bibliothequaire dispo si tout est reglos
par profil
Manoratra regles de gestion 

fonctionnalites (cas d utilisation)
- Preter un livre (exemplaire)
	Objectifs:.... (textuel)
	Acteur: biliothecaire
	Entree: ref exemplaire, ref adherant
	scenario nominal: (Acteur) se connecte , click sur le menu preter un livre, remplir champ adherant; click sur le bouton preter
	Regles de gestion: (sinon page d erreur et message d erreur (raison))
	- Adherant doit exister
	- exemplaire doit etre disponible
	- quota par personne
	- pas de sanction
	- l adehrant est il abonner
	- Rendre livre
	- ajouter adherant
	- est ce adapter a la personne
	Resultat:
	adherant +pret, quota-, exemplaire indispo

Cahier de charge : ensemble des fonctionnalites
UML: methotologie pour un systeme de conception ou textuel
NPD: Tables
Mettre dans une couche service ou OO
avoir une class de test (pre-service,pre-test,...) pour verification
assertion (JInit - Java): class de test

conception 
- donnees
- Architecture


## Cahier de charge

- **Nom**:
	- #Preter_livre
- **Objectifs**: 
	- Prete un livre a un adherant 
- **Acteur**: 
	- Bibliothecaire
- **Entree**: 
	- ref exemplaire
	- ref adherant
	- date actuelle
- **Scenario nominal**: 
	- L adherant veux preter un livre 
	- on entre ses infos 
	- on entre les infos du livre
	- on clique sur 'preter'
- **Regles de gestion**:
	- L adherant doit exister
	- l exemplaire doit etre disponible
	- le pret ne doit pas depasser le quota
	- l adherant n est pas sanctionner
	- l adherant doit etre abonner
	- le livre est t il adapter a la personne
- **Scenario alternatif**:
	- message d erreur et alerte avec les raisons
- **Resultat**:
	- le livre devient indisponible
	- quota de l adherant -1
	- pret +1

---

- **Nom**:
	 
- **Objectifs**: 
	- Prete un livre a un adherant 
- **Acteur**: 
	- Bibliothecaire
- **Entree**: 
	- ref exemplaire
	- ref adherant
	- date actuelle
- **Scenario nominal**: 
	- L adherant veux preter un livre 
	- on entre ses infos 
	- on entre les infos du livre
	- on clique sur 'preter'
- **Regles de gestion**:
	- L adherant doit exister
	- l exemplaire doit etre disponible
	- le pret ne doit pas depasser le quota
	- l adherant n est pas sanctionner
	- l adherant doit etre abonner
	- le livre est t il adapter a la personne
- **Scenario alternatif**:
	- message d erreur et alerte avec les raisons
- **Resultat**:
	- le livre devient indisponible
	- quota de l adherant -1
	- pret +1

---

Voici la suite du **cahier des charges fonctionnel**, avec de **nouvelles fonctionnalités** à partir des éléments que tu as listés :

---

**Nom** : `Rendre_livre`

* **Objectifs** :
  Permettre à un adhérant de restituer un exemplaire emprunté.

* **Acteur** :
  Bibliothécaire

* **Entrée** :
  Référence exemplaire, référence adhérant, date de retour

* **Scénario nominal** :
  L’adhérant revient rendre un livre.
  Le bibliothécaire vérifie les infos du prêt, clique sur 'Rendre'.

* **Règles de gestion** :

  * L’exemplaire est marqué comme emprunté.
  * L’adhérant doit être l’emprunteur de l’exemplaire.
  * Calcul d’éventuelle pénalité si retard **hors jour férié**.
  * Mise à jour du quota de l’adhérant.
  * Suppression du prêt si terminé.

* **Scénario alternatif** :

  * Le livre n’a pas été emprunté.
  * Ce n’est pas le bon adhérant.

* **Résultat** :

  * Le livre devient disponible.
  * Quota +1 pour l’adhérant.
  * Calcul pénalité si en retard.

---

**Nom** : `Reserver_livre`

* **Objectifs** :
  Permettre à un adhérant de réserver un livre qui est actuellement indisponible.

* **Acteur** :
  Adhérant / Bibliothécaire

* **Entrée** :
  Référence du livre, référence adhérant, date de réservation

* **Scénario nominal** :
  L’adhérant réserve un exemplaire indisponible via le système.

* **Règles de gestion** :

  * Le livre n’est pas disponible.
  * L’adhérant doit être abonné et non sanctionné.
  * La réservation rend le livre **indisponible pour d’autres**.
  * Réservation prioritaire sur un prolongement si arrivée avant.
  * Le livre est affecté à l’adhérant dès qu’il est disponible.

* **Scénario alternatif** :

  * Le livre est déjà réservé.
  * L’adhérant a atteint son quota de réservation.

* **Résultat** :

  * Enregistrement d’une réservation.
  * Livre indisponible.

---

**Nom** : `Prolonger_pret`

* **Objectifs** :
  Permettre à un adhérant de prolonger la durée d’un prêt en cours.

* **Acteur** :
  Adhérant / Bibliothécaire

* **Entrée** :
  Référence du prêt, nouvelle date de retour souhaitée

* **Scénario nominal** :
  L’adhérant clique sur « prolonger » avant la date limite.

* **Règles de gestion** :

  * Le prêt est encore actif.
  * Le nombre de prolongements autorisé ne doit pas être dépassé.
  * Le livre n’est pas réservé par un autre adhérant.
  * Priorité donnée à la **réservation** si les deux sont demandés.

* **Scénario alternatif** :

  * Le livre est réservé → refus de prolongement.
  * Nombre max de prolongements atteint.

* **Résultat** :

  * Mise à jour de la date de retour.
  * Compteur de prolongements +1.

---

**Nom** : `Gerer_jours_feries`

* **Objectifs** :
  Ne pas appliquer de pénalité si un retour est fait un jour férié ou le lendemain.

* **Acteur** :
  Bibliothécaire / Système

* **Entrée** :
  Liste des jours fériés (préconfigurée), date de retour réelle

* **Scénario nominal** :
  Un livre est rendu le jour férié ou juste après.

* **Règles de gestion** :

  * Pas de pénalité si la date de retour est un jour férié ou le lendemain.
  * Permet le retour anticipé.

* **Scénario alternatif** :

  * Livre rendu trop tard (hors férié) → pénalité classique.

* **Résultat** :

  * Aucune pénalité appliquée.
  * Le retour est accepté.

---

**Nom** : `Ajouter_adherant`

* **Objectifs** :
  Créer une fiche adhérant selon le type : étudiant, professionnel, professeur.

* **Acteur** :
  Bibliothécaire

* **Entrée** :
  Nom, prénom, date de naissance, type, pièce justificative, cotisation

* **Scénario nominal** :
  Le bibliothécaire saisit les informations d’un nouvel adhérant.

* **Règles de gestion** :

  * Les champs obligatoires doivent être remplis.
  * L’adhésion doit être validée par une cotisation active.

* **Scénario alternatif** :

  * Données incomplètes.
  * Cotisation expirée.

* **Résultat** :

  * L’adhérant peut emprunter.
  * Enregistrement réussi.

---

**Nom** : `Gerer_cotisation`

* **Objectifs** :
  Gérer les cotisations annuelles ou mensuelles des adhérants.

* **Acteur** :
  Bibliothécaire / Système

* **Entrée** :
  Type d’adhérant, durée d’abonnement, montant payé

* **Scénario nominal** :
  L’adhérant paie une cotisation lors de son inscription ou de son renouvellement.

* **Règles de gestion** :

  * L’adhérant doit avoir une cotisation en cours pour emprunter.
  * Notification avant expiration.
  * Blocage automatique si cotisation expirée.

* **Scénario alternatif** :

  * Cotisation expirée → interdiction d’emprunter.

* **Résultat** :

  * Mise à jour de l’adhésion.

---
Nom : Inscription_adherant
Objectifs :
Enregistrer un nouvel adhérant dans le système avec ses informations personnelles et son statut (étudiant, professeur, professionnel).

Acteur :
Bibliothécaire

Entrée :
Nom, prénom, adresse, type, pièce justificative, montant de cotisation, date d’inscription

Scénario nominal :
Le bibliothécaire remplit un formulaire avec les informations du futur adhérant.

Règles de gestion :

L’adhérant ne doit pas déjà être inscrit.

Une cotisation valide est requise pour finaliser l’inscription.

Scénario alternatif :

L’adhérant existe déjà → erreur.

Cotisation non réglée → inscription refusée.

Résultat :

L’adhérant est ajouté à la base.

Peut réserver ou emprunter un livre.

Nom : Sanction_automatique
Objectifs :
Appliquer automatiquement une sanction à un adhérant en cas de non-respect des règles (retard, perte, dégradation).

Acteur :
Système / Bibliothécaire

Entrée :
Historique de prêts, état du livre, date de retour

Scénario nominal :
Un livre est retourné en retard ou dégradé. Le système applique une sanction.

Règles de gestion :

Retard > x jours → blocage temporaire.

Perte ou dégradation → pénalité financière.

Sanction levée manuellement ou après période.

Scénario alternatif :

Justificatif accepté → annulation sanction.

Résultat :

Sanction ajoutée au dossier de l’adhérant.

Restrictions appliquées.

Nom : Suivi_penalite
Objectifs :
Permettre de suivre les pénalités associées à un adhérant et leur règlement.

Acteur :
Bibliothécaire / Adhérant

Entrée :
Référence adhérant, type de pénalité, date, montant

Scénario nominal :
Le bibliothécaire consulte les pénalités ou enregistre un paiement.

Règles de gestion :

Une pénalité non réglée empêche tout nouvel emprunt.

Possibilité de paiement partiel selon le cas.

Scénario alternatif :

Pénalité réglée → suppression ou solde mis à jour.

Résultat :

Mise à jour du solde pénalités.

Déblocage possible de l’adhérant.

Nom : Consulter_catalogue
Objectifs :
Permettre aux adhérants ou visiteurs de consulter les livres disponibles.

Acteur :
Adhérant / Visiteur

Entrée :
Mots-clés, titre, auteur, catégorie, filtre par disponibilité

Scénario nominal :
L'utilisateur effectue une recherche dans le catalogue en ligne.

Règles de gestion :

Affichage limité pour les non-inscrits (visiteurs).

Affichage complet avec disponibilité pour les adhérants.

Scénario alternatif :

Aucun résultat trouvé → message adapté.

Résultat :

Liste des livres correspondants.

Nom : Statistiques_bibliotheque
Objectifs :
Fournir un tableau de bord statistique au bibliothécaire.

Acteur :
Bibliothécaire

Entrée :
Période, filtres (livres les plus empruntés, adhérants actifs, retards)

Scénario nominal :
Le bibliothécaire accède à l’onglet statistiques et visualise les indicateurs.

Règles de gestion :

Accès réservé au personnel.

Données exportables.

Scénario alternatif :

Pas de données pour la période → graphique vide.

Résultat :

Affichage de graphiques et rapports.

