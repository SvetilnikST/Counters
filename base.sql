-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Сен 10 2018 г., 16:56
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
-- Структура таблицы `device`
--

CREATE TABLE IF NOT EXISTS `device` (
`id` int(10) unsigned NOT NULL,
  `network_id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type_id` int(11) unsigned DEFAULT NULL,
  `unitQ_id` int(11) NOT NULL,
  `serial` varchar(100) DEFAULT NULL,
  `lastRequestDate` int(11) DEFAULT NULL,
  `requestsCount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=130 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Структура таблицы `network_device`
--

CREATE TABLE IF NOT EXISTS `network_device` (
`id` int(11) NOT NULL,
  `device_id` int(11) unsigned NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `num_port` int(11) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=58 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Структура таблицы `qBox_data_common`
--

CREATE TABLE IF NOT EXISTS `qBox_data_common` (
`id` int(11) NOT NULL,
  `serial` varchar(255) NOT NULL,
  `unit_q` int(11) NOT NULL,
  `time_request` int(11) NOT NULL,
  `time_device` int(11) DEFAULT NULL,
  `time_on` int(11) DEFAULT NULL,
  `time_run_common` int(11) DEFAULT NULL,
  `inStore1` tinyint(1) NOT NULL DEFAULT '0',
  `inStore2` tinyint(1) NOT NULL DEFAULT '0',
  `inStore3` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=68 ROW_FORMAT=DYNAMIC;

--
-- Дамп данных таблицы `qBox_data_common`
--

INSERT INTO `qBox_data_common` (`id`, `serial`, `unit_q`, `time_request`, `time_device`, `time_on`, `time_run_common`, `inStore1`, `inStore2`, `inStore3`) VALUES
(2, '00002128', 1, 1527706940, 1525115100, 24209554, 23694812, 0, 0, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `qBox_data_system`
--

CREATE TABLE IF NOT EXISTS `qBox_data_system` (
`id` int(11) NOT NULL,
  `qBox_data_common_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `time_run_sys` int(11) DEFAULT NULL,
  `sigma_q` double DEFAULT NULL,
  `q1` double DEFAULT NULL,
  `q2` double DEFAULT NULL,
  `q3` double DEFAULT NULL,
  `v1` double DEFAULT NULL,
  `v2` double DEFAULT NULL,
  `m1` double DEFAULT NULL,
  `m2` double DEFAULT NULL,
  `gm1` float DEFAULT NULL,
  `gm2` float DEFAULT NULL,
  `gv1` float DEFAULT NULL,
  `gv2` float DEFAULT NULL,
  `t1` float DEFAULT NULL,
  `t2` float DEFAULT NULL,
  `t3` float DEFAULT NULL,
  `p1` float DEFAULT NULL,
  `p2` float DEFAULT NULL,
  `p3` float DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=214 ROW_FORMAT=DYNAMIC;

--
-- Дамп данных таблицы `qBox_data_system`
--

INSERT INTO `qBox_data_system` (`id`, `qBox_data_common_id`, `number`, `time_run_sys`, `sigma_q`, `q1`, `q2`, `q3`, `v1`, `v2`, `m1`, `m2`, `gm1`, `gm2`, `gv1`, `gv2`, `t1`, `t2`, `t3`, `p1`, `p2`, `p3`) VALUES
(3, 2, 0, 23694812, 0, 5962.916, 2379.9236, 0, 106217.9, 99938.4, 0, 0, 0, 0, 11.8299, 11.5255, 63.94, 25.14, 10, 0.3824, 0.2731, 0),
(4, 2, 0, 23690000, 0, 5962.006, 2379.0006, 3, 106000.9, 90008.4, 0, 0, 0, 0, 11.8299, 11.5255, 63.94, 25.14, 10, 0.3824, 0.2731, 0);

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
-- Структура таблицы `typeDevice`
--

CREATE TABLE IF NOT EXISTS `typeDevice` (
`id` int(10) unsigned NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Структура таблицы `unitQ`
--

CREATE TABLE IF NOT EXISTS `unitQ` (
`id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=66 ROW_FORMAT=DYNAMIC;

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
-- Индексы таблицы `device`
--
ALTER TABLE `device`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_device_typeDevice_id` (`type_id`), ADD KEY `unitQ_id` (`unitQ_id`), ADD KEY `network_id` (`network_id`);

--
-- Индексы таблицы `network_device`
--
ALTER TABLE `network_device`
 ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `qBox_data_common`
--
ALTER TABLE `qBox_data_common`
 ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `qBox_data_system`
--
ALTER TABLE `qBox_data_system`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_qBox_system_to_data` (`qBox_data_common_id`);

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
 ADD PRIMARY KEY (`idRoleRights`), ADD KEY `FKsye57lsc39s04110n3colpfke` (`idRightsItem`), ADD KEY `FKaylr75pmb82l7jw3cgw5musfj` (`idRole`);

--
-- Индексы таблицы `tblRoles`
--
ALTER TABLE `tblRoles`
 ADD PRIMARY KEY (`idRole`);

--
-- Индексы таблицы `tblUserRole`
--
ALTER TABLE `tblUserRole`
 ADD PRIMARY KEY (`idUserRole`), ADD KEY `FKglmau4338e0dxl03c8xfqxhx` (`idUser`);

--
-- Индексы таблицы `typeDevice`
--
ALTER TABLE `typeDevice`
 ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `unitQ`
--
ALTER TABLE `unitQ`
 ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`), ADD KEY `FKmigkligawmvbp7s89qybn6aih` (`department`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `device`
--
ALTER TABLE `device`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `network_device`
--
ALTER TABLE `network_device`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `qBox_data_common`
--
ALTER TABLE `qBox_data_common`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `qBox_data_system`
--
ALTER TABLE `qBox_data_system`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
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
-- AUTO_INCREMENT для таблицы `typeDevice`
--
ALTER TABLE `typeDevice`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `unitQ`
--
ALTER TABLE `unitQ`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `device`
--
ALTER TABLE `device`
ADD CONSTRAINT `device_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `typeDevice` (`id`),
ADD CONSTRAINT `device_ibfk_3` FOREIGN KEY (`unitQ_id`) REFERENCES `unitQ` (`id`),
ADD CONSTRAINT `device_ibfk_4` FOREIGN KEY (`network_id`) REFERENCES `network_device` (`id`);

--
-- Ограничения внешнего ключа таблицы `qBox_data_system`
--
ALTER TABLE `qBox_data_system`
ADD CONSTRAINT `qBox_data_system_ibfk_1` FOREIGN KEY (`qBox_data_common_id`) REFERENCES `qBox_data_common` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `tblRoleRights`
--
ALTER TABLE `tblRoleRights`
ADD CONSTRAINT `FKaylr75pmb82l7jw3cgw5musfj` FOREIGN KEY (`idRole`) REFERENCES `tblRoles` (`idRole`),
ADD CONSTRAINT `FKsye57lsc39s04110n3colpfke` FOREIGN KEY (`idRightsItem`) REFERENCES `tblRightsItems` (`idRightItem`);

--
-- Ограничения внешнего ключа таблицы `tblUserRole`
--
ALTER TABLE `tblUserRole`
ADD CONSTRAINT `FKglmau4338e0dxl03c8xfqxhx` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
ADD CONSTRAINT `FKmigkligawmvbp7s89qybn6aih` FOREIGN KEY (`department`) REFERENCES `tblDepartment` (`idDepartment`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
