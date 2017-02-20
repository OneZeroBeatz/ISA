package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("SNK")
public class Sanker extends Korisnik{
	
	@ManyToOne(optional = true)
	private Restoran restoran;
	
	public Sanker(){
		
	}	
	
	public Restoran getRestoran() {
		return restoran;
	}
	
}
