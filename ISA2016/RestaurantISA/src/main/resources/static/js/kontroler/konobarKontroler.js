var konobarKontroler = angular.module('restoranApp.konobarKontroler', []);

konobarKontroler.controller('konobarCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniKonobarServis){
	
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
			//TODO mora da se uloguje opet da bi skontao podatke
			$scope.ulogovanKonobar = data;
			// Ucitaj jela u kombobox
			
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
					
					// Kliknuce na detalji
					$scope.jelaKliknutePorudzbine = [];
					$scope.picaKliknutePorudzbine = [];
					$scope.show = -1;
					$scope.kliknuoNaDetalji = function (porudzbina){
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
		    		alert("Izvrsene izmene");
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
						//TODO: doznaka i clear
						$location.path('/konobar');
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			// za izmenu lozinke
			
			$scope.izmeniLozinku = function (){
				if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
					var gost = {
						id : $scope.ulogovanKonobar.id,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniKonobarServis.izmeniLozinku(str).success(function (data){
						//TODO: doznaka i clear
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