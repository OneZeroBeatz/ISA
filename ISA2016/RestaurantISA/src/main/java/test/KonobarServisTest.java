package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.isa.RestaurantIsaApplication;
import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Korisnik;
import com.isa.model.korisnici.TipKorisnika;
import com.isa.services.KonobarServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantIsaApplication.class)
@WebAppConfiguration
public class KonobarServisTest {

	@Autowired
	KonobarServis konobarServis;
	
	@Test
	public void testSave() {
		Konobar pera = new Konobar();
		pera.setEmail("pericaperic@gmail.com");
		pera.setIme("Pera");
		pera.setPrezime("Peric");
		pera.setTipKorisnika(TipKorisnika.KONOBAR);
		pera.setSifra("sifra");
		konobarServis.save(pera);
	
		Konobar konobar = (Konobar) konobarServis.findOne(pera.getId());
        Assert.assertNotNull(pera);
        Assert.assertNotNull(konobar);
        Assert.assertEquals(pera.getId(), konobar.getId());
	}
	
}
