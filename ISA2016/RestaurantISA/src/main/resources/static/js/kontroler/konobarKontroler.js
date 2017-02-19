var konobarKontroler = angular.module('restoranApp.konobarKontroler', []);

konobarKontroler.controller('konobarCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniKonobarServis){
	
	$scope.osveziPrikazZaIzmenu = function (konobar){
		$scope.imeIzmena = konobar.ime;
		$scope.prezimeIzmena = konobar.prezime
		$scope.emailIzmena = konobar.email
	}
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
			//TODO mora da se uloguje opet da bi skontao podatke
			$scope.ulogovanKonobar = data;

			$scope.osveziPrikazZaIzmenu($scope.ulogovanKonobar);
			
			$scope.setTab = function(newTab){
		    	$scope.tab = newTab;
		    };

		    $scope.isSet = function(tabNum){  
		    	return $scope.tab === tabNum;
		    };
			
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