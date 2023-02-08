CREATE DATABASE IF NOT EXISTS trabalho;
USE trabalho;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(50) NOT NULL DEFAULT '0',
  `nome` varchar(50) DEFAULT NULL,
  `datanasc` date DEFAULT NULL,
  `endereco` varchar(50) DEFAULT NULL,
  `sexo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `telefone`;
CREATE TABLE `telefone` (
  `numero` varchar(50) NOT NULL DEFAULT '0',
  `ddd` int(2) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  CONSTRAINT `FK_telefone_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) DEFAULT NULL,
  `preco` int(11) DEFAULT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `FK_produto_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_produto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra` (
  `idUser` int(11) DEFAULT NULL,
  `dataCompra` date DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `precoTotal` int(11) DEFAULT NULL,
  `idProduto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`, `idProduto`),
  KEY `FK_compra_user` (`idUser`),
  CONSTRAINT `FK_compra_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_compra_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `produtos_venda`;
CREATE TABLE `produtos_venda` (
  `idUser` int(11) DEFAULT NULL,
  `idProduto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUser`, `idProduto`),
  CONSTRAINT `FK_produtos_venda_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_produtos_venda_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `idUser` int(11) DEFAULT NULL,
  `idProduto` int(11) DEFAULT NULL,
  `notaAvaliacao` int(1) NOT NULL CHECK (`notaAvaliacao` BETWEEN 1 AND 5),
  PRIMARY KEY (`idUser`, `idProduto`),
  CONSTRAINT `FK_avaliacao_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_avaliacao_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ofertas`;
CREATE TABLE `ofertas` (
  `idProduto` int(11) DEFAULT NULL,
  `desconto` decimal(2,2) NOT NULL CHECK (`desconto` BETWEEN 0 AND 1),
  PRIMARY KEY (`idProduto`),
  CONSTRAINT `FK_oferta_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `user` (`cpf`, `nome`, `datanasc`, `endereco`, `sexo`)
VALUES
  ('12345678901', 'João Silva', '1980-01-01', '1 Rua Principal', 'Masculino'),
  ('23456789012', 'Maria Oliveira', '1985-03-15', '2 Rua do Carmo', 'Feminino'),
  ('34567890123', 'Pedro Souza', '1990-07-10', '3 Rua das Flores', 'Masculino'),
  ('45678901234', 'Ana Costa', '1995-12-01', '4 Rua da Lua', 'Feminino');

INSERT INTO `telefone` (`numero`, `ddd`, `idUser`)
VALUES
  ('1112223333', 11, 1),
  ('2223334444', 21, 2),
  ('3333444555', 31, 3),
  ('4445556666', 41, 4);

