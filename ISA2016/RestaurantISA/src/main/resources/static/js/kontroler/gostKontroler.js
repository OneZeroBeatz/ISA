var gostKontroler = angular.module('restoranApp.gostGlavnaStranaKontroler', []);

gostKontroler.controller('gostCtrl', function($scope, $location, gostGlavnaStranaServis, izmeniGostaServis, menRestoranaServisS, $window, menSistemaServis){
		
		gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
			if(data.message == "NekoNaSesiji"){
				$scope.ulogovanGost = data.obj;
				$scope.imeIzmena = data.obj.ime;
				$scope.prezimeIzmena = data.obj.prezime;
				$scope.emailIzmena = data.obj.email;
				
				var gost = {
						id : data.obj.id,
						ime : data.obj.ime,
						prezime : data.obj.prezime,
						email : data.obj.email,
						sifra : data.obj.sifra
					}
				
				var str = JSON.stringify(gost);
				
				izmeniGostaServis.izlistajZahteveZaPrij(gost).success(function(data){
					$scope.zahteviZaPrijateljstvo = data;
					console.log(data);
				}).error(function(data) {
				});
				
				izmeniGostaServis.izlistajPrijateljeNeprijatelje(gost).success(function(data){
					$scope.prijateljiNeprijatelji = data;
				}).error(function(data) {
				});
				
				menSistemaServis.izlistajRestorane().success(function(data){
	    			$scope.restorani = data;
	    			if(data.length == 0){
	    				alert("Nema raspolozivih restorana");
	    				$scope.setTab(0);
	    			}
	    		}).error (function (data){
	    			alert("Neuspesno ucitavanje restorana");
	    		});
				
				/// SASA RADIO DO ELSA
				$scope.poseteGosta = [];
				$scope.show = -1;
				$scope.odabranaPoseta = null;
				izmeniGostaServis.ucitajPoseteGosta($scope.ulogovanGost).success(function (data){
					$scope.poseteGosta = data;
				}).error(function (data){
					alert("Neuspelo ucitavanje poseta");
				});
				
			}else{
				$window.location.href = '/';
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
		
		$scope.ukloniPrijatelja = function(prij){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							prijatelj: prij 
						}				
						izmeniGostaServis.ukloniPrijatelja(parameter).success(function(data) {
							$scope.prijateljiNeprijatelji = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.prihvatiZahtev = function(prij){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							prijatelj: prij 
						}					
						izmeniGostaServis.prihvatiZahtev(parameter).success(function(data) {
							$scope.zahteviZaPrijateljstvo = data;
							
							gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
								izmeniGostaServis.izlistajPrijateljeNeprijatelje(data).success(function(data){
									$scope.prijateljiNeprijatelji = data;
								}).error(function(data) {
								});								
							});
						}).error(function(data) {						
						});
				}
			});
		}
		
		$scope.odbijZahtev = function(prij){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							prijatelj: prij 
						}					
						izmeniGostaServis.odbijZahtev(parameter).success(function(data) {
							$scope.zahteviZaPrijateljstvo = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.pretraziPravePrijatelje = function(){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							paramPretrageIme: $scope.searchRealFriendIme,
							paramPretragePrz: $scope.searchRealFriendPrz
						}						
						izmeniGostaServis.pretraziPravePrijatelje(parameter).success(function(data) {
							$scope.prijateljiNeprijatelji = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.pretraziPrijatelje = function(){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							paramPretrageIme: $scope.searchFriendIme,
							paramPretragePrz: $scope.searchFriendPrz
						}						
						izmeniGostaServis.pretraziPrijatelje(parameter).success(function(data) {
							$scope.filtriraniPrijatelji = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.dodajPrijatelja = function(prij){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							prijatelj: prij,
							paramPretrageIme: $scope.searchFriendIme,
							paramPretragePrz: $scope.searchFriendPrz
						}						
						izmeniGostaServis.dodajPrijatelja(parameter).success(function(data) {
							$scope.filtriraniPrijatelji = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.otkaziZahtev = function(prij){

			gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
				if(data != ""){
					var parameter = {
							gost: data.obj,
							prijatelj: prij,
							paramPretrageIme: $scope.searchFriendIme,
							paramPretragePrz: $scope.searchFriendPrz
						}						
						izmeniGostaServis.otkaziZahtev(parameter).success(function(data) {
							$scope.filtriraniPrijatelji = data;
						}).error(function(data) {							
						});
				}
			});
		}
		
		$scope.logOut = function(){

			gostGlavnaStranaServis.logOut().success(function(data) {
				if(data.message == "Izlogovan"){
					$window.location.href = '/';
				}else{
				}
			});
		}
		
		$scope.sortTablePr = function(n) {
			  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
			  table = document.getElementById("myPrijatelji");
			  switching = true;
			  dir = "asc"; 
			  while (switching) {
			    switching = false;
			    rows = table.getElementsByTagName("TR");
			    for (i = 1; i < (rows.length - 1); i++) {
			      shouldSwitch = false;
			      x = rows[i].getElementsByTagName("TD")[n];
			      y = rows[i + 1].getElementsByTagName("TD")[n];
			      if (dir == "asc") {
			        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      } else if (dir == "desc") {
			        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      }
			    }
			    if (shouldSwitch) {
			      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			      switching = true;
			      switchcount ++;      
			    } else {
			      if (switchcount == 0 && dir == "asc") {
			        dir = "desc";
			        switching = true;
			      }
			    }
			  }
			}
		
		$scope.sortTableNpr = function(n) {
			  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
			  table = document.getElementById("myNeprijatelji");
			  switching = true;
			  dir = "asc"; 
			  while (switching) {
			    switching = false;
			    rows = table.getElementsByTagName("TR");
			    for (i = 1; i < (rows.length - 1); i++) {
			      shouldSwitch = false;
			      x = rows[i].getElementsByTagName("TD")[n];
			      y = rows[i + 1].getElementsByTagName("TD")[n];
			      if (dir == "asc") {
			        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      } else if (dir == "desc") {
			        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      }
			    }
			    if (shouldSwitch) {
			      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			      switching = true;
			      switchcount ++;      
			    } else {
			      if (switchcount == 0 && dir == "asc") {
			        dir = "desc";
			        switching = true;
			      }
			    }
			  }
			}

		$scope.sortTableZah = function(n) {
			  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
			  table = document.getElementById("myZahtevi");
			  switching = true;
			  dir = "asc"; 
			  while (switching) {
			    switching = false;
			    rows = table.getElementsByTagName("TR");
			    for (i = 1; i < (rows.length - 1); i++) {
			      shouldSwitch = false;
			      x = rows[i].getElementsByTagName("TD")[n];
			      y = rows[i + 1].getElementsByTagName("TD")[n];
			      if (dir == "asc") {
			        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      } else if (dir == "desc") {
			        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			          shouldSwitch= true;
			          break;
			        }
			      }
			    }
			    if (shouldSwitch) {
			      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			      switching = true;
			      switchcount ++;      
			    } else {
			      if (switchcount == 0 && dir == "asc") {
			        dir = "desc";
			        switching = true;
			      }
			    }
			  }
			}
		
		
		
		$scope.odaberiRestoran = function (rest){
			
			$scope.odabranRestoran = rest;
			if ($scope.showRest == rest.id){
				$scope.showRest = -1;
			} else {
				$scope.showRest = rest.id;
			}
			
		}
		
		$scope.prikaz = false;
		
		$scope.isVisible = function(){	// Proveriti da li treba oznaka...
			return $scope.prikaz;
		}
		
		$scope.prikaziInformacije = function(oznaka, restoran){
			$scope.prikaz = true;
			
			var sto = {
				oznaka : oznaka,
				restoran : restoran

			}
			var str = JSON.stringify(sto);
			menRestoranaServisS.izlistajSto(str).success(function(data) {
				$scope.sto = data;
				$scope.segmentStola = data.segment;
			}).error(function(data) {
			});
		}
		
		
		
		// SASA RADIO
		$scope.kliknuoOceni = function (item){
			
			$scope.odabranaPoseta = item;
			if ($scope.show == item.id){
				$scope.show = -1;
			} else {
				$scope.show = item.id;
			}
			
		}
		
		$scope.potvrdiOcenu = function (ocena){
			if(ocena == null){
				alert("Prvo odaberite ocenu")
			} else {
				var ocenaPosete = {
					ocena: ocena,
					poseta: $scope.odabranaPoseta
				}
				izmeniGostaServis.oceniPosetu(ocenaPosete).success(function(data){
					$scope.poseteGosta = data;
				}).error(function (data){
					alert("Neuspesno ocenjivanje");
				});
				
			}
		}
			
			
})