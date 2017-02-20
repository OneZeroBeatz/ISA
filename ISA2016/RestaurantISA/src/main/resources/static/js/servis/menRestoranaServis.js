var menRestoranaServis = angular.module('restoranApp.menRestoranaServis', []);

menRestoranaServis.factory('menRestoranaServisS', function($http) {


	var temp = {};
	
	temp.izlistajPonude = function(menRestorana) {
		return $http.post('/menadzerRestoranaKontroler/izlistajPonude', menRestorana);
	}
	
	temp.izlistajRestoran = function(menRestorana) {
		return $http.post('/menadzerRestoranaKontroler/izlistajRestoran', menRestorana);
	}
	
	temp.izlistajJela = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajJela', restoran);
	}
	
	temp.izbrisiJelo = function(jelo) {
		return $http.post('/menadzerRestoranaKontroler/izbrisiJelo', jelo);
	}
	
	temp.dodajJelo = function(jelo) {;
		return $http.post('/menadzerRestoranaKontroler/dodajJelo', jelo);
	}
	
	temp.izlistajPica = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izlistajPica', restoran);
	}
	
	temp.izbrisiPice = function(pice) {
		return $http.post('/menadzerRestoranaKontroler/izbrisiPice', pice);
	}
	
	temp.dodajPice = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/dodajPice', restoran);
	}
	
	temp.izmeniNazivRestorana = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izmeniNazivRestorana', restoran);
	}
	
	temp.izmeniOpisRestorana = function(restoran) {
		return $http.post('/menadzerRestoranaKontroler/izmeniOpisRestorana', restoran);
	}
	
	temp.napraviStolove = function(nizStolova) {
		return $http.post('/menadzerRestoranaKontroler/napraviStolove', nizStolova);
	}
	
	return temp;

})