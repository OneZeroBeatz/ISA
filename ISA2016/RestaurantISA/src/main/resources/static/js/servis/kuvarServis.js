var kuvarServis = angular.module('restoranApp.kuvarServis', []);

kuvarServis.factory('izmeniKuvarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	temp.izmeniLozinku = function (gost){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', gost);
	}
	
	return temp;
	
})