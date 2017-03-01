insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Pera', 'Peric', 'pera123', 'G','pera@pera.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Sima', 'Simic', 'sima123', 'G','sima@sima.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Djura', 'Djuric', 'djura123', 'G','djura@djura.com', 'GOST', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, is_activated, logovao_se) values ('Verka', 'Verkic', 'verka123', 'G','verka@verka.com', 'GOST', true, true);

--insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Aca', 'aaa', 'a', 'PN','a@a.a', 'PONUDJAC');
--insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('B', 'bbb', 'b', 'PN','b', 'PONUDJAC');

--insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('C', 'ccc', 'c', 'MENRES','c@c.c', 'MENADZER_RESTRORANA');
--insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('D', 'ddddd', 'd', 'MENRES','d', 'MENADZER_RESTRORANA');

insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni, logovao_se) values ('Kero', 'Kerica', 's', 'MENSIS','s', 'MENADZER_SISTEMA', true, true);
insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni, logovao_se) values ('Kero1', 'Kerica1', 'f', 'MENSIS','f', 'MENADZER_SISTEMA', false, true);

insert into restoran(naziv, opis) values ('Restoran 1', 'Opis Restorana 1');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('R', 'rrr', 'r', 'MENRES','r', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 1'), true);

insert into restoran(naziv, opis) values ('Restoran 2', 'Opis Restorana 2');
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id, logovao_se) values ('Z', 'zzz', 'z', 'MENRES','z', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 2'), true);


insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (11, 'segment 1', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (12, 'segment 2', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (13, 'segment 3', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (13, 'segment 4', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (12, 'segment 5', false, 6, (select id from restoran where naziv='Restoran 1'));
insert into sto (oznaka, segment, zauzetost, brojmesta, restoran_id) values (21, 'segment 6', false, 6, (select id from restoran where naziv='Restoran 1'));

insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id) values ("2014-01-12 11:39:11", "2014-01-12 11:42:11", (select id from restoran where naziv='Restoran 1'), false, false, false, 1);
insert into porudzbina(vremeprimanja, vremenaplate, restoran_id, spremna_jela, spremna_pica, spremno_bar_jedno_jelo, sto_id) values ("2014-02-12 11:49:34", "2014-01-12 11:45:12", (select id from restoran where naziv='Restoran 1'), false, false, false, 2);

--insert into pice(naziv, porudzbinamenadzer_id) values ('Pice 1', (select id from porudzbina_menadzer where od='2015-01-01' and do='2016-01-03'));



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
insert into piceuporudzbini(kolicina, porudzbina_id, pice_id) values (8,2,2);
 
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (1,1,1, false);
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (2,1,2, false);
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (3,1,3, false);
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (4,2,4, false);
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (5,2,5, false);
insert into jelouporudzbini(kolicina, porudzbina_id, jelo_id, spremno) values (6,2,6, false);




insert into lista_prijatelja(email_gosta, email_prijatelja) values ('pera@pera.com', 'sima@sima.com');
insert into lista_prijatelja(email_gosta, email_prijatelja) values ('pera@pera.com', 'djura@djura.com');
insert into lista_prijatelja(email_gosta, email_prijatelja) values ('sima@sima.com', 'pera@pera.com');
insert into lista_prijatelja(email_gosta, email_prijatelja) values ('djura@djura.com', 'pera@pera.com');
insert into lista_prijatelja(email_gosta, email_prijatelja) values ('djura@djura.com', 'verka@verka.com');
insert into lista_prijatelja(email_gosta, email_prijatelja) values ('verka@verka.com', 'djura@djura.com');

insert into zahtev_za_prijateljstvo(email_gosta, email_prijatelja) values ('pera@pera.com', 'verka@verka.com');

--insert into ponuda(cena, rok_isporuke, garancija, ponudjac_id) values (50, 5, 'garan123', (select id from korisnik where sifra='b' and email='b'));
--insert into ponuda(cena, rok_isporuke, garancija, ponudjac_id) values (25, 3, 'garan1', (select id from korisnik where sifra='b' and email='b'));

--insert into porudzbina_menadzer(od, do, menadzerrestorana_id) values ('2015-01-01', '2016-01-03', (select id from korisnik where sifra='d' and email='d'));
--insert into porudzbina_menadzer(od, do, menadzerrestorana_id) values ('2005-06-03', '2016-08-03', (select id from korisnik where sifra='d' and email='d'));

--insert into namirnica(naziv, porudzbinamenadzer_id) values ('Nam 1', (select id from porudzbina_menadzer where od='2015-01-01' and do='2016-01-03'));
--insert into namirnica(naziv, porudzbinamenadzer_id) values ('Nam 2', (select id from porudzbina_menadzer where menadzerrestorana_id in (select id from korisnik where sifra='d' and email='d')));

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

insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kuvar1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kuvar1'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kuvar2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kuvar2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, kuvar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kuvar2'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono1'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono1'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PONEDELJAK', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Kono2'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, konobar_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Kono2'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('UTORAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SREDA', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('CETVRTAK', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('PETAK', (select id from korisnik where ime ='Sano'), 1, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('SUBOTA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));
insert into smenaudanu (dan, sanker_id, smena_id, restoran_id) values ('NEDELJA', (select id from korisnik where ime ='Sano'), 2, (select id from restoran where naziv = 'Restoran 1'));

insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (16,1,true, -1, '2017-03-28 08:00:00', 10, 1, -1, -1, '2017-03-28');
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (16,1,true, 5, '2017-02-28 08:00:00', 9, 2, -1, -1, '2017-02-28');
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (15,1,false, -1, '2017-03-01 08:00:00', 2, 3, -1, -1, '2017-03-01');
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (16,1,true, 2, '2017-01-28 08:30:00', 7, 4, -1, -1, '2017-01-28');
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (16,2,true, 1, '2017-02-25 08:30:00', 2, 5, -1, -1, '2017-02-25');
insert into poseta_restoranu (gost_id, restoran_id, obavljena, ocena, termin, br_sati, sto_id, ocena_obroka, ocena_usluge, datumrez) values (15,2,true, 4, '2017-02-28 11:30:00', 5, 3, -1, -1, '2017-02-28');

