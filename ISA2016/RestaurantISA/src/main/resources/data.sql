insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Pera', 'Peric', 'p', 'G','p', 'GOST');
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Pera', 'Peric', 'pera123', 'G','pera@pera.com', 'GOST');
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Sima', 'Simic', 'sima123', 'G','sima@sima.com', 'GOST');
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Djura', 'Djuric', 'djura123', 'G','djura@djura.com', 'GOST');
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Verka', 'Verkic', 'verka123', 'G','verka@verka.com', 'GOST');

insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni) values ('Kero', 'Kerica', 's', 'MENSIS','s', 'MENADZER_SISTEMA', true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni) values ('Kero1', 'Kerica1', 'f', 'MENSIS','f', 'MENADZER_SISTEMA', false);

insert into restoran(naziv, opis) values ('Restoran 1', 'Opis Restorana 1');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('R', 'rrr', 'r', 'MENRES','r', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 1'));

insert into restoran(naziv, opis) values ('Restoran 2', 'Opis Restorana 2');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Z', 'zzz', 'z', 'MENRES','z', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 2'));


--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (11, 'segment 1', false, 6, (select id from restoran where naziv='Restoran 1'));
--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (12, 'segment 2', false, 6, (select id from restoran where naziv='Restoran 1'));
--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (13, 'segment 3', false, 6, (select id from restoran where naziv='Restoran 1'));
--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (14, 'segment 4', false, 6, (select id from restoran where naziv='Restoran 1'));
--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (15, 'segment 5', false, 6, (select id from restoran where naziv='Restoran 1'));
--insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (16, 'segment 6', false, 6, (select id from restoran where naziv='Restoran 1'));

--insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id) values ("2014-01-12 11:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), false, false, false, 1);
--insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id) values ("2014-02-12 11:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), false, false, false, 2);


insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 1', 'Opis Jela 1', 200, (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 2', 'Opis Jela 2', 250, (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 3', 'Opis Jela 3', 300, (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 4', 'Opis Jela 4', 250, (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 5', 'Opis Jela 5', 200, (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 6', 'Opis Jela 6', 350, (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');

insert into pice(naziv, opis, cena, restoran_id) values ('Pice 1', 'Opis Pica 1', 150, (select id from restoran where naziv='Restoran 1'));
insert into pice(naziv, opis, cena, restoran_id) values ('Pice 2', 'Opis Pica 2', 159, (select id from restoran where naziv='Restoran 1'));
insert into pice(naziv, opis, cena, restoran_id) values ('Pice 3', 'Opis Pica 3', 159, (select id from restoran where naziv='Restoran 1'));


insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar1', 'KuvriS', 'ks1', 'KUV','ks1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar2', 'KuvriK', 'kk1', 'KUV','kk1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar3', 'KuvriP', 'kp1', 'KUV','kp1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar4', 'KuvriS', 'ks2', 'KUV','ks2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar5', 'KuvriK', 'kk2', 'KUV','kk2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara) values ('Kuvar6', 'KuvriP', 'kp2', 'KUV','kp2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Kono1', 'Konoric1', 'k1', 'KN','k1', 'KONOBAR', (select id from restoran where naziv='Restoran 1'));
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Kono2', 'Konoric2', 'k2', 'KN','k2', 'KONOBAR', (select id from restoran where naziv='Restoran 1'));
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Kono3', 'Konoric3', 'k3', 'KN','k3', 'KONOBAR', (select id from restoran where naziv='Restoran 1'));
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Kono4', 'Konoric4', 'k4', 'KN','k4', 'KONOBAR', (select id from restoran where naziv='Restoran 1'));
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Sano', 'Sankic', 'sa', 'SNK','sa', 'SANKER', (select id from restoran where naziv='Restoran 1'));
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Sanos', 'Sas', 'ss', 'SNK','ss', 'SANKER', (select id from restoran where naziv='Restoran 1'));

insert into smena(vremeod, vremedo, restoran_id) values ('07:00', '15:00', (select id from restoran where naziv='Restoran 1'));
insert into smena(vremeod, vremedo, restoran_id) values ('15:00', '23:00', (select id from restoran where naziv='Restoran 1'));

--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kuvar1'), 2, (select id from restoran where naziv = 'Restoran 1'));

--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kuvar2'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kuvar2'), 2, (select id from restoran where naziv = 'Restoran 1'));


-- SMENA KONOBARA
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));

--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));

--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));

--insert into smenadan_sto (smenaudanu_id, sto_id) values (14, (select id from sto where oznaka = 11));
--insert into smenadan_sto (smenaudanu_id, sto_id) values (14, (select id from sto where oznaka = 12));
--insert into smenadan_sto (smenaudanu_id, sto_id) values (14, (select id from sto where oznaka = 13));
--insert into smenadan_sto (smenaudanu_id, sto_id) values (14, (select id from sto where oznaka = 14));
--insert into smenadan_sto (smenaudanu_id, sto_id) values (21, (select id from sto where oznaka = 12));
--insert into smenadan_sto (smenaudanu_id, sto_id) values (21, (select id from sto where oznaka = 13));

-- SMENA SANKERA
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
--insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));



--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,1,true, -1, '2018-01-12 11:39:16', 1, 1);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (2,1,true, -1, '2013-01-12 11:39:51', 3, 2);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,1,false, -1, '2015-01-12 11:39:41', 5, 3);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,1,true, -1, '2013-01-12 11:39:11', 2, 4);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,2,true, -1, '2017-01-12 11:59:41', 1, 5);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (2,2,true, -1, '2013-01-12 11:32:11', 5, 6);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,2,false, -1, '2011-01-12 11:49:11', 7, 4);
--insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id) values (1,1,true, -1, '2014-01-12 11:59:11', 8, 3);
 
 

 