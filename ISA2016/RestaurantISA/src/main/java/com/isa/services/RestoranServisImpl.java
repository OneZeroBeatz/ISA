package com.isa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.model.Restoran;
import com.isa.repository.RestoranSkladiste;

@Service
public class RestoranServisImpl implements RestoranServis{

	@Autowired
	RestoranSkladiste restoranSkladiste;

	
	@Override
	public List<Restoran> findAll() {
		return restoranSkladiste.findAll();
	}

	@Override
	public Restoran findOne(Long id) {
		return restoranSkladiste.findOne(id);
	}

	@Override
	public Restoran save(Restoran restoran) {
		return restoranSkladiste.save(restoran);
	}

	@Override
	public void delete(Long id) {
		restoranSkladiste.delete(id);
	}

}
