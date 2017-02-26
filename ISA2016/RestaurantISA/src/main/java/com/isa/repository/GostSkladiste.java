package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.Prijatelj;


@Repository
@Transactional
public interface GostSkladiste extends JpaRepository<Korisnik, Long> {

	List<Korisnik> findById(Long gid);
	
	List<Korisnik> findByEmail(String email);
	
	Page<Korisnik> findByEmailNotLike(String email, Pageable pageable);
	
}
