package com.isa.services;

import java.util.List;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;

public interface KonobarServis {

	List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Konobar save(Konobar konobar);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
    
    Restoran izlistajRestoran (Konobar konobar);
    
    void savePorudzbina(Porudzbina porudzbina);
    
    Jelo pronadjiJelo(Long i);
    
    Pice pronadjiPice(Long i);

	Porudzbina pronadjiPorudzbinu(Long id);
	
	void saveJeloUPorudzbini(JeloUPorudzbini jeloUPorudzbini);

	void savePiceUPorudzbini(PiceUPorudzbini piceUPorudzbini);



    
    
}
