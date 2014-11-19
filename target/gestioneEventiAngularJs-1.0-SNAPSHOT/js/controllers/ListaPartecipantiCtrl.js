gestioneEventi.controller('ListaPartecipantiCtrl', function ($scope, dataServices, $sessionStorage, $routeParams, $location) {

    var listaPartecipantiCallback = function (risposta) {
        if (risposta.codice === 0) {
            $scope.partecipanti = risposta.risultato;
        }
        else
            $scope.messaggio = risposta.messaggio;
    };

    dataServices.getPartecipanti($routeParams.idEvento, listaPartecipantiCallback);

    $scope.logout = function () {
        $sessionStorage.$reset();
        $location.path('/login');
    };
});