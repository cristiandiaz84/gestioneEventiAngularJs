gestioneEventi.controller('registratiCtrl', function($scope, dataServices, $location) {
    var creaUtenteCallback = function(risposta) {
        if (risposta.codice === 0) {
            $scope.utente = risposta.risultato;
            $location.path('/login');
        } else {
            $scope.messaggio = risposta.messaggio;
        }
    };

    $scope.creaUtente = function(utenti) {
        dataServices.creaUtente(utenti, creaUtenteCallback);
    };
});


