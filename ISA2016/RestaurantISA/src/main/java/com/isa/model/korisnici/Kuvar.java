package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("KUV")
public class Kuvar extends Korisnik{

	public Kuvar(){
		
	}


	
}
