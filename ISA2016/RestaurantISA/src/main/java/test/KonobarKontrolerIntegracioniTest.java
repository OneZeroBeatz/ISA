package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.isa.model.Jelo;
import com.isa.model.JeloUPorudzbini;
import com.isa.model.Pice;
import com.isa.model.PiceUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.korisnici.Konobar;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/context.xml")
//@ContextConfiguration(locations = { "classpath:context1.xml" })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class KonobarKontrolerIntegracioniTest {

	@Autowired
	KonobarServis konobarServis;
	
	@Autowired
	RestoranServis restoranServis;
	
	@Test
	public void ucitajKonobareRestoranaTest() {
		RestAssured.when().get("/ucitajKonobareRestorana").then()
				.statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
				.body("id", CoreMatchers.hasItems(4));
	}
	
	@Test
	public void dodajPorudzbinuTest(){
		Porudzbina porudzbina = new Porudzbina();
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		porudzbina.setVremePrimanja(currentTime);
		porudzbina.setPorudzbinaPrihvacena(true);
		Restoran restoran = restoranServis.findOne(1L);
		porudzbina.setRestoran(restoran);
		Konobar konobar = new Konobar();
		konobar.setIme("Sima");
		porudzbina.setSanker(null);
		porudzbina.setKonobar(konobar);
		
		Jelo jelo = new Jelo();
		jelo.setNaziv("Punjene paprike");
		jelo.setOpis("Opis jela 1");
		jelo.setCena(30F);
		JeloUPorudzbini jeloUPorudzbini = new JeloUPorudzbini();
		jeloUPorudzbini.setKolicina(3);
		jeloUPorudzbini.setJelo(jelo);
		jeloUPorudzbini.setPorudzbina(porudzbina);	
		restoranServis.save(jelo);
		konobarServis.saveJeloUPorudzbini(jeloUPorudzbini);	
		konobarServis.savePorudzbina(porudzbina);	
		RestAssured.given()
        .body(porudzbina)
        .contentType(ContentType.JSON)
        .when()
        .post("/dodajPorudzbinu")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .contentType(ContentType.JSON)
        .body("vremePrimanja", CoreMatchers.equalTo(currentTime));
	}



	/*
	@Test
    public void createCookTest(){
        Cook cook = new Cook();
        cook.setName("Milan");
        cook.setSurname("Milanovic");
        cook.setPassword("mico");
        cook.setEmail("mico@gmail.com");
        cook.setType(UserType.COOK);
        cook.setPasswordChanged(true);
        cook.setTypeCook("All");
        Date date = new Date();
        cook.setDate_of_birth(date);
        cook.setDress_size(13);
        cook.setShoe_size(13);
        Restaurant r = restaurantService.findOne(1L);
        cook.setRestaurant(r);


        RestAssured.given()
                .body(cook)
                .contentType(ContentType.JSON)
                .when()
                .post("/addCook")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("name", CoreMatchers.equalTo("Milan"));
    }
	*/
    
    
}

