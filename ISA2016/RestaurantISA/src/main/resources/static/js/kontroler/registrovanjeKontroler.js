var registarKontroler = angular.module('restoranApp.registrovanjeKontroler', []);

registarKontroler.controller('registrovanjeCtrl', function($scope, registrovanjeServis) {

	$scope.registrovanje = function(){
		var korisnik = {
			ime : $scope.ime,
			prezime : $scope.prezime,
			email : $scope.email,
			sifra : $scope.sifra
		}
		
		var str = JSON.stringify(korisnik);
		
		registrovanjeServis.registrujKorisnika(str);
		
	}
	
})