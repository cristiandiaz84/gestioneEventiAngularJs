gestioneEventi.controller('HomeCtrl', function($scope, dataServices, $location, $sessionStorage) {
    var menu = function (){
        if($sessionStorage.utente.profilo ==='admin'){
            return false;
        }else{
            return true;
        }
    };
  
    $scope.menuPrenotazioni = menu();
    
    $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');
    };
});

