-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 27 Février 2018 à 00:05
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

CREATE TABLE `adresse` (
  `adresseId` int(11) NOT NULL,
  `numeroRue` int(11) NOT NULL,
  `rue` char(20) NOT NULL,
  `codePostal` int(11) NOT NULL,
  `ville` char(20) NOT NULL,
  `pays` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `avisclient`
--

CREATE TABLE `avisclient` (
  `id` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `date` date NOT NULL,
  `uId` int(11) NOT NULL,
  `pId` int(11) NOT NULL
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
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

CREATE TABLE `destination` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `droit`
--

CREATE TABLE `droit` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `id` int(11) NOT NULL,
  `raisonSociale` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `siret` int(14) NOT NULL,
  `ape` varchar(5) NOT NULL,
  `nbEmploye` int(4) NOT NULL,
  `adresseId` int(11) NOT NULL
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
-- Structure de la table `famille`
--

CREATE TABLE `famille` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `fraisdeport`
--

CREATE TABLE `fraisdeport` (
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

CREATE TABLE `gamme` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

CREATE TABLE `lignecommande` (
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
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `quantite`
--

CREATE TABLE `quantite` (
  `id` int(11) NOT NULL,
  `disponibilite` int(11) NOT NULL,
  `reserve` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `remise`
--

CREATE TABLE `remise` (
  `id` int(11) NOT NULL,
  `pourcentageRemise` float NOT NULL COMMENT 'Pourcentage de la remise à appliquer',
  `dateDebut` datetime NOT NULL,
  `dateFin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `repriseavoir`
--

CREATE TABLE `repriseavoir` (
  `id` int(11) NOT NULL COMMENT 'Correspond à numéroAvoir dans le diagramme de classe',
  `numAutRetour` int(11) NOT NULL,
  `nbProd` int(11) NOT NULL COMMENT 'Nombre de produit retournés',
  `numCmd` int(11) NOT NULL,
  `dateV` date NOT NULL COMMENT 'validite de l''avoir'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL,
  `description` varchar(400) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `roledroit`
--

CREATE TABLE `roledroit` (
  `roleId` int(11) NOT NULL,
  `droitId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

CREATE TABLE `statut` (
  `id` int(11) NOT NULL,
  `designation` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `typeStock` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transporteur`
--

CREATE TABLE `transporteur` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transportlivraison`
--

CREATE TABLE `transportlivraison` (
  `nom` int(11) NOT NULL,
  `entrepriseId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
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
  `statutId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`adresseId`);

--
-- Index pour la table `avisclient`
--
ALTER TABLE `avisclient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pId` (`pId`),
  ADD KEY `uId` (`uId`);

--
-- Index pour la table `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `droit`
--
ALTER TABLE `droit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `famille`
--
ALTER TABLE `famille`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `gamme`
--
ALTER TABLE `gamme`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `roledroit`
--
ALTER TABLE `roledroit`
  ADD PRIMARY KEY (`roleId`,`droitId`),
  ADD KEY `roleId` (`roleId`),
  ADD KEY `droitId` (`droitId`);

--
-- Index pour la table `statut`
--
ALTER TABLE `statut`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `entrepriseId` (`entrepriseId`),
  ADD KEY `roleId` (`roleId`),
  ADD KEY `statutId` (`statutId`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `avisclient`
--
ALTER TABLE `avisclient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `destination`
--
ALTER TABLE `destination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `droit`
--
ALTER TABLE `droit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `famille`
--
ALTER TABLE `famille`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `gamme`
--
ALTER TABLE `gamme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `statut`
--
ALTER TABLE `statut`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `roledroit`
--
ALTER TABLE `roledroit`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2` FOREIGN KEY (`droitId`) REFERENCES `droit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
