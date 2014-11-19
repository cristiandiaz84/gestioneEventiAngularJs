var gestioneEventi = angular.module('gestioneEventi', ['ngRoute', 'ngStorage']);

gestioneEventi.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/login', {
                    templateUrl: 'partials/login.html',
                    controller: 'LoginCtrl'
                }).
                when('/registrazione', {
                    templateUrl: 'partials/registrati.html',
                    controller: 'registratiCtrl'
                }).
                when('/home', {
                    templateUrl: 'partials/home.html',
                    controller: 'HomeCtrl'
                }).
                when('/creaRelatore', {
                    templateUrl: 'partials/creaRelatore.html',
                    controller: 'CreaRelatoreCtrl'
                }).
                when('/vaiEvento', {
                    templateUrl: 'partials/creaEvento.html',
                    controller: 'CreaEventoCtrl'
                }).
                when('/creaEvento', {
                    templateUrl: 'partials/creaEvento.html',
                    controller: 'CreaEventoCtrl'
                }).
                when('/eventiPassati', {
                    templateUrl: 'partials/listaEventiPassati.html',
                    controller: 'EventiPassatiCtrl'
                }).
                when('/eventiFuturi', {
                    templateUrl: 'partials/listaEventiFuturi.html',
                    controller: 'EventiFuturiCtrl'
                }).
                when('/listaPrenotazioni', {
                    templateUrl: 'partials/listaPrenotazioni.html',
                    controller: 'ListaPrenotazioniCtrl'
                }).
                when('/annullaIscrizione/:idEvento', {
                    templateUrl: 'partials/listaPrenotazioni.html',
                    controller: 'ListaPrenotazioniCtrl'
                }).
                when('/listaPartecipanti/:idEvento', {
                    templateUrl: 'partials/listaPartecipanti.html',
                    controller: 'ListaPartecipantiCtrl'
                }).
                otherwise({
                    redirectTo: '/login'
                });
    }]);
