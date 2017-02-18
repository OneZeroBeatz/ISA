package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.korisnici.Korisnik;

public interface SankerSkladiste extends JpaRepository<Korisnik, Long> {
	
	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);

}
