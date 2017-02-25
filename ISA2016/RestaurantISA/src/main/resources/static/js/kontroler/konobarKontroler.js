var konobarKontroler = angular.module('restoranApp.konobarKontroler', []);

konobarKontroler.controller('konobarCtrl', function($scope, $location, logovanjeServis, gostGlavnaStranaServis, izmeniKonobarServis){
	
	$scope.osveziPrikazZaIzmenu = function (konobar){
		$scope.imeIzmena = konobar.ime;
		$scope.prezimeIzmena = konobar.prezime
		$scope.emailIzmena = konobar.email
		
	}
	
	$scope.vratiNaDodavanje = function(){
		$scope.izmena = false;
		$scope.smeDaBriseJela = true;
		$scope.smeDaBrisePica = true;
		$scope.smeDaDodaJela = true;
		$scope.smeDaDodaPica = true;
	    $scope.dodataPica = [];
	    $scope.dodataJela = [];
	    $scope.dodataPicaId = [];
	    $scope.dodataJelaId = [];
	}
	
	$scope.vratiNaDodavanje();
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){

			$scope.ulogovanKonobar = data;
			$scope.osveziPrikazZaIzmenu($scope.ulogovanKonobar);
			
			$scope.izmena = false;
			$scope.smeDaDodaJela = true;
			$scope.smeDaDodaPica = true;
			$scope.setTab = function(newTab){
				if (newTab == 2){
					izmeniKonobarServis.izlistajJela($scope.ulogovanKonobar).success(function(data){
						$scope.jela = data;
					}).error (function(data){
						alert("Neuspesno ucitana jela");
					});
					
					izmeniKonobarServis.izlistajPica($scope.ulogovanKonobar).success(function(data){
						$scope.pica = data;
					}).error (function(data){
						alert("Neuspesno ucitana pica");
					});
					izmeniKonobarServis.izlistajStolove($scope.ulogovanKonobar).success(function(data){
						$scope.stolovi = data;
					}).error (function(data){
						alert("Neuspesno ucitana pica");
					});		
					
					// UCITAVANJE PORUDZBINA
					$scope.porudzbine = [];
					izmeniKonobarServis.ucitajPorudzbine($scope.ulogovanKonobar).success(function(data) {
						$scope.porudzbine = data;
					}).error(function (data){
						alert("Neuspelo ucitavanje porudzbina");
					});
					
					// Kliknuce na zavrsi i kreiraj racun
					$scope.zavrsiIKreiraj = function (item){
						var konPor = {
								konobar : $scope.ulogovanKonobar,
								porudzbina : item
							}
						
						izmeniKonobarServis.kreiraj(konPor).success(function (data){
				    		$scope.porudzbine = data;	
				    		alert("Uspesno kreiran racun - Porudzbina naplacena");
						}).error(function(data){
							alert("Nemoguce kreirati racun");
						});

						$scope.racun = -1;
					}
					
					// Kliknuce na zavrsi
					$scope.racun = -1;
					$scope.ukupnoRacunKliknute = 0;
					$scope.zavrsi = function(porudzbina){
						$scope.jelaKliknutePorudzbine = [];
						$scope.picaKliknutePorudzbine = [];
						$scope.show = -1;
						izmeniKonobarServis.ucitajJelaPorudzbine(porudzbina).success(function(data){
							$scope.jelaKliknutePorudzbine = data;
						}).error(function (data){
							alert("Neuspela operacija");
						});
						
						izmeniKonobarServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
							$scope.picaKliknutePorudzbine = data;
							if ($scope.racun == porudzbina.id)
								$scope.racun = -1;
							else
								$scope.racun = porudzbina.id
						}).error(function (data){
							alert("Neuspela operacija");
						});
						
						var konPor = {
							konobar : $scope.ulogovanKonobar,
							porudzbina : porudzbina
						}
						
						izmeniKonobarServis.kreirajRacun(konPor).success(function (data){
							$scope.ukupnoRacunKliknute = data.ukupno;
						}).error(function (data){
							alert("Nemoguce kreirati racun");
						});
							
					}					
					// Kliknuce na detalji
					$scope.jelaKliknutePorudzbine = [];
					$scope.picaKliknutePorudzbine = [];
					$scope.show = -1;
					$scope.kliknuoNaDetalji = function (porudzbina){
						$scope.jelaKliknutePorudzbine = [];
						$scope.picaKliknutePorudzbine = [];
						$scope.racun = -1;
						izmeniKonobarServis.ucitajJelaPorudzbine(porudzbina).success(function(data){
							$scope.jelaKliknutePorudzbine = data;
						}).error(function (data){
							alert("Neuspelo ucitavanje detalja");
						});
						
						izmeniKonobarServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
							$scope.picaKliknutePorudzbine = data;
							if ($scope.show == porudzbina.id)
								$scope.show = -1;
							else
								$scope.show = porudzbina.id
						}).error(function (data){
							alert("Neuspelo ucitavanje detalja");
						});
					}
				}
		    	$scope.tab = newTab;
		    };
		    
		    // Dodaj u listu jela
		    $scope.dodataPica = [];
		    $scope.dodataJela = [];

		    $scope.dodataPicaId = [];
		    $scope.dodataJelaId = [];
		    
		    $scope.dodajJelo = function (jelo){
		    	var jelo1 = {
		    		id : $scope.dodataJela.length+1, 
		    		jel: jelo
		    	};
		    	if (jelo1.jel == null){
		    		alert("Niste odabrali jelo");
		    	} else {
		    		$scope.dodataJela.push(jelo1);
		    	}
	    	}
	    
		    $scope.dodajPice = function (pice){
		    	var pice1 = {
			    		id : $scope.dodataPica.length+1, 
			    		pic: pice
			    	};
		    	if (pice1.pic == null){
		    		alert("Niste odabrali pice");
		    	} else {
		    		$scope.dodataPica.push(pice1);
		    	}
		    }
		    
		    $scope.obrisiPice = function (pice){
		    	var index = $scope.dodataPica.indexOf(pice);
		    	$scope.dodataPica.splice(index, 1);
		    };
		    
		    $scope.obrisiJelo = function (jelo){
		    	var index = $scope.dodataJela.indexOf(jelo);
		    	$scope.dodataJela.splice(index, 1);
		    };
		    
		    $scope.isSet = function(tabNum){  
		    	return $scope.tab === tabNum;
		    };
			
		    // DODAJ PORUDZBINU
		    $scope.dodajPorudzbinu = function (){
		    	if ($scope.sto == null){
		    		alert("Niste odabrali sto");
		    		return;
		    	}
		    	for (var i = 0 ; i< $scope.dodataJela.length ; i++){
		    		var jelo1 = {
				    	id : i+1, 
				    	jel: $scope.dodataJela[i].jel.id
				    };
		    		$scope.dodataJelaId.push(jelo1);
		    	}
	    	
		    	for (var i = 0 ; i< $scope.dodataPica.length ; i++){
		    		var pice1 = {
				    	id : i+1, 
				    	pic: $scope.dodataPica[i].pic.id
				    };
		    		$scope.dodataPicaId.push(pice1);
		    	}
	    	
		    	var jelaPica = {
		    		svaJela : $scope.dodataJelaId,
		    		svaPica : $scope.dodataPicaId,
		    		konobar : $scope.ulogovanKonobar,
		    		sto : $scope.sto
		    	};
		    	    	
		    	var jelaPicaStr = JSON.stringify(jelaPica);
				
		    	izmeniKonobarServis.dodajPorudzbinu(jelaPicaStr).success(function (data){
		    		$scope.porudzbine = data;
		    	}).error (function (data){
		    		alert("Neuspasno dodavanje porudzbine");	
		    	});	
		    	$scope.dodataJela = [];
		    	$scope.dodataPica = [];
		    	$scope.dodataJelaId = [];
		    	$scope.dodataPicaId = [];
		    	$scope.smeDaDodaJela = true;
		    	$scope.smeDaDodaPica = true;
		    	$scope.smeDaBriseJela = true;
		    	$scope.smeDaBrisePica = true;
		    	$scope.izmena = false;
		    }
		    // POTVRDI IZMENE
		    $scope.dodajPorudzbinuIzmena = function (){
		    	for (var i = 0 ; i< $scope.dodataJela.length ; i++){
		    		var jelo1 = {
				    	id : i+1, 
				    	jel: $scope.dodataJela[i].jel.id
				    };
		    		$scope.dodataJelaId.push(jelo1);
		    	}
	    	
		    	for (var i = 0 ; i< $scope.dodataPica.length ; i++){
		    		var pice1 = {
				    	id : i+1, 
				    	pic: $scope.dodataPica[i].pic.id
				    };
		    		$scope.dodataPicaId.push(pice1);
		    	}
	    	
		    	var izmeniPorudzbinuPrikaz = {
		    		porudzbina: $scope.porudzbinaIzmena,
		    		svaJela: $scope.dodataJelaId,
		    		svaPica: $scope.dodataPicaId,
		    		smeDaBriseJela: $scope.smeDaBriseJela,
		    		smeDaBrisePica: $scope.smeDaBrisePica,
		    		smeDaDodaJela: $scope.smeDaDodaJela,
		    		smeDaDodaPica: $scope.smeDaDodaPica
		    	}

		    	console.log("brise jela = "+ izmeniPorudzbinuPrikaz.smeDaBriseJela);
		    	console.log("brise pica = "+ izmeniPorudzbinuPrikaz.smeDaBrisePica);
		    	console.log("doda jela = "+ izmeniPorudzbinuPrikaz.smeDaDodaJela);
		    	console.log("doda pica = "+ izmeniPorudzbinuPrikaz.smeDaDodaPica);
		    	
				izmeniKonobarServis.potvrdiIzmene(izmeniPorudzbinuPrikaz).success(function (data){
		    		$scope.porudzbine = data;
		    		$scope.show = -1;
		    	}).error (function (data){
		    		alert("Neuspesna operacija");	
		    	});	
		    	$scope.dodataJela = [];
		    	$scope.dodataPica = [];
		    	$scope.dodataJelaId = [];
		    	$scope.dodataPicaId = [];
		    	$scope.izmena = false;
		    	$scope.smeDaBriseJela = true;
		    	$scope.smeDaBrisePica = true;
		    	$scope.smeDaDodaJela = true;
		    	$scope.smeDaDodaPica = true;
		    }
		    
		    
		    // IZMENI PORUDZBINU
		    $scope.izmeni = function (porudzbina){
		    	$scope.porudzbinaIzmena = porudzbina;
		    	$scope.smeDaBriseJela = false;
		    	$scope.smeDaBrisePica = false;
		    	$scope.smeDaDodaJela = false;
		    	$scope.smeDaDodaPica = false;
			    $scope.izmena = true;
			    izmeniKonobarServis.izmeniPorudzbinu(porudzbina).success(function (data){
			    	$scope.dodataPica = [];
			    	$scope.dodataJela = [];
			    	$scope.smeDaBriseJela = data.smeDaBriseJela;
			    	$scope.smeDaBrisePica = data.smeDaBrisePica;
			    	$scope.smeDaDodaJela = data.smeDaDodaJela;
			    	$scope.smeDaDodaPica = data.smeDaDodaPica;			    	
			    	if ($scope.smeDaBriseJela){
			    		for (var i = 0; i < data.dodataJela.length; i++){
			    			for (var j = 0; j<data.dodataJela[i].kolicina; j++){
			    				$scope.dodajJelo(data.dodataJela[i].jelo);
			    			}
			    		}
			    	} else {
			    		$scope.dodataJela = [];
			    	}
			    	
			    	if($scope.smeDaBrisePica){
			    		for (var i = 0; i < data.dodataPica.length; i++){
			    			for (var j = 0; j<data.dodataPica[i].kolicina; j++){
			    				$scope.dodajPice(data.dodataPica[i].pice);			
			    			}
			    		}
			    	} else {
			    		$scope.dodataPica = [];
			    	}
		    	}).error(function(data){
		    		alert("los klik na izmeni")
		    	});
		    }
		    
			$scope.setTab(0);
			// za izmeenu podataka
			$scope.izmeniKonobaraPodaci = function(){
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanKonobar.id,
				}
				var str = JSON.stringify(gost);
				izmeniKonobarServis.izmeni(str).success(function(data) {
					// TODO: ispravljeno ovo, ali mi je malo sumnjivo
					logovanjeServis.ulogujKorisnika(data).success(function(data) {
						$scope.ulogovanKonobar = data;
						$scope.osveziPrikazZaIzmenu($scope.ulogovanKonobar);

						});
						$location.path('/konobar');
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			// Kliknuo na detalje kalendar
			
			$scope.danasnjiDatum = new Date();
			$scope.danasnjiDan = $scope.danasnjiDatum.getDay();
			
			$scope.prikaziDan = -1;
			$scope.prikaziSmene = function(index){
				if ($scope.prikaziDan == index){
					$scope.prikaziDan = -1;
				} else {
					$scope.prikaziDan = index;
				}
				
			}
			
			$scope.stringuj = function(s){
				return s.getDate() + "-" + (s.getMonth()+1) + "-"+ s.getFullYear();
			}
			
			$scope.setuj = function (){
				$scope.datumPonedeljakStr = $scope.stringuj($scope.datumPonedeljak);
				$scope.datumUtorakStr = $scope.stringuj($scope.datumUtorak);
				$scope.datumSredaStr = $scope.stringuj($scope.datumSreda);
				$scope.datumCetvrtakStr = $scope.stringuj($scope.datumCetvrtak);
				$scope.datumPetakStr = $scope.stringuj($scope.datumPetak);
				$scope.datumSubotaStr = $scope.stringuj($scope.datumSubota);
				$scope.datumNedeljaStr = $scope.stringuj($scope.datumNedelja);
			}
			
			var tomorrow = new Date();
			$scope.datumNedelja = new Date();
			$scope.datumSubota = new Date();
			$scope.datumPetak = new Date();
			$scope.datumCetvrtak = new Date();
			$scope.datumSreda = new Date();
			$scope.datumUtorak = new Date();
			$scope.datumPonedeljak = new Date();
			
			
			if ($scope.danasnjiDan == 1){
				$scope.datumPonedeljak = $scope.danasnjiDatum;
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 2){
				$scope.datumUtorak = $scope.danasnjiDatum;
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 3){
				$scope.datumSreda = $scope.danasnjiDatum;
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 4){
				$scope.datumCetvrtak = $scope.danasnjiDatum;
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 5){
				$scope.datumPetak = $scope.danasnjiDatum;
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 6){
				$scope.datumSubota = $scope.danasnjiDatum;
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 7){
				$scope.datumNedelja = $scope.danasnjiDatum;
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} 
			
			
			// za izmenu lozinke
			$scope.izmeniLozinku = function (){
				if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
					var gost = {
						id : $scope.ulogovanKonobar.id,
						sifraStara : $scope.staraLozinka,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniKonobarServis.izmeniLozinku(str).success(function (data){
						$scope.staraLozinka = "";
						$scope.novaLozinka = "";
						$scope.novaLozinkaPotvrda = "";
						alert("Uspesno promenjena lozinka");
						$location.path('/konobar');
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
			}
			

			
			
			
			
		}else{
			alert("Morate se prvo ulogovati");
			window.location.href = "logovanje.html";
		}
	});
})