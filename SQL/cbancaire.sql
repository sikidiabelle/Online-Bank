-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mer 03 Avril 2019 à 09:14
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
-- Structure de la table `cbancaire`
--

CREATE TABLE `cbancaire` (
  `numCpt` varchar(20) NOT NULL,
  `solde` double NOT NULL,
  `plVir` int(11) NOT NULL,
  `plRetrait` int(11) NOT NULL,
  `plDecouvert` int(11) NOT NULL,
  `cartes` varchar(20) NOT NULL,
  `chequier` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cbancaire`
--

INSERT INTO `cbancaire` (`numCpt`, `solde`, `plVir`, `plRetrait`, `plDecouvert`, `cartes`, `chequier`) VALUES
('CDIJECBKBHIJ', 100, 100, 100, 100, 'non', 'non'),
('HGDHHHEECHGD', 100, 10, 100, 10, 'non', 'non'),
('JHKIACAHIKHI', 100, 100, 100, 100, 'non', 'non');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `cbancaire`
--
ALTER TABLE `cbancaire`
  ADD PRIMARY KEY (`numCpt`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
