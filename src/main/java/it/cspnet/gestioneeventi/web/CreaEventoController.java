package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.model.Evento;
import it.cspnet.gestioneeventi.model.JsonResults;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class CreaEventoController {

    @Autowired
    private ServiziEventi servizi;
    private static Logger logger = Logger.getLogger(CreaEventoController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }
      
    @RequestMapping(value = "/vaiEvento")
    public @ResponseBody
    JsonResults relatoriCensiti() {
        JsonResults jR1 = new JsonResults();
        logger.debug("Carichi i relatori nella select");
        try {            
            jR1.setCodice(0);
            jR1.setRisultato(servizi.listaRelatori()); 
            logger.debug("Relatori caricati correttamente");
        } catch (Exception ex) {
            jR1.setCodice(1);
            jR1.setMessaggio("Sito in manutenzione riprovare più tardi");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR1;
    }
    
    @RequestMapping(value = "/creaEvento", method = RequestMethod.POST)
    public @ResponseBody
    JsonResults creaEvento(@RequestBody Evento evento) {
        logger.debug("Sei nel metodo crea evento");
        JsonResults jR = new JsonResults();
        try {
            servizi.creaEvento(evento);
            jR.setCodice(0);
            jR.setMessaggio("L'evento è stato creato correttamente");
            logger.debug("L'evento è stato creato OK");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione riprovare più tardi");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
}