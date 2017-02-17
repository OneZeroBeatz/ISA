package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Korisnik;

@Repository
@Transactional
public interface KuvarSkladiste extends JpaRepository<Korisnik, Long> {

	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);
}
