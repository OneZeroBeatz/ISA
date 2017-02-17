var ponudjacServis = angular.module('restoranApp.ponudjacServis', []);

ponudjacServis.factory('ponudjacServisS', function($http) {
	
	var temp = {};
	
	temp.izmeni = function(ponudjac) {
		return $http.post('/ponudjacKontroler/izmeniPonudjaca', ponudjac);
	}
	
	return temp;
	
})