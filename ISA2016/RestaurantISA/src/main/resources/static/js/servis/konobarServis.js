var konobarServis = angular.module('restoranApp.konobarServis', []);

konobarServis.factory('izmeniKonobarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(konobar) {
		console.log(konobar);
		return $http.post('/korisnikKontroler/izmeniKorisnika', konobar);
	}
	
	temp.izmeniLozinku = function (konobar){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', konobar);
	}
	
	temp.izlistajJela = function (konobar){
		return $http.post('/konobarKontroler/ucitajJelaKonobara', konobar);
	}

	temp.izlistajPica = function (konobar){
		return $http.post('/konobarKontroler/ucitajPicaKonobara', konobar);
	}
	temp.dodajPorudzbinu = function (jelaPica){
		return $http.post('/konobarKontroler/dodajPorudzbinu', jelaPica);
	}
	
	
	return temp;
	
})