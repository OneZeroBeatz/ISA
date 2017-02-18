package com.isa.services;

import java.util.List;

import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;

public interface SankerServis {

	List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Sanker save(Sanker sanker);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
    
}
