package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MENRES")
public class MenadzerRestorana extends Korisnik{

	public MenadzerRestorana(){
		
	}
}
