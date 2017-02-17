var logovanjeKontroler = angular.module('restoranApp.logovanjeKontroler', []);

logovanjeKontroler.controller('logovanjeCtrl', function($location, $scope, logovanjeServis) {
	
	$scope.changeRoute = function(url, forceReload) {
        $scope = $scope || angular.element(document).scope();
        if(forceReload || $scope.$$phase) { // that's right TWO dollar signs: $$phase
            window.location = url;
        } else {
            $location.path(url);
            $scope.$apply();
        }
    };
	
	$scope.logovanje = function(){
		var korisnik = {
			email : $scope.email,
			sifra : $scope.sifra
		}
		
		var str = JSON.stringify(korisnik);
		
		logovanjeServis.ulogujKorisnika(str).success(function(data) {
			if(data != ""){
				if(data.tipKorisnika == 'GOST')
					$location.path('/gostGlavnaStrana');
				if(data.tipKorisnika == 'PONUDJAC')
					$location.path('/ponudjac');
				if(data.tipKorisnika == 'KUVAR')
					$location.path('/kuvar');
				if(data.tipKorisnika == 'MENADZER_SISTEMA')
					$location.path('/menSistema');
			}else{
				alert("else");
				$scope.changeRoute('/');
			}
		});
		
	}
	
})