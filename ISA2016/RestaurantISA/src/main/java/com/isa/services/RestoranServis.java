package com.isa.services;

import java.util.List;

import com.isa.model.Restoran;

public interface RestoranServis {

	List<Restoran> findAll();
	
	Restoran findOne(Long id);
	
	Restoran save(Restoran restoran);
	
	void delete(Long id);

	
}
