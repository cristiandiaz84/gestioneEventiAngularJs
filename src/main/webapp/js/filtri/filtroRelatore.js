gestioneEventi.filter('filtroRelatore',function(){
    return function(relatore){
        return relatore.nome + ' '+relatore.cognome;
    };
});

//gestioneProdotti.filter('qualita',function() {
//    return function(qualita, par) {
//        if (par == '1')
//            return "Altissima";
//        switch (qualita) {
//            case 0 : return "Bassa";
//            case 1 : return "Media";
//            case 2 : return "Alta";
//        }
//    };
//});
