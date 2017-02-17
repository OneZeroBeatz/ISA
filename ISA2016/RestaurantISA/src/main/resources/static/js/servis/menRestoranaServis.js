var menRestoranaServis = angular.module('restoranApp.menRestoranaServis', []);

menRestoranaServis.factory('menRestoranaServisS', function($http) {

	var temp = {};
	
	temp.izlistajPonude = function(menRestorana) {
		return $http.post('/menadzerRestoranaKontroler/izlistajPonude', menRestorana);
	}
	
	return temp;
})