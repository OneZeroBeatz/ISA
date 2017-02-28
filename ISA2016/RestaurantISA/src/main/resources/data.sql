
insert into restoran(naziv, opis) values ('Restoran 1', 'Opis Restorana 1');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('R', 'rrr', 'r', 'MENRES','r', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 1'));

insert into restoran(naziv, opis) values ('Restoran 2', 'Opis Restorana 2');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('Z', 'zzz', 'z', 'MENRES','z', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 2'));


insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (11, 'segment 1', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (12, 'segment 2', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (13, 'segment 3', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (14, 'segment 4', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (15, 'segment 5', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (16, 'segment 6', false, 6, (select id from restoran where naziv='Restoran 1'));

insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar1', 'KuvriS', 'ks1', 'KUV','ks1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar2', 'KuvriK', 'kk1', 'KUV','kk1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar3', 'KuvriP', 'kp1', 'KUV','kp1', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar4', 'KuvriS', 'ks2', 'KUV','ks2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar5', 'KuvriK', 'kk2', 'KUV','kk2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, tipkuvara, logovao_se) values ('Kuvar6', 'KuvriP', 'kp2', 'KUV','kp2', 'KUVAR', (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA', false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Kono1', 'Konoric1', 'k1', 'KN','k1', 'KONOBAR', (select id from restoran where naziv='Restoran 1'), false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Kono2', 'Konoric2', 'k2', 'KN','k2', 'KONOBAR', (select id from restoran where naziv='Restoran 1'), false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Kono3', 'Konoric3', 'k3', 'KN','k3', 'KONOBAR', (select id from restoran where naziv='Restoran 1'), false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Kono4', 'Konoric4', 'k4', 'KN','k4', 'KONOBAR', (select id from restoran where naziv='Restoran 1'), false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Sano', 'Sankic', 'sa', 'SNK','sa', 'SANKER', (select id from restoran where naziv='Restoran 1'), false);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Sanos', 'Sas', 'ss', 'SNK','ss', 'SANKER', (select id from restoran where naziv='Restoran 1'), false);


insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Pera', 'Peric', 'pera123', 'G','pera@pera.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Pera', 'Peric', 'p', 'G','p', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Sima', 'Simic', 'sima123', 'G','sima@sima.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Djura', 'Djuric', 'djura123', 'G','djura@djura.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Verka', 'Verkic', 'verka123', 'G','verka@verka.com', 'GOST', true, true);

insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni, logovao_se) values ('Kero', 'Kerica', 's', 'MENSIS','s', 'MENADZER_SISTEMA', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni, logovao_se) values ('Kero1', 'Kerica1', 'f', 'MENSIS','f', 'MENADZER_SISTEMA', false, true);


insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), true, false, true, 3, (select id from korisnik where email='k1'), true);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), true, false, true, 4, (select id from korisnik where email='k1'), true);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), true, false, true, 5, (select id from korisnik where email='k1'), true);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), true, false, true, 4, (select id from korisnik where email='k1'), false);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), true, false, true, 2, (select id from korisnik where email='k1'), false);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), true, false, true, 1, (select id from korisnik where email='k1'), true);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), true, false, true, 1, (select id from korisnik where email='k2'), true);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id, konobar_id, porudzbina_prihvacena) values ("2017-02-28 09:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), true, false, true, 3, (select id from korisnik where email='k2'), false);


insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 1', 'Opis Jela 1', 200, (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 2', 'Opis Jela 2', 250, (select id from restoran where naziv='Restoran 1'), 'ZA_SALATE');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 3', 'Opis Jela 3', 300, (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 4', 'Opis Jela 4', 250, (select id from restoran where naziv='Restoran 1'), 'ZA_KUVANA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 5', 'Opis Jela 5', 200, (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');
insert into jelo(naziv, opis, cena, restoran_id, tipkuvara) values ('Jelo 6', 'Opis Jela 6', 350, (select id from restoran where naziv='Restoran 1'), 'ZA_PECENA_JELA');

insert into pice(naziv, opis, cena, restoran_id) values ('Pice 1', 'Opis Pica 1', 150, (select id from restoran where naziv='Restoran 1'));
insert into pice(naziv, opis, cena, restoran_id) values ('Pice 2', 'Opis Pica 2', 159, (select id from restoran where naziv='Restoran 1'));
insert into pice(naziv, opis, cena, restoran_id) values ('Pice 3', 'Opis Pica 3', 159, (select id from restoran where naziv='Restoran 1'));

insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (2,1,1);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (3,2,1);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (8,3,2);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (2,4,1);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (3,5,1);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (8,6,2);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (2,7,3);
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (3,8,1);


insert into smena(vremeod, vremedo, restoran_id) values ('07:00', '12:00', (select id from restoran where naziv='Restoran 1'));
insert into smena(vremeod, vremedo, restoran_id) values ('10:00', '23:00', (select id from restoran where naziv='Restoran 1'));

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
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenadan_sto (smenaudanu_id, sto_id) values (2, (select id from sto where oznaka = 11));
insert into smenadan_sto (smenaudanu_id, sto_id) values (2, (select id from sto where oznaka = 12));
insert into smenadan_sto (smenaudanu_id, sto_id) values (2, (select id from sto where oznaka = 13));
insert into smenadan_sto (smenaudanu_id, sto_id) values (2, (select id from sto where oznaka = 14));
insert into smenadan_sto (smenaudanu_id, sto_id) values (9, (select id from sto where oznaka = 12));
insert into smenadan_sto (smenaudanu_id, sto_id) values (9, (select id from sto where oznaka = 13));

-- SMENA SANKERA
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));



insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (16,1,true, -1, '2017-02-28 08:00:00', 10, 1, -1, -1);
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (16,1,true, -1, '2017-02-28 08:00:00', 9, 2, -1, -1);
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (15,1,false, -1, '2017-02-28 08:00:00', 2, 3, -1, -1);
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (16,1,true, -1, '2017-02-28 08:30:00', 7, 4, -1, -1);
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (16,2,true, -1, '2017-02-28 08:30:00', 2, 5, -1, -1);
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge) values (15,2,true, -1, '2017-02-28 11:30:00', 5, 3, -1, -1);

 
 

 