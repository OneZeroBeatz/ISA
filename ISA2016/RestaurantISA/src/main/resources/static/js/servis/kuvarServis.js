var kuvarServis = angular.module('restoranApp.kuvarServis', []);

kuvarServis.factory('izmeniKuvarServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	return temp;
	
})