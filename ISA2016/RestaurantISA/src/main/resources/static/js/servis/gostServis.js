var gostServis = angular.module('restoranApp.gostGlavnaStranaServis', []);

gostServis.factory('gostGlavnaStranaServis', function($http) {
	
	var temp = {};
	
	temp.koJeNaSesiji = function() {
		return $http.post('/contr/check');
	}
	
	return temp;
	
})

gostServis.factory('izmeniGostaServis', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/gostKontroler/izmeniGosta', gost);
	}
	
	temp.izlistajZahteveZaPrij = function(gost) {
		return $http.post('/gostKontroler/izlistajZahteveZaPrijateljstvo', gost);
	}
	
	temp.izlistajPrijateljeNeprijatelje = function(gost) {
		return $http.post('/gostKontroler/izlistajPrijateljeNeprijatelje', gost);
	}
	
	temp.ukloniPrijatelja = function(id) {
		return $http.post('/gostKontroler/ukloniPrijatelja', id);
	}
	
	temp.prihvatiZahtev = function(id) {
		return $http.post('/gostKontroler/prihvatiZahtev', id);
	}
	
	temp.odbijZahtev = function(obj) {
		return $http.post('/gostKontroler/odbijZahtev', obj);
	}
	
	temp.pretraziPrijatelje = function(obj) {
		return $http.post('/gostKontroler/pretraziPrijatelje', obj);
	}
	
	temp.pretraziPravePrijatelje = function(obj) {
		return $http.post('/gostKontroler/pretraziPravePrijatelje', obj);
	}
	
	temp.dodajPrijatelja = function(obj) {
		return $http.post('/gostKontroler/dodajPrijatelja', obj);
	}
	temp.otkaziZahtev = function(obj) {
		return $http.post('/gostKontroler/otkaziZahtev', obj);
	}
	
	
	
	
	// SASA 
	temp.ucitajPoseteGosta = function (gost){
		return $http.post('/gostKontroler/ucitajPoseteGosta', gost);
	}
	
	temp.oceniPosetu = function (posetaGost){
		return $http.post('/gostKontroler/oceniPosetu', posetaGost);
	}
	
	
	

	return temp;	
})