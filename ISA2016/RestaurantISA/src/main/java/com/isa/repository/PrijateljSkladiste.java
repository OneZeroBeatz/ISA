package com.isa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.Prijatelj;

@Repository
@Transactional
public interface PrijateljSkladiste extends JpaRepository<Prijatelj, Long> {

	Page<Prijatelj> findByEmailGosta(String email, Pageable pageable);
}
