var menRestoranaKontroler = angular.module('restoranApp.menRestoranaKontroler', []);

menRestoranaKontroler.controller('menadzerRestoranaCtrl', function(gostGlavnaStranaServis, $scope, menRestoranaServisS) {
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		if(data != ""){
			var menRest = {
				id : data.id,
				ime : data.ime,
				prezime : data.prezime,
				email : data.email,
				sifra : data.sifra
			}
			
			var str = JSON.stringify(menRest);
			
			menRestoranaServisS.izlistajPonude(str).success(function(data) {
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
			}).error(function(data) {
				alert("Neuspesno izlistavanje restorana!");
			});
			
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
		//TODO: Tabelarno...
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
		
		var tempStolovi = [];
		tempStolovi[0] = restoran.id;
		for(i=0; i<$scope.brojRedova; i++){
			for(j=0; j<$scope.brojKolona; j++){
				tempStolovi[i*$scope.brojRedova + j + 1] = i*i + j;
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
			$scope.segmentStola = $scope.sto.segemnt;
		}).error(function(data) {
		});
	}
	
});