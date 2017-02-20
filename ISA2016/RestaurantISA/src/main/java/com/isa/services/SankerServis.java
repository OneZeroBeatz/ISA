package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Sanker;

public interface SankerServis {

	List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Sanker save(Sanker sanker);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
    
	Page<Porudzbina> izlistajPorudzbine(Restoran restoran, Pageable pageable);

    
    
    
}
