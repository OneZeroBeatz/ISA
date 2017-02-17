package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.model.PorudzbinaMenadzer;

@Entity
@DiscriminatorValue("MENRES")
public class MenadzerRestorana extends Korisnik{

	//@ManyToOne(optional = true)
	//private Set<Restoran> restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menadzerrestorana")
	private Set<PorudzbinaMenadzer> porudzbinamenadzer;
	
	public MenadzerRestorana(){
		
	}
}
