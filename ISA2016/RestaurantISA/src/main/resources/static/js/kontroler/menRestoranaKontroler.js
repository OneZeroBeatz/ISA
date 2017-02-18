var menRestoranaKontroler = angular.module('restoranApp.menRestoranaKontroler', []);

menRestoranaKontroler.controller('menadzerRestoranaCtrl', function(gostGlavnaStranaServis, $scope, menRestoranaServisS) {
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
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
				alert("uspeo restoran!");
				$scope.restoran = data;
			}).error(function(data) {
				alert("nisi uspeo restoran!");
			});
		}else{
			alert("Niko nije ulogovan");
			// redirektuj!
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

	
	$scope.dodajRadnika = function(){
		/*
		var ponudjac = {
			id : $scope.ulogovanKorisnik.id,
			ime : $scope.imeIzmena,
			prezime : $scope.prezimeIzmena,
			email : $scope.emailIzmena,
			sifra : $scope.staraLozinka
			// DOPUNITI, PROVERITI...
			//sifra : $scope.novaLozinka
		}
		// neka pozove ocitavanje tabele ispod...(da doda novog radnika u listu)
		var str = JSON.stringify(ponudjac);
		
		ponudjacServisS.izmeni(str).success(function(data) {
			alert("uspeo!");
		}).error(function(data) {
			alert("nisi uspeo!");
		});
		*/
	}
	
});