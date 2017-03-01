package com.isa;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.model.korisnici.MenadzerRestorana;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.MenadzerRestoranaServis;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class MenadzerRestoranaServisTest {

	@Autowired
	MenadzerRestoranaServis menadzerRestoranaServis;
	
	@Test
	public void testSave() {
		MenadzerRestorana pera = new MenadzerRestorana();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
		pera.setSifra("sifra");
		menadzerRestoranaServis.save(pera);
	
		MenadzerRestorana menadzerRestorana = (MenadzerRestorana) menadzerRestoranaServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(menadzerRestorana);
        Assert.assertEquals(pera.getId(), menadzerRestorana.getId());
	}
	
	
    @Test
    public void testFindAll() {
        List<MenadzerRestorana> menadzerRestoranai = menadzerRestoranaServis.findAll();
        Assert.assertNotNull(menadzerRestoranai);
    }
    
    @Test
    public void testDelete() {
        MenadzerRestorana menadzerRestorana = new MenadzerRestorana();
        menadzerRestorana.setIme("Petar");
        menadzerRestorana.setPrezime("Petrovic");
        menadzerRestorana.setTipKorisnika(TipKorisnika.MENADZER_RESTRORANA);
        menadzerRestorana.setEmail("menadzerRestorana@gmail.com");
        menadzerRestoranaServis.save(menadzerRestorana);
        menadzerRestoranaServis.delete(menadzerRestorana.getId());
        MenadzerRestorana ucitan = menadzerRestoranaServis.findOne(menadzerRestorana.getId());
        Assert.assertNull(ucitan);
    }
	
}
