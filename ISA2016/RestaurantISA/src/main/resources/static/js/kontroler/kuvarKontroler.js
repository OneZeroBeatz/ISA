var kuvarKontroler = angular.module('restoranApp.kuvarKontroler', []);

kuvarKontroler.controller('kuvarCtrl', function($scope, $location, logovanjeServis, gostGlavnaStranaServis, izmeniKuvarServis){
	
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
			
		    
		    // KLIKNUO NA DETALJI PRIHVACENE
		    
		    
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
					logovanjeServis.ulogujKorisnika(data).success(function(data) {
						$scope.ulogovanKuvar = data;
						$scope.osveziPrikazZaIzmenu($scope.ulogovanKuvar);

						});	
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
						sifraStara : $scope.staraLozinka,
						sifra : $scope.novaLozinkaPotvrda
					}
					var str = JSON.stringify(gost);
					
					izmeniKuvarServis.izmeniLozinku(str).success(function (data){
						$scope.staraLozinka = "";
						$scope.novaLozinka = "";
						$scope.novaLozinkaPotvrda = "";
						alert("Uspesno promenjena lozinka");
						$location.path('/kuvar');
					}).error(function (data){
						alert("Neuspesne izmene!");
					});
				} else {
					alert ("Ne podudaraju se nove lozinke")
				}
				
			}
			//TODO: Obrisati funkcije koje su visak iz kuvar kontrolera i servisa
			
			// UCITAVANJE PORUDZBINA
			$scope.porudzbine = [];
			izmeniKuvarServis.ucitajPorudzbine($scope.ulogovanKuvar).success(function(data) {
				
				$scope.porudzbine = data;
				if ($scope.porudzbine.length == 0){
					alert("Nema raspolozivih porudzbina");
					$scope.setTab(0);
				}
				
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			// UCITAVANJE KLAS PORUDZBINA
			$scope.klasifikovanePorudzbine = [];
			izmeniKuvarServis.ucitajPorudzbineKlasifikovane($scope.ulogovanKuvar).success(function(data) {
				$scope.klasifikovanePorudzbine = data;	
			}).error(function (data){
				alert("Neuspelo ucitavanje porudzbina");
			});
			
			
			// Kliknuce na detalji moguce
			$scope.jelaKliknuteMogucePorudzbine = [];
			$scope.kliknuoNaDetaljiMoguce = function (porudzbina){
				var porKur = {
					porudzbina: porudzbina,
					kuvar: $scope.ulogovanKuvar
				}
				
				izmeniKuvarServis.ucitajJelaPorudzbine(porKur).success(function(data){
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
			$scope.jelaKliknutePrihvacenePorudzbine = [];
			$scope.kliknuoNaDetaljiPrihvacene = function (porudzbina){
				var porKur = {
					porudzbina: porudzbina,
					kuvar: $scope.ulogovanKuvar
				}
				
				izmeniKuvarServis.ucitajJelaPorudzbine(porKur).success(function(data){
					$scope.picaKliknutePrihvacenePorudzbine = data;
					if ($scope.showPrihvacene == porudzbina.id)
						$scope.showPrihvacene = -1;
					else
						$scope.showPrihvacene = porudzbina.id;
					
				}).error(function (data){
					alert("Neuspelo ucitavanje detalja");
				});
			}
			

			// PROMENJEN KUVAR
		    $scope.promenjenKuvar = function(){
		    	$scope.odabranKuvar = $scope.selektovaniKuvar;
		    	alert($scope.selektovaniKuvar.ime);
		    }
			
			// UCITAJ KUVARE RESTORANA
			$scope.kuvariRestorana = [];
			izmeniKuvarServis.ucitajKuvareRestorana($scope.ulogovanKuvar).success(function(data) {
				$scope.kuvariRestorana = data;
				}).error(function (data){
				alert("Neuspelo ucitavanje kuvara");
			});
			
			
			/// Kliknuo prihvati
			$scope.prihvati = function (porudzbina){
				var sanKuv = {
						kuvar: $scope.ulogovanKuvar,
						porudzbina: porudzbina	
				}
				izmeniKuvarServis.prihvatiPorudzbinu(sanKuv).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Nemoguce preuzeti ponudu.");
				});
			}
			
			$scope.zavrsi = function (porudzbina){
				var sanKuv = {
						kuvar: $scope.ulogovanKuvar,
						porudzbina: porudzbina	
				}
				izmeniKuvarServis.zavrsiPorudzbinu(sanKuv).success(function(data){
					$scope.klasifikovanePorudzbine = data;	
				}).error(function(data){
					alert("Nemoguce zavrsiti porudzbinu");
				});
			}
			
			
			
		}else{
			alert("Morate se prvo ulogovati");
			window.location.href = "logovanje.html";
		}
	});
})