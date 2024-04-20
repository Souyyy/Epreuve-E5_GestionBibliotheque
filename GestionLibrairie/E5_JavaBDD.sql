-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 20, 2024 at 11:12 AM
-- Server version: 5.7.24
-- PHP Version: 8.1.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dev_lib`
--
CREATE DATABASE IF NOT EXISTS `dev_lib` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dev_lib`;

-- --------------------------------------------------------

--
-- Table structure for table `adherent`
--

CREATE TABLE `adherent` (
  `num` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `adherent`
--

INSERT INTO `adherent` (`num`, `nom`, `prenom`, `email`) VALUES
(1, 'Nom1', 'Prenom1', 'prenom1@gmail.com'),
(2, 'Nom2', 'Prenom2', 'prenom2@gmail.com'),
(3, 'Nom3', 'Prenom3', 'prenom3@gmail.com'),
(4, 'Nom4', 'Prenom4', 'prenom4@gmail.com'),
(5, 'Nom5', 'Prenom5', 'prenom5@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `auteur`
--

CREATE TABLE `auteur` (
  `num` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `auteur`
--

INSERT INTO `auteur` (`num`, `nom`, `prenom`, `date_naissance`, `description`) VALUES
(1, 'Auteur1', 'Prenom1', NULL, NULL),
(2, 'Auteur2', 'Prenom2', NULL, NULL),
(3, 'Auteur3', 'Prenom3', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `emprunt`
--

CREATE TABLE `emprunt` (
  `id` int(11) NOT NULL,
  `id_livre` int(11) NOT NULL,
  `id_adherent` int(11) NOT NULL,
  `date_emprunt` date NOT NULL,
  `date_retour` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `emprunt`
--

INSERT INTO `emprunt` (`id`, `id_livre`, `id_adherent`, `date_emprunt`, `date_retour`) VALUES
(1, 1, 1, '2024-04-20', '2024-05-18'),
(2, 2, 1, '2024-04-20', '2024-05-18'),
(3, 3, 1, '2024-04-20', '2024-05-18'),
(4, 4, 1, '2024-04-20', '2024-05-18'),
(5, 5, 1, '2024-04-20', '2024-05-18'),
(6, 6, 2, '2024-04-20', '2024-05-18');

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

CREATE TABLE `livre` (
  `ISBN` int(20) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `prix` float DEFAULT NULL,
  `auteur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`ISBN`, `titre`, `prix`, `auteur`) VALUES
(1, 'Livre1', 5, 1),
(2, 'Livre2', 5, 1),
(3, 'Livre3', 5, 1),
(4, 'Livre4', 5, 1),
(5, 'Livre5', 5, 1),
(6, 'Livre1', 10, 2),
(7, 'Livre2', 10, 2),
(8, 'Livre3', 10, 2),
(9, 'Livre4', 10, 2),
(10, 'Livre5', 10, 2),
(11, 'Livre1', 15, 3),
(12, 'Livre2', 15, 3),
(13, 'Livre3', 15, 3),
(14, 'Livre4', 15, 3),
(15, 'Livre5', 15, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adherent`
--
ALTER TABLE `adherent`
  ADD PRIMARY KEY (`num`);

--
-- Indexes for table `auteur`
--
ALTER TABLE `auteur`
  ADD PRIMARY KEY (`num`);

--
-- Indexes for table `emprunt`
--
ALTER TABLE `emprunt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1` (`id_adherent`),
  ADD KEY `FK3` (`id_livre`) USING BTREE;

--
-- Indexes for table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`ISBN`),
  ADD KEY `FK2` (`auteur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adherent`
--
ALTER TABLE `adherent`
  MODIFY `num` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `auteur`
--
ALTER TABLE `auteur`
  MODIFY `num` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `emprunt`
--
ALTER TABLE `emprunt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `livre`
--
ALTER TABLE `livre`
  MODIFY `ISBN` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `FK1` FOREIGN KEY (`id_adherent`) REFERENCES `adherent` (`num`),
  ADD CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`id_livre`) REFERENCES `livre` (`ISBN`);

--
-- Constraints for table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `FK2` FOREIGN KEY (`auteur`) REFERENCES `auteur` (`num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
