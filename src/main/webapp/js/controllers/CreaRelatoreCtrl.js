gestioneEventi.controller('CreaRelatoreCtrl', function ($scope, dataServices, $location, $sessionStorage) {
    var creaRelatoreCallback = function (risposta) {
        if (risposta.codice === 0) {
            $scope.eventi = risposta.risultato;
            $scope.messaggio = risposta.messaggio;
            $scope.relatoreOK = true;
        } else {
            $scope.messaggio = risposta.messaggio;
            $scope.relatoreOK = false;
        }

    },
            menu = function () {
                if ($sessionStorage.utente.profilo === 'admin') {
                    return false;
                } else {
                    return true;
                }
            };

    $scope.menuPrenotazioni = menu();

    $scope.creaRelatore = function (relatore) {
        dataServices.creaRelatore(relatore, creaRelatoreCallback);
    };

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };
});

