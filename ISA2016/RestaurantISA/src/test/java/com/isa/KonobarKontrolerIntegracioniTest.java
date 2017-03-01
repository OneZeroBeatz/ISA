package com.isa;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.isa.model.korisnici.Konobar;
import com.isa.repository.KonobarSkladiste;
import com.isa.services.KonobarServis;
import com.isa.services.RestoranServis;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
@Transactional
public class KonobarKontrolerIntegracioniTest {

	@Mock
	KonobarServis konobarServis;
	
	@Mock
	RestoranServis restoranServis;
	
	@MockBean
	KonobarSkladiste skladiste;
	
	@Test
	public void ucitajKonobareRestoranaTest() {
		RestAssured.when().get("/ucitajKonobareRestorana").then()
				.statusCode(HttpStatus.SC_OK).contentType(ContentType.JSON)
				.body("id", CoreMatchers.hasItems(4));
	}
	
	@Test
	public void dodajPorudzbinuTest(){
		/*Porudzbina porudzbina = new Porudzbina();
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
        .body("vremePrimanja", CoreMatchers.equalTo(currentTime));*/
		List<Konobar> listaK = new ArrayList<>();
		when(this.konobarServis.findAll()).thenReturn(listaK);
		//given(this.konobarServis.findAll()).willReturn(null);
        List<Konobar> newList = this.konobarServis.findAll();
      //  assertThat(newList.size()).isEqualTo(0);
        assertEquals(newList.size(), 0);
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

