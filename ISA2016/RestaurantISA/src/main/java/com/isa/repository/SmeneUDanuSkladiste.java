package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.DanUNedelji;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Sanker;

public interface SmeneUDanuSkladiste extends JpaRepository<SmenaUDanu, Long>{

	SmenaUDanu save(SmenaUDanu smenaUDanu);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndKonobarNotNull(Restoran restoran, DanUNedelji danUNedelji);
	
	List<SmenaUDanu> findByRestoranAndDanUNedeljiAndKuvarNotNull(Restoran restoran, DanUNedelji danUNedelji);

	List<SmenaUDanu> findByKuvar(Kuvar kuvar);

	List<SmenaUDanu> findByKonobar(Konobar konobar);
	
	List<SmenaUDanu> findBySanker(Sanker sanker);
}
