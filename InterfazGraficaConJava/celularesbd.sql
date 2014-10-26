CREATE DATABASE  IF NOT EXISTS `celularesdb`;
USE `celularesdb`;
DROP TABLE IF EXISTS `celular`;
CREATE TABLE `celular` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `marca` varchar(10) NOT NULL,
  `modelo` varchar(32) NOT NULL,
  `anioLanzamiento` varchar(32) NOT NULL,
  `sistemaOperativo` varchar(32) DEFAULT NULL,
  `operadora` varchar(32) DEFAULT NULL,
  `observacion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
LOCK TABLES `celular` WRITE;
INSERT INTO `celular` VALUES (1,'Nokia','Lumi 730','2013','windows 8','Movistar',NULL),(2,'Samsung','Galaxi s3 mini','2012','android','Claro','No hay actualizacion');
UNLOCK TABLES;