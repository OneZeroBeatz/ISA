var ponudjacKontroler = angular.module('restoranApp.ponudjacKontroler', []);


ponudjacKontroler.controller('ponudjacCtrl', function(gostGlavnaStranaServis, $scope, ponudjacServisS) {
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
			$scope.ulogovanKorisnik = data;
			$scope.imeIzmena = data.ime;
			$scope.prezimeIzmena = data.prezime;
			$scope.emailIzmena = data.email;
			$scope.staraLozinka = data.sifra;
			
			// AKTIVNE PORUDZBINE
			// Ponudjac nije slao ponudu:
			ponudjacServisS.izlistajPorudzbineBezPonude(data).success(function(data) {
				$scope.porudzbineMen = data;
				
			}).error(function(data) {
			});
			
			$scope.prikaziDetalje = function(porudzbina){
				ponudjacServisS.izlistajStavkePorudzbine(porudzbina).success(function(data) {
					$scope.stavke = data;
				}).error(function(data) {
				});
				
				$scope.selektovanaPorudzbinaId = porudzbina.id;
			}
			
			$scope.vidljivosPorudzbine = function(id){
				return $scope.selektovanaPorudzbinaId === id;
			}
			
			$scope.slanjePonude = function(porudzbina){
				var pon = {
					porudzbinamenadzer : porudzbina,
					ponudjac : data,
					cena : 'cena'+$scope.porudzbina.id,//$scope.cenaPonude,
					rokisporuke : $scope.rokIsporuke,
					garancija : $scope.garancija
				}
				var str = JSON.stringify(pon);
				ponudjacServisS.posaljiPonudu(str);
			}
			
			// Ponudjac je poslao ponudu ali je moguca izmena:
			
			ponudjacServisS.izlistajPorudzbineSaPonudom(data).success(function(data) {
				$scope.ponudePonudjaca = data;
			}).error(function(data) {
			});
			
			$scope.prikaziDetaljePon = function(porudzbina){
				ponudjacServisS.izlistajStavkePorudzbine(porudzbina).success(function(data) {
					$scope.stavkePon = data;
				}).error(function(data) {
				});
				
				$scope.selektovanaPorudzbinaPonId = porudzbina.id;
			}
			
			$scope.vidljivosPorudzbinePon = function(id){
				return $scope.selektovanaPorudzbinaPonId === id;
			}
			
			$scope.izmenaPonude = function(ponuda){
				var pon = {
					porudzbinamenadzer : ponuda.porudzbinamenadzer,
					ponudjac : ponuda.ponudjac,
					cena : ponuda.cena,
					rokisporuke : ponuda.rokisporuke,
					garancija : ponuda.garancija
					
					/*
					cena : $scope.cenaPonude,
					rokisporuke : $scope.rokIsporuke,
					garancija : $scope.garancija
					*/
				}
				var str = JSON.stringify(pon);
				ponudjacServisS.izmeniPonudu(str);
			}
		}else{
			alert("Niko nije ulogovan");
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
	
	$scope.isVisible = function(id){
		return $scope.izmeniP === id;
	}
	
	$scope.izmeniPonudu = function(idPonude){		
		$scope.izmeniP = idPonude;
	}
	
	$scope.setTab(1);
	
	$scope.izmeniPonudjaca = function(){
		
		var ponudjac = {
			id : $scope.ulogovanKorisnik.id,
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			sifra : $scope.staraLozinka
			// DOPUNITI, PROVERITI...
			//sifra : $scope.novaLozinka
		}
		
		var str = JSON.stringify(ponudjac);
		
		ponudjacServisS.izmeni(str).success(function(data) {
			alert("uspeo!");
		}).error(function(data) {
			alert("nisi uspeo!");
		});
	}
	
	
	$scope.izlistajPonude = function(){
		
		var ponudjac = {
			id : $scope.ulogovanKorisnik.id,
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			sifra : $scope.staraLozinka
			// DOPUNITI, PROVERITI...
			//sifra : $scope.novaLozinka
		}
		
		var str = JSON.stringify(ponudjac);
		
		ponudjacServisS.izlistaj(str).success(function(data) {
			alert("uspeo izl!");
			$scope.items = data;
		}).error(function(data) {
			alert("nisi uspeo izl!");
		});
	}
	
	
});