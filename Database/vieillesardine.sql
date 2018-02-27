-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 27 fév. 2018 à 05:34
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
  `description` int(11) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idStock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `numeroCMD` int(11) NOT NULL,
  `dateCMD` datetime NOT NULL,
  `etatCMD` enum('en préparation','préparée','en livraison','livrée') NOT NULL,
  `modeLivraison` enum('poste','transporteur','','') NOT NULL,
  `emeteur` enum('en ligne','en boutique','par correspondance','') NOT NULL,
  `fk_idUser` int(11) NOT NULL
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
  `adresseId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `idFacture` int(11) NOT NULL,
  `dateFacturation` datetime NOT NULL,
  `dateEcheance` datetime NOT NULL,
  `remise` float NOT NULL,
  `prixHT` float NOT NULL,
  `typeFacturation` int(11) NOT NULL,
  `fk_numeroCMD` int(11) NOT NULL,
  `fk_adresseId` int(11) NOT NULL
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
  `idFP` int(11) NOT NULL,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `montant` float NOT NULL,
  `fk_numeroCMD` int(11) NOT NULL
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
  `fk_numeroCMD` int(11) NOT NULL,
  `fk_idProduit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
CREATE TABLE IF NOT EXISTS `livraison` (
  `numLivraison` int(11) NOT NULL,
  `delai` int(11) NOT NULL,
  `dateColisage` datetime NOT NULL,
  `dateExpd` datetime NOT NULL,
  `fk_numeroCMD` int(11) NOT NULL,
  `fk_adresseId` int(11) NOT NULL
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `quantite`
--

DROP TABLE IF EXISTS `quantite`;
CREATE TABLE IF NOT EXISTS `quantite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disponibilite` int(11) NOT NULL,
  `reserve` int(11) NOT NULL,
  PRIMARY KEY (`id`)
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
  `numCmd` int(11) NOT NULL,
  PRIMARY KEY (`id`)
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
  KEY `roleId` (`roleId`),
  KEY `droitId` (`droitId`)
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
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transportlivraison`
--

DROP TABLE IF EXISTS `transportlivraison`;
CREATE TABLE IF NOT EXISTS `transportlivraison` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL,
  PRIMARY KEY (`nom`,`entrepriseId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` char(15) DEFAULT NULL,
  `prenom` char(15) DEFAULT NULL,
  `dateNaiss` date NOT NULL,
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
