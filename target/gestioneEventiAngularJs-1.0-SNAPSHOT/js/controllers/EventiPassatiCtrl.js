gestioneEventi.controller('EventiPassatiCtrl', function ($scope, dataServices, $location, $sessionStorage) {
    var eventiPassatiCallback = function (risposta) {
        if (risposta.codice === 0)
            $scope.eventi = risposta.risultato;
        else
            $scope.messaggio = risposta.messaggio;
    },
            listaPartecipanti = function () {
                if ($sessionStorage.utente.profilo === 'admin') {
                    return true;
                } else {
                    return false;
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

    $scope.listaPartecipanti = listaPartecipanti();

    dataServices.eventiPassati(eventiPassatiCallback);

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };
});

