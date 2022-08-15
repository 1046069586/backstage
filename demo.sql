-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2022-08-15 10:36:37
-- 服务器版本： 10.4.21-MariaDB
-- PHP 版本： 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `vuedemo`
--

-- --------------------------------------------------------

--
-- 表的结构 `sys_file`
--

CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `is_delete` tinyint(1) DEFAULT 0,
  `enable` tinyint(1) DEFAULT 1,
  `md5` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `sys_file`
--

INSERT INTO `sys_file` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enable`, `md5`) VALUES
(1, '1548548284832.jpg', 'jpg', 68, 'http://localhost:9090/file/0c4f7d24e6f841809391878e88c2d8ed.jpg', 0, 0, '2adef93b19b34be7ec7c9b2bea298ac3'),
(2, '1548548284832.jpg', 'jpg', 68, 'http://localhost:9090/file/0c4f7d24e6f841809391878e88c2d8ed.jpg', 0, 0, '2adef93b19b34be7ec7c9b2bea298ac3'),
(3, '8655100_154118450191_2.jpg', 'jpg', 14, 'http://localhost:9090/file/7df28cd9bfeb4edd8898241c5af9dd8b.jpg', 0, 0, 'b789ff52864e487063c0972b6ad25dfb'),
(4, '8863b468-669c-4593-ba10-cbd1deb14377.png', 'png', 228, 'http://localhost:9090/file/a7402e61f3724e11b133b51f98b065e7.png', 0, 0, '1237c1052827b1ac7365fd22899d1863'),
(5, '7f0ea366-9d95-4dd4-894f-13735c37b11b.jpg', 'jpg', 268, 'http://localhost:9090/file/af74fa96c5664fac983913dca243b38f.jpg', 0, 0, '08a33e403839820ff1f9b6de9008ced3'),
(6, '1dba4ccc-de6e-4bf3-9e60-e8402180540f.png', 'png', 60, 'http://localhost:9090/file/e163ff89cdbf45a091a0770bfb2f43c9.png', 0, 0, 'ff7fcf4d390cb1b0ea2af6db7c78acd1'),
(7, 'f2448573-48c4-49a2-ad0a-d31d606830fc.png', 'png', 230, 'http://localhost:9090/file/5b34e950f27d442d85996712be30cb3b.png', 0, 1, '69fe31d205fbb79f4f90786eb0a114f6');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `email`, `phone`, `address`, `create_time`) VALUES
(1, 'admin', 'admin', '管理员', 'admin@qq.com', '1238144', 'usa', '2022-02-16 13:29:26'),
(2, 'zhu', 'zhu', 'zz', 'zz', '123', '123', '2022-02-21 02:36:49'),
(13, '7', NULL, 'q', 'q', NULL, NULL, '2022-08-11 02:28:59'),
(14, '8', NULL, NULL, NULL, NULL, NULL, '2022-08-11 02:29:08'),
(15, '9', NULL, NULL, NULL, NULL, NULL, '2022-08-11 02:29:18'),
(24, '6', NULL, '期望', NULL, NULL, NULL, '2022-08-11 02:42:23'),
(25, '34', NULL, '33', '3', '33', NULL, '2022-08-12 13:13:27');

--
-- 转储表的索引
--

--
-- 表的索引 `sys_file`
--
ALTER TABLE `sys_file`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `sys_file`
--
ALTER TABLE `sys_file`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `sys_user`
--
ALTER TABLE `sys_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
