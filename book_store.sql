-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2015 at 01:03 AM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `book_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `ISBN` int(10) NOT NULL,
  `Title` varchar(50) NOT NULL,
  `Publisher_ID` int(10) NOT NULL,
  `Publication_Year` year(4) NOT NULL,
  `Selling_Price` int(10) NOT NULL,
  `Category_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ISBN`, `Title`, `Publisher_ID`, `Publication_Year`, `Selling_Price`, `Category_ID`) VALUES
(1, 'android', 1, 2013, 12, 4),
(2, 'Java', 8, 2015, 20, 5),
(9, 'embedded', 3, 2015, 10, 3),
(10, 'OS', 6, 2015, 10, 1),
(11, 'Music', 6, 2012, 14, 2),
(15, 'Technology', 6, 2000, 199, 1),
(19, 'math', 5, 2015, 14, 5),
(22, 'Swift', 2, 2000, 70, 6),
(23, 'Swift 2', 12, 2004, 34, 8),
(24, 'Node JS', 1, 2005, 60, 8),
(25, 'Angular JS', 1, 2005, 60, 6),
(26, 'C++', 4, 2015, 34234, 3),
(27, 'JavaFX', 13, 1980, 234, 10),
(29, 'Triangles', 1, 2000, 12, 11),
(30, 'Hello World', 13, 2014, 66, 8),
(54, 'Mechanics', 2, 1993, 60, 4),
(80, 'IOS Learn', 10, 2001, 50, 6),
(100, 'control', 8, 2015, 123, 1),
(101, 'IOS', 6, 2015, 10, 2),
(112, 'Statistics', 9, 2014, 14, 2),
(123, 'electro', 1, 2015, 123, 1),
(145, 'kids', 4, 2014, 123, 3),
(155, 'Work', 8, 2005, 29, 5),
(1455, 'LABPROJECT', 1, 2015, 14, 8),
(8020, 'IOS Learn', 10, 2001, 50, 6);

-- --------------------------------------------------------

--
-- Table structure for table `book_authors`
--

CREATE TABLE IF NOT EXISTS `book_authors` (
  `ISBN` int(10) NOT NULL,
  `Author_Name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_authors`
--

INSERT INTO `book_authors` (`ISBN`, `Author_Name`) VALUES
(1, 'besho'),
(1, 'faten'),
(2, 'Bassem'),
(9, 'gamal'),
(9, 'micheal'),
(10, 'gamal'),
(11, 'John'),
(15, 'micheal'),
(19, 'mody'),
(22, 'Tarek'),
(23, 'Hassan'),
(23, 'Tarek'),
(24, 'Badr'),
(25, 'Badr'),
(26, 'Hassan'),
(26, 'Tarek'),
(27, 'Gamal'),
(29, 'Hazem'),
(30, 'Steve'),
(80, 'Reem'),
(100, 'adel'),
(100, 'tamer'),
(101, 'gamal'),
(112, 'John'),
(123, 'mohamed'),
(145, 'adam'),
(155, 'Ayman'),
(155, 'micheal'),
(1455, 'TAREK'),
(8020, 'Reem');

-- --------------------------------------------------------

--
-- Table structure for table `book_orders`
--

CREATE TABLE IF NOT EXISTS `book_orders` (
  `ISBN` int(10) NOT NULL,
  `Order_Date` date NOT NULL,
  `Quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_orders`
--

INSERT INTO `book_orders` (`ISBN`, `Order_Date`, `Quantity`) VALUES
(1455, '2015-06-02', 10);

--
-- Triggers `book_orders`
--
DELIMITER //
CREATE TRIGGER `confirm_order` BEFORE DELETE ON `book_orders`
 FOR EACH ROW BEGIN
	UPDATE `book_quantity`
    SET Quantity=OLD.QUANTITY+`book_quantity`.`Quantity`

    WHERE ISBN=OLD.ISBN;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `book_quantity`
--

CREATE TABLE IF NOT EXISTS `book_quantity` (
  `ISBN` int(10) NOT NULL,
  `Threshold` int(10) NOT NULL,
  `Quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_quantity`
--

INSERT INTO `book_quantity` (`ISBN`, `Threshold`, `Quantity`) VALUES
(1, 12, 13824),
(2, 50, 486),
(9, 18, 82944),
(11, 12, 108),
(15, 12, 110592),
(19, 13, 29952),
(22, 30, 486),
(23, 60, 4500),
(24, 40, 120),
(25, 40, 40),
(26, 44, 486),
(27, 243, 729),
(29, 22, 306),
(30, 30, 360),
(100, 12, 12),
(112, 12, 864),
(123, 12, 12),
(145, 12, 18432),
(155, 12, 4608),
(1455, 10, 0);

--
-- Triggers `book_quantity`
--
DELIMITER //
CREATE TRIGGER `Negative_Quantity` BEFORE UPDATE ON `book_quantity`
 FOR EACH ROW BEGIN

      IF (NEW.Quantity < 0) THEN
            set NEW.Quantity=OLD.Quantity;
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT='Stock will be negative';
      END IF;
END
//
DELIMITER ;
DELIMITER //
CREATE TRIGGER `PlaceOrder` AFTER UPDATE ON `book_quantity`
 FOR EACH ROW BEGIN
       IF (NEW.Quantity < OLD.Threshold) THEN
  			IF (SELECT count(*) FROM book_orders WHERE ISBN=NEW.ISBN and Order_Date=CURDATE()) = 0 THEN
        		insert into book_orders values(NEW.ISBN,CURDATE(),NEW.Threshold+NEW.Quantity);
        	ELSE
        		update book_orders set Order_Date=CURDATE(), Quantity=Quantity*2 where ISBN=NEW.ISBN and Order_Date=CURDATE();
     		 END IF;
       END IF;

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
`Category_ID` int(10) NOT NULL,
  `Category_Name` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Category_ID`, `Category_Name`) VALUES
(2, 'Art'),
(8, 'Computer Science'),
(10, 'Front End'),
(5, 'Geography'),
(4, 'History'),
(6, 'IOS'),
(11, 'Mathematics'),
(3, 'Religion'),
(1, 'Science');

-- --------------------------------------------------------

--
-- Table structure for table `publisher`
--

CREATE TABLE IF NOT EXISTS `publisher` (
`Publisher_ID` int(10) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Phone` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `publisher`
--

INSERT INTO `publisher` (`Publisher_ID`, `Name`, `Address`, `Phone`) VALUES
(1, 'eslam', 'sidibesher', '1234'),
(2, 'ehab', 'alex', '12345'),
(3, 'ahmed', 'alex', '125'),
(4, 'salem', 'alex', '1234'),
(5, 'rony', 'england', '123456'),
(6, 'zee', 'london', '12386'),
(7, 'zee', 'london', '12386'),
(8, 'mazen', 'alex', '12362'),
(9, 'Ron', 'America', '12345'),
(10, 'Magdy', 'Elsa3a', '01235478921'),
(11, 'Magdy', 'Elsa3a', '01235478921'),
(12, 'Bassant', 'Giza', '123123123'),
(13, 'Haitham', 'Downtown', '012234');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE IF NOT EXISTS `sales` (
`SID` int(10) NOT NULL,
  `Book_Name` varchar(50) NOT NULL,
  `Buy_Time` date NOT NULL,
  `Customer_Name` varchar(20) NOT NULL,
  `Selling_Price` int(10) NOT NULL,
  `Quantity` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=824 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`SID`, `Book_Name`, `Buy_Time`, `Customer_Name`, `Selling_Price`, `Quantity`) VALUES
(719, 'electro', '2015-06-01', 'remon', 984, 14),
(720, 'math', '2015-06-01', 'remon', 42, 15),
(721, 'electro', '2015-06-01', 'remon', 615, 0),
(722, 'kids', '2015-06-01', 'remon', 123, 14),
(723, 'math', '2015-06-01', 'remon', 210, 0),
(725, 'android', '2015-06-01', 'beto', 36, 3),
(726, 'embedded', '2015-06-01', 'alaa', 40, 4),
(727, 'math', '2015-06-01', 'alaa', 14, 1),
(728, 'control', '2015-06-01', 'alaa', 123, 1),
(729, 'control', '2015-06-01', 'shimo', 123, 1),
(730, 'electro', '2015-06-01', 'shimo', 123, 1),
(731, 'control', '2015-06-01', 'Maha', 123, 1),
(732, 'kids', '2015-06-01', 'Maha', 492, 4),
(733, 'Music', '2015-06-01', 'Maha', 56, 4),
(734, 'Statistics', '2015-06-01', 'Maha', 56, 4),
(735, 'Music', '2015-06-01', 'Farida', 14, 0),
(736, 'Statistics', '2015-06-01', 'Farida', 14, 0),
(737, 'Music', '2015-06-01', 'Farida', 266, 0),
(738, 'Technology', '2015-06-01', 'Ali', 796, 20),
(739, 'android', '2015-06-01', 'Ali', 48, 12),
(740, 'Technology', '2015-06-01', 'Ali', 199, 0),
(741, 'control', '2015-06-01', 'Ali', 123, 0),
(742, 'android', '2015-06-01', 'Saad', 12, 0),
(743, 'Technology', '2015-06-01', 'Saad', 199, 0),
(744, 'android', '2015-06-01', 'Reham', 12, 0),
(745, 'electro', '2015-06-01', 'Reham', 123, 0),
(746, 'kids', '2015-06-01', 'Reham', 123, 0),
(747, 'embedded', '2015-06-01', 'Reem', 10, 0),
(748, 'Technology', '2015-06-01', 'Reem', 199, 0),
(749, 'electro', '2015-06-01', 'Reem', 123, 0),
(750, 'Technology', '2015-06-01', 'Mostafa', 199, 0),
(751, 'control', '2015-06-01', 'Mostafa', 123, 0),
(752, 'electro', '2015-06-01', 'Mostafa', 123, 0),
(753, 'embedded', '2015-06-01', 'Mostafa', 10, 0),
(754, 'kids', '2015-06-01', 'Mostafa', 123, 0),
(755, 'math', '2015-06-01', 'Mostafa', 14, 0),
(756, 'Work', '2015-06-01', 'Mostafa', 29, 20),
(757, 'Technology', '2015-06-01', 'Negm', 199, 0),
(758, 'control', '2015-06-01', 'Negm', 123, 0),
(759, 'electro', '2015-06-01', 'Negm', 123, 0),
(760, 'Music', '2015-06-01', 'Negm', 14, 0),
(761, 'Statistics', '2015-06-01', 'Negm', 14, 0),
(762, 'embedded', '2015-06-01', 'Negm', 10, 0),
(763, 'kids', '2015-06-01', 'Negm', 123, 0),
(764, 'android', '2015-06-01', 'Negm', 12, 0),
(765, 'math', '2015-06-01', 'Negm', 14, 0),
(766, 'Work', '2015-06-01', 'Negm', 29, 0),
(767, 'Technology', '2015-06-01', 'Adel', 199, 0),
(768, 'control', '2015-06-01', 'Adel', 123, 0),
(769, 'electro', '2015-06-01', 'Adel', 123, 0),
(770, 'embedded', '2015-06-01', 'Adel', 10, 0),
(771, 'kids', '2015-06-01', 'Adel', 123, 0),
(772, 'math', '2015-06-01', 'Adel', 14, 0),
(773, 'Work', '2015-06-01', 'Adel', 29, 0),
(774, 'Technology', '2015-06-01', 'Wael', 199, 0),
(775, 'control', '2015-06-01', 'Wael', 123, 0),
(776, 'electro', '2015-06-01', 'Wael', 123, 0),
(777, 'embedded', '2015-06-01', 'Wael', 10, 0),
(778, 'kids', '2015-06-01', 'Wael', 123, 0),
(779, 'android', '2015-06-01', 'Wael', 12, 0),
(780, 'math', '2015-06-01', 'Wael', 14, 0),
(781, 'Work', '2015-06-01', 'Wael', 29, 0),
(782, 'control', '2015-06-01', 'Wael', 123, 0),
(783, 'electro', '2015-06-01', 'Wael', 123, 0),
(784, 'embedded', '2015-06-01', 'Wael', 10, 0),
(785, 'kids', '2015-06-01', 'Wael', 123, 0),
(786, 'android', '2015-06-01', 'Wael', 12, 0),
(787, 'math', '2015-06-01', 'Wael', 14, 0),
(788, 'Work', '2015-06-01', 'Wael', 29, 0),
(789, 'Technology', '2015-06-01', 'Rofael', 199, 0),
(790, 'control', '2015-06-01', 'Rofael', 123, 0),
(791, 'electro', '2015-06-01', 'Rofael', 123, 0),
(792, 'Music', '2015-06-01', 'Rofael', 14, 0),
(793, 'Statistics', '2015-06-01', 'Rofael', 14, 0),
(794, 'embedded', '2015-06-01', 'Rofael', 10, 0),
(795, 'kids', '2015-06-01', 'Rofael', 123, 0),
(796, 'android', '2015-06-01', 'Rofael', 12, 0),
(797, 'math', '2015-06-01', 'Rofael', 14, 0),
(798, 'Work', '2015-06-01', 'Rofael', 29, 0),
(799, 'Technology', '2015-06-01', 'beto', 199, 0),
(800, 'control', '2015-06-01', 'beto', 123, 0),
(801, 'electro', '2015-06-01', 'beto', 123, 0),
(802, 'embedded', '2015-06-01', 'beto', 10, 0),
(803, 'kids', '2015-06-01', 'beto', 123, 0),
(804, 'math', '2015-06-01', 'beto', 14, 0),
(805, 'Work', '2015-06-01', 'beto', 29, 0),
(806, 'Technology', '2015-06-01', 'Reem', 199, 0),
(807, 'control', '2015-06-01', 'Reem', 123, 0),
(808, 'electro', '2015-06-01', 'Reem', 123, 0),
(809, 'embedded', '2015-06-01', 'Reem', 10, 0),
(810, 'kids', '2015-06-01', 'Reem', 123, 0),
(811, 'android', '2015-06-01', 'Reem', 12, 0),
(812, 'math', '2015-06-01', 'Reem', 14, 0),
(813, 'Work', '2015-06-01', 'Reem', 29, 0),
(814, 'Music', '2015-06-02', 'beto', 56, 4),
(815, 'Node JS', '2015-06-02', 'beto', 1740, 29),
(816, 'Angular JS', '2015-06-02', 'beto', 1380, 23),
(817, 'JavaFX', '2015-06-02', 'beto', 7020, 30),
(818, 'Angular JS', '2015-06-02', 'beto', 60, 1),
(819, 'control', '2015-06-02', 'beto', 123, 1),
(820, 'electro', '2015-06-02', 'beto', 123, 1),
(821, 'LABPROJECT', '2015-06-02', 'beto', 98, 7),
(822, 'LABPROJECT', '2015-06-02', 'shaimaa', 56, 4),
(823, 'LABPROJECT', '2015-06-02', 'beto', 28, 2);

-- --------------------------------------------------------

--
-- Table structure for table `shipping_cart`
--

CREATE TABLE IF NOT EXISTS `shipping_cart` (
  `Username` varchar(20) NOT NULL,
  `ISBN` int(10) NOT NULL,
  `Quantity` int(10) NOT NULL,
  `Buy_Time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shipping_cart`
--

INSERT INTO `shipping_cart` (`Username`, `ISBN`, `Quantity`, `Buy_Time`) VALUES
('tarek', 15, 8, '2015-06-02');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `First_Name` varchar(20) NOT NULL,
  `Last_Name` varchar(20) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Phone` varchar(15) NOT NULL,
  `Shopping_Address` varchar(50) NOT NULL,
  `Is_Manager` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `Password`, `First_Name`, `Last_Name`, `Email`, `Phone`, `Shopping_Address`, `Is_Manager`) VALUES
('Adel', '741258', 'Adel', 'Emam', 'Adel@gmail.com', '0114521025', 'Cairo', 1),
('ahmed', '123456', 'ahmed', 'gamal', 'ahmed@yahoo.com', '12384', 'alex', 0),
('alaa', '123456', 'lolo', 'khaled', 'lolo@yahoo.com', '12345', 'alex', 1),
('Ali', '123458', 'Ali', 'Taha', 'Ali@gmail.com', '01148269941', 'El-Seyouff', 0),
('bassem', 'qweqwe', 'Bassem', 'Khaled', 'bassem@gmail.com', '123123', 'Germany', 0),
('beto', '123456', 'beto', 'tota', 'beto@yahoo.com', '1234', 'sidibeshr', 1),
('dodo', '123123123', 'Dodo', 'Dodi', 'dodo@yahoo.com', '56565656', 'Canada', 1),
('Farida', '123456', 'fofo', 'ahmed', 'fofo@yahoo.com', '1235', 'alex', 0),
('gamal', '123123', 'Gamal', 'Gamil', 'gamal@yahoo.com', '213213132', 'Cairo', 1),
('ghada', '123123', 'Ghada', 'Adel', 'ghada@gmail.com', '2323232', 'Aswan', 0),
('hadi', 'QWEQWE', 'Hadi', 'Hendi', 'hadi@gmail.com', '4443334444', 'Hawaii', 0),
('hamdi', 'QWEQWE', 'Hamdi', 'Hassan', 'hamdi@gmail.com', '12334345', 'KSA', 0),
('hanan', '123456', 'hanan', 'a', 'h@yahoo.com', '12344', 'alex', 0),
('hoda', 'dfgdfg', 'Hoda', 'Hakim', 'hoda@gmail.com', '686868686', 'India', 0),
('Hossam', 'QWEQWE', 'Hossam', 'Hassan', 'hossam@gmail.com', '123343', 'KSA', 0),
('Maha', '123456', 'maha', 'A.h', 'm@yahoo.com', '13467', 'alex', 0),
('mahmoud', '123123', 'Mahmoud', 'Khaled', 'mmm@yahoo.com', '112233', 'Giza', 0),
('mido', '123123', 'Mido', 'Mody', 'mido@gmail.com', '456456', 'Japan', 0),
('mohamed', '123456', 'mohamed', 'ahmed', 'm@yahoo.com', '123', 'jksd', 0),
('Mostafa', 'Mostafa', 'Mostafa', 'Abd El-Aziz', 'safsaf@yahoo.com', '01054897106', 'Elmandara', 0),
('nader', '123123', 'Nader', 'Elsaid', 'nader@yahoo.com', '123456', 'USA', 0),
('Negm', '0123456', 'Negm', 'Kaukab', 'Negm@shamaa.com', '01000000000', 'Magara', 0),
('Reem', 'reem@gmail.com', 'Reem', 'Yousry', 'reem@gmail.com', '0114789542', 'Tanta', 1),
('Reham', 'alex.edu.eg', 'Reham', 'Fathallah', 'reham.55@alex.edu.eg', '01265489634', 'Victoria', 0),
('remon', '123456', 'Remon', 'Hanna', 'remonjc2@gmail.com', '01148265591', 'Add', 1),
('Rofael', '654321', 'Rofael', 'Emil', 'Rofael@gmail.com', '01236541201', 'Ibrahimaya', 0),
('Saad', 'saad@yahoo.com', 'Saad', 'El-Araaby', 'saad@yahoo.com', '012548967136', 'SidiGaber', 0),
('salma', '123456', 'sos', 'bob', 'sos@yahoo.com', '12346', 'alex', 0),
('shaimaa', '1234567', 'shaimaa', 'mousa', 'sdsf@gmail.com', '12345678', 'sanvfbv', 0),
('shimo', '123456', 'shimo', 'shimo', 'shimo@yahoo.com', '14566', 'delengat', 1),
('tarek', '123456', 'Tarek', 'Khaled', 'tarekcsed@yahoo.com', '123456', 'alex', 1),
('Wael', '123456789', 'Wael', 'Sadek', 'wael@yahoo.com', '012365478910', 'Mansour', 0),
('Zeyaad', '123456', 'Zeyaad', 'El-masry', 'Zeyaad@yahoo.com', '01234565486', 'Semoha', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
 ADD PRIMARY KEY (`ISBN`), ADD KEY `Publisher_ID` (`Publisher_ID`), ADD KEY `Category_ID` (`Category_ID`), ADD KEY `Title` (`Title`);

--
-- Indexes for table `book_authors`
--
ALTER TABLE `book_authors`
 ADD PRIMARY KEY (`ISBN`,`Author_Name`);

--
-- Indexes for table `book_orders`
--
ALTER TABLE `book_orders`
 ADD PRIMARY KEY (`ISBN`,`Order_Date`);

--
-- Indexes for table `book_quantity`
--
ALTER TABLE `book_quantity`
 ADD PRIMARY KEY (`ISBN`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
 ADD PRIMARY KEY (`Category_ID`), ADD KEY `Category_Name` (`Category_Name`);

--
-- Indexes for table `publisher`
--
ALTER TABLE `publisher`
 ADD PRIMARY KEY (`Publisher_ID`), ADD KEY `Name` (`Name`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
 ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `shipping_cart`
--
ALTER TABLE `shipping_cart`
 ADD PRIMARY KEY (`Username`,`ISBN`), ADD KEY `Username` (`Username`), ADD KEY `ISBN` (`ISBN`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
MODIFY `Category_ID` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `publisher`
--
ALTER TABLE `publisher`
MODIFY `Publisher_ID` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
MODIFY `SID` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=824;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`Publisher_ID`) REFERENCES `publisher` (`Publisher_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `book_ibfk_2` FOREIGN KEY (`Category_ID`) REFERENCES `category` (`Category_ID`);

--
-- Constraints for table `book_authors`
--
ALTER TABLE `book_authors`
ADD CONSTRAINT `book_authors_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `book_orders`
--
ALTER TABLE `book_orders`
ADD CONSTRAINT `book_orders_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `book_quantity`
--
ALTER TABLE `book_quantity`
ADD CONSTRAINT `book_quantity_ibfk_1` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shipping_cart`
--
ALTER TABLE `shipping_cart`
ADD CONSTRAINT `shipping_cart_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `shipping_cart_ibfk_2` FOREIGN KEY (`ISBN`) REFERENCES `book` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
