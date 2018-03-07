CREATE SCHEMA `lvs` DEFAULT CHARACTER SET utf8 ;
USE lvs;

DROP TABLE IF EXISTS produitRepriseAvoir;
DROP TABLE IF EXISTS repriseAvoir;
DROP TABLE IF EXISTS remise;
DROP TABLE IF EXISTS prodStock;
DROP TABLE IF EXISTS avisClient;
DROP TABLE IF EXISTS ligneCommande;
DROP TABLE IF EXISTS compositionProduit;
DROP TABLE IF EXISTS produit;
DROP TABLE IF EXISTS gammeProduit;
DROP TABLE IF EXISTS familleProduit;
DROP TABLE IF EXISTS facture;
DROP TABLE IF EXISTS livraison;
DROP TABLE IF EXISTS commandeMultipleUtilisateur;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS fraisDePort;
DROP TABLE IF EXISTS clientAdresse;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS entreprise;
DROP TABLE IF EXISTS roleDroit;
DROP TABLE IF EXISTS droit;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS boutique;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS adresse;

CREATE TABLE adresse 
(	adrId INT(11) PRIMARY KEY,
	rue VARCHAR(100),
	cp VARCHAR(5),
	ville VARCHAR(80),
	pays VARCHAR (50)
)ENGINE=InnoDB;

CREATE TABLE stock
(
	stockId INT(11) PRIMARY KEY,
	typeStock ENUM('stock retour','stock local','')
)ENGINE=InnoDB;

CREATE TABLE boutique
(
	btqId INT(11) PRIMARY KEY,
	description VARCHAR(500),
	adrId INT(11),
	stockId INT(11),
	FOREIGN KEY (adrId) REFERENCES adresse(adrId) ON DELETE SET NULL,
	FOREIGN KEY (stockId) REFERENCES stock(stockId) ON DELETE SET NULL
)ENGINE=InnoDB;

CREATE TABLE role
(
	roleId INT(3) PRIMARY KEY,
	libelle VARCHAR(50),
	description VARCHAR(150)
)ENGINE=InnoDB; 

CREATE TABLE droit
(
	droitId INT(3) PRIMARY KEY,
	libelle VARCHAR(50),
	description VARCHAR(150)
)ENGINE=InnoDB;

CREATE TABLE roleDroit
(
	roleId INT(3),
	droitId INT(3),
	PRIMARY KEY(roleId,droitId),
	FOREIGN KEY (roleId) REFERENCES role(roleId),
	FOREIGN KEY (droitId) REFERENCES droit(droitId)
)ENGINE=InnoDB;

CREATE TABLE entreprise
(
	entId INT(11)PRIMARY KEY,
	raisonSociale VARCHAR(50),
	nom VARCHAR(100),
	siret VARCHAR(14),
	ape VARCHAR(5),
	activitePrincipale VARCHAR(30),
	nbEmployes INT(5),
	adrId INT(11),
	FOREIGN KEY (adrId) REFERENCES adresse(adrId) ON UPDATE SET NULL ON DELETE SET NULL
)ENGINE=InnoDB;

CREATE TABLE utilisateur
(
	uId INT(11) PRIMARY KEY,
	civilite ENUM('M','Mme'),
	nom VARCHAR(30),
	prenom VARCHAR(30),
	dateNaiss DATE,
	telPrincipal VARCHAR(10),
	telSecondaire VARCHAR(10),
	adrMail VARCHAR(80),
	entId INT(11),
	mtMaxCmd FLOAT(7,2),
	mdp VARCHAR(255),
	cle VARCHAR(16),
	sel VARCHAR(255),
	dateCreationCompte DATETIME,
	dateDerModif DATETIME,
	dateDerConnex DATETIME,
	etatCompte INT(1),
	typeUtilisateur VARCHAR(30),
	roleId INT(3),
	FOREIGN KEY (entId) REFERENCES entreprise(entId) ON UPDATE SET NULL ON DELETE SET NULL,
	FOREIGN KEY (roleId) REFERENCES role(roleId) ON UPDATE SET NULL ON DELETE SET NULL
)ENGINE=InnoDB;

CREATE TABLE clientAdresse
(
	uId INT(11),
	adrId INT(11),
	typeAdresse ENUM('Livraison','Facturation',''),
	PRIMARY KEY(uId,adrId),
	FOREIGN KEY (uId) REFERENCES utilisateur(uId) ON DELETE CASCADE,
	FOREIGN KEY (adrId) REFERENCES adresse(adrId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE fraisDePort
(
	fraisPortId INT(11) PRIMARY KEY,
	dateDebut DATE,
	dateFin DATE,
	montant FLOAT(4,2)
)ENGINE=InnoDB;

CREATE TABLE commande
(
	cmdId INT(11) PRIMARY KEY,
	dateCmd DATETIME,
	etatCmd ENUM('en preparation','preparee','en cours de livraison','livree'),
	modeLivraison ENUM('poste','transporteur','pointRelais'),
	moyenPriseCmd ENUM('boutique','correspondance','internet'),
	adrLivId INT(11),
	adrFactId INT(11),
	userId INT(11),
	fraisPortId INT(11),
	FOREIGN KEY (userId,adrLivId) REFERENCES clientAdresse(uId,adrId),
	FOREIGN KEY (userId,adrFactId) REFERENCES clientAdresse(uId,adrId),
	FOREIGN KEY (userId) REFERENCES utilisateur(uId) ON DELETE CASCADE,
	FOREIGN KEY (fraisPortId) REFERENCES fraisDePort(fraisPortId)
)ENGINE=InnoDB;

CREATE TABLE commandeMultipleUtilisateur
(
	cmdId INT(11),
	uId INT(11),
	PRIMARY KEY (cmdId,uId),
	FOREIGN KEY (cmdId) REFERENCES commande(cmdId) ON DELETE CASCADE,
    FOREIGN KEY (uId) REFERENCES utilisateur(uId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE livraison
(
	livraisonId INT(11)PRIMARY KEY,
	dateColisage DATE,
	dateExpedition DATE,
	delaisLivraison INT(2),
	transporteurId INT(11),
	cmdId INT(11),
	FOREIGN KEY (cmdId) REFERENCES commande(cmdId),
	FOREIGN KEY (transporteurId) REFERENCES entreprise(entId)
)ENGINE=InnoDB;

CREATE TABLE facture
(
	factId INT(11) PRIMARY KEY,
	dateFacturation DATE,
	dateEcheance DATE,
	prixHT FLOAT(7,2),
	remise FLOAT(5,2),
	cmdId INT(11),
	FOREIGN KEY (cmdId) REFERENCES commande(cmdId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE familleProduit
(
	familleProduitId INT(11) PRIMARY KEY,
	libelle VARCHAR(50),
	description VARCHAR(150)
)ENGINE=InnoDB;

CREATE TABLE gammeProduit
(
	gammeProduitId INT(11) PRIMARY KEY,
	libelle VARCHAR(50),
	description VARCHAR(150)
)ENGINE=InnoDB;

CREATE TABLE produit
(
	prodId INT(11) PRIMARY KEY,
	libelle VARCHAR(50),
	marque VARCHAR(50),
	description VARCHAR(200),
	poids FLOAT(5,2),
	prixHT FLOAT(6,2),
	lot INT(1),
	placeRayon VARCHAR(30),
	typeTVA ENUM('5,5%','10%','20%'),
	familleProduitId INT(11),
	gammeProduitId INT(11),
	destination VARCHAR(30),
	FOREIGN KEY (familleProduitId) REFERENCES familleProduit(familleProduitId),
	FOREIGN KEY (gammeProduitId) REFERENCES gammeProduit(gammeProduitId)
)ENGINE=InnoDB;

CREATE TABLE compositionProduit
(
	produitComposeId INT(11),
	produitComposantId INT(11),
	PRIMARY KEY (produitComposeId,produitComposantId),
	FOREIGN KEY (produitComposeId) REFERENCES produit(prodId) ON DELETE CASCADE,
	FOREIGN KEY (produitComposantId) REFERENCES produit(prodId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE ligneCommande
(
	cmdId INT(11),
	prodId INT(11),
	quantite INT(2),
	PRIMARY KEY(cmdId,prodId),
	FOREIGN KEY (cmdId) REFERENCES commande(cmdId) ON DELETE CASCADE,
	FOREIGN KEY (prodId) REFERENCES produit(prodId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE avisClient
(
	avisId INT(11)PRIMARY KEY,
	note FLOAT(2,1),
	dateAvis DATETIME,
	commentaire VARCHAR(1000),
	uId INT(11),
	pId INT(11),
	FOREIGN KEY (uId) REFERENCES utilisateur(uId)ON DELETE CASCADE,
	FOREIGN KEY (pId) REFERENCES produit(prodId)ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE prodStock
(
	prodId INT(11),
	stockId INT(11),
	QtePhysique INT(6),
	QteReserve INT(6),
	PRIMARY KEY(prodId,stockId),
	FOREIGN KEY (prodId) REFERENCES produit(prodId) ON DELETE CASCADE,
	FOREIGN KEY (stockId) REFERENCES stock(stockId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE remise
(
	remiseId INT(11) PRIMARY KEY,
	dateDebut DATE,
	dateFin DATE,
	pourcentageRemise FLOAT(5,2),
	prodId INT(11),
	familleProduitId INT(11),
	FOREIGN KEY (prodId) REFERENCES produit(prodId) ON DELETE CASCADE,
	FOREIGN KEY (familleProduitId) REFERENCES familleProduit(familleProduitId) ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE TABLE repriseAvoir
(
	repriseId INT(11) PRIMARY KEY,
	numAuthRetour INT(11),
	cmdId INT(11),
	FOREIGN KEY (cmdId) REFERENCES commande(cmdId)
)ENGINE=InnoDB;

CREATE TABLE produitRepriseAvoir
(
	repriseId INT(11),
	prodId INT(11),
	qteRetourne INT(2),
	PRIMARY KEY(repriseId,prodId),
	FOREIGN KEY (repriseId) REFERENCES repriseAvoir(repriseId) ON DELETE CASCADE,
	FOREIGN KEY (prodId) REFERENCES produit(prodId) ON DELETE CASCADE
)ENGINE=InnoDB;