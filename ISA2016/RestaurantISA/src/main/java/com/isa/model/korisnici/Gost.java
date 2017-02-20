package com.isa.model.korisnici;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("G")
public class Gost extends Korisnik{

	public Gost(){		
	}

}
