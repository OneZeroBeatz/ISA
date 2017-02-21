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
	
	temp.izlistajPrijateljeNeprijatelje = function(gost) {
		return $http.post('/gostKontroler/izlistajPrijateljeNeprijatelje', gost);
	}
	
	temp.ukloniPrijatelja = function(id) {
		return $http.post('/gostKontroler/ukloniPrijatelja', id)
	}
	
	return temp;	
})