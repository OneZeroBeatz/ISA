var ponudjacServis = angular.module('restoranApp.ponudjacServis', []);

ponudjacServis.factory('ponudjacServisS', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izmeniPonudjaca', ponudjac);
	}
	
	temp.izlistaj = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPonude', ponudjac);
	}
	
	temp.izlistajPorudzbineBezPonude = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izlistajPorudzbineBezPonude', ponudjac);
	}
	
	temp.izlistajStavkePorudzbine = function(porudzbina) {
		return $http.post('/ponudjacKontroler/izlistajStavkePorudzbine', porudzbina);
	}
	
	return temp;
	
})