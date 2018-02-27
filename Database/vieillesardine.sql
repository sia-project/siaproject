-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 27 fév. 2018 à 19:02
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `vieillesardine`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `adresseId` int(11) NOT NULL,
  `numeroRue` int(11) NOT NULL,
  `rue` char(20) NOT NULL,
  `codePostal` int(11) NOT NULL,
  `ville` char(20) NOT NULL,
  `pays` char(15) NOT NULL,
  PRIMARY KEY (`adresseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `avisclient`
--

DROP TABLE IF EXISTS `avisclient`;
CREATE TABLE IF NOT EXISTS `avisclient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` int(11) NOT NULL,
  `date` date NOT NULL,
  `uId` int(11) NOT NULL,
  `pId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pId` (`pId`),
  KEY `uId` (`uId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `boutique`
--

DROP TABLE IF EXISTS `boutique`;
CREATE TABLE IF NOT EXISTS `boutique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(250) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idStock` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_adresseid` (`idAdresse`),
  KEY `fk_stockid` (`idStock`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateCMD` datetime NOT NULL,
  `etatCMD` enum('en préparation','préparée','en livraison','livrée') NOT NULL,
  `modeLivraison` enum('poste','transporteur') NOT NULL,
  `emeteur` enum('en ligne','en boutique','par correspondance','') NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idUser` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `destination`
--

DROP TABLE IF EXISTS `destination`;
CREATE TABLE IF NOT EXISTS `destination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

DROP TABLE IF EXISTS `droit`;
CREATE TABLE IF NOT EXISTS `droit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `raisonSociale` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `siret` int(14) NOT NULL,
  `ape` varchar(5) NOT NULL,
  `nbEmploye` int(4) NOT NULL,
  `adresse_Id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idadresse` (`adresse_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `idFacture` int(11) NOT NULL AUTO_INCREMENT,
  `dateFacturation` datetime NOT NULL,
  `dateEcheance` datetime NOT NULL,
  `remise` float NOT NULL,
  `prixHT` float NOT NULL,
  `typeFacturation` int(11) NOT NULL,
  `idCommande` int(11) NOT NULL,
  `adresseId` int(11) NOT NULL,
  PRIMARY KEY (`idFacture`),
  KEY `fk_idCommande` (`idCommande`),
  KEY `fk_factureAdresseid` (`adresseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `famille`
--

DROP TABLE IF EXISTS `famille`;
CREATE TABLE IF NOT EXISTS `famille` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fraisdeport`
--

DROP TABLE IF EXISTS `fraisdeport`;
CREATE TABLE IF NOT EXISTS `fraisdeport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `montant` float NOT NULL,
  `idCommande` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idCommandeFacture` (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `gamme`
--

DROP TABLE IF EXISTS `gamme`;
CREATE TABLE IF NOT EXISTS `gamme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

DROP TABLE IF EXISTS `lignecommande`;
CREATE TABLE IF NOT EXISTS `lignecommande` (
  `idCommande` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`idCommande`,`idProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
CREATE TABLE IF NOT EXISTS `livraison` (
  `numLivraison` int(11) NOT NULL AUTO_INCREMENT,
  `delai` int(11) NOT NULL,
  `dateColisage` datetime NOT NULL,
  `dateExpd` datetime NOT NULL,
  `idCommande` int(11) NOT NULL,
  `adresseId` int(11) NOT NULL,
  `transporteurId` int(11) NOT NULL,
  PRIMARY KEY (`numLivraison`),
  KEY `fk_adresseidLivraison` (`adresseId`),
  KEY `fk_commandeId` (`idCommande`),
  KEY `fk_transporteurLivraison` (`transporteurId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(20) NOT NULL,
  `description` varchar(150) NOT NULL,
  `poids` float NOT NULL COMMENT 'Poids du produit',
  `prix` float NOT NULL COMMENT 'Prix du produit',
  `lot` int(1) NOT NULL COMMENT 'Indique si le produit est un lot (est comoposé d''autres produit), par défaut non.',
  `placeRayon` varchar(10) NOT NULL COMMENT 'Indique aux ouvriers l''endroit où se trouve le produit dans les stocks',
  `familleId` int(11) NOT NULL,
  `gammeId` int(11) NOT NULL,
  `marqueId` int(11) NOT NULL,
  `destinationId` int(11) NOT NULL,
  `remiseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_familleId` (`familleId`),
  KEY `fk_gammeId` (`gammeId`),
  KEY `fk_marqueId` (`marqueId`),
  KEY `fk_destinationId` (`destinationId`),
  KEY `fk_remiseId` (`remiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `quantite`
--

DROP TABLE IF EXISTS `quantite`;
CREATE TABLE IF NOT EXISTS `quantite` (
  `disponibilite` int(11) NOT NULL,
  `reserve` int(11) NOT NULL,
  `produitId` int(11) NOT NULL,
  `stockId` int(11) NOT NULL,
  PRIMARY KEY (`produitId`,`stockId`),
  KEY `fk_StockIds` (`stockId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `remise`
--

DROP TABLE IF EXISTS `remise`;
CREATE TABLE IF NOT EXISTS `remise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pourcentageRemise` float NOT NULL COMMENT 'Pourcentage de la remise à appliquer',
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `plafondAchat` int(11) NOT NULL DEFAULT '2000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `repriseavoir`
--

DROP TABLE IF EXISTS `repriseavoir`;
CREATE TABLE IF NOT EXISTS `repriseavoir` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correspond à numéroAvoir dans le diagramme de classe',
  `numAutRetour` int(11) NOT NULL,
  `nbProd` int(11) NOT NULL COMMENT 'Nombre de produit retournés',
  `idCommande` int(11) NOT NULL,
  `dateValidite` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idCommandeRepriseAvoir` (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `roledroit`
--

DROP TABLE IF EXISTS `roledroit`;
CREATE TABLE IF NOT EXISTS `roledroit` (
  `roleId` int(11) NOT NULL,
  `droitId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`droitId`),
  KEY `fk_droitId` (`droitId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

DROP TABLE IF EXISTS `statut`;
CREATE TABLE IF NOT EXISTS `statut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeStock` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur`
--

DROP TABLE IF EXISTS `transporteur`;
CREATE TABLE IF NOT EXISTS `transporteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_entrepriseID` (`entrepriseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `dateNaiss` datetime NOT NULL,
  `civilite` enum('M','Mme') NOT NULL,
  `telPrin` varchar(14) NOT NULL,
  `telCom` varchar(14) NOT NULL,
  `dateCrCompt` datetime NOT NULL,
  `dateDerCnx` datetime NOT NULL,
  `dateDerMdf` datetime NOT NULL,
  `mdp` varchar(20) NOT NULL,
  `cles` varchar(16) NOT NULL,
  `sel` varchar(16) NOT NULL,
  `etatCompte` tinyint(1) NOT NULL DEFAULT '0',
  `MontantMaxCour` float NOT NULL,
  `avatar` varchar(250) DEFAULT NULL,
  `entrepriseId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `statutId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrepriseId` (`entrepriseId`),
  KEY `roleId` (`roleId`),
  KEY `statutId` (`statutId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `avisclient`
--
ALTER TABLE `avisclient`
  ADD CONSTRAINT `fk_pid` FOREIGN KEY (`pId`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `fk_uid` FOREIGN KEY (`uId`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `boutique`
--
ALTER TABLE `boutique`
  ADD CONSTRAINT `fk_adresseid` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`adresseId`),
  ADD CONSTRAINT `fk_stockid` FOREIGN KEY (`idStock`) REFERENCES `stock` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_idUser` FOREIGN KEY (`idUser`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD CONSTRAINT `fk_idadresse` FOREIGN KEY (`adresse_Id`) REFERENCES `adresse` (`adresseId`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `fk_factureAdresseid` FOREIGN KEY (`adresseId`) REFERENCES `adresse` (`adresseId`),
  ADD CONSTRAINT `fk_idCommande` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `fraisdeport`
--
ALTER TABLE `fraisdeport`
  ADD CONSTRAINT `fk_idCommandeFacture` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `fk_adresseidLivraison` FOREIGN KEY (`adresseId`) REFERENCES `adresse` (`adresseId`),
  ADD CONSTRAINT `fk_commandeId` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`),
  ADD CONSTRAINT `fk_transporteurLivraison` FOREIGN KEY (`transporteurId`) REFERENCES `transporteur` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_destinationId` FOREIGN KEY (`destinationId`) REFERENCES `destination` (`id`),
  ADD CONSTRAINT `fk_familleId` FOREIGN KEY (`familleId`) REFERENCES `famille` (`id`),
  ADD CONSTRAINT `fk_gammeId` FOREIGN KEY (`gammeId`) REFERENCES `gamme` (`id`),
  ADD CONSTRAINT `fk_marqueId` FOREIGN KEY (`marqueId`) REFERENCES `marque` (`id`),
  ADD CONSTRAINT `fk_remiseId` FOREIGN KEY (`remiseId`) REFERENCES `remise` (`id`);

--
-- Contraintes pour la table `quantite`
--
ALTER TABLE `quantite`
  ADD CONSTRAINT `fk_StockIds` FOREIGN KEY (`stockId`) REFERENCES `stock` (`id`),
  ADD CONSTRAINT `fk_produitId` FOREIGN KEY (`produitId`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `repriseavoir`
--
ALTER TABLE `repriseavoir`
  ADD CONSTRAINT `fk_idCommandeRepriseAvoir` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `roledroit`
--
ALTER TABLE `roledroit`
  ADD CONSTRAINT `fk_droitId` FOREIGN KEY (`droitId`) REFERENCES `droit` (`id`),
  ADD CONSTRAINT `fk_roledroit` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `transporteur`
--
ALTER TABLE `transporteur`
  ADD CONSTRAINT `fk_entrepriseID` FOREIGN KEY (`entrepriseId`) REFERENCES `entreprise` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_UserRole` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `fk_entrepriseIDUser` FOREIGN KEY (`entrepriseId`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `fk_statutId` FOREIGN KEY (`statutId`) REFERENCES `statut` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
