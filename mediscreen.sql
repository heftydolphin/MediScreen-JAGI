-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2019 at 09:31 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mediscreen`
--

-- --------------------------------------------------------

--
-- Table structure for table `gp`
--

CREATE TABLE `gp` (
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(25) DEFAULT NULL,
  `practiceAddress1` varchar(35) DEFAULT NULL,
  `practiceAddress2` varchar(35) DEFAULT NULL,
  `practiceAddress3` varchar(35) DEFAULT NULL,
  `telNo` int(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gp`
--

INSERT INTO `gp` (`fName`, `lName`, `practiceAddress1`, `practiceAddress2`, `practiceAddress3`, `telNo`, `email`, `password`) VALUES
('Joe', 'McSweeny', '24R', 'Oliver Plunket Street', 'Cork City', 216382624, 'mcsweeny_gp@gp.com', 'Amy200207');

-- --------------------------------------------------------

--
-- Table structure for table `insurer`
--

CREATE TABLE `insurer` (
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(20) DEFAULT NULL,
  `telNo` int(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `insurer`
--

INSERT INTO `insurer` (`fName`, `lName`, `telNo`, `email`, `password`) VALUES
('David', 'O\' Connel', 855293753, 'david_connel123@hotmail.net', 'Password123');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(30) DEFAULT NULL,
  `insuranceMemberNum` int(5) DEFAULT NULL,
  `insurancePolicyNum` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD UNIQUE KEY `insurancePolicyNum` (`insurancePolicyNum`),
  ADD UNIQUE KEY `insuranceMemberNum` (`insuranceMemberNum`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
