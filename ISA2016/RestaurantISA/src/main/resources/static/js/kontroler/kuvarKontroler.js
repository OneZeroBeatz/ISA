var kuvarKontroler = angular.module('restoranApp.kuvarKontroler', []);

kuvarKontroler.controller('kuvarCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniKuvarServis){
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
			$scope.ulogovanKuvar = data;
			$scope.imeIzmena = data.ime;
			$scope.prezimeIzmena = data.prezime;
			$scope.emailIzmena = data.email;

			$scope.setTab = function(newTab){
		    	$scope.tab = newTab;
		    };

		    $scope.isSet = function(tabNum){   
		    	return $scope.tab === tabNum;
		    };
			
			$scope.setTab(0);
			
			$scope.izmeniKuvaraPodaci = function(){
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanKuvar.id,
					sifra : $scope.ulogovanKuvar.sifra
				}
				
				var str = JSON.stringify(gost);
					
				izmeniKuvarServis.izmeni(str).success(function(data) {
						$location.path('/kuvar');
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			
			
			
			
		}else{
			alert("Morate se prvo ulogovati");
			window.location.href = "logovanje.html";
		}
	});
})