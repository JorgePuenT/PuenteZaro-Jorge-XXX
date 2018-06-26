-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: AcmeNewspaper
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

start transaction;

create database `AcmeNewspaper`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `AcmeNewspaper`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `AcmeNewspaper`.* to 'acme-manager'@'%';

use `AcmeNewspaper`;

--
-- Table structure for table `actor_addressess`
--

DROP TABLE IF EXISTS `actor_addressess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_addressess` (
  `Actor_id` int(11) NOT NULL,
  `addressess` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_addressess`
--

LOCK TABLES `actor_addressess` WRITE;
/*!40000 ALTER TABLE `actor_addressess` DISABLE KEYS */;
INSERT INTO `actor_addressess` VALUES (1205,'Avda. Reina Mercedes no.5 2C'),(1206,'Calle falsa 123, Sevilla'),(1207,'Calle verdadera 88, Ecija'),(1209,'Avenida la Palmera 33, Sevilla'),(1209,'Calle Marina 12, Zahara de los Atunes'),(1209,'Plaza Mayor, Salamanca'),(1211,'Calle Laraña 12, Sevilla'),(1213,'Calle Real 12, Barbate'),(1215,'Calle Nueva 12, Miajadas'),(1216,'Calle Falsa 123 1'),(1217,'Calle Falsa 123 2'),(1218,'Calle Falsa 123 3'),(1339,'Avda. Reina Mercedes no.2 4D');
/*!40000 ALTER TABLE `actor_addressess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_emailss`
--

DROP TABLE IF EXISTS `actor_emailss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_emailss` (
  `Actor_id` int(11) NOT NULL,
  `emailss` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_emailss`
--

LOCK TABLES `actor_emailss` WRITE;
/*!40000 ALTER TABLE `actor_emailss` DISABLE KEYS */;
INSERT INTO `actor_emailss` VALUES (1205,'pacheco@correo.com'),(1206,'ppgg@correo.es'),(1207,'pptoja@correo.es'),(1207,'pptoja@us.es'),(1208,'jpuj@hotmail.cat'),(1209,'carpuro@gmail.es'),(1210,'lozesparta@correo.es'),(1211,'ale@correo.es'),(1212,'gordo@correo.es'),(1212,'gordoAgui@hotmail.es'),(1213,'pacheco@correo.es'),(1214,'inevalo@correo.es'),(1215,'silverTe@correo.es'),(1216,'johwicree@correo.com'),(1217,'rubrubomg@correo.com'),(1218,'lolfer@correo.com'),(1339,'rnavarro@correo.com');
/*!40000 ALTER TABLE `actor_emailss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_folder`
--

DROP TABLE IF EXISTS `actor_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_folder` (
  `Actor_id` int(11) NOT NULL,
  `folders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dp573h40udupcm5m1kgn2bc2r` (`folders_id`),
  CONSTRAINT `FK_dp573h40udupcm5m1kgn2bc2r` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_folder`
--

LOCK TABLES `actor_folder` WRITE;
/*!40000 ALTER TABLE `actor_folder` DISABLE KEYS */;
INSERT INTO `actor_folder` VALUES (1205,1261),(1205,1262),(1205,1263),(1205,1264),(1205,1265),(1206,1266),(1206,1267),(1206,1268),(1206,1269),(1206,1270),(1207,1271),(1207,1272),(1207,1273),(1207,1274),(1207,1275),(1208,1276),(1208,1277),(1208,1278),(1208,1279),(1208,1280),(1209,1281),(1209,1282),(1209,1283),(1209,1284),(1209,1285),(1210,1286),(1210,1287),(1210,1288),(1210,1289),(1210,1290),(1211,1291),(1211,1292),(1211,1293),(1211,1294),(1211,1295),(1212,1296),(1212,1297),(1212,1298),(1212,1299),(1212,1300),(1213,1301),(1213,1302),(1213,1303),(1213,1304),(1213,1305),(1214,1306),(1214,1307),(1214,1308),(1214,1309),(1214,1310),(1215,1311),(1215,1312),(1215,1313),(1215,1314),(1215,1315),(1216,1316),(1216,1317),(1216,1318),(1216,1319),(1216,1320),(1217,1321),(1217,1322),(1217,1323),(1217,1324),(1217,1325),(1218,1326),(1218,1327),(1218,1328),(1218,1329),(1218,1330),(1205,1331),(1339,1340),(1339,1341),(1339,1342),(1339,1343),(1339,1344);
/*!40000 ALTER TABLE `actor_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_phoness`
--

DROP TABLE IF EXISTS `actor_phoness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_phoness` (
  `Actor_id` int(11) NOT NULL,
  `phoness` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_phoness`
--

LOCK TABLES `actor_phoness` WRITE;
/*!40000 ALTER TABLE `actor_phoness` DISABLE KEYS */;
INSERT INTO `actor_phoness` VALUES (1205,'+34617179563'),(1206,'+635879562'),(1208,'+965879562'),(1208,'+635875682'),(1208,'+635877562'),(1209,'+965879562'),(1211,'+856879562'),(1213,'+856669562'),(1214,'+856878562'),(1216,'+34618179563'),(1217,'+34618149563'),(1218,'+34618179561'),(1339,'+34618234563');
/*!40000 ALTER TABLE `actor_phoness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  KEY `AdminUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1205,0,'John','Tabares Pacheco',1191),(1339,0,'Juan','Romero Navarro',1338);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bannerUrl` varchar(255) DEFAULT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `targetUrl` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `agent_id` int(11) NOT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7n9ussuxsi3k6rm34vajrccvn` (`agent_id`),
  KEY `FK_2a9jqcvexg35eohaebb71i4xu` (`newspaper_id`),
  CONSTRAINT `FK_2a9jqcvexg35eohaebb71i4xu` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_7n9ussuxsi3k6rm34vajrccvn` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` VALUES (1257,0,'https://cms-assets.tutsplus.com/uploads/users/127/posts/19971/image/3_1.jpg','Visa',674,6,2019,'Armando Garrido Castro','4498121078246142','\0','https://s-media-cache-ak0.pinimg.com/originals/39/dc/42/39dc420df80ccb5f9dbb6f06b2394542.png','Advertisement 1',1216,1242),(1258,0,'https://www.ponferradahoy.com/wp-content/uploads/2018/01/fortnite.jpg','Visa',674,6,2019,'Armando Garrido Castro','4498121078246142','\0','http://www.dicetowernews.com/wp-content/uploads/2018/01/target.png','Advertisement 2',1216,1242),(1259,0,'https://vignette.wikia.nocookie.net/zeldaeu/images/b/b1/Zelda.png/revision/latest?cb=20170416190604','Visa',883,7,2019,'Jorge Puente Zaro','4067527284728593','\0','https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Target_logo.svg/1200px-Target_logo.svg.png','Advertisement 3',1217,1246),(1260,0,'https://vignette.wikia.nocookie.net/zeldaeu/images/b/b1/Zelda.png/revision/latest?cb=20170416190604','Visa',883,7,2019,'Jorge Puente Zaro','4067527284728593','\0','https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Target_logo.svg/1200px-Target_logo.svg.png','Sexo, viagra',1217,1249);
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5cg6nedtnilfs6spfq209syse` (`userAccount_id`),
  KEY `AgentUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_5cg6nedtnilfs6spfq209syse` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1216,0,'John','Wick Reeves',1202),(1217,0,'Ruben','Rubius OMG',1203),(1218,0,'Lolito','Fernandez',1204);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `finalMode` bit(1) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `publicationMoment` datetime DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_41w77a5vfwp4dgryvj07s5tqd` (`newspaper_id`,`publicationMoment`,`inappropriate`,`summary`,`body`,`title`),
  CONSTRAINT `FK_pftm848lf5hu8sd0vghfs605l` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1243,0,'El camino de santiago es la opcion por la que mas se decantan los jovenes debido a su precio','','\0','2018-05-02 12:22:00','uno de los mejores planes para este verano','Camino de santiago',1242),(1244,0,'podras pasear por la plaza del sol de madrid o quizas buscar un bus que te lleve hasta el escorial','','\0','2018-03-01 12:22:00','que hacer en invierno por en la cominidad de madrid','Madrid en vacaciones',1242),(1245,0,'se estima que mas de 25000 personas han pasado ta por la calle larios para observar el espectaulo navideño por xcelencia a nivel andaluz','','\0','2018-01-01 12:22:00','alumbrado de navidad','Malaga destino',1242),(1247,0,'Rafa nada dice que no cambiaria su kia por nada pero en realidad tiene dos ferraris y un yate','','\0','2017-03-11 12:22:00','identidades sobre los dueños de los mejores coches','Identidades',1246),(1248,0,'el gobierno australiano acepta una ley donde se permiten las peleas entre canguros y humanos','','\0','2018-03-03 12:22:00','Polemica en australia','Pelea de canguros',1246),(1250,0,'el villamarin recientemente agrandado se muestra espectacular mientras que el pizjuan estrenó un juego de luces maravilloso','','\0','2018-04-04 12:22:00','visitar ambos estadios y ver sus impresionantes estructuras','Estadios de la capital hispalense',1249),(1252,0,'Aquí pudimos ver como dos grandes equipos se enfrentaban en la final de los Worlds del LoL','\0','\0','2018-04-02 12:22:00','Se pueden ver estos inmensos estadios','Estadios de la e-sports',1251),(1254,0,'Este jugador se caracteriza por su defensa agresiva, y por su mecánica en los tiros de 3','','\0','2018-04-04 12:22:00','Rafael Tgr el único español','Un español en el draft de la nueva liga de 2k',1253);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_picturess`
--

DROP TABLE IF EXISTS `article_picturess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_picturess` (
  `Article_id` int(11) NOT NULL,
  `picturess` varchar(255) DEFAULT NULL,
  KEY `FK_dojvlpqxgicr7ljwgjptg2ovn` (`Article_id`),
  CONSTRAINT `FK_dojvlpqxgicr7ljwgjptg2ovn` FOREIGN KEY (`Article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_picturess`
--

LOCK TABLES `article_picturess` WRITE;
/*!40000 ALTER TABLE `article_picturess` DISABLE KEYS */;
INSERT INTO `article_picturess` VALUES (1243,'https://cdn.20m.es/img2/recortes/2015/03/07/214137-870-489.jpg\n				'),(1244,'http://apartamentosmadridplaza.es/blog/wp-content/uploads/Plaza-de-sol-aerea.jpg\n				'),(1245,'http://www.spain.info/export/sites/spaininfo/comun/carrusel-recursos/andalucia/malaga-26926024-istock.jpg_369272544.jpg'),(1245,'https://www.recordrentacar.com/images/beforefooter/destinos/alquiler-coches-malaga.jpg'),(1247,'https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/3/005/097/225/31df52b.jpg'),(1247,'http://chlcapital.cl/images/fotos/team-francisca.jpg'),(1247,'https://pbs.twimg.com/profile_images/466141077028622337/8ZXWp0r1.jpeg'),(1248,'https://d.otcdn.com/imglib/almacen_fotos/geo_destinos_1920x440/30024_australia/30024_40850_12.jpg\n				'),(1250,'https://thebestfutbol.com/wp-content/uploads/2018/01/Villamarin.jpg\n				'),(1252,'http://estaticos03.marca.com/imagenes/2015/03/04/e-sports/1425471526_extras_ladillos_5_0.jpg\n				');
/*!40000 ALTER TABLE `article_picturess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_htwa0xf4olgk89qnhe8mt578j` (`user_id`,`inappropriate`,`creationMoment`,`description`,`title`),
  CONSTRAINT `FK_t10lk4j2g8uw7k7et58ytrp70` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (1227,0,'2017-12-26 22:22:00','A quien se le ocurre hoy en dia cialis a sus hijos para fomentar la velocidad mental','\0','Le daba dos yoyas',1206),(1228,0,'2015-12-09 22:22:00','Quien se cree aun la gran mentira del dia de la marmota, yo soy mas del pulpo paul','\0','Rarezas de la vida',1206),(1229,0,'2016-12-26 22:22:00','He estado leyendo sobre los superdotados, por su naturaleza ller libros es como el sexo','\0','Superdotados',1207),(1230,0,'2018-03-26 22:22:00','Tu analisis para conseguir caer una milesima mas rapido y matar al campero que se esconde en el arbol es una maravilla','\0','Fornite',1207),(1231,0,'2016-12-30 22:22:00','El mejor coctel para el verano es el sex on the beach','\0','Cocteles',1208),(1232,0,'2018-01-30 22:22:00','Tu articulo sobre comida fué extraño, ¿quien le echa viagra a la ensalada?','\0','Se recomienda llevar comida',1208),(1233,0,'2016-01-01 22:22:00','Te ha faltado hablar sobre el las torres Kio de Madrid','\0','Rascacielos',1209),(1234,0,'2017-12-26 22:22:00','Los articulos escritos por ti dejan mucho que desear','\0','Vaya caca',1207),(1235,0,'2015-10-15 22:22:00','Tremenda la experiencia contada por esos viajeros','\0','Los viajes',1206),(1236,0,'2017-12-26 22:22:00','El año pasado lei un articulo religioso que hablaban sobre el sexo','','articulo religioso',1206),(1237,0,'2017-12-21 22:22:00','El otro día vi como el Madrid ganaba de nuevo','','Articulo de futbol',1208),(1238,0,'2016-12-26 22:22:00','Me encantó la victoria de los Cavs a los Warriors en esta mítica final','','NBA',1207),(1239,0,'2017-12-26 22:22:00','El año pasado lei un articulo religioso que hablaban sobre el sexo','','articulo religioso',1206);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  KEY `CustomerUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1211,0,'Alejandro ','Oasis Gómez',1197),(1212,0,'Carlos ','Gordo Aguilar',1198),(1213,0,'Jorge ','Heredia Pacheco',1199),(1214,0,'Ines ','Gonzalez Valo',1200),(1215,0,'Tera ','Jimenez Plata',1201);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examentity`
--

DROP TABLE IF EXISTS `examentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `displayMoment` datetime DEFAULT NULL,
  `draft` bit(1) NOT NULL,
  `gauge` int(11) NOT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `admin_id` int(11) NOT NULL,
  `newspaper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_98mh1hl39xh0gpgt46grkher9` (`ticker`),
  KEY `FK_gm9vebbn88efblqtvjupj0mi3` (`admin_id`),
  KEY `FK_9bc320k506kxpd0bid58jp8lw` (`newspaper_id`),
  CONSTRAINT `FK_9bc320k506kxpd0bid58jp8lw` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_gm9vebbn88efblqtvjupj0mi3` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examentity`
--

LOCK TABLES `examentity` WRITE;
/*!40000 ALTER TABLE `examentity` DISABLE KEYS */;
INSERT INTO `examentity` VALUES (1345,0,'Es un periódico genial, me encanta.','2018-04-22 07:32:00','\0',1,'ASDF12341','Periódico de 10',1205,1249),(1346,0,'Este periódico merece un premio.','2017-04-22 12:08:00','',2,'ASDF12342','Un periódico excepcional',1205,NULL),(1347,0,'Es una idea muy buena pero le falta tiempo para ser de los mejores.','2018-04-25 21:16:00','\0',3,'ASDF12343','Puede mejorar pero es muy bueno',1339,1242),(1348,0,'Potencial en bruto, es fantástico el trabajo.','2020-11-22 22:22:00','\0',1,'ASDF12344','De los mejores',1339,1251);
/*!40000 ALTER TABLE `examentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `system` bit(1) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e6lcmpm09goh6x4n16fbq5uka` (`parent_id`),
  CONSTRAINT `FK_e6lcmpm09goh6x4n16fbq5uka` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (1261,0,'Inbox','',NULL),(1262,0,'Outbox','',NULL),(1263,0,'Trashbox','',NULL),(1264,0,'Notificationbox','',NULL),(1265,0,'Spambox','',NULL),(1266,0,'Inbox','',NULL),(1267,0,'Outbox','',NULL),(1268,0,'Trashbox','',NULL),(1269,0,'Notificationbox','',NULL),(1270,0,'Spambox','',NULL),(1271,0,'Inbox','',NULL),(1272,0,'Outbox','',NULL),(1273,0,'Trashbox','',NULL),(1274,0,'Notificationbox','',NULL),(1275,0,'Spambox','',NULL),(1276,0,'Inbox','',NULL),(1277,0,'Outbox','',NULL),(1278,0,'Trashbox','',NULL),(1279,0,'Notificationbox','',NULL),(1280,0,'Spambox','',NULL),(1281,0,'Inbox','',NULL),(1282,0,'Outbox','',NULL),(1283,0,'Trashbox','',NULL),(1284,0,'Notificationbox','',NULL),(1285,0,'Spambox','',NULL),(1286,0,'Inbox','',NULL),(1287,0,'Outbox','',NULL),(1288,0,'Trashbox','',NULL),(1289,0,'Notificationbox','',NULL),(1290,0,'Spambox','',NULL),(1291,0,'Inbox','',NULL),(1292,0,'Outbox','',NULL),(1293,0,'Trashbox','',NULL),(1294,0,'Notificationbox','',NULL),(1295,0,'Spambox','',NULL),(1296,0,'Inbox','',NULL),(1297,0,'Outbox','',NULL),(1298,0,'Trashbox','',NULL),(1299,0,'Notificationbox','',NULL),(1300,0,'Spambox','',NULL),(1301,0,'Inbox','',NULL),(1302,0,'Outbox','',NULL),(1303,0,'Trashbox','',NULL),(1304,0,'Notificationbox','',NULL),(1305,0,'Spambox','',NULL),(1306,0,'Inbox','',NULL),(1307,0,'Outbox','',NULL),(1308,0,'Trashbox','',NULL),(1309,0,'Notificationbox','',NULL),(1310,0,'Spambox','',NULL),(1311,0,'Inbox','',NULL),(1312,0,'Outbox','',NULL),(1313,0,'Trashbox','',NULL),(1314,0,'Notificationbox','',NULL),(1315,0,'Spambox','',NULL),(1316,0,'Inbox','',NULL),(1317,0,'Outbox','',NULL),(1318,0,'Trashbox','',NULL),(1319,0,'Notificationbox','',NULL),(1320,0,'Spambox','',NULL),(1321,0,'Inbox','',NULL),(1322,0,'Outbox','',NULL),(1323,0,'Trashbox','',NULL),(1324,0,'Notificationbox','',NULL),(1325,0,'Spambox','',NULL),(1326,0,'Inbox','',NULL),(1327,0,'Outbox','',NULL),(1328,0,'Trashbox','',NULL),(1329,0,'Notificationbox','',NULL),(1330,0,'Spambox','',NULL),(1331,0,'Trabajo','\0',NULL),(1340,0,'Inbox','',NULL),(1341,0,'Outbox','',NULL),(1342,0,'Trashbox','',NULL),(1343,0,'Notificationbox','',NULL),(1344,0,'Spambox','',NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup`
--

DROP TABLE IF EXISTS `followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `publicationMoment` date DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_freq297hr07gk5gy5a4auue0q` (`publicationMoment`),
  KEY `FK_aer0q20rslre6418yqlfwmeek` (`article_id`),
  CONSTRAINT `FK_aer0q20rslre6418yqlfwmeek` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup`
--

LOCK TABLES `followup` WRITE;
/*!40000 ALTER TABLE `followup` DISABLE KEYS */;
INSERT INTO `followup` VALUES (1349,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el calor se recomienda.','Se recomienda llevar comida',1248),(1350,0,'false','2017-12-26','Vamos a estar fuera todo el día, y parece que llueve.','Se recomienda llevar paraguas',1243),(1351,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el calor se recomienda.','Se recomienda llevar bebidas',1244),(1352,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el frio se recomienda.','Se recomienda llevar ropa abrigada',1248),(1353,0,'false','2017-12-26','Vamos a estar fuera todo el día.','Se recomienda llevar ropa cómoda',1248),(1354,0,'false','2017-12-26','Vamos a estar fuera todo el día, y es posible que queráis comprar algo de souvenir.','Se recomienda llevar dinero',1243),(1355,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el calor se recomienda.','Se recomienda llevar comida',1243),(1356,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el calor se recomienda.','Es muy interesante la comida de Este lugar.',1244),(1357,0,'false','2017-12-26','Vamos a estar fuera todo el día, para evitar problemas con el calor se recomienda.','Se me olvidó decir que la música de estos lares es preciosa.',1245),(1358,0,'false','2017-12-26','si ya sabemos quien va a ganar...','Otra vez vamos a tener una final koreana',1252),(1359,0,'false','2017-12-26','La única pega es que es fan de los Celtics, ya podría ser de Toronto','Me encanta este chico',1254),(1360,0,'false','2017-12-26','Soy fan de sus what ifs, siempre la acaba liando','Espero que lo cojan',1254);
/*!40000 ALTER TABLE `followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_picturess`
--

DROP TABLE IF EXISTS `followup_picturess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup_picturess` (
  `FollowUp_id` int(11) NOT NULL,
  `picturess` varchar(255) DEFAULT NULL,
  KEY `FK_gay3v6q6el0rv9nwudcfsm3m7` (`FollowUp_id`),
  CONSTRAINT `FK_gay3v6q6el0rv9nwudcfsm3m7` FOREIGN KEY (`FollowUp_id`) REFERENCES `followup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_picturess`
--

LOCK TABLES `followup_picturess` WRITE;
/*!40000 ALTER TABLE `followup_picturess` DISABLE KEYS */;
INSERT INTO `followup_picturess` VALUES (1349,'http://crucerosensevilla.com/wp-content/uploads/2015/09/Sevilla4.jpg'),(1349,'https://sevillasecreta.co/wp-content/uploads/2016/02/Plaza7.jpg'),(1349,'http://www.andalucia.org/media/fotos/image_148527_jpeg_800x600_q85.jpg'),(1349,'http://2.bp.blogspot.com/_ejfsu9g5t-8/S_cojAYoyGI/AAAAAAAAArY/2jSCIzYsO-M/s1600/alameda3.jpg'),(1351,'http://www.semovimiento.com/wp-content/uploads/2017/06/madrid-overview-sunsetovermadrid-xlarge.jpeg'),(1351,'https://afrodita-production.s3.amazonaws.com/images/bus-madrid.original.jpg'),(1352,'https://www.viajablog.com/wp-content/uploads/2017/04/Feria-de-Abril-de-Sevilla.jpg'),(1352,'https://cdn.elviajerofisgon.com/wp-content/uploads/2015/05/Giralda-1-iStock-750x390.jpg'),(1353,'http://pasion-origin.sevilla.abc.es/pasionensevilla/wp-content/uploads/2016/07/plaza-campana-nueva.jpg');
/*!40000 ALTER TABLE `followup_picturess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `sentTime` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `actorFrom_id` int(11) NOT NULL,
  `actorTo_id` int(11) NOT NULL,
  `folder_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7t1ls63lqb52igs4ms20cf94t` (`folder_id`),
  CONSTRAINT `FK_7t1ls63lqb52igs4ms20cf94t` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1332,0,'GG! I win in two set',1,'2016-01-25 12:01:00','tennis 20:00',1206,1207,1267),(1333,0,'GG! I win in two set',1,'2016-01-25 12:01:00','tennis 20:00',1206,1207,1267),(1334,0,'Hi, i want buy many viagra',0,'2017-07-12 10:05:00','Hello',1206,1208,1267),(1335,0,'Hi, i want buy many viagra',0,'2017-07-12 10:05:00','Hello',1206,1208,1272),(1336,0,'we will win sure',2,'2017-10-11 12:01:00','RM-FCB',1207,1209,1272),(1337,0,'Today classic',2,'2017-10-11 12:01:00','RM-FCB',1205,1206,1262);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspaper`
--

DROP TABLE IF EXISTS `newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newspaper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `isPrivate` bit(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_9d4jmr7swxswfoel8e6qoyi47` (`user_id`,`title`,`description`,`inappropriate`,`publicationDate`,`isPrivate`),
  CONSTRAINT `FK_1vjxhvxnpuoan0f5uyrs4veeg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspaper`
--

LOCK TABLES `newspaper` WRITE;
/*!40000 ALTER TABLE `newspaper` DISABLE KEYS */;
INSERT INTO `newspaper` VALUES (1241,0,'Esta revista trata sobre los mejores coches del mundo','','','http://crucerosensevilla.com/wp-content/uploads/2015/09/Sevilla4.jpg',1,'2017-11-26','MarcaMotor',1206),(1242,0,'Viajes a lo largo del mundo, ¿Algo mejor que leer?','\0','\0','http://www.andalucia.org/media/fotos/image_148527_jpeg_800x600_q85.jpg',1.5,'2018-03-11','Viajes',1206),(1246,0,'Viajes a lo largo del mundo, ¿Algo mejor que leer?','\0','','http://2.bp.blogspot.com/_ejfsu9g5t-8/S_cojAYoyGI/AAAAAAAAArY/2jSCIzYsO-M/s1600/alameda3.jpg',2,'2019-04-11','Lugares con encanto',1207),(1249,0,'Nos centramos en las mejores fiestas de españa','\0','\0','https://www.viajablog.com/wp-content/uploads/2017/04/Feria-de-Abril-de-Sevilla.jpg',1,'2016-03-11','Fiesta',1208),(1251,0,'Actualidad sobre la capital española','\0','\0','http://apartamentosmadridplaza.es/blog/wp-content/uploads/Plaza-de-sol-aerea.jpg',2,'2017-03-11','Madrid informa',1210),(1253,0,'Actualidad sobre la capital andaluza','\0','\0','',2,'2017-03-11','Sevilla informa',1206);
/*!40000 ALTER TABLE `newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `newspaper_id` int(11) DEFAULT NULL,
  `volume_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_nc1vf3np9y553nvhx2n6mxd1m` (`newspaper_id`,`customer_id`),
  KEY `FK_2i5w4btb7x3r6d2jsd213aqct` (`customer_id`),
  KEY `FK_gfburoi2d5er3v6w64xkqlb1y` (`volume_id`),
  CONSTRAINT `FK_gfburoi2d5er3v6w64xkqlb1y` FOREIGN KEY (`volume_id`) REFERENCES `volume` (`id`),
  CONSTRAINT `FK_2i5w4btb7x3r6d2jsd213aqct` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_b3d3q413vlktogdjnnus3ep9e` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (1219,1,'Visa',674,6,2019,'Armando Garrido Castro','4498121078246142',1211,1241,1255),(1220,1,'Visa',674,6,2019,'Armando Garrido Castro','4498121078246142',1211,1242,1255),(1221,1,'Visa',674,6,2019,'Armando Garrido Castro','4498121078246142',1211,1249,NULL),(1222,1,'Visa',883,7,2019,'Jorge Puente Zaro','4067527284728593',1212,1249,NULL),(1223,1,'Visa',883,7,2019,'Jorge Puente Zaro','4067527284728593',1212,1251,NULL),(1224,1,'Panama Bank',800,2,2019,'Manuel Perez Carmona','4269520017415022',1214,1241,NULL),(1225,1,'Panama Bank',800,2,2019,'Manuel Perez Carmona','4269520017415022',1214,1242,NULL),(1226,1,'Maldivas Bank',111,2,2022,'Rafael Trujillo Pérez','4269520017415022',1215,1241,NULL);
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig`
--

DROP TABLE IF EXISTS `systemconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig`
--

LOCK TABLES `systemconfig` WRITE;
/*!40000 ALTER TABLE `systemconfig` DISABLE KEYS */;
INSERT INTO `systemconfig` VALUES (1240,0);
/*!40000 ALTER TABLE `systemconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig_taboowordss`
--

DROP TABLE IF EXISTS `systemconfig_taboowordss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig_taboowordss` (
  `SystemConfig_id` int(11) NOT NULL,
  `tabooWordss` varchar(255) DEFAULT NULL,
  KEY `FK_h9owbp8lhtwicbvkjnex7qpu1` (`SystemConfig_id`),
  CONSTRAINT `FK_h9owbp8lhtwicbvkjnex7qpu1` FOREIGN KEY (`SystemConfig_id`) REFERENCES `systemconfig` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig_taboowordss`
--

LOCK TABLES `systemconfig_taboowordss` WRITE;
/*!40000 ALTER TABLE `systemconfig_taboowordss` DISABLE KEYS */;
INSERT INTO `systemconfig_taboowordss` VALUES (1240,'sex'),(1240,'sexo'),(1240,'viagra'),(1240,'cialis');
/*!40000 ALTER TABLE `systemconfig_taboowordss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  KEY `UserUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1206,0,'Pepe','Palotes Gonzalez',1192),(1207,0,'Pablo','Picasso Pantoja',1193),(1208,0,'Julio','Iglesias Pujol',1194),(1209,0,'Carles Jose','Puig Rodriguez',1195),(1210,0,'Antonio','Espartaco Lozano',1196);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `follows_id` int(11) NOT NULL,
  `followedBy_id` int(11) NOT NULL,
  KEY `FK_qlqckc2up307vwrb3kqju18fl` (`followedBy_id`),
  KEY `FK_p9wcdbf2yc1blf0qgv5tm4ji6` (`follows_id`),
  CONSTRAINT `FK_p9wcdbf2yc1blf0qgv5tm4ji6` FOREIGN KEY (`follows_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qlqckc2up307vwrb3kqju18fl` FOREIGN KEY (`followedBy_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
INSERT INTO `user_user` VALUES (1206,1207),(1206,1208),(1207,1206),(1207,1208);
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1191,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(1192,0,'24c9e15e52afc47c225b757e7bee1f9d','user1'),(1193,0,'7e58d63b60197ceb55a1c487989a3720','user2'),(1194,0,'92877af70a45fd6a2ed7fe81e1236b78','user3'),(1195,0,'3f02ebe3d7929b091e3d8ccfde2f3bc6','user4'),(1196,0,'0a791842f52a0acfbb3a783378c066b8','user5'),(1197,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(1198,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(1199,0,'033f7f6121501ae98285ad77f216d5e7','customer3'),(1200,0,'55feb130be438e686ad6a80d12dd8f44','customer4'),(1201,0,'9e8486cdd435beda9a60806dd334d964','customer5'),(1202,0,'83a87fd756ab57199c0bb6d5e11168cb','agent1'),(1203,0,'b1a4a6b01cc297d4677c4ca6656e14d7','agent2'),(1204,0,'6f097a447415dd5030d579a9051165f0','agent3'),(1338,0,'c84258e9c39059a89ab77d846ddab909','admin2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (1191,'ADMIN'),(1192,'USER'),(1193,'USER'),(1194,'USER'),(1195,'USER'),(1196,'USER'),(1197,'CUSTOMER'),(1198,'CUSTOMER'),(1199,'CUSTOMER'),(1200,'CUSTOMER'),(1201,'CUSTOMER'),(1202,'AGENT'),(1203,'AGENT'),(1204,'AGENT'),(1338,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume`
--

DROP TABLE IF EXISTS `volume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1g852qpv1irbrshl0rmqgfm3a` (`user_id`),
  CONSTRAINT `FK_1g852qpv1irbrshl0rmqgfm3a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume`
--

LOCK TABLES `volume` WRITE;
/*!40000 ALTER TABLE `volume` DISABLE KEYS */;
INSERT INTO `volume` VALUES (1255,0,'Descripcion','Volumen 1',2016,1206),(1256,0,'Descripcion Volume 2','Volumen 2',2018,1207);
/*!40000 ALTER TABLE `volume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume_newspaper`
--

DROP TABLE IF EXISTS `volume_newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume_newspaper` (
  `volumes_id` int(11) NOT NULL,
  `newspapers_id` int(11) NOT NULL,
  KEY `FK_55de0xvt5cb2u4p2xkeofporj` (`newspapers_id`),
  KEY `FK_m83owyms7s3qmk2o1lssxkv8v` (`volumes_id`),
  CONSTRAINT `FK_m83owyms7s3qmk2o1lssxkv8v` FOREIGN KEY (`volumes_id`) REFERENCES `volume` (`id`),
  CONSTRAINT `FK_55de0xvt5cb2u4p2xkeofporj` FOREIGN KEY (`newspapers_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume_newspaper`
--

LOCK TABLES `volume_newspaper` WRITE;
/*!40000 ALTER TABLE `volume_newspaper` DISABLE KEYS */;
INSERT INTO `volume_newspaper` VALUES (1255,1242),(1255,1253),(1256,1246);
/*!40000 ALTER TABLE `volume_newspaper` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-26  1:55:17
