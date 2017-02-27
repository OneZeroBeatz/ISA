package com.isa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Gost;

public interface PoseteSkladiste extends JpaRepository<PosetaRestoranu, Serializable> {

	List<PosetaRestoranu> findByGost(Gost gost);

	List<PosetaRestoranu> findBySto(Sto sto);

	
	
}
