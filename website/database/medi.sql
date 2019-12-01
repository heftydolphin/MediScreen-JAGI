-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2019 at 04:30 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medi`
--

-- --------------------------------------------------------

--
-- Table structure for table `gp`
--

CREATE TABLE `gp` (
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_no` int(255) NOT NULL,
  `address1` varchar(255) NOT NULL,
  `address2` varchar(255) NOT NULL,
  `address3` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `gp`
--

INSERT INTO `gp` (`first_name`, `last_name`, `phone_no`, `address1`, `address2`, `address3`, `email`, `password`) VALUES
('John', 'Doe', 123, '123', '4567', '4575', 'johndoe@gmail.com', '$2y$10$U9KQqVC8tvhaeU787HM6UeI3i6mhAuquyJCnAhbbh6YaBSbTxIgKe'),
('Bob', 'Doe', 123456, '9 Generic Street', 'Cork', 'Ireland', 'bob@bob.com', '$2y$10$oMSpmji.qgpmWm244ndzDuf.um9IptWi86sxmPYniPrn6iNgvqWfe'),
('Peter', 'O\'Brien', 861738137, '12 Random Road', 'Waterford', 'Ireland', 'pd@gmail.com', '$2y$10$sdCt/QhBTzrFYoUNw.HmSukqG1fm5sJCUfcDJtxQ/qO6qMi3rV8Ny'),
('jordan', 'curley', 132421, '124', '1', 'assfg', 'jordan@gmail.com', '$2y$10$3VZ3BObNXAarZpz0rXx4D.2WO0oGud8fIxe2XXTJ2rnOwC9zpTQf6'),
('James', 'Example', 123, 'sdgj', 'dsfhg', 'cork', 'james@j.com', '$2y$10$ninW5SkeVm./ItmtAjZGkubH5.KcVmSTDD2plZ22vZOglLPxk6ZlG'),
('Eric', 'O\'Murphy', 127537265, '123 Random Street', 'Town', 'Cork', 'ericmurphy@gmail.com', '$2y$10$0iQzjCouIRGi7nwpjWDDXezeGBEYlAjp4B46Tph0Qv4xtTl261ryq'),
('Paul', 'Murphy', 257324, '', '', '', 'pm@gmail.com', '$2y$10$yTWvNywnmKHuyRv/eE70k.BvKyc.lQQRtKzBWqHaLOMN6Sm9YG3NK');

-- --------------------------------------------------------

--
-- Table structure for table `insurer`
--

CREATE TABLE `insurer` (
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_no` int(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `insurer`
--

INSERT INTO `insurer` (`first_name`, `last_name`, `phone_no`, `email`, `password`) VALUES
('John', 'Smith', 123, 'jsm@gmail.com', '$2y$10$.wdJ2dfM/HJLPdvklazR2edYzzQupmCcXrOyXA7F4OBV3NKCpgja2'),
('Peter', 'Brien', 345435, 'peterobrien@gmail.com', '$2y$10$ZfxNCrxZTnhj4u8WlNUyJ.8iQKyiteWPcLRXNJoGK0rr9600WhMXS'),
('Bob', 'Smith', 2147483647, '', '$2y$10$tZL6SWeTuaun4y.uLEKj4ODwFgwxLkFALMxr1OcoGWPlMSb78aUjq'),
('Jordan', 'Curley', 214624234, 'jordanc@gmail.com', '$2y$10$/NaXXD3LDh4K8mLdhv/hBuGFB/pZyJrcuIj06625dI7EToxoqyIR2'),
('Paul', 'O\'Brien', 93287585, 'pob@gmail.com', '$2y$10$iSZBbKrSxATPoagTpDfil.firoMq14sFF/RfiewCA5HEjUPaspZ9C');

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

CREATE TABLE `login_details` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`email`, `password`, `user_type`) VALUES
('jordan@gmail.com', '$2y$10$t1blkGjJj3rPc0AlQkX/KObrej9Dc7aKFODb8OVzWYOr3nEZLzmc6', ''),
('james@j.com', '$2y$10$lG67AdrYAGXjNWLWLN8Q9e1p5pR8G/epWfoCihCt2tpgWsCdaqOE6', ''),
('jd@com.com', '$2y$10$M6smt0iYJsqpeBmBFlWEr.Sjmw.zWyjIJ2wzcsL8qzwfyDFv9ybY6', ''),
('jordanc@gmail.com', '$2y$10$3FFVwQHC/tzt8F0EeMRqoOF8S9j9pwjZ3RslLBbNA5GgFcNzX1bLC', 'INS'),
('ericmurphy@gmail.com', '$2y$10$jAIVdlnuA/MA4/o.ZG2oNu8cJOH3OeYcx5gwLWv.2jYjbfvmJyZz6', 'GP'),
('pm@gmail.com', '$2y$10$ThliXxiVh8BqjT1eoyyoBOsYXidG2U1Tfruf3jDzUHremNkj8at4G', 'INS'),
('pob@gmail.com', '$2y$10$bBVmQSmPmGYgWvzbdVdR0u/VhdM8jIFA5Tcy4ZJxm9M67S6ruckWK', 'INS');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_no` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gp_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_no`, `first_name`, `last_name`, `address`, `gender`, `phone_no`, `dob`, `email`, `gp_name`) VALUES
('PL22', 'Aderson', 'Amary', ' 12B Hosty Close, Bishoptown, Cork', 'F ', '021-225633 ', '1980-05-04', '\r\nAdersonAmary@gmail.com ', 'John Doe'),
('PLA9', 'Debora', 'Cecil', '01 Stony Building,\r\nWilton, Cork', 'F', '085-3639652', '1983-07-11', 'DeboraCecil@gmail.com', 'Bob Doe'),
('PLG5', 'Anita', 'Joanna', '43D Erica House, Ballincollig,\r\nCork', 'F', '087-2394693', '1973-04-20', 'AnitaJoanna@gmail.com', 'John Doe'),
('PLP1', 'Martin', 'Luthers', '12 Eagle Valley, Wilton, Cork', ' M ', '087-4363853', '2017-11-03', ' martingLuthers@gmail.com ', 'Paul Murphy'),
('PLS3', 'Kent', 'Water', '16 Abbey Court, Model Farm Rd., Cork', 'M', '086-6363863', '1975-06-05', 'Kentabbey@yahoo.com', 'Paul Murphy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
