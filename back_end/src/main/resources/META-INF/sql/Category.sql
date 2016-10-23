-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               PostgreSQL 9.5.4, compiled by Visual C++ build 1800, 64-bit
-- Server OS:                    
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table public.Category
CREATE TABLE IF NOT EXISTS Category (
	categoryId bigserial,
	Name CHARACTER VARYING(50) NULL,
	PRIMARY KEY (categoryId)
);

-- Dumping data for table public.Category: 9 rows
/*!40000 ALTER TABLE Category DISABLE KEYS */;
INSERT INTO Category (Name, categoryId) VALUES(E'Keyboard', 1), (E'Mouse', 2), (E'Displays', 3), (E'Board', 4), (E'Network', 5), (E'Storage', 6), (E'Tablets', 7), (E'Laptops', 8), (E'Computers', 9); /*!40000 ALTER TABLE Category ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
