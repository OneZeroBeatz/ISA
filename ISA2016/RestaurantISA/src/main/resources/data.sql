insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Pera', 'Peric', 'pera123', 'G','pera@pera.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Sima', 'Simic', 'sima123', 'G','sima@sima.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Djura', 'Djuric', 'djura123', 'G','djura@djura.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Verka', 'Verkic', 'verka123', 'G','verka@verka.com', 'GOST')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Kuvar', 'Kuvric', 'k', 'KUV','k', 'KUVAR')

insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('Aca', 'aaa', 'a', 'PN','a@a.a', 'PONUDJAC')
insert into korisnik(ime, prezime, sifra, ctype, email, tip) values ('B', 'bbb', 'b', 'PN','b', 'PONUDJAC')

insert into lista_prijatelja(id_gosta, id_prijatelja) values (1, 2)
insert into lista_prijatelja(id_gosta, id_prijatelja) values (1, 3)
insert into lista_prijatelja(id_gosta, id_prijatelja) values (2, 4)


insert into ponuda(cena, rok_isporuke, garancija, ponudjac_id) values (50, 5, 'garan123', 2)

insert into korisnik(ime, prezime, sifra, ctype, email, tip, glavni) values ('Kero', 'Kerica', 's', 'MENSIS','s', 'MENADZER_SISTEMA', true)
