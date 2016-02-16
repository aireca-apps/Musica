-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.9 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.5049
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for aitor
DROP DATABASE IF EXISTS `aitor`;
CREATE DATABASE IF NOT EXISTS `aitor` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `aitor`;

-- Dumping structure for table aitor.estilo
DROP TABLE IF EXISTS `estilo`;
CREATE TABLE IF NOT EXISTS `estilo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `codigo` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 COMMENT='Tabla Auxiliar para gestión de roles de usuarios';

-- Dumping data for table aitor.estilo: ~4 rows (approximately)
/*!40000 ALTER TABLE `estilo` DISABLE KEYS */;
REPLACE INTO `estilo` (`id`, `nombre`, `descripcion`, `codigo`) VALUES
	(1, 'Soul', '<p>m&uacute;sica Soul</p>', 'SO'),
	(2, 'Rock', '<p>m&uacute;sica Jazz</p>', 'RC'),
	(3, 'jazz', 'música Jazz', 'JZ'),
	(4, 'Folk', '<p>M&uacute;sica Folk</p>', 'FK');
/*!40000 ALTER TABLE `estilo` ENABLE KEYS */;

-- Dumping structure for procedure aitor.getById
DROP PROCEDURE IF EXISTS `getById`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getById`(IN `pid` INT)
BEGIN

select
g.`id` as `grupo_id`,
g.`nombre`,
g.`componentes`,
g.`fecha_inicio`,
g.`fecha_fin`,
g.`estilo_id`,
e.`nombre` as estilo_nombre,
e.`descripcion` as estilo_descripcion,
e.`codigo` as estilo_codigo
from `grupo` AS g INNER JOIN `estilo` AS e ON g.`estilo_id` = e.`id`
where g.`id` = pid;

END//
DELIMITER ;

-- Dumping structure for procedure aitor.getGrupos
DROP PROCEDURE IF EXISTS `getGrupos`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrupos`(IN `pLimite` INT)
    COMMENT 'Obtiene todos los grupos limitado al parámetro que se le pase'
BEGIN

select
g.`id` as `grupo_id`,
g.`nombre`,
g.`componentes`,
g.`fecha_inicio`,
g.`fecha_fin`,
g.`estilo_id`,
e.`nombre` as estilo_nombre,
e.`descripcion` as estilo_descripcion,
e.`codigo` as estilo_codigo
from `grupo` AS g INNER JOIN `estilo` AS e ON g.`estilo_id` = e.`id`
order by g.`id` LIMIT pLimite;

END//
DELIMITER ;

-- Dumping structure for table aitor.grupo
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE IF NOT EXISTS `grupo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'identificador',
  `nombre` varchar(250) NOT NULL COMMENT 'nombre del grupo',
  `componentes` varchar(250) NOT NULL COMMENT 'componentes actuales del grupo',
  `fecha_inicio` date NOT NULL COMMENT 'fecha de creación del grupo',
  `fecha_fin` date DEFAULT NULL COMMENT 'fecha de disolución del grupo',
  `estilo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;

-- Dumping data for table aitor.grupo: 4 rows
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
REPLACE INTO `grupo` (`id`, `nombre`, `componentes`, `fecha_inicio`, `fecha_fin`, `estilo_id`) VALUES
	(320, 'qwqweqweqw', '<p>qweqweq</p>', '2016-02-02', NULL, 1),
	(321, 'aaaaaaaaaaaaa', '<p>aaaaaaaaaaaaaaaaaa</p>', '2016-02-03', '2016-02-03', 3),
	(1, 'email@email.com', 'asda', '2016-02-08', NULL, 1),
	(301, 'Metallica', '<p>son cuatro</p>', '2016-02-06', '2016-02-12', 2);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;

-- Dumping structure for procedure aitor.login
DROP PROCEDURE IF EXISTS `login`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(IN `pEmail` VARCHAR(50), IN `pPass` INT)
BEGIN
select
nick
from `usuarios` WHERE email = pEmail and pass = pPass;

END//
DELIMITER ;

-- Dumping structure for procedure aitor.searchGrupos
DROP PROCEDURE IF EXISTS `searchGrupos`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchGrupos`(IN `pdato` VARCHAR(250))
BEGIN
select
g.`id` as `grupo_id`,
g.`nombre`,
g.`componentes`,
g.`fecha_inicio`,
g.`fecha_fin`,
g.`estilo_id`,
e.`nombre` as estilo_nombre,
e.`descripcion` as estilo_descripcion,
e.`codigo` as estilo_codigo
from `grupo` AS g INNER JOIN `estilo` AS e ON g.`estilo_id` = e.`id`
WHERE 
	g.nombre like CONCAT('%', pdato , '%')
	or e.nombre like CONCAT('%', pdato , '%')
	or componentes like CONCAT('%', pdato , '%')
	or fecha_inicio like CONCAT('%', pdato , '%')
	or fecha_fin like CONCAT('%', pdato , '%')
	or descripcion like CONCAT('%', pdato , '%')
	or codigo like CONCAT('%', pdato , '%')
ORDER BY g.`id`;


END//
DELIMITER ;

-- Dumping structure for table aitor.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `email` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `nick` varchar(250) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Dumping data for table aitor.usuarios: 1 rows
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
REPLACE INTO `usuarios` (`email`, `pass`, `nick`) VALUES
	('correo@correo.com', '123', 'Aitor');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
