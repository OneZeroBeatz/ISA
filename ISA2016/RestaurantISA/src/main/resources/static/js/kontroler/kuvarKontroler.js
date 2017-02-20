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
			// za izmeenu podataka
			$scope.izmeniKuvaraPodaci = function(){
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanKuvar.id,
				}
				var str = JSON.stringify(gost);
				izmeniKuvarServis.izmeni(str).success(function(data) {
						//TODO: doznaka i clear
						$location.path('/kuvar');
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			// za izmenu lozinke
			
			$scope.izmeniLozinku = function (){
				if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
					var gost = {
						id : $scope.ulogovanKuvar.id,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniKuvarServis.izmeniLozinku(str).success(function (data){
						//TODO: doznaka i clear
						$location.path('/kuvar');
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