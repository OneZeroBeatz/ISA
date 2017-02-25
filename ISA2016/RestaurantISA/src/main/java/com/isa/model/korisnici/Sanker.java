package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;

@Entity
@DiscriminatorValue("SNK")
public class Sanker extends Korisnik{
	
	@ManyToOne(optional = true)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanker")
	private Set<Porudzbina> porudzbina;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanker")
	private Set<SmenaUDanu> smenaudanu;
	
	public Sanker(){
		
	}	
	
	public Restoran getRestoran() {
		return restoran;
	}
	
}
