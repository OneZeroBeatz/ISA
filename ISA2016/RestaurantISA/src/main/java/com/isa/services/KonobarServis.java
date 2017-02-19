package com.isa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;

public interface KonobarServis {

	List<Korisnik> findAll();

    Korisnik findOne(Long id);

    Konobar save(Konobar konobar);

    Korisnik delete(Long id);

    Korisnik findByEmail(String email);
}
