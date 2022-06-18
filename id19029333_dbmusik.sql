-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 18, 2022 at 08:08 AM
-- Server version: 10.5.12-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19029333_dbmusik`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblmusik`
--

CREATE TABLE `tblmusik` (
  `id` int(10) NOT NULL,
  `judul` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `penyanyi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `album` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `aliran` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblmusik`
--

INSERT INTO `tblmusik` (`id`, `judul`, `penyanyi`, `album`, `aliran`, `created_at`, `updated_at`) VALUES
(1, '有點甜', '汪蘇瀧, By2', 'Silence Gravitation', 'Pop', '2022-06-05 08:06:13', NULL),
(2, 'ただ声一つ', 'ロクデナシ', 'ただ声一つ', 'J-Pop', '2022-06-05 08:07:56', NULL),
(3, 'My Love', 'Westlife', 'Coast to Coast', 'R&B/Soul, Pop', '2022-06-05 08:11:35', NULL),
(4, 'Night Changes', 'One Direction', 'Four', 'Pop', '2022-06-05 08:22:45', NULL),
(5, 'Right Here Waiting', 'Richard Marx', 'Repeat Offender', 'Pop', '2022-06-05 08:24:43', NULL),
(6, 'Memories', 'Maroon 5', 'Emote Pa More', 'Reggae Fusion', '2022-06-05 08:40:03', '2022-06-05 08:42:10'),
(7, 'Double Take', 'Dhruv', 'Rapunzel', 'R&B, R&B/Soul', '2022-06-05 08:43:24', NULL),
(11, 'Angel Baby', 'Troye Sivan', 'Songs For Moms', 'Pop', '2022-06-13 16:02:02', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblmusik`
--
ALTER TABLE `tblmusik`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblmusik`
--
ALTER TABLE `tblmusik`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
