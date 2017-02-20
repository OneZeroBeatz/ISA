package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("KUV")
public class Kuvar extends Korisnik{

	@ManyToOne(optional = true)
	private Restoran restoran;
	
	public Kuvar(){
		
	}

	public Restoran getRestoran() {
		return restoran;
	}
	

	
}
