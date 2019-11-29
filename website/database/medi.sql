-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2019 at 12:12 PM
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
('Paul', 'Example', 24867345, '123 Street', 'Town', 'Country', 'paul@gmail.com', '$2y$10$DWd04t.v4jCIQkiDH5FHW.3MJRxsybgLqb2Kyg4W/tkA2mV54Fbki'),
('jordan', 'curley', 132421, '124', '1', 'assfg', 'jordan@gmail.com', '$2y$10$3VZ3BObNXAarZpz0rXx4D.2WO0oGud8fIxe2XXTJ2rnOwC9zpTQf6'),
('James', 'Example', 123, 'sdgj', 'dsfhg', 'cork', 'james@j.com', '$2y$10$ninW5SkeVm./ItmtAjZGkubH5.KcVmSTDD2plZ22vZOglLPxk6ZlG'),
('Eric', 'Murphy', 871645391, '123 Random Street', 'Town', 'Cork', 'ericmurphy@gmail.com', '$2y$10$foWoLye568r7ZRf/ZdBcg.WVDuhna.E/gtaX785L5mN9lslFw5y8u');

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
('Peter', 'O\'Brien', 2147483647, 'peterobrien@gmail.com', '$2y$10$Cdzq2ZclfHdpwjRpVXNExufNK9DXsTAr2VOCFm0E7zxDNj4o/LWsu'),
('Bob', 'Smith', 2147483647, '', '$2y$10$tZL6SWeTuaun4y.uLEKj4ODwFgwxLkFALMxr1OcoGWPlMSb78aUjq'),
('John', 'Doe', 12824, 'jd@com.com', '$2y$10$x7k9UHrT1lctRKN1Wl2eWuWzwf8YHcpbegbn1iTanCklqh6C5IBOW'),
('Jordan', 'Curley', 214624234, 'jordanc@gmail.com', '$2y$10$/NaXXD3LDh4K8mLdhv/hBuGFB/pZyJrcuIj06625dI7EToxoqyIR2');

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
('ericmurphy@gmail.com', '$2y$10$zjnfJQ.rM69Sq/N0rEpZ1OCn/jq/zr0aLg/J42AGn/XKwThLDi9Jq', 'GP');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
