package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Kuvar;

@Repository
@Transactional
public interface KuvarSkladiste extends JpaRepository<Kuvar, Long> {

	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);

	List<Kuvar> findByRestoran(Restoran restoran);
}
