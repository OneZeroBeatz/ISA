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
			// Ucitaj jela u kombobox
			
			$scope.osveziPrikazZaIzmenu($scope.ulogovanKonobar);
			
			$scope.setTab = function(newTab){
				if (newTab == 2){
					izmeniKonobarServis.izlistajJela($scope.ulogovanKonobar).success(function(data){
						$scope.jela = data;
					}).error (function(data){
						alert("Neuspesno ucitana jela");
					});
					
					izmeniKonobarServis.izlistajPica($scope.ulogovanKonobar).success(function(data){
						$scope.pica = data;
					}).error (function(data){
						alert("Neuspesno ucitana pica");
					});
					izmeniKonobarServis.izlistajStolove($scope.ulogovanKonobar).success(function(data){
						$scope.stolovi = data;
					}).error (function(data){
						alert("Neuspesno ucitana pica");
					});		
					
					// UCITAVANJE PORUDZBINA
					$scope.porudzbine = [];
					izmeniKonobarServis.ucitajPorudzbine($scope.ulogovanKonobar).success(function(data) {
						$scope.porudzbine = data;
					}).error(function (data){
						alert("Neuspelo ucitavanje porudzbina");
					});
				}
		    	$scope.tab = newTab;
		    };
		    
		    // Dodaj u listu jela
		    $scope.dodataPica = [];
		    $scope.dodataJela = [];

		    $scope.dodataPicaId = [];
		    $scope.dodataJelaId = [];
		    
		    $scope.dodajJelo = function (){
		    	var jelo1 = {
		    		id : $scope.dodataJela.length+1, 
		    		jel: $scope.jelo
		    	};
		    	if (jelo1.jel == null){
		    		alert("Niste odabrali jelo");
		    	} else {
				    $scope.dodataJela.push(jelo1);
		    	}
		    }

		    $scope.dodajPice = function (){
		    	var pice1 = {
			    		id : $scope.dodataPica.length+1, 
			    		pic: $scope.pice
			    	};
		    	if (pice1.pic == null){
		    		alert("Niste odabrali pice");
		    	} else {
				    $scope.dodataPica.push(pice1);
		    	}
		    }
		    
		    
		    $scope.obrisiPice = function (pice){
		    	var index = $scope.dodataPica.indexOf(pice);
		    	$scope.dodataPica.splice(index, 1);
		    };
		    
		    $scope.obrisiJelo = function (jelo){
		    	var index = $scope.dodataJela.indexOf(jelo);
		    	$scope.dodataJela.splice(index, 1);
		    };
		    
		    $scope.isSet = function(tabNum){  
		    	return $scope.tab === tabNum;
		    };
			
		    $scope.dodajPorudzbinu = function (){
		    	if ($scope.sto == null){
		    		alert("Niste odabrali sto");
		    		return;
		    	}
		    	for (var i = 0 ; i< $scope.dodataJela.length ; i++){
		    		var jelo1 = {
				    	id : i+1, 
				    	jel: $scope.dodataJela[i].jel.id
				    };
		    		$scope.dodataJelaId.push(jelo1);
		    	}
	    	
		    	for (var i = 0 ; i< $scope.dodataPica.length ; i++){
		    		var pice1 = {
				    	id : i+1, 
				    	pic: $scope.dodataPica[i].pic.id
				    };
		    		$scope.dodataPicaId.push(pice1);
		    	}
	    	
		    	var jelaPica = {
		    		svaJela : $scope.dodataJelaId,
		    		svaPica : $scope.dodataPicaId,
		    		konobar : $scope.ulogovanKonobar,
		    		sto : $scope.sto
		    	};
		    	    	
		    	var jelaPicaStr = JSON.stringify(jelaPica);
				console.log(jelaPicaStr);
				
		    	izmeniKonobarServis.dodajPorudzbinu(jelaPicaStr).success(function (data){
		    		$scope.porudzbine = data;
		    		alert("Dodata porudzbina");
		    	}).error (function (data){
		    		alert("Neuspasno dodavanje porudzbine");	
		    	});	
		    	$scope.dodataJela = [];
		    	$scope.dodataPica = [];
		    	$scope.dodataJelaId = [];
		    	$scope.dodataPicaId = [];
		    }
		    
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