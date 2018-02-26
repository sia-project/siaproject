-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  lun. 26 fév. 2018 à 22:20
-- Version du serveur :  5.6.38
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `SIA2`
--

-- --------------------------------------------------------

--
-- Structure de la table `Adresse`
--

CREATE TABLE `Adresse` (
  `adresseId` int(11) NOT NULL,
  `numeroRue` int(11) NOT NULL,
  `rue` char(20) NOT NULL,
  `codePostal` int(11) NOT NULL,
  `ville` char(20) NOT NULL,
  `pays` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `boutique`
--

CREATE TABLE `boutique` (
  `id` int(11) NOT NULL,
  `description` int(11) NOT NULL,
  `idAdresse` int(11) NOT NULL,
  `idStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `Commande`
--

CREATE TABLE `Commande` (
  `numeroCMD` int(11) NOT NULL,
  `dateCMD` datetime NOT NULL,
  `etatCMD` enum('en préparation','préparée','en livraison','livrée') NOT NULL,
  `modeLivraison` enum('poste','transporteur','','') NOT NULL,
  `emeteur` enum('en ligne','en boutique','par correspondance','') NOT NULL,
  `fk_idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Entreprise`
--

CREATE TABLE `Entreprise` (
  `nom` char(30) NOT NULL,
  `raisonSociale` char(30) NOT NULL,
  `siret` int(11) NOT NULL,
  `APE/NAF` int(11) NOT NULL,
  `nbEmploye` int(11) NOT NULL,
  `idEntreprise` int(11) NOT NULL,
  `fk_adresseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
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
-- Structure de la table `fraisDePort`
--

CREATE TABLE `fraisDePort` (
  `idFP` int(11) NOT NULL,
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL,
  `montant` float NOT NULL,
  `fk_numeroCMD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ligneCommande`
--

CREATE TABLE `ligneCommande` (
  `fk_numeroCMD` int(11) NOT NULL,
  `fk_idProduit` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `numLivraison` int(11) NOT NULL,
  `delai` int(11) NOT NULL,
  `dateColisage` datetime NOT NULL,
  `dateExpd` datetime NOT NULL,
  `fk_numeroCMD` int(11) NOT NULL,
  `fk_adresseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `designation` varchar(20) NOT NULL,
  `description` varchar(150) NOT NULL,
  `poids` float NOT NULL COMMENT 'Poids du produit',
  `prix` float NOT NULL COMMENT 'Prix du produit',
  `lot` int(1) NOT NULL COMMENT 'Indique si le produit est un lot (est comoposé d''autres produit), par défaut non.',
  `placeRayon` varchar(10) NOT NULL COMMENT 'Indique aux ouvriers l''endroit où se trouve le produit dans les stocks'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `quantite`
--

CREATE TABLE `quantite` (
  `id` int(11) NOT NULL,
  `disponibilite` int(11) NOT NULL,
  `reserve` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `remise`
--

CREATE TABLE `remise` (
  `id` int(11) NOT NULL,
  `pourcentageRemise` float NOT NULL COMMENT 'Pourcentage de la remise à appliquer',
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `repriseavoir`
--

CREATE TABLE `repriseavoir` (
  `id` int(11) NOT NULL COMMENT 'Correspond à numéroAvoir dans le diagramme de classe',
  `numAutRetour` int(11) NOT NULL,
  `nbProd` int(11) NOT NULL COMMENT 'Nombre de produit retournés',
  `numCmd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `typeStock` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur`
--

CREATE TABLE `transporteur` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transportlivraison`
--

CREATE TABLE `transportlivraison` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Adresse`
--
ALTER TABLE `Adresse`
  ADD PRIMARY KEY (`adresseId`);

--
-- Index pour la table `boutique`
--
ALTER TABLE `boutique`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idAdresse` (`idAdresse`),
  ADD KEY `idStock` (`idStock`);

--
-- Index pour la table `Commande`
--
ALTER TABLE `Commande`
  ADD PRIMARY KEY (`numeroCMD`),
  ADD UNIQUE KEY `numeroCMD_2` (`numeroCMD`),
  ADD KEY `numeroCMD` (`numeroCMD`,`fk_idUser`);

--
-- Index pour la table `Entreprise`
--
ALTER TABLE `Entreprise`
  ADD PRIMARY KEY (`idEntreprise`),
  ADD KEY `fk_adresseId` (`fk_adresseId`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`idFacture`),
  ADD KEY `fk_numeroCMD` (`fk_numeroCMD`,`fk_adresseId`),
  ADD KEY `fk_ad` (`fk_adresseId`);

--
-- Index pour la table `fraisDePort`
--
ALTER TABLE `fraisDePort`
  ADD KEY `fk_nCMD` (`fk_numeroCMD`);

--
-- Index pour la table `ligneCommande`
--
ALTER TABLE `ligneCommande`
  ADD KEY `fk_numeroCMD` (`fk_numeroCMD`,`fk_idProduit`),
  ADD KEY `fk_idProduit` (`fk_idProduit`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`numLivraison`),
  ADD KEY `fk_numeroCMD` (`fk_numeroCMD`,`fk_adresseId`),
  ADD KEY `fk_adr` (`fk_adresseId`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `quantite`
--
ALTER TABLE `quantite`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `remise`
--
ALTER TABLE `remise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `repriseavoir`
--
ALTER TABLE `repriseavoir`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transporteur`
--
ALTER TABLE `transporteur`
  ADD PRIMARY KEY (`nom`);

--
-- Index pour la table `transportlivraison`
--
ALTER TABLE `transportlivraison`
  ADD PRIMARY KEY (`nom`,`entrepriseId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `boutique`
--
ALTER TABLE `boutique`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `quantite`
--
ALTER TABLE `quantite`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `remise`
--
ALTER TABLE `remise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `repriseavoir`
--
ALTER TABLE `repriseavoir`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correspond à numéroAvoir dans le diagramme de classe';

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `boutique`
--
ALTER TABLE `boutique`
  ADD CONSTRAINT `fk_idAdresse` FOREIGN KEY (`idAdresse`) REFERENCES `Adresse` (`adresseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idStock` FOREIGN KEY (`idStock`) REFERENCES `stock` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Entreprise`
--
ALTER TABLE `Entreprise`
  ADD CONSTRAINT `fk_adress` FOREIGN KEY (`fk_adresseId`) REFERENCES `Adresse` (`adresseId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `fk_ad` FOREIGN KEY (`fk_adresseId`) REFERENCES `Adresse` (`adresseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_numCMD` FOREIGN KEY (`fk_numeroCMD`) REFERENCES `Commande` (`numeroCMD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fraisDePort`
--
ALTER TABLE `fraisDePort`
  ADD CONSTRAINT `fk_nCMD` FOREIGN KEY (`fk_numeroCMD`) REFERENCES `Commande` (`numeroCMD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligneCommande`
--
ALTER TABLE `ligneCommande`
  ADD CONSTRAINT `fk_idProduit` FOREIGN KEY (`fk_idProduit`) REFERENCES `produit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_numeroCMD` FOREIGN KEY (`fk_numeroCMD`) REFERENCES `Commande` (`numeroCMD`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `fk_adr` FOREIGN KEY (`fk_adresseId`) REFERENCES `Adresse` (`adresseId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_numeCMD` FOREIGN KEY (`fk_numeroCMD`) REFERENCES `Commande` (`numeroCMD`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
