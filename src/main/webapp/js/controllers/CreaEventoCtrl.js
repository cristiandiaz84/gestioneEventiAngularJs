gestioneEventi.controller('CreaEventoCtrl', function ($scope, dataServices, $location, $sessionStorage) {
    var relatoriCensitiCallback = function (risposta) {
        if (risposta.codice === 0) {
            $scope.relatori = risposta.risultato;

        } else {
            $scope.messaggio = risposta.messaggio;
        }
    },
            menu = function () {
                if ($sessionStorage.utente.profilo === 'admin') {
                    return false;
                } else {
                    return true;
                }
            },
            creaEventoCallback = function (risposta) {
                if (risposta.codice === 0) {
                    $scope.evento = risposta.risultato;
                    $scope.eventoOK = true;
                    $scope.messaggio = risposta.messaggio;
                } else {
                    $scope.eventoOK = false;
                    $scope.messaggio = risposta.messaggio;
                }
            };

    $scope.menuPrenotazioni = menu();

    dataServices.relatoriCensiti(relatoriCensitiCallback);

    $scope.creaEvento = function (evento) {
        dataServices.creaEvento(evento, creaEventoCallback);
    };

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };
});



