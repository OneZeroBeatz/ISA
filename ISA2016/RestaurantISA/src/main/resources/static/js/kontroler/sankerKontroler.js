var sankerKontroler = angular.module('restoranApp.sankerKontroler', []);

sankerKontroler.controller('sankerCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniSankerServis){
	
	$scope.osveziPrikazZaIzmenu = function (sanker){
		$scope.imeIzmena = sanker.ime;
		$scope.prezimeIzmena = sanker.prezime
		$scope.emailIzmena = sanker.email
	}
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
			//TODO mora da se uloguje opet da bi skontao podatke
			$scope.ulogovanSanker = data;

			$scope.osveziPrikazZaIzmenu($scope.ulogovanSanker);
			
			$scope.setTab = function(newTab){
		    	$scope.tab = newTab;
		    };

		    $scope.isSet = function(tabNum){  
		    	return $scope.tab === tabNum;
		    };
			
			$scope.setTab(0);
			// za izmeenu podataka
			$scope.izmeniSankeraPodaci = function(){
				var gost = {
					ime : $scope.imeIzmena,
					prezime : $scope.prezimeIzmena,
					email : $scope.emailIzmena,
					id : $scope.ulogovanSanker.id,
				}
				var str = JSON.stringify(gost);
				izmeniSankerServis.izmeni(str).success(function(data) {
						//TODO: doznaka i clear
						$location.path('/sanker');
					}).error(function(data) {
						alert("Neuspesne izmene!");
					});
			}
			
			// za izmenu lozinke
			
			$scope.izmeniLozinku = function (){
				if($scope.novaLozinka == $scope.novaLozinkaPotvrda){
					var gost = {
						id : $scope.ulogovanSanker.id,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniSankerServis.izmeniLozinku(str).success(function (data){
						//TODO: doznaka i clear
						$location.path('/sanker');
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
				
			}
			
			
			// UCITAVANJE PORUDZBINA
			
			izmeniSankerServis.ucitajPorudzbine($scope.ulogovanSanker).success(function(data) {
				$scope.porudzbine = data;
				if ($scope.porudzbine.length == 0){
					alert("Nema raspolozivih porudzbina");
					$scope.setTab(0);
				}
				alert("uspesno ucitao porudzbine");
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
		}else{
			alert("Morate se prvo ulogovati");
			window.location.href = "logovanje.html";
		}
	});
})