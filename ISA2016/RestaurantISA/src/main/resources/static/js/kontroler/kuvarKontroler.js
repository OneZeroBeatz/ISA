var kuvarKontroler = angular.module('restoranApp.kuvarKontroler', []);

kuvarKontroler.controller('kuvarCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniKuvarServis){
	
	$scope.osveziPrikazZaIzmenu = function (kuvar){
		$scope.imeIzmena = kuvar.ime;
		$scope.prezimeIzmena = kuvar.prezime
		$scope.emailIzmena = kuvar.email
	}
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
			//TODO mora da se uloguje opet da bi skontao podatke
			$scope.ulogovanKuvar = data;

			$scope.osveziPrikazZaIzmenu($scope.ulogovanKuvar);
			
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
				}
				var str = JSON.stringify(gost);
				var uspeo = false;
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