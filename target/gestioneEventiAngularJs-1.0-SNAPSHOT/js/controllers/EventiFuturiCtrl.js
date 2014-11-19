gestioneEventi.controller('EventiFuturiCtrl', function ($scope, dataServices, $sessionStorage,$location) {
    var eventiFuturiCallback = function (risposta) {
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
            listaPartecipanti = function(){
              if ($sessionStorage.utente.profilo === 'admin') {
                    return true;
                } else {
                    return false;
                }
            },
            iscriviCallback = function (risposta) {
                if (risposta.codice === 0) {
                    $scope.messaggio = risposta.messaggio;
                    $scope.iscrizioneOK = true;
                }
                else
                    $scope.messaggio = risposta.messaggio;
                    $scope.iscrizioneOK = false;
            };

    $scope.menuPrenotazioni = menu();
    
    $scope.listaPartecipanti = listaPartecipanti();

    dataServices.eventiFuturi(eventiFuturiCallback);

    $scope.iscrizione = function (idEvento) {
        dataServices.iscrizione(idEvento, iscriviCallback);
    };
    
        $scope.logout = function(){
        $sessionStorage.$reset();
        $location.path('/login');
    };
});