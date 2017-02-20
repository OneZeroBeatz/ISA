var gostKontroler = angular.module('restoranApp.gostGlavnaStranaKontroler', []);

gostKontroler.controller('gostCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniGostaServis){
		
		gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
			if(data != ""){
				$scope.ulogovanGost = data;
				$scope.imeIzmena = data.ime;
				$scope.prezimeIzmena = data.prezime;
				$scope.emailIzmena = data.email;
				
				var gost = {
						id : data.id,
						ime : data.ime,
						prezime : data.prezime,
						email : data.email,
						sifra : data.sifra
					}
				
				var str = JSON.stringify(gost);
				
				izmeniGostaServis.izlistajPrijateljeNeprijatelje(gost).success(function(data){
					$scope.prijateljiNeprijatelji = data;
				}).error(function(data) {
				});
				
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
		
		$scope.setTab(0);
		
		$scope.izmeniGostaPodaci = function(){
			var gost = {
				ime : $scope.imeIzmena,
				prezime : $scope.prezimeIzmena,
				email : $scope.emailIzmena,
				id : $scope.ulogovanGost.id,
				sifra : $scope.ulogovanGost.sifra
			}
			
			var str = JSON.stringify(gost);
				
			izmeniGostaServis.izmeni(str).success(function(data) {
					$location.path('/gostGlavnaStrana');
				}).error(function(data) {
					alert("Neuspesne izmene!");
				});
		}
		
		$scope.ukloniPrijatelja = function(id){

			var str = JSON.stringify(id);
			izmeniGostaServis.ukloniPrijatelja(str).success(function(data) {
				//RADI!
			}).error(function(data) {
				
			});
		}
})