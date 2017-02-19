var konobarServis = angular.module('restoranApp.konobarServis', []);

konobarServis.factory('izmeniKonobarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	temp.izmeniLozinku = function (gost){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', gost);
	}
	
	return temp;
	
})