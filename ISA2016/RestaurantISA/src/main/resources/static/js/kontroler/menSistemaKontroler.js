var menSistemaKontroler = angular.module ('restoranApp.menSistemaKontroler', []);

menSistemaKontroler.controller('menSistemaCtrl', function (gostGlavnaStranaServis, $scope, menSistemaServis){

	//za selektovanje tabova
	
    $scope.setTab = function(newTab){
    	$scope.tab = newTab;
    };

    $scope.isSet = function(tabNum){   
    	return $scope.tab === tabNum;
    };
    
	$scope.setTab(1);

});