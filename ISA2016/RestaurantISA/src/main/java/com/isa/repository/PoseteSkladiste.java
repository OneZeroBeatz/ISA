package com.isa.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;
import com.isa.model.Sto;
import com.isa.model.korisnici.Gost;

public interface PoseteSkladiste extends JpaRepository<PosetaRestoranu, Serializable> {

	List<PosetaRestoranu> findByGost(Gost gost);

	List<PosetaRestoranu> findBySto(Sto sto);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezBetween(Restoran restoran, int ocena, Date datumod, Date datumdo);

	List<PosetaRestoranu> findByRestoranAndOcenaNot(Restoran restoran, int i);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezBefore(Restoran restoran, int i, Date doDatum);

	List<PosetaRestoranu> findByRestoranAndOcenaNotAndDatumrezAfter(Restoran restoran, int i, Date odDatum);
	
	List<PosetaRestoranu> findByRestoranAndSto(Restoran restoran, Sto sto);
}
