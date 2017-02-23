package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isa.model.Namirnica;

public interface NamirnicaSkladista extends JpaRepository<Namirnica, Long>{

	List<Namirnica> findAll();
	
}
