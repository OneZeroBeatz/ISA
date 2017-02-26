package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("G")
public class Gost extends Korisnik{

	@Column(name = "canSend", nullable = true)
	private Boolean canSend;
	
	@Column(name = "canDecline", nullable = true)
	private Boolean canDecline;
	
	@Column(name = "canAccept", nullable = true)
	private Boolean canAccept;
	
	public Gost(){		
	}

	public Boolean isCanDecline() {
		return canDecline;
	}

	public void setCanDecline(Boolean canDecline) {
		this.canDecline = canDecline;
	}

	public Boolean isCanSend() {
		return canSend;
	}

	public void setCanSend(Boolean canSend) {
		this.canSend = canSend;
	}

	public Boolean getCanAccept() {
		return canAccept;
	}

	public void setCanAccept(Boolean canAccept) {
		this.canAccept = canAccept;
	}
}
