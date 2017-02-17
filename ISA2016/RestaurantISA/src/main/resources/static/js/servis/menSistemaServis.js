var menSistemaServis = angular.module('restoranApp.menSistemaServis', []);

menSistemaServis.factory('menSistemaServis', function($http){
	
	var temp = {};
	
	temp.registrujRestoran = function(restoran){
		return $http.post ('/menSistemaKontroler/registrujRestoran', restoran);
	}
	
	return temp;
	
})