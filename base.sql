-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Авг 09 2018 г., 16:39
-- Версия сервера: 5.5.60-0+deb8u1
-- Версия PHP: 5.6.33-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `counters`
--

-- --------------------------------------------------------

--
-- Структура таблицы `tblDepartment`
--

CREATE TABLE IF NOT EXISTS `tblDepartment` (
`idDepartment` int(11) NOT NULL,
  `Department` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tblDepartment`
--

INSERT INTO `tblDepartment` (`idDepartment`, `Department`) VALUES
(20, 'Все'),
(30, 'КТЦ'),
(40, 'РК "Ксты"'),
(50, 'РСУ'),
(60, 'РТС'),
(70, 'ХЦ'),
(80, 'ЦТАИ'),
(90, 'ЭЦ'),
(100, 'УМиА');

-- --------------------------------------------------------

--
-- Структура таблицы `tblRightsItems`
--

CREATE TABLE IF NOT EXISTS `tblRightsItems` (
`idRightItem` int(11) NOT NULL,
  `rightItem` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tblRightsItems`
--

INSERT INTO `tblRightsItems` (`idRightItem`, `rightItem`) VALUES
(10, 'admin'),
(3, 'changeOrder'),
(11, 'changeStatus'),
(4, 'createFile'),
(5, 'createOrder'),
(9, 'createUser'),
(12, 'createеTask'),
(2, 'markDel'),
(7, 'markDirector'),
(1, 'markFileRuk'),
(8, 'markGlingener'),
(6, 'markTB');

-- --------------------------------------------------------

--
-- Структура таблицы `tblRoleRights`
--

CREATE TABLE IF NOT EXISTS `tblRoleRights` (
`idRoleRights` int(11) NOT NULL,
  `idRole` int(11) NOT NULL,
  `idRightsItem` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tblRoleRights`
--

INSERT INTO `tblRoleRights` (`idRoleRights`, `idRole`, `idRightsItem`) VALUES
(1, 6, 5),
(2, 5, 3),
(3, 5, 4),
(4, 3, 1),
(5, 3, 2),
(6, 3, 7),
(7, 4, 1),
(8, 4, 2),
(9, 4, 8),
(10, 2, 6),
(11, 2, 2),
(12, 1, 1),
(13, 1, 2),
(14, 1, 3),
(15, 1, 4),
(16, 1, 5),
(17, 1, 6),
(18, 1, 7),
(19, 1, 8),
(20, 1, 9),
(21, 1, 10),
(22, 6, 3),
(23, 5, 11);

-- --------------------------------------------------------

--
-- Структура таблицы `tblRoles`
--

CREATE TABLE IF NOT EXISTS `tblRoles` (
`idRole` int(11) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tblRoles`
--

INSERT INTO `tblRoles` (`idRole`, `role`) VALUES
(1, 'admin'),
(2, 'tb'),
(3, 'director'),
(4, 'glingener'),
(5, 'nss'),
(6, 'ceh');

-- --------------------------------------------------------

--
-- Структура таблицы `tblUserRole`
--

CREATE TABLE IF NOT EXISTS `tblUserRole` (
`idUserRole` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idRole` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tblUserRole`
--

INSERT INTO `tblUserRole` (`idUserRole`, `idUser`, `idRole`) VALUES
(3, 2, 3),
(4, 13, 4),
(10, 5, 6),
(11, 6, 6),
(12, 7, 6),
(13, 8, 5),
(14, 8, 6),
(15, 9, 6),
(16, 10, 6),
(17, 12, 5),
(18, 14, 2),
(19, 15, 6),
(22, 1, 1),
(28, 1, 7),
(29, 1, 8),
(30, 1, 9),
(31, 1, 10),
(32, 16, 4),
(33, 16, 1),
(37, 6, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'alexei@pst.vitebsk.energo.net',
  `status` smallint(6) NOT NULL DEFAULT '10',
  `created_at` int(11) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `department` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `username`, `auth_key`, `password_hash`, `password_reset_token`, `email`, `status`, `created_at`, `updated_at`, `department`) VALUES
(1, 'admin', 'RHtFncPObrbZ0ZYIJ2ld-M8e8TKNiAZO', '$2y$13$DT0fW/fAo2INMYiXD7F.QO5GWOmZYUP4g8cVWqPQ2DUGVEqkVURG2', NULL, 'alexei@pst.vitebsk.energo.net12', 10, 1485861979, 1531311248, 20),
(2, 'director', 'YjSZneKdiDddeiB__766SZB5iQQpC7cj', '$2y$13$vunKwMy6fTU83z43yckDe.skvhGyd2nl8IGXS7NTY4S8UCLjhqT3i', NULL, 'director@pst.vitebsk.energo.net', 10, 1485861588, 1485861588, 20),
(5, 'etl', 'H3p-Lc7TT7yMuUrJRiTjo53U1snUoiCS', '$2y$13$.eMQsjAL20CogKioMVmLyOTbZYZx0YwyCyDgDaXEy9/9X.Mo6WUTy', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680274, 1526374758, 20),
(6, 'tai', 'Переделать', '$2y$13$3JIKIQsz.gidO1S2nlwR6.kqRrIgJU6FTvMmRHqku6U9GZLKwFNDS', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680286, 1531310922, 80),
(7, 'him', 'X7mHugII3rTGIbJ9yiT2W4s84rFY8a-J', '$2y$13$PkQOxWSzfkcYBUPoJ4ek5.sS45VsgbUjjl7pHplE8QVplpX85cYs2', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680299, 1526374838, 20),
(8, 'kst', 'UNzUmkpQvYDqD2GFLMS90sRSYoBJdxNu', '$2y$13$8wVqT4nmTG2p/UNt0pAVy.UShc8NFaefK9DnsWwx25fpVMbPRa2nq', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680310, 1526374876, 20),
(9, 'rts', 'EOsnqJhQSkKwfm9ypaJTck-Lflk2JFvK', '$2y$13$g6kX9Lj1rufCszMnRVfVUu9zhuZVVX5ByjUMDluySbXjCx6.tOMuC', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680320, 1526374889, 20),
(10, 'rsu', 'Ah82hB5vtZ-WS5Szbv4TWDqZw79jcoI2', '$2y$13$pJxpaRvF9XWMkwV9I7S4p.f8BkevS0Fom0lDe3D/PJ2J2SyMvDDia', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680341, 1526374903, 20),
(11, 'ktc', 'qm_OUOfk7SeWrOyoib-kBYMyBDAabjbP', '$2y$13$yN5DuAgadyohy9galvnq9.TR/Q3UsviYZL3ysrLf3JdUSlSqp6Gem', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680351, 1487680351, 20),
(12, 'nss', '2fMbwn91q1LGdHY6OBDTUA7963MImWGJ', '$2y$13$8HYhbk9T8XNAzBwJppKhTOmdqLn5M4pnz4RWzXHc4guTWhA/NozZS', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680366, 1531142867, 20),
(13, 'glingener', 'HkNaVwWUDuyniBRQbAMPGJdCqFrl6mon', '$2y$13$1etljPkBGCKvbNN7YFSvmu/gjbvUbXLOKweEudv84cVmcAaVAYA92', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680393, 1487680393, 20),
(14, 'tb', 'Переделать', '$2y$13$Kw4ByMICc4kUVaFMtOWKfOLwWkM6uMR.m6EIZxjJHS/yOnFOXrvLK', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1489388014, 1526629406, 20),
(15, 'etl1', 'H3p-Lc7TT7yMuUrJRiTjo53U1snUoiCS', '$2y$13$.eMQsjAL20CogKioMVmLyOTbZYZx0YwyCyDgDaXEy9/9X.Mo6WUTy', NULL, 'alexei@pst.vitebsk.energo.net', 10, 1487680274, 1526374949, 20),
(16, 'admin2', 'Переделать', '$2y$13$hhb1MAsu301bdBbB15.i..4gOTqWfmb5DVf5vYOkCiI1kE3J6rciq', NULL, 'asdfas', 10, 1526470487, 1526470487, 40);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `tblDepartment`
--
ALTER TABLE `tblDepartment`
 ADD PRIMARY KEY (`idDepartment`);

--
-- Индексы таблицы `tblRightsItems`
--
ALTER TABLE `tblRightsItems`
 ADD PRIMARY KEY (`idRightItem`), ADD UNIQUE KEY `rightItem` (`rightItem`);

--
-- Индексы таблицы `tblRoleRights`
--
ALTER TABLE `tblRoleRights`
 ADD PRIMARY KEY (`idRoleRights`);

--
-- Индексы таблицы `tblRoles`
--
ALTER TABLE `tblRoles`
 ADD PRIMARY KEY (`idRole`);

--
-- Индексы таблицы `tblUserRole`
--
ALTER TABLE `tblUserRole`
 ADD PRIMARY KEY (`idUserRole`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `tblDepartment`
--
ALTER TABLE `tblDepartment`
MODIFY `idDepartment` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT для таблицы `tblRightsItems`
--
ALTER TABLE `tblRightsItems`
MODIFY `idRightItem` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT для таблицы `tblRoleRights`
--
ALTER TABLE `tblRoleRights`
MODIFY `idRoleRights` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT для таблицы `tblRoles`
--
ALTER TABLE `tblRoles`
MODIFY `idRole` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT для таблицы `tblUserRole`
--
ALTER TABLE `tblUserRole`
MODIFY `idUserRole` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
