-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: vendas
-- ------------------------------------------------------
-- Server version	8.0.36

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `cliente_seq`
--

DROP TABLE IF EXISTS `cliente_seq`;

CREATE TABLE `cliente_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `cliente_seq`
--

LOCK TABLES `cliente_seq` WRITE;

INSERT INTO `cliente_seq` VALUES (101);

UNLOCK TABLES;

--
-- Table structure for table `itens_pedido`
--

DROP TABLE IF EXISTS `itens_pedido`;

CREATE TABLE `itens_pedido` (
  `id_itens` int NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `numero_controle` int NOT NULL,
  `quantidade` int DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  `numero_pedido` int DEFAULT NULL,
  PRIMARY KEY (`id_itens`),
  KEY `FKjd8gry400g0xbuptbfeq39v74` (`cliente_id`),
  KEY `FK3vebxwd5fig1xjxyhrah1nhnm` (`numero_pedido`),
  CONSTRAINT `FK3vebxwd5fig1xjxyhrah1nhnm` FOREIGN KEY (`numero_pedido`) REFERENCES `pedido` (`numero_pedido`),
  CONSTRAINT `FKjd8gry400g0xbuptbfeq39v74` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `itens_pedido_seq`
--

DROP TABLE IF EXISTS `itens_pedido_seq`;

CREATE TABLE `itens_pedido_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `itens_pedido_seq`
--

LOCK TABLES `itens_pedido_seq` WRITE;

INSERT INTO `itens_pedido_seq` VALUES (151);

UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `numero_pedido` int NOT NULL,
  `data_cadastro` date NOT NULL,
  `valor_total` double DEFAULT NULL,
  PRIMARY KEY (`numero_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





--
-- Table structure for table `pedido_seq`
--

DROP TABLE IF EXISTS `pedido_seq`;

CREATE TABLE `pedido_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Dumping data for table `pedido_seq`
--

LOCK TABLES `pedido_seq` WRITE;

INSERT INTO `pedido_seq` VALUES (101);

UNLOCK TABLES;




-- Dump completed on 2024-06-13  12:06:50
