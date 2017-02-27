package com.isa.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isa.model.DanUNedelji;
import com.isa.model.Jelo;
import com.isa.model.Pice;
import com.isa.model.Restoran;
import com.isa.model.Smena;
import com.isa.model.SmenaUDanu;
import com.isa.model.Sto;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Kuvar;
import com.isa.model.korisnici.Ponudjac;
import com.isa.model.korisnici.Sanker;
import com.isa.repository.JeloSkladiste;
import com.isa.repository.JeloUPorudzbiniSkladiste;
import com.isa.repository.KonobarSkladiste;
import com.isa.repository.KuvarSkladiste;
import com.isa.repository.PiceSkladiste;
import com.isa.repository.PiceUPorudzbiniSkladiste;
import com.isa.repository.PonudjacSkladiste;
import com.isa.repository.RestoranSkladiste;
import com.isa.repository.SankerSkladiste;
import com.isa.repository.SmenaSkladiste;
import com.isa.repository.SmeneUDanuSkladiste;
import com.isa.repository.StoSkladiste;

@Service
public class RestoranServisImpl implements RestoranServis{

	@Autowired
	RestoranSkladiste restoranSkladiste;

	@Autowired
	JeloSkladiste jeloSkladiste;
	
	@Autowired
	PiceSkladiste piceSkladiste;

	@Autowired
	KuvarSkladiste kuvarSkladiste;
	
	@Autowired
	StoSkladiste stoSkladiste;

	@Autowired
	KonobarSkladiste konobarSkladiste;
	
	@Autowired
	JeloUPorudzbiniSkladiste jeloUPorudzbiniSkladiste;
	
	@Autowired
	PiceUPorudzbiniSkladiste piceUPorudzbiniSkladiste;
	
	@Autowired
	PonudjacSkladiste ponudjacSkladiste;
	
	@Autowired
	SmeneUDanuSkladiste smeneUDanuSkladiste;
	
	@Autowired
	SmenaSkladiste smenaSkladiste;
	
	@Autowired
	SankerSkladiste sankerSkladiste;

	
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

	@Override
	public Page<Jelo> izlistajJelovnik(Restoran restoran, Pageable pageable) {
		return jeloSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public Page<Pice> izlistajKartuPica(Restoran restoran, Pageable pageable) {
		return piceSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public void save(Jelo jelo) {
		jeloSkladiste.save(jelo);
	}

	@Override
	public void save(Pice pice) {
		piceSkladiste.save(pice);
	}

	@Override
	public void izbrisiJelo(Long id) {
		jeloSkladiste.delete(id);
	}

	@Override
	public void izbrisiPice(Long id) {
		piceSkladiste.delete(id);
	}

	@Override
	public Page<Sto> kreirajStolove(Restoran restoran, ArrayList<Integer> oznake, Pageable pageable) {
		List<Sto> stolovi = stoSkladiste.findByRestoranAndZauzetTrue(restoran);
		if(stolovi == null || stolovi.isEmpty()){
			stoSkladiste.delete(stoSkladiste.findByRestoran(restoran));
			
			for(Integer i : oznake){
				Sto s = new Sto();
				s.setOznaka(i);
				s.setBrojmesta(0);
				s.setSegment("nijesto");
				s.setZauzet(false);
				s.setRestoran(restoran);
				stoSkladiste.save(s);
			}
			return stoSkladiste.findByRestoran(restoran, pageable);
		}
		
		return null;
	}

	@Override
	public Sto izlistajSto(Sto sto) {
		return stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
	}

	@Override
	public Page<Sto> izlistajStolove(Restoran restoran, Pageable pageable) {
		return stoSkladiste.findByRestoran(restoran, pageable);
	}

	@Override
	public void dodajRedoveIKolone(Restoran rest) {
		restoranSkladiste.save(rest);
		
	}

	@Override
	public List<Ponudjac> izlistajPonudjaceVanRestorana(Restoran restoran) {
		List<Ponudjac> sviPonudjaci = ponudjacSkladiste.findAll();
		List<Ponudjac> restoranoviPonudjaci = ponudjacSkladiste.findByRestoran(restoran);
		for(Ponudjac pon : restoranoviPonudjaci){
			if(sviPonudjaci.contains(pon)){
				sviPonudjaci.remove(pon);
			}
		}
		
		return sviPonudjaci;
	}

	@Override
	public void dodajPonudjacaURestoran(Restoran restoran, Ponudjac ponudjac) {
		/*List<Ponudjac> ponudjaci = ponudjacSkladiste.findByRestoran(restoran);
		if(ponudjaci == null)
			ponudjaci = new ArrayList<>();
		
		ponudjaci.add(ponudjac);
		*/
		restoran.getPonudjac().add(ponudjac);
		restoranSkladiste.save(restoran);
	}

	@Override
	public Ponudjac save(Ponudjac ponudjac) {
		return ponudjacSkladiste.save(ponudjac);
	}
	
	@Override
	public void izbrisiPiceUPorudzbini(Long id) {
		piceUPorudzbiniSkladiste.deleteById(id);
	}

	@Override
	public void izbrisiJeloUPorudzbini(Long id) {
		jeloUPorudzbiniSkladiste.deleteById(id);
	}

	@Override

	public List<SmenaUDanu> izlistajSmeneKuvara(Restoran restoran, DanUNedelji danUNedelji) {
		return smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndKuvarNotNull(restoran, danUNedelji);
	}

	@Override
	public List<Smena> izlistajSmene(Restoran restoran) {
		return smenaSkladiste.findByRestoran(restoran);
	}

	@Override
	public void dodajSmenu(Smena smena) {
		String odSmena = smena.getVremeod();
		String doSmena = smena.getVremedo();

		odSmena = odSmena.split("T")[1];
		doSmena = doSmena.split("T")[1];
		
		odSmena = odSmena.substring(0, 5);
		doSmena = doSmena.substring(0, 5);
		int temp1 = Integer.parseInt(odSmena.split(":")[0]);
		if(temp1+1 > 23){
			temp1 = 0;
		}else{
			temp1++;
		}
		int temp2 = Integer.parseInt(doSmena.split(":")[0]);
		if(temp2+1 > 23){
			temp2 = 0;
		}else{
			temp2++;
		}
		String temp1String = String.valueOf(temp1);
		String temp2String = String.valueOf(temp2);
		if(temp1String.length() == 1){
			temp1String = 0 + temp1String;
		}
		if(temp2String.length() == 1){
			temp2String = 0 + temp2String;
		}
		odSmena = temp1String + odSmena.substring(2);
		doSmena = temp2String + doSmena.substring(2);	
		
		smena.setVremedo(doSmena);
		smena.setVremeod(odSmena);
		
		smenaSkladiste.save(smena);
	}

	@Override
	public List<Kuvar> izlistajDostupneKuvare(Restoran restoran, DanUNedelji danUNedelji) {
		List<Kuvar> retVal = kuvarSkladiste.findByRestoran(restoran);
		List<SmenaUDanu> sud = smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndKuvarNotNull(restoran, danUNedelji);
		
		for(SmenaUDanu s : sud){
			if(retVal.contains(s.getKuvar())){
				retVal.remove(s.getKuvar());
			}
		}
		
		return retVal;
	}
	
	public List<Konobar> izlistajKonobare(Restoran restoran) {
		return konobarSkladiste.findByRestoran(restoran);
	}

	@Override
	public List<Kuvar> izlistajKuvare(Restoran restoran) {
		return kuvarSkladiste.findByRestoran(restoran);
	}

	@Override
	public List<Sanker> izlistajSankere(Restoran restoran) {
		return sankerSkladiste.findByRestoran(restoran);
	}

	@Override
	public void dodajKuvaraUSmenuDana(SmenaUDanu smenaUDanu) {
		smeneUDanuSkladiste.save(smenaUDanu);
		
	}

	@Override
	public List<SmenaUDanu> izlistajSmeneKonobara(Restoran restoran, DanUNedelji danUNedelji, Sto sto) {
		Sto s = stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
		List<SmenaUDanu> smene = smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndStoAndKonobarNotNull(restoran, danUNedelji, s);
		
		return smene;
	}

	@Override
	public List<Smena> izlistajDostupneSmeneKonobarDan(Restoran restoran, DanUNedelji danUNedelji, Sto sto) {
		List<Smena> smene = smenaSkladiste.findByRestoran(restoran);
		List<Smena> retVal = smenaSkladiste.findByRestoran(restoran);
		Sto s = stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
		List<SmenaUDanu> smeneDan = smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndStoAndKonobarNotNull(restoran, danUNedelji, s);
		
		for(Smena sm : smene){
			for(SmenaUDanu sud : smeneDan){
				if(sm.equals(sud.getSmena())){
					retVal.remove(sm);
					break;
				}
			}
		}
		
		return retVal;
	}

	@Override
	public List<Konobar> izlistajDostupneKonobare(Restoran restoran, DanUNedelji danUNedelji, Sto sto, Smena smena) {
		List<Konobar> konobari = konobarSkladiste.findByRestoran(restoran);
		List<Konobar> retVal = konobarSkladiste.findByRestoran(restoran);
		Sto s = stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
		Smena smenaa = smenaSkladiste.findOne(smena.getId());
		try{
			for(Konobar kon : konobari){
				if(smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndStoAndKonobarAndSmena(restoran, danUNedelji, s, kon, smenaa) != null){		// Ako vec radi za tim stolom
					retVal.remove(kon);
				}else{
					try{
						if(!smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndKonobar(restoran, danUNedelji, kon).getSmena().equals(smenaa)){
							retVal.remove(kon);
						}
					}catch(Exception e){
						System.out.println("Konobar ne radi taj dan");
					}
				}
			}
		}catch(Exception e){
			System.out.println("Nesto puca");
		}
				
		return retVal;
	}

	@Override
	public void dodajKonobaraStoSmenaDan(Restoran restoran, DanUNedelji danUNedelji, Konobar konobar, Smena smena,
			Sto sto) {
		Sto s = stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
		SmenaUDanu sud = smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndKonobarAndSmena(restoran, danUNedelji, konobar, smena);
		if(sud == null){
			SmenaUDanu smenaDana = new SmenaUDanu();
			smenaDana.setRestoran(restoran);
			smenaDana.setDanUNedelji(danUNedelji);
			smenaDana.setKonobar(konobar);
			smenaDana.setSmena(smena);
			Set<Sto> stolovi = new HashSet<>();
			stolovi.add(s);
			smenaDana.setSto(stolovi);
			smeneUDanuSkladiste.save(smenaDana);
		}else{
			sud.getSto().add(s);
			smeneUDanuSkladiste.save(sud);
		}
	}

	@Override
	public List<Sanker> izlistajDostupneSankere(Restoran restoran, DanUNedelji danUNedelji) {
		List<Sanker> retVal = sankerSkladiste.findByRestoran(restoran);
		List<SmenaUDanu> sud = smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndSankerNotNull(restoran, danUNedelji);
		
		for(SmenaUDanu s : sud){
			if(retVal.contains(s.getSanker())){
				retVal.remove(s.getSanker());
			}
		}
		
		return retVal;
	}

	@Override
	public void dodajSankeraUSmenuDana(SmenaUDanu smenaUDanu) {
		smeneUDanuSkladiste.save(smenaUDanu);
		
	}

	@Override
	public List<SmenaUDanu> izlistajSmeneSankera(Restoran restoran, DanUNedelji danUNedelji) {
		return smeneUDanuSkladiste.findByRestoranAndDanUNedeljiAndSankerNotNull(restoran, danUNedelji);
	}
	
	public List<SmenaUDanu> izlistajSmenePoDanimaKuvara(Kuvar kuvar) {
		return smeneUDanuSkladiste.findByKuvar(kuvar);
	}

	@Override
	public List<SmenaUDanu> izlistajSmenePoDanimaKonobara(Konobar konobar) {
		return smeneUDanuSkladiste.findByKonobar(konobar);
	}
	
	@Override
	public List<SmenaUDanu> izlistajSmenePoDanimaSankera(Sanker Sanker) {
		return smeneUDanuSkladiste.findBySanker(Sanker);

	}

	@Override
	public Sto izmeniSto(Sto sto) {
		Sto s = stoSkladiste.findByRestoranAndOznaka(sto.getRestoran(), sto.getOznaka());
		if(s.getZauzet() && sto.getSegment() == "nijesto")
			return null;
		s.setBrojmesta(sto.getBrojmesta());
		s.setSegment(sto.getSegment());
		stoSkladiste.save(s);
		return s;
	}

}
