package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.model.JsonResults;
import it.cspnet.gestioneeventi.model.Relatore;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreaRelatoreController {

    @Autowired
    private ServiziEventi servizi;
    private static Logger logger = Logger.getLogger(CreaRelatoreController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/creaRelatore")
    public @ResponseBody
    JsonResults creaRelatore(@RequestBody Relatore relatore) {
        JsonResults jR = new JsonResults();
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.creaRelatore(relatore));
            jR.setMessaggio("Relatore censito correttamente!");
            logger.debug("Evento caricato correttamente");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione riprovare più tardi");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
}
