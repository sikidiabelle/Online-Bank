-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Lun 01 Avril 2019 à 14:50
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bank`
--

-- --------------------------------------------------------

--
-- Structure de la table `cepargne`
--

CREATE TABLE `cepargne` (
  `numCpt` varchar(20) NOT NULL,
  `solde` double NOT NULL,
  `TauxInteret` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `cepargne`
--
ALTER TABLE `cepargne`
  ADD PRIMARY KEY (`numCpt`);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cepargne`
--
ALTER TABLE `cepargne`
  ADD CONSTRAINT `fk_ce_cpt` FOREIGN KEY (`numCpt`) REFERENCES `compte` (`numCompte`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
