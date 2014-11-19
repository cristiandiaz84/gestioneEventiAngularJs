package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.exception.IscrizioneGiaEffettuataException;
import it.cspnet.gestioneeventi.model.JsonResults;
import it.cspnet.gestioneeventi.model.Utente;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventoController {

    @Autowired
    private ServiziEventi servizi;
    private static Logger logger = Logger.getLogger(EventoController.class);
    
    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/eventiPassati")
    public @ResponseBody JsonResults eventiPassati() {
        JsonResults jR = new JsonResults();
        logger.debug("Metodo per richiamare gli eventi passati");
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.getEventiPassati());
            logger.debug("Eventi passati caricati correttamente");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("MSG");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
    
    @RequestMapping(value = "/eventiFuturi")
    public @ResponseBody JsonResults eventiFuturi() {
        JsonResults jR = new JsonResults();
        logger.debug("Metodo per richiamare gli eventi futuri");
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.getListaEventiFuturi());
            logger.debug("Eventi futuri caricati correttamente");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("MSG");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }

    @RequestMapping(value = "/iscrizione/{idEvento}", method = RequestMethod.GET)
    public @ResponseBody JsonResults iscrizioneAdEvento(@PathVariable long idEvento,HttpServletRequest req) {
       String username = req.getParameter("username");
       JsonResults jR = new JsonResults();
       logger.debug("Metodo per iscriversi agli eventi");
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.iscrizioneAdEvento(idEvento, username));
            jR.setMessaggio("Iscrizione effettuata con successo");
            logger.debug("Iscrizione effettuata con successo");
        } catch (IscrizioneGiaEffettuataException ex) {
            jR.setCodice(1);
            jR.setMessaggio("Utente già iscritto");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
    
    @RequestMapping(value = "/listaPartecipanti/{idEvento}")
    public @ResponseBody JsonResults listaPartecipanti(@PathVariable Long idEvento) {
        JsonResults jR = new JsonResults();
        logger.debug("Metodo per richiamare la lista dei partecipanti");
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.listaPartecipanti(idEvento).getUtentiPartecipanti());
            logger.debug("Lista dei partecipanti");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Lista partecipanti vuota");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
    
    @RequestMapping(value = "/listaPrenotazioni",method = RequestMethod.POST)
    public @ResponseBody JsonResults listaPrenotazioni(@RequestBody Utente utente) {
        JsonResults jR = new JsonResults();
        logger.debug("Metodo per richiamare la lista delle prenotazioni");
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.listaPrenotazioni(utente.getUsername()));
            logger.debug("Lista delle prenotazioni");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("MSG");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
 
    @RequestMapping(value = "/annullaIscrizione/{idEvento}", method = RequestMethod.DELETE)
    public @ResponseBody JsonResults annullaIscrizione(@PathVariable long idEvento,HttpServletRequest req) {
        JsonResults jR = new JsonResults();
        logger.debug("Metodo per annullare l'iscrizione all'evento");
        String username = req.getParameter("username");
        try {
            jR.setCodice(0);
            servizi.annullaIscrizione(idEvento, username);
            jR.setMessaggio("Iscrizione cancellata correttamente");
            logger.debug("Iscrizione cancellata correttamente");
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Siamo spiancenti non è possibile elimare la prenotazione");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }
        return jR;
    }
}
