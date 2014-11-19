gestioneEventi.controller('LoginCtrl', function($scope, dataServices, $location, $sessionStorage) {
    var loginCallback = function(risposta) {
        if (risposta.codice === 0) {
            $sessionStorage.utente = risposta.risultato;
            $scope.messaErrore = false;
            $scope.messaggio = risposta.messaggio;
            $location.path('/home');
        } else{
            $scope.messaggio = risposta.messaggio;
            $scope.messaErrore = true;
        }
        
        if($sessionStorage.utente.profilo ==='admin'){
            $scope.menuPrenotazioni = false;
        }else{
            $scope.menuPrenotazioni = true;
        }
            
    };
    
    $scope.login=function(utente) {
        dataServices.login(utente, loginCallback);
    };
});
    


