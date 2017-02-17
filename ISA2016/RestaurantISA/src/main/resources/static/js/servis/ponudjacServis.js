var ponudjacServis = angular.module('restoranApp.ponudjacServis', []);

ponudjacServis.factory('ponudjacServisS', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izmeniPonudjaca', ponudjac);
	}
	
	temp.izlistaj = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPonude', ponudjac);
	}
	
	return temp;
	
})