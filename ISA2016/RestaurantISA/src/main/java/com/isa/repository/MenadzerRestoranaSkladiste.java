package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.MenadzerRestorana;

@Repository
@Transactional
public interface MenadzerRestoranaSkladiste extends JpaRepository<MenadzerRestorana, Long>{

	List<MenadzerRestorana> findAll();
	List<MenadzerRestorana> findById(Long id);
	MenadzerRestorana save(MenadzerRestorana ponudjac);
	void delete(Long id);
	
}
