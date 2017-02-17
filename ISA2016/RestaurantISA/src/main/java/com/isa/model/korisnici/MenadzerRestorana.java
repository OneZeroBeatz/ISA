package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MENRES")
public class MenadzerRestorana extends Korisnik{

	//@ManyToOne(optional = true)
	//private Set<Restoran> restoran;
	
	public MenadzerRestorana(){
		
	}
}
