-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 26 fév. 2018 à 20:00
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
-- Structure de la table `boutique`
--

DROP TABLE IF EXISTS `boutique`;
CREATE TABLE IF NOT EXISTS `boutique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` int(11) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idStock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeStock` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur`
--

DROP TABLE IF EXISTS `transporteur`;
CREATE TABLE IF NOT EXISTS `transporteur` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transportlivraison`
--

DROP TABLE IF EXISTS `transportlivraison`;
CREATE TABLE IF NOT EXISTS `transportlivraison` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL,
  PRIMARY KEY (`nom`,`entrepriseId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
