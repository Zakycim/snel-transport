/* Insert data into Customer table */
INSERT INTO Customer (id, Name, Adres, PostalCode, City, Tel, Fax, Number) VALUES (1, E'Kantoor Snel Transport / Distributiecentrum', E'Zeugstraat', E'2801JD', E'Gouda', 182512784, NULL, E'92'), (2, E'De Concurrent', E'Jacob van Lennepstraat', E'1053HL', E'Amsterdam', 203456456, NULL, E'46'), (3, E'E-h@ven', E'Kerkstraat', E'6233BK', E'Maastricht', 433632110, NULL, E'16'), (4, E'ByteSystems', E'Lambertus Buddestraat', E'7521SB', E'Enschede', 534343311, NULL, E'70'), (5, E'E-note', E'Groningerweg', E'9738AB', E'Groningen', 505742600, NULL, E'45/2'), (6, E'St@lker', E'Blauwkapelseweg', E'3572KC', E'Utrecht', 302713291, NULL, E'77b'), (7, E'Elbers ICT', E'Hommelseweg', E'6821LG', E'Arnhem', 264434680, NULL, E'231'), (9, E'West-IT', E'Zeugstraat', E'2801JC', E'Gouda', 182516393, 182580153, E'42'), (10, E'IT Works', E'Energieweg', E'1785AD', E'Den Helder', 223630204, 223638027, E'6'), (11, E'@pple IT', E'St Domusstraat', E'4301CM', E'Zierikzee ', 111413395, 111421851, E'43'), (12, E'IT Force', E'Mortiereboulevaard', E'4336RA', E'Middelburg ', 118650382, 118613909, E'8'), (13, E'Tablet & Co', E'Spinveld', E'4815HS', E'Breda', 765207080, 765207081, E'14'), (15, E'Tablet & Co', E'Lodewykstraat', E'5652AC', E'Eindhoven ', 402572700, 402524712, E'16'), (16, E'IT4All', E'Ivoorstraat', E'1812RE', E'Alkmaar', 725411820, 725411825, E'19'), (17, E'PC-markt Almere', E'Koningsbeltweg', E'1329AE', E'Almere', 365485080, 365485085, E'61'), (18, E'PC-markt Amersfoort', E'Nijverheidsweg-Noord', E'3812PM ', E'Amersfoort', 334650111, 334657172, E'44'), (20, E'Sloterdijk Gyro-IT', E'Gyroscoopweg', E'1042AZ', E'Amsterdam', 204480484, 206134525, E'142'), (21, E'Kerketuinen Computers', E'Zilverstraat', E'2544EL', E's\'Gravenhage', 703669914, 703674158, E'126'), (22, E'Eindhoven IT centrum', E'Lodewijkstraat', E'5652AC', E'Eindhoven', 402572700, 402524712, E'16'), (23, E'@pple IT', E'Lambertus Buddestraat', E'7521SB', E'Enschede', 534343311, 534329364, E'70'), (24, E'Tablet & Co', E'Marconistraat', E'2809PH', E'Gouda', 182686890, 182686899, E'27'), (25, E'IT hunter', E'Bornholmstraat', E'9723AX', E'Groningen', 503187000, 503181980, E'38'), (26, E'Professional ICT', E'A Hofmanwg', E'2031BH', E'Haarlem', 235530330, 235530349, E'3a'), (27, E'IT Vossenbeemd', E'Vossenbeemd', E'5705CL', E'Helmond', 492509310, 492509319, E'102'), (28, E'DynaByte Hilversum', E'Nieuwe Havenweg', E'1216BP', E'Hilversum', 355289250, 356319076, E'89'), (29, E'ICT shop Hoensbroek', E'Industriestraat', E'6433JX', E'Hoensbroek', 455223600, 455226364, E'2a'), (30, E'ICT shop Katwijk', E'Mandenmakerstraat', E'2222AX', E'Katwijk', 714097272, 714086411, E'4'), (31, E'Kramer ICT', E'Simon Vestdijkwei', E'8914AX', E'Leeuwarden', 582340900, 582340909, E'1'), (32, E'VelocITy', E'Draadbaan', E'2352AW', E'Leiderdorp', 715813310, 715813320, E'10'), (33, E'CoolIT', E'Middenhoven', E'6042NX', E'Roermond', 475316253, 475334495, E'3'), (34, E'Notebook4You', E'Boterdiep', E'3077AW', E'Rotterdam', 104825353, 104822742, E'28'), (19, E'Amsterdam-Amstel ICT shop', E'Daniël Goedkoopstraat', E'1096BD', E'Amsterdam', 206946767, 206685649, E'31'), (14, E'Formidoos Eindhoven', E'Hoogstraat', E'5615PV', E'Eindhoven ', 402519497, 402573491, E'154-160'), (35, E'Intern@t', E'Philippusweg', E'3125AS', E'Schiedam', 104375311, 104158160, E'5'), (36, E'ICT centrum Sittard', E'Doctor Nolenslaan', E'6136GM', E'Sittard', 464584750, 464585202, E'11'), (37, E'ICT centrum Tilburg', E'Ceramstraat', E'5013BB', E'Tilburg', 135457830, 135452853, E'3'), (38, E'E-learning & IT Centre Utrecht', E'Sint Laurensdreef', E'3565AK', E'Utrecht', 302614161, 302610871, E'810'), (39, E'DynaByte', E'Wittevrouwensingel', E'3514AL', E'Utrecht', 302721406, 302760719, E'95A'), (40, E'IT Zaanstad', E'Roode Wildemanweg', E'1521PT', E'Wormerveer', 756470760, 756470769, E'2'), (41, E'IT Zoetermeer', E'Koperstraat', E'2718RG', E'Zoetermeer', 793634141, 793634140, E'3'), (8, E'IT Pro\'s', E'Admiraal de Ruijterweg', E'1057JX', E'Amsterdam', 206162945, NULL, E'65'); /*!40000 ALTER TABLE "Customers" ENABLE KEYS */;

/* Insert data into Category table */
INSERT INTO Category (Name, categoryId) VALUES(E'Keyboard', 1), (E'Mouse', 2), (E'Displays', 3), (E'Board', 4), (E'Network', 5), (E'Storage', 6), (E'Tablets', 7), (E'Laptops', 8), (E'Computers', 9); /*!40000 ALTER TABLE Category ENABLE KEYS */;

/* Insert data into Product table */
INSERT INTO Product (Name, Code, Price, CategoryID, id) VALUES(E'Asus U2000 Keyboard + Mouse Set', E'90-XB1000KM00020', 16.45, 1, 1), (E'Cherry Compact Keyboard - G84-4420 - USB/Black/Trackball - QWERTY', E'G84-4420LUBEU-2', 100.25, 1, 4), (E'Cherry Compact Keyboard - G84-4420 - USB/White/Trackball - QWERTY', E'G84-4420LUBEU-0', 100.25, 1, 5), (E'Cherry Keyboard - DC 2000 Corded Desktop Zwart - QWERTY', E'JD-0800EU-2', 18.05, 1, 6), (E'Apple Mouse - USB', E'MB112ZM/C', 56.30, 2, 7), (E'Asus Mouse Cerberus Gaming - USB', E'90YH00Q1-BAUA00', 34.35, 2, 8), (E'Asus Mouse Echelon Laser Gaming - USB', E'90YH0051-BBUA00', 49.55, 2, 9), (E'Asus Mouse GX860 Gaming - USB', E'90XB02C0-BMU000', 59.25, 2, 10), (E'Asus Mouse ROG Gladius Gaming - USB', E'90MP0081-B0UA00', 72.15, 2, 11), (E'Asus Mouse ROG Sica Gaming - USB', E'90MP00B1-B0UA00', 48.85, 2, 12), (E'Asus Mouse Strix Claw Dark Edition Gaming - USB', E'90YH00C2-BAUA00', 63.10, 2, 13), (E'Asus 15.6 inch IPS LED Monitor MB169B+', E'90LM0183-B01170', 217.95, 3, 14), (E'Asus 15.6 inch IPS LED Monitor MB169C+', E'90LM0180-B01170', 258.95, 3, 15), (E'IIyama 15 inch Touchscreen Monitor ProLite T1521MSC-B1', E'T1521MSC-B1', 377.55, 3, 16), (E'IIyama 15 inch Touchscreen Monitor ProLite T1531SAW-B1', E'T1531SAW-B1', 441.65, 3, 17), (E'ASRock mATX MB - AMD - QC5000M', E'90-MXGYU0-A0UAYZ', 57.90, 4, 18), (E'ASRock mATX MB - Intel N3050M', E'90-MXGZ20-A0UAYZ', 63.35, 4, 19), (E'ASRock mATX MB - Intel N3150M', E'90-MXGZD0-A0UAYZ', 78.50, 4, 20), (E'ASRock mATX MB - Intel N3700M', E'90-MXGXX0-A0UAYZ', 101.45, 4, 21), (E'ASRock mini-ITX MB - AMD - QC5000M-ITX/PH', E'90-MXGYS0-A0UAYZ', 65.35, 4, 22), (E'Delock PCI Card 1 x LAN 10/100 Mb/s', E'88316', 7.65, 5, 23), (E'Edimax Gigabit Ethernet Netwerkadapter', E'EN-9235TX-32 V2', 12.05, 5, 24), (E'Intel PRO/1000 GT Gigabit Network Adapter (PCI) Bulk', E'PWLA8391GTBLK', 27.25, 5, 25), (E'Asus USB-N14 Wireless N300 Adapter (USB)', E'90IG0120-BM0000', 25.75, 5, 26), (E'Asus USB-N53 Wireless-N600 Dual Band Adapter Ver A1 (USB)', E'USB-N53', 30.20, 5, 27), (E'D-Link DWA-121 Wireless N150 Adapter (USB)', E'DWA-121', 13.50, 5, 28), (E'D-Link NAS - DNS-327L - ShareCenter+ 2-Bay Cloud Network Storage Enclosure', E'DNS-327L', 109.55, 6, 29), (E'Fantec NAS - CL-35B2 RAID Cloud NAS Server', E'1558', 108.65, 6, 30), (E'Fantec NAS - MWiD25 Mobile WiFi Disk', E'1636', 52.15, 6, 31), (E'Kingston NAS - MobileLite Wireless Flash Reader', E'MLW221', 27.75, 6, 32), (E'Delock SDHC Card - 32 GB - Class 10', E'55745', 14.95, 6, 33), (E'Kingston SDHC Card - 16 GB - Class 10 UHS-I', E'SDA10/16GB', 9.55, 6, 34), (E'Kingston SDHC Card - 16 GB - Class 10 UHS-I V2', E'SD10VG2/16GB', 7.25, 6, 35), (E'Kingston SDHC Card - 16 GB - Class 4', E'SD4/16GB', 6.35, 6, 36), (E'Kingston SDHC Card - 16 GB - UHS-I Speed Class 3 (U3)', E'SDA3/16GB', 19.95, 6, 37), (E'Kingston SDHC Card - 32 GB - Class 10 UHS-I', E'SDA10/32GB', 16.95, 6, 38), (E'Kingston SDHC Card - 32 GB - Class 10 UHS-I V2', E'SD10VG2/32GB', 10.95, 6, 39), (E'Kingston SDHC Card - 32 GB - Class 4', E'SD4/32GB', 16.55, 6, 40), (E'Kingston SDHC Card - 32 GB - UHS-I Speed Class 3 (U3)', E'SDA3/32GB', 16.85, 6, 41), (E'Kingston SDHC Card - 8 GB - Class 4', E'SD4/8GB', 4.85, 6, 42), (E'Lexar Platinum II 300x SDHC Card - 16 GB - Class 10 UHS-I', E'LSD16GBBEU300', 10.25, 6, 43), (E'Apple iPad Air 2 Wi-Fi - 16 GB - Silver', E'MGLW2FD/A', 424.95, 7, 44), (E'Samsung Galaxy Tab E - Wi-Fi - 9.6 inch - 8 GB - Zwart', E'SM-T560NZKAPHN', 170.05, 7, 45), (E'Hannspree Tab HANNSPAD W72B - 10.1i - Wi-Fi - 3G - 8 GB - Zwart', E'SN1AW72B2E', 189.10, 7, 46), (E'Samsung Galaxy Tab A - Wi-Fi - 9.7i - 16 GB Zwart', E'SM-T550NZKADBT', 221.70, 7, 47), (E'Samsung Galaxy Tab A - Wi-Fi - 9.7i - 16 GB Wit', E'SM-T550NZWADBT', 221.70, 7, 48), (E'Samsung Galaxy Tab A - Wi-Fi + 4G - 9.7i - 16 GB - Zwart', E'SM-T555NZKADBT', 277.75, 7, 49), (E'Apple iPad mini Retina WiFi 16GB Space Gray', E'ME276FD/A', 294.60, 7, 50), (E'Apple iPad mini 3 Wi-Fi + 4G - 16 GB - Silver', E'MGHW2FD/A', 353.80, 7, 51), (E'Apple 13.3-inch MacBook Pro [Intel Core-i5 - 4GB - 500GB - HD4000 - MAC/US Layout - 13.3inch]', E'MD101US/A', 1123.50, 8, 52), (E'Apple MacBook Pro Retina 39cm (15.4 inch) [Core-i7 2.5GHz - 16GB - 512SSD - R9M370X - US]', E'MJLT2LL/A', 2642.05, 8, 53), (E'Apple 27-inch iMac met Retina 5K-display [Intel Core i5-3.2 GHz - 8 GB - 1 TB - AMD Radeon R9 M380 2GB - US Layout]', E'MK462LL/A', 2049.95, 9, 54), (E'Apple 27-inch iMac met Retina 5K-Display [Intel Core i5-3.3 GHz - 8 GB - 2 TB Fusion Drive - AMD Radeon R9 M395 2GB - US Layout]', E'MK482LL/A', 2441.35, 9, 55), (E'Asus W3000 Desktop - Draadloos - Wit - Qwerty', E'90-XB2400KM00130', 24.10, 1, 2), (E'Asus W3000 Desktop - Draadloos - Zwart - Qwerty', E'90-XB2400KM00030', 24.10, 1, 3); /*!40000 ALTER TABLE Product ENABLE KEYS */;

/* Insert data into Status table */
INSERT INTO "status" ("id", "name") VALUES (1, E'In behandeling'), (2, E'Verzonden'), (3, E'Afgeleverd'), (4, E'Geannuleerd'); /*!40000 ALTER TABLE "status" ENABLE KEYS */;
