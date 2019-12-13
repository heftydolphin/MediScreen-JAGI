CREATE DATABASE mediscreen;

CREATE TABLE `gp` (
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(25) DEFAULT NULL,
  `practiceAddress1` varchar(35) DEFAULT NULL,
  `practiceAddress2` varchar(35) DEFAULT NULL,
  `practiceAddress3` varchar(35) DEFAULT NULL,
  `telNo` int(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL
)


INSERT INTO `gp` (`fName`, `lName`, `practiceAddress1`, `practiceAddress2`, `practiceAddress3`, `telNo`, `email`, `password`) VALUES
('Joe', 'McSweeny', '24R', 'Oliver Plunket Street', 'Cork City', 216382624, 'mcsweeny_gp@gp.com', 'Amy200207'),
('', '', '', '', '', 0, '', ''),
('Mary', 'Mary O', 'Bishopstown', '', 'Cork city', 1462715711, 'gpmaryo@gmail.com', 'GPMmary01234');

CREATE TABLE `insurer` (
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(20) DEFAULT NULL,
  `telNo` int(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL
)

INSERT INTO `insurer` (`fName`, `lName`, `telNo`, `email`, `password`) VALUES
('David', 'O\' Connel', 855293753, 'david_connel123@hotmail.net', 'Password123'),
('', '', 0, '', ''),
('Iggy', '', 851820107, '', ''),
('Iggy', '', 0, '', ''),
('Test', 'Test', 0, 'test@test.ts', 'TestT12345'),
('', '', 0, 'iggyobrien@gmail.com', 'uyguyfyufyu55R'),
('Mary', 'Mary O', 1234567891, 'maryo@gmail.com', 'Maryo12345');

CREATE TABLE `medical_history` (
  `gender` tinyint(1) DEFAULT NULL,
  `ageGroup` varchar(10) DEFAULT NULL,
  `exercise` tinyint(1) DEFAULT NULL,
  `pastCondition` tinyint(1) DEFAULT NULL,
  `glucose` tinyint(1) DEFAULT NULL,
  `familyHistory` tinyint(1) DEFAULT NULL,
  `bloodPressure` tinyint(1) DEFAULT NULL,
  `cholesterol` tinyint(1) DEFAULT NULL,
  `height` int(4) DEFAULT NULL,
  `weight` int(4) DEFAULT NULL,
  `patientID` varchar(50) DEFAULT NULL
)

CREATE TABLE `patient` (
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `fName` varchar(15) DEFAULT NULL,
  `lName` varchar(30) DEFAULT NULL,
  `insuranceMemberNum` int(5) DEFAULT NULL,
  `insurancePolicyNum` int(5) DEFAULT NULL
)

INSERT INTO `patient` (`email`, `password`, `fName`, `lName`, `insuranceMemberNum`, `insurancePolicyNum`) VALUES
('test@test.com', 'Test1234', 'Test', 'Test', 12345, 12345),
('hey@fhjg.com', 'hjfjj5G5', 'Iggy', 'O Brien', 56889, 56582),
('iggyobrien@gmail.com', 'pjsbj', 'hdjdh', 'hndjd', 52339, 59464),
('iggy.obrien@mycit.ie', 'hhdy8gbn', 'Iggy', 'O Brien', 56988, 82283);

ALTER TABLE `patient`
  ADD UNIQUE KEY `insurancePolicyNum` (`insurancePolicyNum`),
  ADD UNIQUE KEY `insuranceMemberNum` (`insuranceMemberNum`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;
