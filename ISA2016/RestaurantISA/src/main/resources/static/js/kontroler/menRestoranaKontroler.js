var menRestoranaKontroler = angular.module('restoranApp.menRestoranaKontroler', []);

menRestoranaKontroler.controller('menadzerRestoranaCtrl', function(gostGlavnaStranaServis, $scope, menRestoranaServisS) {
	$scope.radniciTip = ["Konobar", "Kuvar", "Sanker"];
	
	gostGlavnaStranaServis.koJeNaSesiji().success(function(data) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		$scope.listaStavki = [];
		
		if(data != ""){
			var menRest = {
				id : data.id,
				ime : data.ime,
				prezime : data.prezime,
				email : data.email,
				sifra : data.sifra
			}
			
			var str = JSON.stringify(menRest);
			
			menRestoranaServisS.izlistajPonude(str).success(function(data) {		//????
				$scope.ponude = data;
			}).error(function(data) {
			});
			
			menRestoranaServisS.izlistajRestoran(str).success(function(data) {
				$scope.restoran = data;
				menRestoranaServisS.izlistajJela(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.jela = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema jela
					alert("Neuspesno izlistavanje jelovnika!");
				});
				
				menRestoranaServisS.izlistajPica(data).success(function(data) {
					// TODO: Aca ~ Refesh
					$scope.pica = data;
				}).error(function(data) {
					// TODO: Aca ~ Uraditi slucaj ako nema pica
					alert("Neuspesno izlistavanje pica!");
				});
				
				menRestoranaServisS.izlistajDostupnePonudjace(data).success(function(data) {
					$scope.moguciPonudjaci = data;
				}).error(function(data) {
					alert("Neuspesno izlistavanje dostupnih ponudjaca!");
				});
				
				if($scope.restoran.brojredova != null && $scope.restoran.brojkolona != null){
					$scope.brojRedova = $scope.restoran.brojredova;
					$scope.brojKolona = $scope.restoran.brojkolona;
					
					for(i=0; i<$scope.brojRedova; i++){
						$scope.brRedova.push(i);
					}
					for(i=0; i<$scope.brojKolona; i++){
						$scope.brKolona.push(i);
					}
					
					menRestoranaServisS.izlistajStolove(data).success(function(data) {
						$scope.stolovi = data;
					}).error(function(data) {
						alert("Nema stolova...!");
					});
					
					$scope.promenaRadnika = function(x){
						alert(x);
					}
				}
				
				menRestoranaServisS.izlistajSmene($scope.restoran).success(function(data) {
					$scope.smeneRestorana = data;
				}).error(function(data) {
				});
			}).error(function(data) {
				alert("Neuspesno izlistavanje restorana!");
			});
			
			$scope.dodajSmenu = function(){

				var smenaa = {
					vremeod : $scope.vremeSmeneOd,
					vremedo : $scope.vremeSmeneDo,
					restoran : $scope.restoran
				}
				
				var str = JSON.stringify(smenaa);
				menRestoranaServisS.dodajSmenu(str);
			}
			
			$scope.promenjenTip = function(){
				$scope.selektovanTip = $scope.tipRadnika;
				
				var danString = "";
				if($scope.prikaziDan == 1){
					danString = "PONEDELJAK";
				}else if($scope.prikaziDan == 2){
					danString = "UTORAK";
				}else if($scope.prikaziDan == 3){
					danString = "SREDA";
				}else if($scope.prikaziDan == 4){
					danString = "CETVRTAK";
				}else if($scope.prikaziDan == 5){
					danString = "PETAK";
				}else if($scope.prikaziDan == 6){
					danString = "SUBOTA";
				}else if($scope.prikaziDan == 7){
					danString = "NEDELJA";
				}
				
				
				if($scope.selektovanTip == "Konobar"){	
					var smenaUDanu = {
						restoran : $scope.restoran,
						danUNedelji : danString,
					}
						
					var str = JSON.stringify(smenaUDanu);
					menRestoranaServisS.izlistajSmeneKonobara(str).success(function(data) {
						$scope.smeneZaKonobare = data;
						// TODO:
					}).error(function(data) {
					});
				}else if($scope.selektovanTip == "Kuvar"){
					var smenaUDanu = {
						restoran : $scope.restoran,
						danUNedelji : danString,
					}
						
					var str = JSON.stringify(smenaUDanu);
					menRestoranaServisS.izlistajSmeneKuvara(str).success(function(data) {
						$scope.smeneZaKuvare = data;
						
						menRestoranaServisS.dostupniKuvari(str).success(function(data1) {
							if($scope.prikaziDan == 1){
								$scope.dostupniKuvPonedeljak = data1;
							}else if($scope.prikaziDan == 2){
								// TODO:
							}else if($scope.prikaziDan == 3){
								// TODO:
							}else if($scope.prikaziDan == 4){
								// TODO:
							}else if($scope.prikaziDan == 5){
								// TODO:
							}else if($scope.prikaziDan == 6){
								// TODO:
							}else if($scope.prikaziDan == 7){
								// TODO:
							}
						});
					}).error(function(data) {
					});
				}else if($scope.selektovanTip == "Sanker"){
					var smenaUDanu = {
						restoran : $scope.restoran,
						danUNedelji : danString,
					}
						
					var str = JSON.stringify(smenaUDanu);
					menRestoranaServisS.izlistajSmeneSankera(str).success(function(data) {
						$scope.smeneZaSankere = data;
						// TODO:
					}).error(function(data) {
					});
				}

			}
			
			
			
			
			
			menRestoranaServisS.izlistajSveNamirnice().success(function(data) {
				$scope.listaNamirnica = data;
			}).error(function(data) {
				alert("Nece namirnice!");
			});
			
			menRestoranaServisS.izlistajSvaPica().success(function(data) {
				$scope.listaPica = data;
			}).error(function(data) {
				alert("Nece pica!");
			});
			
			$scope.objaviPorudzbinu = function(){
				var porMen = {
					menadzerrestorana : data,
					aktivnoOd : $scope.datumOd,
					aktivnoDo : $scope.datumDo
				}
				
				var stav = {
					porudzbinaMenadzer : porMen,
					stavke : $scope.listaStavki
				}
				var str = JSON.stringify(stav);
				menRestoranaServisS.dodajStavke(str).success(function(data) {
				});
			}
			
			
			// Kalendar...
			$scope.danasnjiDatum = new Date();
			$scope.danasnjiDan = $scope.danasnjiDatum.getDay();
			
			$scope.prikaziDan = -1;
			$scope.prikaziSmene = function(index){
				if ($scope.prikaziDan == index){
					$scope.prikaziDan = -1;
				} else {
					$scope.prikaziDan = index;
				}
				
			}
			
			$scope.stringuj = function(s){
				return s.getDate() + "-" + (s.getMonth()+1) + "-"+ s.getFullYear();
			}
			
			$scope.setuj = function (){
				$scope.datumPonedeljakStr = $scope.stringuj($scope.datumPonedeljak);
				$scope.datumUtorakStr = $scope.stringuj($scope.datumUtorak);
				$scope.datumSredaStr = $scope.stringuj($scope.datumSreda);
				$scope.datumCetvrtakStr = $scope.stringuj($scope.datumCetvrtak);
				$scope.datumPetakStr = $scope.stringuj($scope.datumPetak);
				$scope.datumSubotaStr = $scope.stringuj($scope.datumSubota);
				$scope.datumNedeljaStr = $scope.stringuj($scope.datumNedelja);
			}
			
			var tomorrow = new Date();
			$scope.datumNedelja = new Date();
			$scope.datumSubota = new Date();
			$scope.datumPetak = new Date();
			$scope.datumCetvrtak = new Date();
			$scope.datumSreda = new Date();
			$scope.datumUtorak = new Date();
			$scope.datumPonedeljak = new Date();
			
			
			if ($scope.danasnjiDan == 1){
				$scope.datumPonedeljak = $scope.danasnjiDatum;
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 2){
				$scope.datumUtorak = $scope.danasnjiDatum;
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 3){
				$scope.datumSreda = $scope.danasnjiDatum;
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 4){
				$scope.datumCetvrtak = $scope.danasnjiDatum;
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 5){
				$scope.datumPetak = $scope.danasnjiDatum;
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 6){
				$scope.datumSubota = $scope.danasnjiDatum;
				$scope.datumNedelja.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} else if ($scope.danasnjiDan == 7){
				$scope.datumNedelja = $scope.danasnjiDatum;
				$scope.datumPonedeljak.setDate($scope.danasnjiDatum.getDate() + 1);
				$scope.datumUtorak.setDate($scope.danasnjiDatum.getDate() + 2);
				$scope.datumSreda.setDate($scope.danasnjiDatum.getDate() + 3);
				$scope.datumCetvrtak.setDate($scope.danasnjiDatum.getDate() + 4);
				$scope.datumPetak.setDate($scope.danasnjiDatum.getDate() + 5);
				$scope.datumSubota.setDate($scope.danasnjiDatum.getDate() + 6);
				$scope.setuj();
			} 
		}else{
			alert("Niko nije ulogovan");
			// TODO: Aca ~ Redirektuj!
		}
	});
	
	$scope.setTab = function(newTab){
    	$scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){   
    	return $scope.tab === tabNum;
    };
    
	$scope.changedValue = function(item) {
		$scope.zaPrikaz = [];
		for (var i = 0; i<$scope.news.length; i++){
			if ($scope.news[i].type == item)
				$scope.zaPrikaz.push($scope.news[i]);
		}
	};
	
	$scope.setTab(1);
	
	$scope.dodajJelo = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var jeloo = {
			naziv : $scope.nazivJela,
			opis : $scope.opisJela,
			cena : $scope.cenaJela,
			restoran : restoran
		}
		
		var str = JSON.stringify(jeloo);
		menRestoranaServisS.dodajJelo(str).success(function(data) {
			alert("Uspesno dodato jelo!");
		}).error(function(data) {
			alert("Jelo nije dodato!");
		});
	}
	
	$scope.dodajPice = function(restoran){
		// TODO: Aca ~ Uraditi validacuju
		var picee = {
			naziv : $scope.nazivPica,
			opis : $scope.opisPica,
			cena : $scope.cenaPica,
			restoran : restoran
		}
		
		var str = JSON.stringify(picee);
		menRestoranaServisS.dodajPice(str).success(function(data) {
			alert("Uspesno dodato pice!");
		}).error(function(data) {
			alert("Pice nije dodato!");
		});
	}
	
	$scope.izmenaNazivaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			naziv : restoran.naziv,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniNazivRestorana(str).success(function(data) {
			alert("Uspesno izmenjen naziv!");
		}).error(function(data) {
			alert("Naziv nije izmenjen!");
		});
	}
	
	$scope.izmenaOpisaRestorana = function(restoran) {
		// TODO: Aca ~ Uraditi validacuju
		var rest = {
			id : restoran.id,
			opis : restoran.opis,

		}
		
		var str = JSON.stringify(rest);
		menRestoranaServisS.izmeniOpisRestorana(str).success(function(data) {
			alert("Uspesno izmenjen opis!");
		}).error(function(data) {
			alert("Opis nije izmenjen!");
		});
	}
	
	$scope.izmenaJela = function(jeloId){
		// TODO: Tabelarno...
	}
	
	$scope.odbrisiJelo = function(jeloId){
		var str = JSON.stringify(jeloId);
		menRestoranaServisS.izbrisiJelo(str).success(function(data) {
		}).error(function(data) {
		});
	}
	
	$scope.odbrisiPice = function(piceId){
		var str = JSON.stringify(piceId);
		menRestoranaServisS.izbrisiPice(str).success(function(data) {
		}).error(function(data) {	
		});
	}
	
	$scope.napraviKonfiguraciju = function(restoran) {
		$scope.brRedova = [];
		$scope.brKolona = [];
		for(i=0; i<$scope.brojRedova; i++){
			$scope.brRedova.push(i);
		}
		for(i=0; i<$scope.brojKolona; i++){
			$scope.brKolona.push(i);
		}
		
		var sizeStolova = {
			id : restoran.id,
			brojredova : $scope.brojRedova,
			brojkolona : $scope.brojKolona
		}
		var str = JSON.stringify(sizeStolova);
		menRestoranaServisS.brojeviRedovaIKolona(str).success(function(data) {
			alert("broj redova i kolona dodat");
		}).error(function(data) {
			alert("dodat nije");
		});
		
		var tempStolovi = [];
		tempStolovi[0] = restoran.id;
		for(i=0; i<$scope.brojRedova; i++){
			for(j=0; j<$scope.brojKolona; j++){
				tempStolovi[i*$scope.brojKolona + j + 1] = i*$scope.brojKolona + j;
			}
		}
		
		var str = JSON.stringify(tempStolovi);
		menRestoranaServisS.napraviStolove(str).success(function(data) {
			$scope.stolovi = data;
			alert("stolovi napravljeni");
		}).error(function(data) {
			alert("stolovi nisu napravljeni");
		});
		
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
	
	$scope.dodajStavkuNamirnicu = function(){
		var stavka = {
			kolicina : $scope.kolNamirnica,
			pice : $scope.namirnicaStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajStavkuPice = function(){
		var stavka = {
			kolicina : $scope.kolPica,
			pice : $scope.piceStavka
		}
		$scope.listaStavki.push(stavka);
	}
	
	$scope.dodajPonudjaca = function(ponudjac){
		var ponRest = {
			ponudjac : ponudjac,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.dodajPonudjaca(str);
		
	}
	
	$scope.registrujIDodajPonudjaca = function(){
		var pon = {
			ime : $scope.imePonudjaca,
			prezime : $scope.prezimePonudjaca,
			sifra : $scope.lozinkaPonudjaca,
			email : $scope.emailPonudjaca,
		}
		
		var ponRest = {
			ponudjac : pon,
			restoran : $scope.restoran
		}
		var str = JSON.stringify(ponRest);
		menRestoranaServisS.registrujIDodajPonudjaca(str);
		
	}
	
});