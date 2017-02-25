package com.isa.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;
import com.isa.pomocni.GostPrijatelj;


public interface GostServis {

    List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Gost save(Gost gost);

    void delete(Long id);

    Korisnik findByEmail(String email);
    
    Page<Prijatelj> izlistajPrijatelje(Gost gost, Pageable pageable);

	void delete(GostPrijatelj gostPrij);
    
}
