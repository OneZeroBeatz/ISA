var sankerKontroler = angular.module('restoranApp.sankerKontroler', []);

sankerKontroler.controller('sankerCtrl', function($scope, $route, $location, logovanjeServis, gostGlavnaStranaServis, izmeniSankerServis){
	
	$scope.osveziPrikazZaIzmenu = function (sanker){
		$scope.imeIzmena = sanker.ime;
		$scope.prezimeIzmena = sanker.prezime
		$scope.emailIzmena = sanker.email
	}
	
	
	
	//TODO: Kada porudzbina nema pica ne prikazati je
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		if(data != ""){
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
					logovanjeServis.ulogujKorisnika(data).success(function(data) {
						$scope.ulogovanKuvar = data;
						$scope.osveziPrikazZaIzmenu($scope.ulogovanSanker);

						});
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
						sifraStara : $scope.staraLozinka,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniSankerServis.izmeniLozinku(str).success(function (data){
						$scope.staraLozinka = "";
						$scope.novaLozinka = "";
						$scope.novaLozinkaPotvrda = "";
						alert("Uspesno promenjena lozinka");
						$location.path('/sanker');
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
				
			}
			
			
			// UCITAVANJE PORUDZBINA
			$scope.porudzbine = [];
			izmeniSankerServis.ucitajPorudzbine($scope.ulogovanSanker).success(function(data) {
				$scope.porudzbine = data;
				if ($scope.porudzbine.length == 0){
					alert("Nema raspolozivih porudzbina");
					$scope.setTab(0);
				}
				
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			// UCITAVANJE MOGUCIH PORUDZBINA
			$scope.klasifikovanePorudzbine = [];
			izmeniSankerServis.ucitajPorudzbineKlasifikovane($scope.ulogovanSanker).success(function(data) {
				$scope.klasifikovanePorudzbine = data;	
				//TODO: Hendluj ako je prazna neka od lista
			}).error(function (data){
				alert("Neuspelo ucitavanje mogucih porudzbina");
			});
			
			// Kliknuce na detalji			
			$scope.picaKliknutePorudzbine = [];
			$scope.kliknuoNaDetalji = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknutePorudzbine = data;
					if ($scope.show == porudzbina.id)
						$scope.show = -1;
					else
						$scope.show = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			
			// Kliknuce na detalji moguce		
			$scope.picaKliknuteMogucePorudzbine = [];
			$scope.kliknuoNaDetaljiMoguce = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknuteMogucePorudzbine = data;
					if ($scope.showMoguce == porudzbina.id)
						$scope.showMoguce = -1;
					else
						$scope.showMoguce = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}

			// Kliknuce na detalji prihvacene		
			$scope.picaKliknutePrihvacenePorudzbine = [];
			$scope.kliknuoNaDetaljiPrihvacene = function (porudzbina){
				izmeniSankerServis.ucitajPicaPorudzbine(porudzbina).success(function(data){
					$scope.picaKliknutePrihvacenePorudzbine = data;
					if ($scope.showPrihvacene == porudzbina.id)
						$scope.showPrihvacene = -1;
					else
						$scope.showPrihvacene = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			
			
			// PROMENJEN KONOBAR
		    $scope.promenjenSanker = function(){
		    	$scope.odabranSanker = $scope.selektovaniSanker;
		    	alert($scope.selektovaniSanker.ime);
		    }
			
			// UCITAJ KONOBARE RESTORANA
			$scope.sankeriRestorana = [];
			izmeniSankerServis.ucitajSankerRestorana($scope.ulogovanSanker).success(function(data) {

				$scope.sankeriRestorana = data;
				}).error(function (data){
				alert("Neuspelo ucitavanje konobara");
			});

			// Kliknuo prihvati
			$scope.prihvati = function (porudzbina){
				var sanKon = {
					sanker: $scope.ulogovanSanker,
					porudzbina: porudzbina	
				}
				izmeniSankerServis.prihvatiPorudzbinu(sanKon).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Neuspesno prihvacena ponuda.");
				});

			}
			// kliknuo zavrsi
			$scope.zavrsi = function (porudzbina){
				izmeniSankerServis.zavrsiPorudzbinu(porudzbina).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("PorudzbinaNeuspesnoZavrsena")
				});
				
			}
			
			
			
			
			
		}else{
			alert("Morate se prvo ulogovati");
			window.location.href = "logovanje.html";
		}
	});
})