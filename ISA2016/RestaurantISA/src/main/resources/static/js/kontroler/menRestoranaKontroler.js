var menRestoranaKontroler = angular.module('restoranApp.menRestoranaKontroler', []);

menRestoranaKontroler.controller('menadzerRestoranaCtrl', function(gostGlavnaStranaServis, $scope, menRestoranaServisS) {
	
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		$scope.listaStavki = [];
		
		if(data != ""){
			var menRest = {
				id : data.id,
				ime : data.ime,
				prezime : data.prezime,
				email : data.email,
				sifra : data.sifra
			}
			
			var str = JSON.stringify(menRest);
			
			menRestoranaServisS.izlistajPonude(str).success(function(data) {		//????
				$scope.ponude = data;
			}).error(function(data) {
			});
			
			menRestoranaServisS.izlistajRestoran(str).success(function(data) {
				$scope.restoran = data;
				menRestoranaServisS.izlistajJela(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.jela = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema jela
					alert("Neuspesno izlistavanje jelovnika!");
				});
				
				menRestoranaServisS.izlistajPica(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.pica = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema pica
					alert("Neuspesno izlistavanje pica!");
				});
				
				menRestoranaServisS.izlistajDostupnePonudjace(data).success(function(data) {
					$scope.moguciPonudjaci = data;
				}).error(function(data) {
					alert("Neuspesno izlistavanje dostupnih ponudjaca!");
				});
				
				if($scope.restoran.brojredova != null && $scope.restoran.brojkolona != null){
					$scope.brojRedova = $scope.restoran.brojredova;
					$scope.brojKolona = $scope.restoran.brojkolona;
					
					for(i=0; i<$scope.brojRedova; i++){
						$scope.brRedova.push(i);
					}
					for(i=0; i<$scope.brojKolona; i++){
						$scope.brKolona.push(i);
					}
					
					menRestoranaServisS.izlistajStolove(data).success(function(data) {
						$scope.stolovi = data;
					}).error(function(data) {
						alert("Nema stolova...!");
					});
				}
			}).error(function(data) {
				alert("Neuspesno izlistavanje restorana!");
			});
			
			menRestoranaServisS.izlistajSveNamirnice().success(function(data) {
				$scope.listaNamirnica = data;
			}).error(function(data) {
				alert("Nece namirnice!");
			});
			
			menRestoranaServisS.izlistajSvaPica().success(function(data) {
				$scope.listaPica = data;
			}).error(function(data) {
				alert("Nece pica!");
			});
			
			$scope.objaviPorudzbinu = function(){
				var porMen = {
					menadzerrestorana : data,
					aktivnoOd : $scope.datumOd,
					aktivnoDo : $scope.datumDo
				}
				
				var stav = {
					porudzbinaMenadzer : porMen,
					stavke : $scope.listaStavki
				}
				var str = JSON.stringify(stav);
				menRestoranaServisS.dodajStavke(str).success(function(data) {
				});
			}
			
		}else{
			alert("Niko nije ulogovan");
			// TODO: Aca ~ Redirektuj!
		}
	});
	
	$scope.setTab = function(newTab){
    	$scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){   
    	return $scope.tab === tabNum;
    };
    
	$scope.changedValue = function(item) {
		$scope.zaPrikaz = [];
		for (var i = 0; i<$scope.news.length; i++){
			if ($scope.news[i].type == item)
				$scope.zaPrikaz.push($scope.news[i]);
		}
	};
	
	$scope.setTab(1);
	
	$scope.dodajJelo = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var jeloo = {
			naziv : $scope.nazivJela,
			opis : $scope.opisJela,
			cena : $scope.cenaJela,
			restoran : restoran
		}
		
		var str = JSON.stringify(jeloo);
		menRestoranaServisS.dodajJelo(str).success(function(data) {
			alert("Uspesno dodato jelo!");
		}).error(function(data) {
			alert("Jelo nije dodato!");
		});
	}
	
	$scope.dodajPice = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var picee = {
			naziv : $scope.nazivPica,
			opis : $scope.opisPica,
			cena : $scope.cenaPica,
			restoran : restoran
		}
		
		var str = JSON.stringify(picee);
		menRestoranaServisS.dodajPice(str).success(function(data) {
			alert("Uspesno dodato pice!");
		}).error(function(data) {
			alert("Pice nije dodato!");
		});
	}
	
	$scope.izmenaNazivaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			naziv : restoran.naziv,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniNazivRestorana(str).success(function(data) {
			alert("Uspesno izmenjen naziv!");
		}).error(function(data) {
			alert("Naziv nije izmenjen!");
		});
	}
	
	$scope.izmenaOpisaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			opis : restoran.opis,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniOpisRestorana(str).success(function(data) {
			alert("Uspesno izmenjen opis!");
		}).error(function(data) {
			alert("Opis nije izmenjen!");
		});
	}
	
	$scope.izmenaJela = function(jeloId){
		// TODO: Tabelarno...
	}
	
	$scope.odbrisiJelo = function(jeloId){
		var str = JSON.stringify(jeloId);
		menRestoranaServisS.izbrisiJelo(str).success(function(data) {
		}).error(function(data) {
		});
	}
	
	$scope.odbrisiPice = function(piceId){
		var str = JSON.stringify(piceId);
		menRestoranaServisS.izbrisiPice(str).success(function(data) {
		}).error(function(data) {	
		});
	}
	
	$scope.napraviKonfiguraciju = function(restoran) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		for(i=0; i<$scope.brojRedova; i++){
			$scope.brRedova.push(i);
		}
		for(i=0; i<$scope.brojKolona; i++){
			$scope.brKolona.push(i);
		}
		
		var sizeStolova = {
			id : restoran.id,
			brojredova : $scope.brojRedova,
			brojkolona : $scope.brojKolona
		}
		var str = JSON.stringify(sizeStolova);
		menRestoranaServisS.brojeviRedovaIKolona(str).success(function(data) {
			alert("broj redova i kolona dodat");
		}).error(function(data) {
			alert("dodat nije");
		});
		
		var tempStolovi = [];
		tempStolovi[0] = restoran.id;
		for(i=0; i<$scope.brojRedova; i++){
			for(j=0; j<$scope.brojKolona; j++){
				tempStolovi[i*$scope.brojKolona + j + 1] = i*$scope.brojKolona + j;
			}
		}
		
		var str = JSON.stringify(tempStolovi);
		menRestoranaServisS.napraviStolove(str).success(function(data) {
			$scope.stolovi = data;
			alert("stolovi napravljeni");
		}).error(function(data) {
			alert("stolovi nisu napravljeni");
		});
		
	}
	
	$scope.prikaz = false;
	
	$scope.isVisible = function(){	// Proveriti da li treba oznaka...
		return $scope.prikaz;
	}
	
	$scope.prikaziInformacije = function(oznaka, restoran){
		$scope.prikaz = true;
		
		var sto = {
			oznaka : oznaka,
			restoran : restoran

		}
		var str = JSON.stringify(sto);
		menRestoranaServisS.izlistajSto(str).success(function(data) {
			$scope.sto = data;
			$scope.segmentStola = data.segment;
		}).error(function(data) {
		});
	}
	
	$scope.dodajStavkuNamirnicu = function(){
		var stavka = {
			kolicina : $scope.kolNamirnica,
			pice : $scope.namirnicaStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajStavkuPice = function(){
		var stavka = {
			kolicina : $scope.kolPica,
			pice : $scope.piceStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajPonudjaca = function(ponudjac){
		var ponRest = {
			ponudjac : ponudjac,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.dodajPonudjaca(str);
		
	}
	
	$scope.registrujIDodajPonudjaca = function(){
		var pon = {
			ime : $scope.imePonudjaca,
			prezime : $scope.prezimePonudjaca,
			sifra : $scope.lozinkaPonudjaca,
			email : $scope.emailPonudjaca,
		}
		
		var ponRest = {
			ponudjac : pon,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.registrujIDodajPonudjaca(str);
		
	}
	
});