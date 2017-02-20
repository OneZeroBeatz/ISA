var sankerServis = angular.module('restoranApp.sankerServis', []);

sankerServis.factory('izmeniSankerServis', function($http) {

	var temp = {};
	
	temp.izmeni = function(gost) {
		return $http.post('/korisnikKontroler/izmeniKorisnika', gost);
	}
	
	temp.izmeniLozinku = function (gost){
		return $http.post('/korisnikKontroler/izmeniLozinkuKorisnika', gost);
	}
	
	temp.ucitajPorudzbine = function(sanker){
		return $http.post('/sankerKontroler/ucitajPorudzbine', sanker);
	}
	
	temp.ucitajPicaPorudzbine = function (porudzbina){
		return $http.post('/sankerKontroler/ucitajPicaPorudzbine', porudzbina);
	}
	temp.prihvatiPorudzbinu = function (sankon){
		return $http.post('/sankerKontroler/prihvatiPorudzbinu', sankon);
	}
	temp.zavrsiPorudzbinu = function (porudzbina){
		return $http.post('/sankerKontroler/zavrsiPorudzbinu', porudzbina);
	}
	
	
	
	return temp;
	
})