package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SNK")
public class Sanker extends Korisnik{
	
	public Sanker(){
		
	}	
}
