-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2020 at 06:35 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Id`, `UserName`, `Password`, `Entry_Date`) VALUES
(1, 'Admin', 'Password', '2019-12-22 15:02:31'),
(2, 'username', 'password', '2019-12-22 15:02:31'),
(3, 'admin', 'admin', '2019-12-22 15:02:31');

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `Id` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`Id`, `Employee_ID`, `UserName`, `Password`, `Entry_Date`) VALUES
(1, 1001, 'username', 'password', '2019-12-22 15:40:11'),
(2, 1002, 'admin', 'admin', '2019-12-22 15:42:14'),
(3, 1003, 'Abdul1206', 'abdulhamid123', '2019-12-22 15:42:14');

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `Id` int(11) NOT NULL,
  `Country_Code` varchar(10) NOT NULL,
  `Country_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`Id`, `Country_Code`, `Country_Name`) VALUES
(1, 'AF', 'Afghanistan'),
(2, 'AL', 'Albania'),
(3, 'DZ', 'Algeria'),
(4, 'DS', 'American Samoa'),
(5, 'AD', 'Andorra'),
(6, 'AO', 'Angola'),
(7, 'AI', 'Anguilla'),
(8, 'AQ', 'Antarctica'),
(9, 'AG', 'Antigua and Barbuda'),
(10, 'AR', 'Argentina'),
(11, 'AM', 'Armenia'),
(12, 'AW', 'Aruba'),
(13, 'AU', 'Australia'),
(14, 'AT', 'Austria'),
(15, 'AZ', 'Azerbaijan'),
(16, 'BS', 'Bahamas'),
(17, 'BH', 'Bahrain'),
(18, 'BD', 'Bangladesh'),
(19, 'BB', 'Barbados'),
(20, 'BY', 'Belarus'),
(21, 'BE', 'Belgium'),
(22, 'BZ', 'Belize'),
(23, 'BJ', 'Benin'),
(24, 'BM', 'Bermuda'),
(25, 'BT', 'Bhutan'),
(26, 'BO', 'Bolivia'),
(27, 'BA', 'Bosnia and Herzegovina'),
(28, 'BW', 'Botswana'),
(29, 'BV', 'Bouvet Island'),
(30, 'BR', 'Brazil'),
(31, 'IO', 'British Indian Ocean Territory'),
(32, 'BN', 'Brunei Darussalam'),
(33, 'BG', 'Bulgaria'),
(34, 'BF', 'Burkina Faso'),
(35, 'BI', 'Burundi'),
(36, 'KH', 'Cambodia'),
(37, 'CM', 'Cameroon'),
(38, 'CA', 'Canada'),
(39, 'CV', 'Cape Verde'),
(40, 'KY', 'Cayman Islands'),
(41, 'CF', 'Central African Republic'),
(42, 'TD', 'Chad'),
(43, 'CL', 'Chile'),
(44, 'CN', 'China'),
(45, 'CX', 'Christmas Island'),
(46, 'CC', 'Cocos (Keeling) Islands'),
(47, 'CO', 'Colombia'),
(48, 'KM', 'Comoros'),
(49, 'CD', 'Democratic Republic of the Congo'),
(50, 'CG', 'Republic of Congo'),
(51, 'CK', 'Cook Islands'),
(52, 'CR', 'Costa Rica'),
(53, 'HR', 'Croatia (Hrvatska)'),
(54, 'CU', 'Cuba'),
(55, 'CY', 'Cyprus'),
(56, 'CZ', 'Czech Republic'),
(57, 'DK', 'Denmark'),
(58, 'DJ', 'Djibouti'),
(59, 'DM', 'Dominica'),
(60, 'DO', 'Dominican Republic'),
(61, 'TP', 'East Timor'),
(62, 'EC', 'Ecuador'),
(63, 'EG', 'Egypt'),
(64, 'SV', 'El Salvador'),
(65, 'GQ', 'Equatorial Guinea'),
(66, 'ER', 'Eritrea'),
(67, 'EE', 'Estonia'),
(68, 'ET', 'Ethiopia'),
(69, 'FK', 'Falkland Islands (Malvinas)'),
(70, 'FO', 'Faroe Islands'),
(71, 'FJ', 'Fiji'),
(72, 'FI', 'Finland'),
(73, 'FR', 'France'),
(74, 'FX', 'France, Metropolitan'),
(75, 'GF', 'French Guiana'),
(76, 'PF', 'French Polynesia'),
(77, 'TF', 'French Southern Territories'),
(78, 'GA', 'Gabon'),
(79, 'GM', 'Gambia'),
(80, 'GE', 'Georgia'),
(81, 'DE', 'Germany'),
(82, 'GH', 'Ghana'),
(83, 'GI', 'Gibraltar'),
(84, 'GK', 'Guernsey'),
(85, 'GR', 'Greece'),
(86, 'GL', 'Greenland'),
(87, 'GD', 'Grenada'),
(88, 'GP', 'Guadeloupe'),
(89, 'GU', 'Guam'),
(90, 'GT', 'Guatemala'),
(91, 'GN', 'Guinea'),
(92, 'GW', 'Guinea-Bissau'),
(93, 'GY', 'Guyana'),
(94, 'HT', 'Haiti'),
(95, 'HM', 'Heard and Mc Donald Islands'),
(96, 'HN', 'Honduras'),
(97, 'HK', 'Hong Kong'),
(98, 'HU', 'Hungary'),
(99, 'IS', 'Iceland'),
(100, 'IN', 'India'),
(101, 'IM', 'Isle of Man'),
(102, 'ID', 'Indonesia'),
(103, 'IR', 'Iran (Islamic Republic of)'),
(104, 'IQ', 'Iraq'),
(105, 'IE', 'Ireland'),
(106, 'IL', 'Israel'),
(107, 'IT', 'Italy'),
(108, 'CI', 'Ivory Coast'),
(109, 'JE', 'Jersey'),
(110, 'JM', 'Jamaica'),
(111, 'JP', 'Japan'),
(112, 'JO', 'Jordan'),
(113, 'KZ', 'Kazakhstan'),
(114, 'KE', 'Kenya'),
(115, 'KI', 'Kiribati'),
(116, 'KP', 'Korea, Democratic People\'s Republic of'),
(117, 'KR', 'Korea, Republic of'),
(118, 'XK', 'Kosovo'),
(119, 'KW', 'Kuwait'),
(120, 'KG', 'Kyrgyzstan'),
(121, 'LA', 'Lao People\'s Democratic Republic'),
(122, 'LV', 'Latvia'),
(123, 'LB', 'Lebanon'),
(124, 'LS', 'Lesotho'),
(125, 'LR', 'Liberia'),
(126, 'LY', 'Libyan Arab Jamahiriya'),
(127, 'LI', 'Liechtenstein'),
(128, 'LT', 'Lithuania'),
(129, 'LU', 'Luxembourg'),
(130, 'MO', 'Macau'),
(131, 'MK', 'North Macedonia'),
(132, 'MG', 'Madagascar'),
(133, 'MW', 'Malawi'),
(134, 'MY', 'Malaysia'),
(135, 'MV', 'Maldives'),
(136, 'ML', 'Mali'),
(137, 'MT', 'Malta'),
(138, 'MH', 'Marshall Islands'),
(139, 'MQ', 'Martinique'),
(140, 'MR', 'Mauritania'),
(141, 'MU', 'Mauritius'),
(142, 'TY', 'Mayotte'),
(143, 'MX', 'Mexico'),
(144, 'FM', 'Micronesia, Federated States of'),
(145, 'MD', 'Moldova, Republic of'),
(146, 'MC', 'Monaco'),
(147, 'MN', 'Mongolia'),
(148, 'ME', 'Montenegro'),
(149, 'MS', 'Montserrat'),
(150, 'MA', 'Morocco'),
(151, 'MZ', 'Mozambique'),
(152, 'MM', 'Myanmar'),
(153, 'NA', 'Namibia'),
(154, 'NR', 'Nauru'),
(155, 'NP', 'Nepal'),
(156, 'NL', 'Netherlands'),
(157, 'AN', 'Netherlands Antilles'),
(158, 'NC', 'New Caledonia'),
(159, 'NZ', 'New Zealand'),
(160, 'NI', 'Nicaragua'),
(161, 'NE', 'Niger'),
(162, 'NG', 'Nigeria'),
(163, 'NU', 'Niue'),
(164, 'NF', 'Norfolk Island'),
(165, 'MP', 'Northern Mariana Islands'),
(166, 'NO', 'Norway'),
(167, 'OM', 'Oman'),
(168, 'PK', 'Pakistan'),
(169, 'PW', 'Palau'),
(170, 'PS', 'Palestine'),
(171, 'PA', 'Panama'),
(172, 'PG', 'Papua New Guinea'),
(173, 'PY', 'Paraguay'),
(174, 'PE', 'Peru'),
(175, 'PH', 'Philippines'),
(176, 'PN', 'Pitcairn'),
(177, 'PL', 'Poland'),
(178, 'PT', 'Portugal'),
(179, 'PR', 'Puerto Rico'),
(180, 'QA', 'Qatar'),
(181, 'RE', 'Reunion'),
(182, 'RO', 'Romania'),
(183, 'RU', 'Russian Federation'),
(184, 'RW', 'Rwanda'),
(185, 'KN', 'Saint Kitts and Nevis'),
(186, 'LC', 'Saint Lucia'),
(187, 'VC', 'Saint Vincent and the Grenadines'),
(188, 'WS', 'Samoa'),
(189, 'SM', 'San Marino'),
(190, 'ST', 'Sao Tome and Principe'),
(191, 'SA', 'Saudi Arabia'),
(192, 'SN', 'Senegal'),
(193, 'RS', 'Serbia'),
(194, 'SC', 'Seychelles'),
(195, 'SL', 'Sierra Leone'),
(196, 'SG', 'Singapore'),
(197, 'SK', 'Slovakia'),
(198, 'SI', 'Slovenia'),
(199, 'SB', 'Solomon Islands'),
(200, 'SO', 'Somalia'),
(201, 'ZA', 'South Africa'),
(202, 'GS', 'South Georgia South Sandwich Islands'),
(203, 'SS', 'South Sudan'),
(204, 'ES', 'Spain'),
(205, 'LK', 'Sri Lanka'),
(206, 'SH', 'St. Helena'),
(207, 'PM', 'St. Pierre and Miquelon'),
(208, 'SD', 'Sudan'),
(209, 'SR', 'Suriname'),
(210, 'SJ', 'Svalbard and Jan Mayen Islands'),
(211, 'SZ', 'Swaziland'),
(212, 'SE', 'Sweden'),
(213, 'CH', 'Switzerland'),
(214, 'SY', 'Syrian Arab Republic'),
(215, 'TW', 'Taiwan'),
(216, 'TJ', 'Tajikistan'),
(217, 'TZ', 'Tanzania, United Republic of'),
(218, 'TH', 'Thailand'),
(219, 'TG', 'Togo'),
(220, 'TK', 'Tokelau'),
(221, 'TO', 'Tonga'),
(222, 'TT', 'Trinidad and Tobago'),
(223, 'TN', 'Tunisia'),
(224, 'TR', 'Turkey'),
(225, 'TM', 'Turkmenistan'),
(226, 'TC', 'Turks and Caicos Islands'),
(227, 'TV', 'Tuvalu'),
(228, 'UG', 'Uganda'),
(229, 'UA', 'Ukraine'),
(230, 'AE', 'United Arab Emirates'),
(231, 'GB', 'United Kingdom'),
(232, 'US', 'United States'),
(233, 'UM', 'United States minor outlying islands'),
(234, 'UY', 'Uruguay'),
(235, 'UZ', 'Uzbekistan'),
(236, 'VU', 'Vanuatu'),
(237, 'VA', 'Vatican City State'),
(238, 'VE', 'Venezuela'),
(239, 'VN', 'Vietnam'),
(240, 'VG', 'Virgin Islands (British)'),
(241, 'VI', 'Virgin Islands (U.S.)'),
(242, 'WF', 'Wallis and Futuna Islands'),
(243, 'EH', 'Western Sahara'),
(244, 'YE', 'Yemen'),
(245, 'ZM', 'Zambia'),
(246, 'ZW', 'Zimbabwe');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Employee_ID` int(11) NOT NULL,
  `Designation` varchar(100) NOT NULL,
  `Join_Date` date DEFAULT NULL,
  `Salary` double(200,3) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Employee_ID`, `Designation`, `Join_Date`, `Salary`, `Entry_Date`) VALUES
(1001, 'Manager', '2019-12-22', 12500.000, '2019-12-22 15:12:54'),
(1002, 'CEO', '2019-12-21', 50000.000, '2019-12-22 15:16:13'),
(1003, 'Superviser', '2019-12-19', 10000.000, '2019-12-22 15:16:13'),
(1004, 'Waiter', '2019-12-17', 6800.000, '2019-12-22 15:16:13'),
(1005, 'Supervisor', '2019-12-12', 12600.000, '2019-12-23 16:56:44'),
(1006, 'Manager', '2019-09-21', 15000.000, '2019-12-25 21:31:59'),
(1007, 'Supervisor', '2019-11-13', 9000.000, '2019-12-25 21:32:12');

-- --------------------------------------------------------

--
-- Table structure for table `floor1`
--

CREATE TABLE `floor1` (
  `Table_Id` int(11) NOT NULL,
  `Status` enum('Booked','UnBooked') DEFAULT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `floor1`
--

INSERT INTO `floor1` (`Table_Id`, `Status`, `Entry_Date`) VALUES
(1, 'UnBooked', '2019-12-22 15:52:23'),
(2, 'UnBooked', '2019-12-22 15:52:23'),
(3, 'UnBooked', '2019-12-22 15:52:23'),
(4, 'UnBooked', '2019-12-22 15:52:23'),
(5, 'UnBooked', '2019-12-22 15:52:23'),
(6, 'UnBooked', '2019-12-22 15:52:23'),
(7, 'UnBooked', '2019-12-22 15:52:23'),
(8, 'UnBooked', '2019-12-22 15:52:23'),
(9, 'UnBooked', '2019-12-22 15:52:23'),
(10, 'UnBooked', '2019-12-22 15:52:23'),
(11, 'UnBooked', '2019-12-22 15:52:23'),
(12, 'UnBooked', '2019-12-22 15:52:23'),
(13, 'UnBooked', '2019-12-22 15:52:23'),
(14, 'UnBooked', '2019-12-22 15:52:23');

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `Food_Id` int(11) NOT NULL,
  `Food_Name` varchar(100) NOT NULL,
  `Buying_price` double(200,3) NOT NULL,
  `Selling_Price` double(200,3) NOT NULL,
  `Profit` double(200,3) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`Food_Id`, `Food_Name`, `Buying_price`, `Selling_Price`, `Profit`, `Entry_Date`) VALUES
(1, 'Cocacole', 130.000, 140.000, 10.000, '2019-12-22 18:51:48'),
(2, 'Tehari', 50.000, 90.000, 40.000, '2019-12-22 18:51:48'),
(3, 'Rice', 5.000, 15.000, 10.000, '2019-12-22 18:51:48'),
(4, 'Rui Fish', 100.000, 150.000, 50.000, '2019-12-22 18:51:48'),
(5, 'Water', 10.000, 15.000, 5.000, '2019-12-22 18:51:48'),
(6, 'Cocacola', 100.000, 150.000, 50.000, '2019-12-22 18:51:48'),
(7, 'Burger', 80.000, 130.000, 50.000, '2019-12-22 18:51:48');

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE `order_table` (
  `Order_ID` int(11) NOT NULL,
  `Order_Type` enum('Home Deliver','Table') DEFAULT NULL,
  `Kitchen` int(11) NOT NULL,
  `Total` double(200,3) NOT NULL,
  `Profit` double(200,3) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_table`
--

INSERT INTO `order_table` (`Order_ID`, `Order_Type`, `Kitchen`, `Total`, `Profit`, `Entry_Date`) VALUES
(1, 'Table', 1, 325.000, 50.000, '2020-01-09 11:23:19'),
(2, 'Table', 1, 210.000, 100.000, '2020-01-09 11:33:53'),
(3, 'Table', 1, 300.000, 140.000, '2020-01-09 11:43:28'),
(4, 'Table', 1, 75.000, 50.000, '2020-01-09 11:43:35'),
(5, 'Table', 1, 315.000, 150.000, '2020-01-09 12:08:15'),
(6, 'Table', 1, 270.000, 10.000, '2020-01-09 18:22:09'),
(7, 'Table', 1, 280.000, 20.000, '2020-01-09 18:31:15'),
(8, 'Table', 1, 140.000, 10.000, '2020-01-10 10:39:36'),
(9, 'Table', 1, 420.000, 30.000, '2020-01-10 10:41:47'),
(10, 'Table', 1, 140.000, 10.000, '2020-01-10 11:26:08'),
(11, 'Table', 1, 280.000, 20.000, '2020-01-10 11:38:25'),
(12, 'Table', 1, 210.000, 100.000, '2020-01-10 11:53:16'),
(13, 'Table', 1, 140.000, 10.000, '2020-01-10 12:13:01'),
(14, 'Table', 2, 180.000, 80.000, '2020-01-10 12:13:29'),
(15, 'Table', 1, 230.000, 50.000, '2020-01-10 14:24:02'),
(16, 'Table', 1, 140.000, 10.000, '2020-01-11 11:34:24');

-- --------------------------------------------------------

--
-- Table structure for table `personal_details`
--

CREATE TABLE `personal_details` (
  `Id` int(11) NOT NULL,
  `Employee_ID` int(11) NOT NULL,
  `First_Name` varchar(100) NOT NULL,
  `Last_Name` varchar(100) NOT NULL,
  `Phone` int(11) NOT NULL,
  `Gender` enum('Male','Female','Others') DEFAULT NULL,
  `Country` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Passport_NID` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `Id` int(11) NOT NULL,
  `Order_ID` int(11) NOT NULL,
  `Table_Id` int(11) DEFAULT NULL,
  `Food_Id` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Sub_Total` double(200,3) NOT NULL,
  `Sub_Profit` double(200,3) NOT NULL,
  `Entry_Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Selldate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`Id`, `Order_ID`, `Table_Id`, `Food_Id`, `Quantity`, `Sub_Total`, `Sub_Profit`, `Entry_Date`, `Selldate`) VALUES
(1, 1, 1, 1, 2, 280.000, 20.000, '2020-01-09 11:23:19', '2020-01-09'),
(2, 2, 1, 2, 2, 180.000, 80.000, '2020-01-09 11:33:53', '2020-01-09'),
(3, 2, 1, 3, 2, 30.000, 20.000, '2020-01-09 11:33:53', '2020-01-09'),
(4, 3, 2, 2, 3, 270.000, 120.000, '2020-01-09 11:43:28', '2020-01-09'),
(5, 3, 2, 3, 2, 30.000, 20.000, '2020-01-09 11:43:28', '2020-01-09'),
(6, 4, 3, 3, 5, 75.000, 50.000, '2020-01-09 11:43:35', '2020-01-09'),
(7, 5, 4, 2, 3, 270.000, 120.000, '2020-01-09 12:08:15', '2020-01-09'),
(8, 5, 5, 3, 3, 45.000, 30.000, '2020-01-09 12:08:15', '2020-01-09'),
(10, 6, 6, 2, 3, 270.000, 10.000, '2020-01-09 18:22:20', '2020-01-09'),
(11, 7, 7, 1, 2, 280.000, 20.000, '2020-01-09 18:31:15', '2020-01-09'),
(12, 8, 1, 1, 1, 140.000, 10.000, '2020-01-10 10:39:36', '2020-01-10'),
(13, 9, 2, 1, 3, 420.000, 30.000, '2020-01-10 10:41:47', '2020-01-10'),
(14, 10, 1, 1, 1, 140.000, 10.000, '2020-01-10 11:26:08', '2020-01-10'),
(15, 11, 1, 1, 2, 280.000, 20.000, '2020-01-10 11:38:25', '2020-01-10'),
(16, 12, 2, 2, 2, 180.000, 80.000, '2020-01-10 11:53:16', '2020-01-10'),
(17, 12, 2, 3, 2, 30.000, 20.000, '2020-01-10 11:53:16', '2020-01-10'),
(18, 13, 3, 1, 1, 140.000, 10.000, '2020-01-10 12:13:01', '2020-01-10'),
(19, 14, 4, 2, 2, 180.000, 80.000, '2020-01-10 12:13:29', '2020-01-10'),
(20, 15, 1, 1, 1, 140.000, 10.000, '2020-01-10 14:24:02', '2020-01-10'),
(21, 15, 1, 2, 1, 90.000, 40.000, '2020-01-10 14:24:03', '2020-01-10'),
(22, 16, 1, 1, 1, 140.000, 10.000, '2020-01-11 11:34:24', '2020-01-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Employee_ID` (`Employee_ID`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Employee_ID`);

--
-- Indexes for table `floor1`
--
ALTER TABLE `floor1`
  ADD PRIMARY KEY (`Table_Id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`Food_Id`);

--
-- Indexes for table `order_table`
--
ALTER TABLE `order_table`
  ADD PRIMARY KEY (`Order_ID`);

--
-- Indexes for table `personal_details`
--
ALTER TABLE `personal_details`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Employee_ID` (`Employee_ID`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Order_ID` (`Order_ID`),
  ADD KEY `Food_Id` (`Food_Id`),
  ADD KEY `Table_Id` (`Table_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `app_user`
--
ALTER TABLE `app_user`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=247;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `Employee_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1008;

--
-- AUTO_INCREMENT for table `floor1`
--
ALTER TABLE `floor1`
  MODIFY `Table_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `Food_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `personal_details`
--
ALTER TABLE `personal_details`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `app_user`
--
ALTER TABLE `app_user`
  ADD CONSTRAINT `app_user_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`);

--
-- Constraints for table `personal_details`
--
ALTER TABLE `personal_details`
  ADD CONSTRAINT `personal_details_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`Order_ID`) REFERENCES `order_table` (`Order_ID`),
  ADD CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`Food_Id`) REFERENCES `food` (`Food_Id`),
  ADD CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`Table_Id`) REFERENCES `floor1` (`Table_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
