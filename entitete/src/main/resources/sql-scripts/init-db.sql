INSERT INTO uporabniki(ime, priimek, uporabnisko_ime, email) VALUES ('Mila', 'Marinkovic', 'mmilsss', 'mm9136@student.uni-lj.si');
INSERT INTO uporabniki (ime, priimek, uporabnisko_ime, email) VALUES ('Tamara', 'Trajkovska', 't.trajkovska', 'tt2834@student.uni-lj.si');

INSERT INTO nakupovalni_seznam (naziv, opis, ustvarjen, uporabnik_id) VALUES ('Nakup Oblačila', 'jesenska oblacila', '2020-06-10 14:53:01', 1);
INSERT INTO nakupovalni_seznam (naziv, opis, ustvarjen, uporabnik_id) VALUES ('Nakup Hrane', 'kupljeni izdelki na popustu', '2020-06-10 14:54:03', 2);

INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Majice', 'zenske oblacila', 1);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Teniske', 'zenske obutev', 1);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Hlače', 'zenske oblacila', 1);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Topi', 'zenske oblacila', 1);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Salonarji', 'zenske obutev', 1);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Solata', 'Zelenjava', 2);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Krof', 'Sladko', 2);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Kumare', 'Zelenjava', 2);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Mandarine', 'Sadje', 2);
INSERT INTO artikel (naziv, opis, nakupovalni_seznam_id) VALUES ('Baklava', 'Sladko', 2);

INSERT INTO oznaka (ime, opis) VALUES ('Zenske oblacila', 'majice, jopice, legice, srajce,...');
INSERT INTO oznaka (ime, opis) VALUES ('Zenske obutev', 'salonarji, teniske, skornji, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Moske oblacila', 'kavbojke,hlace,puloverji, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Moske obutev', 'gleznjari,natikaci, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Zenske dodatki', 'Torbe, Nakit, Klobuki, Kape, rokavice, ...');

INSERT INTO oznaka (ime, opis) VALUES ('Sladko', 'krof, cokolada, baklava, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Sadje', 'mandarine, borovnica, orehi, breskev, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Zelenjava', 'kumare, solate, paprika, paradiznik, cvetaca, ...');
INSERT INTO oznaka (ime, opis) VALUES ('Testenine', 'makarone');

INSERT INTO nakupovalni_seznam_oznaka (nakupovalni_seznam_id, oznaka_id) VALUES (1, 1);
INSERT INTO nakupovalni_seznam_oznaka (nakupovalni_seznam_id, oznaka_id) VALUES (1, 2);
INSERT INTO nakupovalni_seznam_oznaka (nakupovalni_seznam_id, oznaka_id) VALUES (2, 6);
INSERT INTO nakupovalni_seznam_oznaka (nakupovalni_seznam_id, oznaka_id) VALUES (2, 7);
INSERT INTO nakupovalni_seznam_oznaka (nakupovalni_seznam_id, oznaka_id) VALUES (2, 8);

