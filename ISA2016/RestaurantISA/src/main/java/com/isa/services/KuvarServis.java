package com.isa.services;

import java.util.List;

import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;

public interface KuvarServis {

	List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Kuvar save(Kuvar gost);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
}
