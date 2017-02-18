insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Pera', 'Peric', 'pera123', 'G','pera@pera.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Sima', 'Simic', 'sima123', 'G','sima@sima.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Djura', 'Djuric', 'djura123', 'G','djura@djura.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Verka', 'Verkic', 'verka123', 'G','verka@verka.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Kuvar', 'Kuvric', 'k', 'KUV','k', 'KUVAR')

insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Aca', 'aaa', 'a', 'PN','a@a.a', 'PONUDJAC')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('B', 'bbb', 'b', 'PN','b', 'PONUDJAC')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('C', 'ccc', 'c', 'MENRES','c@c.c', 'MENADZER_RESTRORANA')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('D', 'ddddd', 'd', 'MENRES','d', 'MENADZER_RESTRORANA')
insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni) values ('Kero', 'Kerica', 's', 'MENSIS','s', 'MENADZER_SISTEMA', true)


insert into lista_prijatelja(id_gosta, id_prijatelja) values (1, 2)
insert into lista_prijatelja(id_gosta, id_prijatelja) values (1, 3)
insert into lista_prijatelja(id_gosta, id_prijatelja) values (2, 4)

insert into ponuda(cena, rok_isporuke, garancija, ponudjac_id) values (50, 5, 'garan123', (select id from korisnik where sifra='b' and email='b'))
insert into ponuda(cena, rok_isporuke, garancija, ponudjac_id) values (25, 3, 'garan1', (select id from korisnik where sifra='b' and email='b'))

insert into porudzbina_menadzer(od, do, menadzerrestorana_id) values ('2015-01-01', '2016-01-03', (select id from korisnik where sifra='d' and email='d'))
insert into porudzbina_menadzer(od, do, menadzerrestorana_id) values ('2005-06-03', '2016-08-03', (select id from korisnik where sifra='d' and email='d'))

insert into namirnica(naziv, porudzbinamenadzer_id) values ('Nam 1', (select id from porudzbina_menadzer where od='2015-01-01' and do='2016-01-03'))
--insert into namirnica(naziv, porudzbinamenadzer_id) values ('Nam 2', (select id from porudzbina_menadzer where menadzerrestorana_id in (select id from korisnik where sifra='d' and email='d')))
insert into pice(naziv, porudzbinamenadzer_id) values ('Pice 1', (select id from porudzbina_menadzer where od='2015-01-01' and do='2016-01-03'))

insert into restoran(naziv, opis) values ('Restoran 1', 'Opis Restorana 1')
insert into korisnik(ime, prezime, sifra, ctype, email, tip, restoran_id) values ('R', 'rrr', 'r', 'MENRES','r', 'MENADZER_RESTRORANA', (select id from restoran where naziv='Restoran 1'))

