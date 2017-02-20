package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Porudzbina;

public interface PorudzbinaSkladiste extends JpaRepository<Porudzbina, Serializable>{

	Porudzbina save(Porudzbina namirnica);
	
	
}
