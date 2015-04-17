-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-04-2015 a las 08:22:19
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `recicladora`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canjes`
--

CREATE TABLE IF NOT EXISTS `canjes` (
  `idpremio` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE IF NOT EXISTS `materiales` (
  `idmaterial` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materiales`
--

INSERT INTO `materiales` (`idmaterial`, `nombre`) VALUES
(1, 'pet'),
(2, 'carton');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `premios`
--

CREATE TABLE IF NOT EXISTS `premios` (
  `idpremio` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `valor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntos`
--

CREATE TABLE IF NOT EXISTS `puntos` (
  `idusuario` int(11) NOT NULL,
  `idmaterial` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidoPaterno` varchar(20) NOT NULL,
  `apellidoMaterno` varchar(20) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `nick` varchar(20) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `tipo` varchar(20) DEFAULT NULL,
  `huellaDigital` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `nombre`, `apellidoPaterno`, `apellidoMaterno`, `fechaNacimiento`, `nick`, `password`, `tipo`, `huellaDigital`) VALUES
(1, 'jose', 'molina', 'jose', '1990-03-03', 'pepe', '123', 'admin', NULL),
(2, 'jose', 'molina', 'fidencio', '1990-03-03', 'hola', '12345', 'usuario', NULL),
(3, 'tere', 'morales', 'domingues', '1990-03-03', NULL, NULL, NULL, NULL),
(4, 'jose', 'qwerty', 'fudencio', '1990-11-11', NULL, NULL, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `canjes`
--
ALTER TABLE `canjes`
 ADD KEY `fk_premio` (`idpremio`), ADD KEY `fk_usuario_canjes` (`idusuario`);

--
-- Indices de la tabla `materiales`
--
ALTER TABLE `materiales`
 ADD PRIMARY KEY (`idmaterial`);

--
-- Indices de la tabla `premios`
--
ALTER TABLE `premios`
 ADD PRIMARY KEY (`idpremio`);

--
-- Indices de la tabla `puntos`
--
ALTER TABLE `puntos`
 ADD KEY `fk_usuario` (`idusuario`), ADD KEY `fk_material` (`idmaterial`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`idusuario`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `canjes`
--
ALTER TABLE `canjes`
ADD CONSTRAINT `fk_premio` FOREIGN KEY (`idpremio`) REFERENCES `premios` (`idpremio`),
ADD CONSTRAINT `fk_usuario_canjes` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`);

--
-- Filtros para la tabla `puntos`
--
ALTER TABLE `puntos`
ADD CONSTRAINT `fk_material` FOREIGN KEY (`idmaterial`) REFERENCES `materiales` (`idmaterial`),
ADD CONSTRAINT `fk_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
