package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.model.Ponuda;


@Entity
@DiscriminatorValue("PN")
public class Ponudjac extends Korisnik{
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ponudjac")
	private Set<Ponuda> ponuda;
	
	public Ponudjac(){
		
	}

}
