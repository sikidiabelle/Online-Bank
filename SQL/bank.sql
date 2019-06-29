-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 02 Mai 2019 à 14:38
-- Version du serveur :  5.7.11
-- Version de PHP :  7.0.3

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
-- Structure de la table `action`
--

CREATE TABLE `action` (
  `idAction` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prix` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `actionnaire`
--

CREATE TABLE `actionnaire` (
  `idAction` int(11) NOT NULL,
  `numCpt` varchar(20) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `idActionnaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

CREATE TABLE `carte` (
  `idCarte` int(11) NOT NULL,
  `numCrt` varchar(20) NOT NULL,
  `numCpt` varchar(20) NOT NULL,
  `dateExp` date NOT NULL,
  `cvc` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Structure de la table `cepargne`
--

CREATE TABLE `cepargne` (
  `numCpt` varchar(20) NOT NULL,
  `solde` double NOT NULL,
  `TauxInteret` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `chequier`
--

CREATE TABLE `chequier` (
  `idCheq` int(11) NOT NULL,
  `numCpt` varchar(20) NOT NULL,
  `numCheq` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `civ` char(1) NOT NULL,
  `nom` varchar(70) NOT NULL,
  `prenom` varchar(70) NOT NULL,
  `adr` varchar(200) NOT NULL,
  `bday` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `pays` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `nat` varchar(50) NOT NULL,
  `cP` varchar(10) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `mdp` varchar(250) NOT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `numCompte` varchar(20) NOT NULL,
  `rib` char(2) NOT NULL,
  `iban` varchar(34) NOT NULL,
  `bic` varchar(20) NOT NULL DEFAULT 'BANKTIC',
  `ClientID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `cours_bourse`
--

CREATE TABLE `cours_bourse` (
  `idCours` int(11) NOT NULL,
  `dateCours` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `indice` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ctitre`
--

CREATE TABLE `ctitre` (
  `numCpt` varchar(20) NOT NULL,
  `capital` double NOT NULL,
  `NIF` varchar(20) NOT NULL,
  `residence` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

CREATE TABLE `gerant` (
  `idGerant` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  `mdp` varchar(250) COLLATE utf8_bin NOT NULL,
  `status` varchar(15) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `key_client_confirm`
--

CREATE TABLE `key_client_confirm` (
  `idClient` int(11) NOT NULL,
  `keyClient` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `idTrans` int(11) NOT NULL,
  `descp` varchar(70) NOT NULL,
  `numCpt_src` varchar(20) NOT NULL,
  `iban_cible` varchar(34) NOT NULL,
  `montant` double NOT NULL,
  `type` tinyint(1) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Index pour les tables exportées
--

--
-- Index pour la table `action`
--
ALTER TABLE `action`
  ADD PRIMARY KEY (`idAction`);

--
-- Index pour la table `actionnaire`
--
ALTER TABLE `actionnaire`
  ADD PRIMARY KEY (`idActionnaire`),
  ADD KEY `idAction` (`idAction`),
  ADD KEY `numCpt` (`numCpt`);

--
-- Index pour la table `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`idCarte`),
  ADD KEY `numCpt` (`numCpt`);

--
-- Index pour la table `cbancaire`
--
ALTER TABLE `cbancaire`
  ADD PRIMARY KEY (`numCpt`);

--
-- Index pour la table `cepargne`
--
ALTER TABLE `cepargne`
  ADD PRIMARY KEY (`numCpt`);

--
-- Index pour la table `chequier`
--
ALTER TABLE `chequier`
  ADD PRIMARY KEY (`idCheq`),
  ADD KEY `numCpt` (`numCpt`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`numCompte`),
  ADD KEY `ClientID` (`ClientID`);

--
-- Index pour la table `cours_bourse`
--
ALTER TABLE `cours_bourse`
  ADD PRIMARY KEY (`idCours`);

--
-- Index pour la table `ctitre`
--
ALTER TABLE `ctitre`
  ADD PRIMARY KEY (`numCpt`);

--
-- Index pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD PRIMARY KEY (`idGerant`);

--
-- Index pour la table `key_client_confirm`
--
ALTER TABLE `key_client_confirm`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idTrans`),
  ADD KEY `numCpt_src` (`numCpt_src`);

--
-- AUTO_INCREMENT pour les tables exportées
--
--
-- AUTO_INCREMENT pour la table `action`
--
ALTER TABLE `action`
  MODIFY `idAction` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `actionnaire`
--
ALTER TABLE `actionnaire`
  MODIFY `idActionnaire` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `carte`
--
ALTER TABLE `carte`
  MODIFY `idCarte` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `chequier`
--
ALTER TABLE `chequier`
  MODIFY `idCheq` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `cours_bourse`
--
ALTER TABLE `cours_bourse`
  MODIFY `idCours` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=251;
--
-- AUTO_INCREMENT pour la table `gerant`
--
ALTER TABLE `gerant`
  MODIFY `idGerant` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idTrans` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `actionnaire`
--
ALTER TABLE `actionnaire`
  ADD CONSTRAINT `fk_idAction` FOREIGN KEY (`idAction`) REFERENCES `action` (`idAction`),
  ADD CONSTRAINT `fk_numCpt` FOREIGN KEY (`numCpt`) REFERENCES `ctitre` (`numCpt`);

--
-- Contraintes pour la table `carte`
--
ALTER TABLE `carte`
  ADD CONSTRAINT `fk_cc_cpt` FOREIGN KEY (`numCpt`) REFERENCES `cbancaire` (`numCpt`) ON DELETE CASCADE;

--
-- Contraintes pour la table `cbancaire`
--
ALTER TABLE `cbancaire`
  ADD CONSTRAINT `fk_numCpt_Compte` FOREIGN KEY (`numCpt`) REFERENCES `compte` (`numCompte`) ON DELETE CASCADE;

--
-- Contraintes pour la table `cepargne`
--
ALTER TABLE `cepargne`
  ADD CONSTRAINT `fk_ce_cpt` FOREIGN KEY (`numCpt`) REFERENCES `compte` (`numCompte`) ON DELETE CASCADE;

--
-- Contraintes pour la table `chequier`
--
ALTER TABLE `chequier`
  ADD CONSTRAINT `fk_ch_cpt` FOREIGN KEY (`numCpt`) REFERENCES `cbancaire` (`numCpt`) ON DELETE CASCADE;

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `fk_id_client` FOREIGN KEY (`ClientID`) REFERENCES `client` (`idClient`) ON DELETE CASCADE;

--
-- Contraintes pour la table `ctitre`
--
ALTER TABLE `ctitre`
  ADD CONSTRAINT `fk_ct_cpt` FOREIGN KEY (`numCpt`) REFERENCES `compte` (`numCompte`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `key_client_confirm`
--
ALTER TABLE `key_client_confirm`
  ADD CONSTRAINT `fk_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`) ON DELETE CASCADE;

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `fk_numCpt_src` FOREIGN KEY (`numCpt_src`) REFERENCES `compte` (`numCompte`);

--
-- Contenu de la table `action`
--

INSERT INTO `action` (`idAction`, `nom`, `prix`) VALUES
(1, 'Accor Hotels', 38030),
(2, 'Air Liquide', 118000),
(3, 'Airbus', 122300),
(4, 'Arcelor Mittal', 19958),
(5, 'Atos', 96000),
(6, 'Axa', 23750),
(7, 'Bnp Paribas', 46835),
(8, 'Bouygues', 34510),
(9, 'CapGemini', 114800),
(10, 'Carrefour', 17345),
(11, 'Credit Agricole', 12040),
(12, 'Danone', 71220),
(13, 'Dassault Systemes', 142050),
(14, 'Engie', 12980),
(15, 'EssilorLuxottica', 106450),
(16, 'Hermes', 619200),
(17, 'Kering', 532800),
(18, 'Legrand SA', 64500),
(19, 'L\'oreal', 244600),
(20, 'Lvmh', 345900),
(21, 'Michelin', 117300),
(22, 'Orange', 14130),
(23, 'Pernod Ricard', 156150),
(24, 'Peugeot', 24080),
(25, 'Publicis Groupe', 52300),
(26, 'Renault', 60010),
(27, 'Safran', 125500),
(28, 'Saint Gobain', 36055),
(29, 'Sanofi', 74040),
(30, 'Schneider Electric', 76720),
(31, 'Societe Generale', 27495),
(32, 'Sodexo', 103750),
(33, 'Stmicroelectronics', 16745),
(34, 'TechnipFMC', 22450),
(35, 'Total', 50130),
(36, 'Unibail - Wfd Unibai', 154700),
(37, 'Valeo', 31260),
(38, 'Veolia Environ.', 21140),
(39, 'Vinci', 89100),
(40, 'Vivendi', 25550);

--
-- Contenu de la table `cours_bourse`
--

INSERT INTO `cours_bourse` (`idCours`, `dateCours`, `indice`) VALUES
(1, '2019-05-02 14:56:06', 5558.96),
(2, '2019-05-02 16:15:15', 5102.16),
(3, '2019-05-02 16:15:15', 4912.17),
(4, '2019-05-02 16:15:15', 5528.04),
(5, '2019-05-02 16:15:15', 4788.22),
(6, '2019-05-02 16:15:15', 5239.17),
(7, '2019-05-02 16:15:15', 5102.57),
(8, '2019-05-02 16:15:15', 5336.34),
(9, '2019-05-02 16:15:15', 4888.2),
(10, '2019-05-02 16:15:15', 5488.7),
(11, '2019-05-02 16:15:15', 5605.97),
(12, '2019-05-02 16:15:15', 5038.5),
(13, '2019-05-02 16:15:15', 5318.26),
(14, '2019-05-02 16:15:15', 5232.02),
(15, '2019-05-02 16:15:15', 5252.51),
(16, '2019-05-02 16:15:15', 5259.64),
(17, '2019-05-02 16:15:15', 5401.4),
(18, '2019-05-02 16:15:15', 4940.51),
(19, '2019-05-02 16:15:15', 5288.43),
(20, '2019-05-02 16:15:15', 4635.21),
(21, '2019-05-02 16:15:15', 4935.42),
(22, '2019-05-02 16:15:15', 5382.35),
(23, '2019-05-02 16:15:15', 4602.85),
(24, '2019-05-02 16:15:15', 4934.65),
(25, '2019-05-02 16:15:15', 4730.25),
(26, '2019-05-02 16:15:15', 4767.32),
(27, '2019-05-02 16:15:15', 4735.49),
(28, '2019-05-02 16:15:15', 5162.25),
(29, '2019-05-02 16:15:15', 5588.25),
(30, '2019-05-02 16:15:15', 4756.98),
(31, '2019-05-02 16:15:15', 5548.24),
(32, '2019-05-02 16:15:15', 5347.19),
(33, '2019-05-02 16:15:15', 4940.92),
(34, '2019-05-02 16:15:15', 4627.42),
(35, '2019-05-02 16:15:15', 4807.53),
(36, '2019-05-02 16:15:15', 4673.89),
(37, '2019-05-02 16:15:15', 5579.28),
(38, '2019-05-02 16:15:15', 4698.6),
(39, '2019-05-02 16:15:15', 5099.58),
(40, '2019-05-02 16:15:15', 4693.48),
(41, '2019-05-02 16:15:16', 5072.14),
(42, '2019-05-02 16:15:16', 4804.57),
(43, '2019-05-02 16:15:16', 4969.08),
(44, '2019-05-02 16:15:16', 5339),
(45, '2019-05-02 16:15:16', 5082.96),
(46, '2019-05-02 16:15:16', 5003.17),
(47, '2019-05-02 16:15:16', 5137.58),
(48, '2019-05-02 16:15:16', 5544.6),
(49, '2019-05-02 16:15:16', 4890.77),
(50, '2019-05-02 16:15:16', 5142.23),
(51, '2019-05-02 16:15:16', 5290.82),
(52, '2019-05-02 16:15:16', 4604.76),
(53, '2019-05-02 16:15:16', 5578.62),
(54, '2019-05-02 16:15:16', 4567.15),
(55, '2019-05-02 16:15:16', 4675.36),
(56, '2019-05-02 16:15:16', 4708.4),
(57, '2019-05-02 16:15:16', 5214.58),
(58, '2019-05-02 16:15:16', 5336.9),
(59, '2019-05-02 16:15:16', 5083.78),
(60, '2019-05-02 16:15:16', 5054.74),
(61, '2019-05-02 16:15:16', 4830.08),
(62, '2019-05-02 16:15:16', 5073.37),
(63, '2019-05-02 16:15:16', 4609.77),
(64, '2019-05-02 16:15:16', 4922.97),
(65, '2019-05-02 16:15:16', 5038.09),
(66, '2019-05-02 16:15:16', 4767.88),
(67, '2019-05-02 16:15:16', 4798.56),
(68, '2019-05-02 16:15:16', 5394.31),
(69, '2019-05-02 16:15:16', 5561.09),
(70, '2019-05-02 16:15:16', 5403.67),
(71, '2019-05-02 16:15:16', 4772.02),
(72, '2019-05-02 16:15:16', 5354.27),
(73, '2019-05-02 16:15:16', 5081.61),
(74, '2019-05-02 16:15:16', 5441.33),
(75, '2019-05-02 16:15:16', 4882.84),
(76, '2019-05-02 16:15:16', 5381.93),
(77, '2019-05-02 16:15:16', 5403.68),
(78, '2019-05-02 16:15:16', 5180.59),
(79, '2019-05-02 16:15:16', 4851.92),
(80, '2019-05-02 16:15:16', 5368.43),
(81, '2019-05-02 16:15:16', 5285.74),
(82, '2019-05-02 16:15:16', 5201.97),
(83, '2019-05-02 16:15:16', 5274.25),
(84, '2019-05-02 16:15:16', 4567.86),
(85, '2019-05-02 16:15:16', 4601.86),
(86, '2019-05-02 16:15:16', 5228.36),
(87, '2019-05-02 16:15:16', 5398.42),
(88, '2019-05-02 16:15:16', 5117.16),
(89, '2019-05-02 16:15:16', 4946.95),
(90, '2019-05-02 16:15:16', 4948.99),
(91, '2019-05-02 16:15:16', 5203.17),
(92, '2019-05-02 16:15:16', 5198.96),
(93, '2019-05-02 16:15:16', 5082.65),
(94, '2019-05-02 16:15:16', 4976.94),
(95, '2019-05-02 16:15:16', 5145.68),
(96, '2019-05-02 16:15:16', 4799.12),
(97, '2019-05-02 16:15:16', 5103.72),
(98, '2019-05-02 16:15:16', 5180.91),
(99, '2019-05-02 16:15:16', 5290.62),
(100, '2019-05-02 16:15:16', 4568.36),
(101, '2019-05-02 16:15:16', 5225.48),
(102, '2019-05-02 16:15:16', 5229.92),
(103, '2019-05-02 16:15:16', 5399.26),
(104, '2019-05-02 16:15:16', 4740.74),
(105, '2019-05-02 16:15:16', 4950.8),
(106, '2019-05-02 16:15:16', 4981.72),
(107, '2019-05-02 16:15:16', 5050.41),
(108, '2019-05-02 16:15:16', 4948.49),
(109, '2019-05-02 16:15:16', 5059.02),
(110, '2019-05-02 16:15:16', 4681.58),
(111, '2019-05-02 16:15:16', 5081.38),
(112, '2019-05-02 16:15:16', 4636.36),
(113, '2019-05-02 16:15:16', 4601.44),
(114, '2019-05-02 16:15:16', 4641.09),
(115, '2019-05-02 16:15:16', 5388.84),
(116, '2019-05-02 16:15:16', 4576.26),
(117, '2019-05-02 16:15:16', 5420.75),
(118, '2019-05-02 16:15:16', 5197.65),
(119, '2019-05-02 16:15:16', 4777.95),
(120, '2019-05-02 16:15:16', 4598.58),
(121, '2019-05-02 16:15:16', 5467.86),
(122, '2019-05-02 16:15:16', 5239.49),
(123, '2019-05-02 16:15:16', 4646.29),
(124, '2019-05-02 16:15:16', 5437.08),
(125, '2019-05-02 16:15:16', 5156.95),
(126, '2019-05-02 16:15:16', 4608.17),
(127, '2019-05-02 16:15:16', 4907.92),
(128, '2019-05-02 16:15:16', 4836.87),
(129, '2019-05-02 16:15:16', 4856.2),
(130, '2019-05-02 16:15:16', 4869.36),
(131, '2019-05-02 16:15:16', 5382.11),
(132, '2019-05-02 16:15:16', 4866.76),
(133, '2019-05-02 16:15:16', 4751.69),
(134, '2019-05-02 16:15:16', 4707.18),
(135, '2019-05-02 16:15:16', 4691.88),
(136, '2019-05-02 16:15:16', 5093.78),
(137, '2019-05-02 16:15:16', 4957.31),
(138, '2019-05-02 16:15:16', 4604.65),
(139, '2019-05-02 16:15:16', 4723.5),
(140, '2019-05-02 16:15:16', 5196.6),
(141, '2019-05-02 16:15:16', 4738.94),
(142, '2019-05-02 16:15:16', 4585.18),
(143, '2019-05-02 16:15:16', 4792.85),
(144, '2019-05-02 16:15:17', 5221.7),
(145, '2019-05-02 16:15:17', 5078.48),
(146, '2019-05-02 16:15:17', 5273.87),
(147, '2019-05-02 16:15:17', 5416.43),
(148, '2019-05-02 16:15:17', 5054.94),
(149, '2019-05-02 16:15:17', 4890.57),
(150, '2019-05-02 16:15:17', 5628.42),
(151, '2019-05-02 16:15:17', 4717.7),
(152, '2019-05-02 16:15:17', 5655.4),
(153, '2019-05-02 16:15:17', 5046.37),
(154, '2019-05-02 16:15:17', 5734.27),
(155, '2019-05-02 16:15:17', 4739.68),
(156, '2019-05-02 16:15:17', 5177.98),
(157, '2019-05-02 16:15:17', 5403.14),
(158, '2019-05-02 16:15:17', 4932.21),
(159, '2019-05-02 16:15:17', 5691.93),
(160, '2019-05-02 16:15:17', 5655.42),
(161, '2019-05-02 16:15:17', 5386.99),
(162, '2019-05-02 16:15:17', 5512.9),
(163, '2019-05-02 16:15:17', 5289.07),
(164, '2019-05-02 16:15:17', 4995.36),
(165, '2019-05-02 16:15:17', 5264.91),
(166, '2019-05-02 16:15:17', 5343.3),
(167, '2019-05-02 16:15:17', 5213.01),
(168, '2019-05-02 16:15:17', 4888.17),
(169, '2019-05-02 16:15:17', 5248.02),
(170, '2019-05-02 16:15:17', 4878.24),
(171, '2019-05-02 16:15:17', 4776.61),
(172, '2019-05-02 16:15:17', 5178.55),
(173, '2019-05-02 16:15:17', 4891.92),
(174, '2019-05-02 16:15:17', 5628.47),
(175, '2019-05-02 16:15:17', 5258.07),
(176, '2019-05-02 16:15:17', 5624.59),
(177, '2019-05-02 16:15:17', 5664.44),
(178, '2019-05-02 16:15:17', 5570.97),
(179, '2019-05-02 16:15:17', 4869),
(180, '2019-05-02 16:15:17', 4937.09),
(181, '2019-05-02 16:15:17', 5498.89),
(182, '2019-05-02 16:15:17', 4939.06),
(183, '2019-05-02 16:15:17', 5045.14),
(184, '2019-05-02 16:15:17', 4946.14),
(185, '2019-05-02 16:15:17', 5167.64),
(186, '2019-05-02 16:15:17', 5565.63),
(187, '2019-05-02 16:15:17', 5568.93),
(188, '2019-05-02 16:15:17', 4869.99),
(189, '2019-05-02 16:15:17', 5431.82),
(190, '2019-05-02 16:15:17', 5151.95),
(191, '2019-05-02 16:15:17', 4996.99),
(192, '2019-05-02 16:15:17', 5129.05),
(193, '2019-05-02 16:15:17', 5266.55),
(194, '2019-05-02 16:15:17', 5414.26),
(195, '2019-05-02 16:15:17', 5075.81),
(196, '2019-05-02 16:15:17', 5592.24),
(197, '2019-05-02 16:15:17', 5371.08),
(198, '2019-05-02 16:15:17', 5270.22),
(199, '2019-05-02 16:15:17', 4902.68),
(200, '2019-05-02 16:15:17', 4883.14),
(201, '2019-05-02 16:15:17', 4803.93),
(202, '2019-05-02 16:15:17', 5207.49),
(203, '2019-05-02 16:15:17', 5342.25),
(204, '2019-05-02 16:15:17', 4996.03),
(205, '2019-05-02 16:15:17', 5193.95),
(206, '2019-05-02 16:15:17', 5168.03),
(207, '2019-05-02 16:15:17', 5500.58),
(208, '2019-05-02 16:15:17', 5243.22),
(209, '2019-05-02 16:15:17', 5269.9),
(210, '2019-05-02 16:15:17', 4868.97),
(211, '2019-05-02 16:15:17', 5005.54),
(212, '2019-05-02 16:15:17', 5622.12),
(213, '2019-05-02 16:15:17', 4787.88),
(214, '2019-05-02 16:15:17', 4895.43),
(215, '2019-05-02 16:15:17', 5213.79),
(216, '2019-05-02 16:15:17', 5689.58),
(217, '2019-05-02 16:15:17', 5553.51),
(218, '2019-05-02 16:15:17', 5094.62),
(219, '2019-05-02 16:15:17', 5496.5),
(220, '2019-05-02 16:15:17', 5237.55),
(221, '2019-05-02 16:15:17', 5701.89),
(222, '2019-05-02 16:15:17', 4817.69),
(223, '2019-05-02 16:15:17', 4916.82),
(224, '2019-05-02 16:15:17', 4975.38),
(225, '2019-05-02 16:15:17', 5603.72),
(226, '2019-05-02 16:15:17', 5465.12),
(227, '2019-05-02 16:15:17', 5216.82),
(228, '2019-05-02 16:15:17', 5592.64),
(229, '2019-05-02 16:15:17', 5562.89),
(230, '2019-05-02 16:15:17', 4814.62),
(231, '2019-05-02 16:15:17', 5676.17),
(232, '2019-05-02 16:15:17', 5735.09),
(233, '2019-05-02 16:15:17', 5313.13),
(234, '2019-05-02 16:15:17', 4875.93),
(235, '2019-05-02 16:15:17', 4976.49),
(236, '2019-05-02 16:15:17', 5665.23),
(237, '2019-05-02 16:15:17', 5641.85),
(238, '2019-05-02 16:15:17', 5203.84),
(239, '2019-05-02 16:15:17', 5555.6),
(240, '2019-05-02 16:15:17', 5499.41),
(241, '2019-05-02 16:15:17', 5041.39),
(242, '2019-05-02 16:15:17', 5512.57),
(243, '2019-05-02 16:15:17', 5326.2),
(244, '2019-05-02 16:15:17', 5488.33),
(245, '2019-05-02 16:15:17', 5220.86),
(246, '2019-05-02 16:15:17', 5002.41),
(247, '2019-05-02 16:15:17', 4970.64),
(248, '2019-05-02 16:15:17', 5043.16),
(249, '2019-05-02 16:15:17', 5057.49),
(250, '2019-05-02 16:15:17', 5150.87);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
