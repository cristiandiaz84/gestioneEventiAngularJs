gestioneEventi.controller('ListaPrenotazioniCtrl', function ($scope, dataServices, $sessionStorage, $route,$location) {
    var listaPrenotazioniCallback = function (risposta) {
        if (risposta.codice === 0)
            $scope.eventi = risposta.risultato;
        else
            $scope.messaggio = risposta.messaggio;
    },
            menu = function () {
                if ($sessionStorage.utente.profilo === 'admin') {
                    return false;
                } else {
                    return true;
                }
            },
            annullaIscrizioneCallback = function (risposta) {
                if (risposta.codice === 0) {
                    $scope.messaggio = risposta.messaggio;
                    $route.reload();
                } else
                    $scope.messaggio = risposta.messaggio;
            };

    $scope.menuPrenotazioni = menu();

    dataServices.listaPrenotazioni($sessionStorage.utente, listaPrenotazioniCallback);

    $scope.annullaIscrizione = function (idEvento) {
        dataServices.annullaIscrizione(idEvento, annullaIscrizioneCallback);
    };

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };

});
