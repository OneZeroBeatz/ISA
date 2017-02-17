package com.isa.services;

import java.util.List;

import com.isa.model.korisnici.Gost;
import com.isa.model.korisnici.Korisnik;


public interface GostServis {

    List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Gost save(Gost gost);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
}
