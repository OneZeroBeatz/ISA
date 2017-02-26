package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.isa.model.PosetaRestoranu;
import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("G")
public class Gost extends Korisnik{

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gost")
	private Set<PosetaRestoranu> posete;
	

	
	public Gost(){		
	}

}
