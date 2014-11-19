gestioneEventi.factory('dataServices', function($http, $sessionStorage) {
    var login = function(utente, loginCallback) {
        $http.post('rest/login', utente).
                success(function(data, status, headers, config) {
                    loginCallback(data);
                });

    },
            creaUtente = function(utente, creaUtenteCallback) {
                $http.post('rest/registrati', utente).
                        success(function(data, status, headers, config) {
                            creaUtenteCallback(data);
                        });
            },
            creaRelatore = function(relatore, creaRelatoreCallback) {
                $http.post('rest/creaRelatore', relatore).
                        success(function(data, status, headers, config) {
                            creaRelatoreCallback(data);
                        });

            },
            relatoriCensiti = function(relatoriCensitiCallback) {
                $http.get('rest/vaiEvento').
                        success(function(data, status, headers, config) {
                            relatoriCensitiCallback(data);
                        });
            },
            creaEvento = function(evento, creaEventoCallback) {
                $http.post('rest/creaEvento', evento).
                        success(function(data, status, headers, config) {
                            creaEventoCallback(data);
                        });

            },
            eventiPassati = function(eventiPassatiCallback) {
                $http.get('rest/eventiPassati').
                        success(function(data, status, headers, config) {
                            eventiPassatiCallback(data);
                        });
            },
            eventiFuturi = function(eventiFuturiCallback) {
                $http.get('rest/eventiFuturi').
                        success(function(data, status, headers, config) {
                            eventiFuturiCallback(data);
                        });
            },
            iscrizione = function(idEvento, iscriviCallback) {
                $http.get('rest/iscrizione/' + idEvento, {params: {
                        username: $sessionStorage.utente.username
                    }
                }).
                        success(function(data, status, headers, config) {
                            iscriviCallback(data);
                        });
            },
            listaPrenotazioni = function(utente, listaPrenotazioniCallback) {
                $http.post('rest/listaPrenotazioni', utente).
                        success(function(data, status, headers, config) {
                            listaPrenotazioniCallback(data);
                        });
            },
            annullaIscrizione = function(idEvento, annullaIscrizioneCallback) {
                $http.delete('rest/annullaIscrizione/' + idEvento, {params: {
                        username: $sessionStorage.utente.username
                    }
                }).
                        success(function(data, status, headers, config) {
                            annullaIscrizioneCallback(data);
                        });
            },
            getPartecipanti = function(idEvento, listaPartecipantiCallback) {
                $http.get('rest/listaPartecipanti/' + idEvento).
                        success(function(data, status, headers, config) {
                            listaPartecipantiCallback(data);
                        });
            };


    return {
        login: login,
        creaUtente: creaUtente,
        creaRelatore: creaRelatore,
        creaEvento: creaEvento,
        relatoriCensiti: relatoriCensiti,
        eventiPassati: eventiPassati,
        eventiFuturi: eventiFuturi,
        iscrizione: iscrizione,
        listaPrenotazioni: listaPrenotazioni,
        annullaIscrizione: annullaIscrizione,
        getPartecipanti: getPartecipanti
    };

});