-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server versie:                PostgreSQL 9.6rc1, compiled by Visual C++ build 1800, 64-bit
-- Server OS:                    
-- HeidiSQL Versie:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Structuur van  tabel public.Customers wordt geschreven
CREATE TABLE IF NOT EXISTS Customer (
	id bigserial,
	Name CHARACTER VARYING(255),
	Adres CHARACTER VARYING(255),
	PostalCode CHARACTER VARYING(10),
	City CHARACTER VARYING(255),
	Tel NUMERIC(10,0),
	Fax NUMERIC(10,0),
	Number CHARACTER VARYING(10),
	PRIMARY KEY (id)
);

-- Dumpen data van tabel public.Customers: 41 rows
/*!40000 ALTER TABLE "Customers" DISABLE KEYS */;
INSERT INTO Customer (id, Name, Adres, PostalCode, City, Tel, Fax, Number) VALUES (1, E'Kantoor Snel Transport / Distributiecentrum', E'Zeugstraat', E'2801JD', E'Gouda', 182512784, NULL, E'92'), (2, E'De Concurrent', E'Jacob van Lennepstraat', E'1053HL', E'Amsterdam', 203456456, NULL, E'46'), (3, E'E-h@ven', E'Kerkstraat', E'6233BK', E'Maastricht', 433632110, NULL, E'16'), (4, E'ByteSystems', E'Lambertus Buddestraat', E'7521SB', E'Enschede', 534343311, NULL, E'70'), (5, E'E-note', E'Groningerweg', E'9738AB', E'Groningen', 505742600, NULL, E'45/2'), (6, E'St@lker', E'Blauwkapelseweg', E'3572KC', E'Utrecht', 302713291, NULL, E'77b'), (7, E'Elbers ICT', E'Hommelseweg', E'6821LG', E'Arnhem', 264434680, NULL, E'231'), (9, E'West-IT', E'Zeugstraat', E'2801JC', E'Gouda', 182516393, 182580153, E'42'), (10, E'IT Works', E'Energieweg', E'1785AD', E'Den Helder', 223630204, 223638027, E'6'), (11, E'@pple IT', E'St Domusstraat', E'4301CM', E'Zierikzee ', 111413395, 111421851, E'43'), (12, E'IT Force', E'Mortiereboulevaard', E'4336RA', E'Middelburg ', 118650382, 118613909, E'8'), (13, E'Tablet & Co', E'Spinveld', E'4815HS', E'Breda', 765207080, 765207081, E'14'), (15, E'Tablet & Co', E'Lodewykstraat', E'5652AC', E'Eindhoven ', 402572700, 402524712, E'16'), (16, E'IT4All', E'Ivoorstraat', E'1812RE', E'Alkmaar', 725411820, 725411825, E'19'), (17, E'PC-markt Almere', E'Koningsbeltweg', E'1329AE', E'Almere', 365485080, 365485085, E'61'), (18, E'PC-markt Amersfoort', E'Nijverheidsweg-Noord', E'3812PM ', E'Amersfoort', 334650111, 334657172, E'44'), (20, E'Sloterdijk Gyro-IT', E'Gyroscoopweg', E'1042AZ', E'Amsterdam', 204480484, 206134525, E'142'), (21, E'Kerketuinen Computers', E'Zilverstraat', E'2544EL', E's\'Gravenhage', 703669914, 703674158, E'126'), (22, E'Eindhoven IT centrum', E'Lodewijkstraat', E'5652AC', E'Eindhoven', 402572700, 402524712, E'16'), (23, E'@pple IT', E'Lambertus Buddestraat', E'7521SB', E'Enschede', 534343311, 534329364, E'70'), (24, E'Tablet & Co', E'Marconistraat', E'2809PH', E'Gouda', 182686890, 182686899, E'27'), (25, E'IT hunter', E'Bornholmstraat', E'9723AX', E'Groningen', 503187000, 503181980, E'38'), (26, E'Professional ICT', E'A Hofmanwg', E'2031BH', E'Haarlem', 235530330, 235530349, E'3a'), (27, E'IT Vossenbeemd', E'Vossenbeemd', E'5705CL', E'Helmond', 492509310, 492509319, E'102'), (28, E'DynaByte Hilversum', E'Nieuwe Havenweg', E'1216BP', E'Hilversum', 355289250, 356319076, E'89'), (29, E'ICT shop Hoensbroek', E'Industriestraat', E'6433JX', E'Hoensbroek', 455223600, 455226364, E'2a'), (30, E'ICT shop Katwijk', E'Mandenmakerstraat', E'2222AX', E'Katwijk', 714097272, 714086411, E'4'), (31, E'Kramer ICT', E'Simon Vestdijkwei', E'8914AX', E'Leeuwarden', 582340900, 582340909, E'1'), (32, E'VelocITy', E'Draadbaan', E'2352AW', E'Leiderdorp', 715813310, 715813320, E'10'), (33, E'CoolIT', E'Middenhoven', E'6042NX', E'Roermond', 475316253, 475334495, E'3'), (34, E'Notebook4You', E'Boterdiep', E'3077AW', E'Rotterdam', 104825353, 104822742, E'28'), (19, E'Amsterdam-Amstel ICT shop', E'Daniël Goedkoopstraat', E'1096BD', E'Amsterdam', 206946767, 206685649, E'31'), (14, E'Formidoos Eindhoven', E'Hoogstraat', E'5615PV', E'Eindhoven ', 402519497, 402573491, E'154-160'), (35, E'Intern@t', E'Philippusweg', E'3125AS', E'Schiedam', 104375311, 104158160, E'5'), (36, E'ICT centrum Sittard', E'Doctor Nolenslaan', E'6136GM', E'Sittard', 464584750, 464585202, E'11'), (37, E'ICT centrum Tilburg', E'Ceramstraat', E'5013BB', E'Tilburg', 135457830, 135452853, E'3'), (38, E'E-learning & IT Centre Utrecht', E'Sint Laurensdreef', E'3565AK', E'Utrecht', 302614161, 302610871, E'810'), (39, E'DynaByte', E'Wittevrouwensingel', E'3514AL', E'Utrecht', 302721406, 302760719, E'95A'), (40, E'IT Zaanstad', E'Roode Wildemanweg', E'1521PT', E'Wormerveer', 756470760, 756470769, E'2'), (41, E'IT Zoetermeer', E'Koperstraat', E'2718RG', E'Zoetermeer', 793634141, 793634140, E'3'), (8, E'IT Pro\'s', E'Admiraal de Ruijterweg', E'1057JX', E'Amsterdam', 206162945, NULL, E'65'); /*!40000 ALTER TABLE "Customers" ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
