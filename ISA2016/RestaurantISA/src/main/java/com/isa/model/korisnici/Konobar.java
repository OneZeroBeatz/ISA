package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("KN")
public class Konobar extends Korisnik{

	public Konobar() {
	}
}
