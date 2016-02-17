-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.17 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.3.0.5048
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para aitor
DROP DATABASE IF EXISTS `aitor`;
CREATE DATABASE IF NOT EXISTS `aitor` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `aitor`;

-- Volcando estructura para tabla aitor.estilo
DROP TABLE IF EXISTS `estilo`;
CREATE TABLE IF NOT EXISTS `estilo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `codigo` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COMMENT='Tabla Auxiliar para gestión de roles de usuarios';

-- Volcando datos para la tabla aitor.estilo: ~4 rows (aproximadamente)
DELETE FROM `estilo`;
/*!40000 ALTER TABLE `estilo` DISABLE KEYS */;
INSERT INTO `estilo` (`id`, `nombre`, `descripcion`, `codigo`) VALUES
	(1, 'Soul', '<p>m&uacute;sica Soul</p>', 'SO'),
	(2, 'Rock', '<p>m&uacute;sica Jazz</p>', 'RC'),
	(3, 'jazz', 'música Jazz', 'JZ'),
	(4, 'Folk', '<p>M&uacute;sica Folk</p>', 'FK');
/*!40000 ALTER TABLE `estilo` ENABLE KEYS */;

-- Volcando estructura para procedimiento aitor.getById
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

-- Volcando estructura para procedimiento aitor.getGrupos
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

-- Volcando estructura para tabla aitor.grupo
DROP TABLE IF EXISTS `grupo`;
CREATE TABLE IF NOT EXISTS `grupo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'identificador',
  `nombre` varchar(250) NOT NULL COMMENT 'nombre del grupo',
  `componentes` varchar(250) NOT NULL COMMENT 'componentes actuales del grupo',
  `fecha_inicio` date NOT NULL COMMENT 'fecha de creación del grupo',
  `fecha_fin` date DEFAULT NULL COMMENT 'fecha de disolución del grupo',
  `estilo_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=378 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla aitor.grupo: 4 rows
DELETE FROM `grupo`;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` (`id`, `nombre`, `componentes`, `fecha_inicio`, `fecha_fin`, `estilo_id`) VALUES
	(320, 'qwqweqweqw', '<p>qweqweq</p>', '2016-02-02', NULL, 1),
	(321, 'aaaaaaaaaaaaa', '<p>aaaaaaaaaaaaaaaaaa</p>', '2016-02-03', '2016-02-03', 3),
	(1, 'email@email.com', 'asda', '2016-02-08', NULL, 1),
	(301, 'Metallica', '<p>son cuatro</p>', '2016-02-06', '2016-02-12', 2);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;

-- Volcando estructura para procedimiento aitor.login
DROP PROCEDURE IF EXISTS `login`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(IN `pEmail` VARCHAR(50), IN `pPass` INT)
BEGIN
select
id, nick, email, pass
from `usuario` WHERE email = pEmail and pass = pPass;

END//
DELIMITER ;

-- Volcando estructura para procedimiento aitor.searchGrupos
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

-- Volcando estructura para tabla aitor.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `nick` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla aitor.usuario: 1 rows
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `pass`, `nick`) VALUES
	(1, 'correo@correo.com', '123', 'Aitor'),
	(2, 'otrocorreo@correo.com', '123456', 'Jefe'),
	(3, 'loquesea@loquesea.com', '123456', 'Mi nick');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
