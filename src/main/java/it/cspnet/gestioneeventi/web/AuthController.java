package it.cspnet.gestioneeventi.web;

import it.cspnet.gestioneeventi.exception.ExistingUserException;
import it.cspnet.gestioneeventi.exception.GandalfException;
import it.cspnet.gestioneeventi.exception.UserNotFoundException;
import it.cspnet.gestioneeventi.exception.WrongPasswordException;
import it.cspnet.gestioneeventi.model.JsonResults;
import it.cspnet.gestioneeventi.model.Utente;
import it.cspnet.gestioneeventi.servizi.ServiziEventi;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @Autowired
    private ServiziEventi servizi;
    private static Logger logger = Logger.getLogger(AuthController.class);

    public void setServizi(ServiziEventi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JsonResults login(@RequestBody Utente logInUtente) {
        logger.debug("Sono nel metodo di login");
        JsonResults jR = new JsonResults();
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.login(logInUtente.getUsername(), logInUtente.getPassword()));
            logger.debug("Utente loggato correttamente");
        } catch (UserNotFoundException ex) {
            jR.setCodice(1);
            jR.setMessaggio("Utente '" + logInUtente.getUsername() + "' non trovato!");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        } catch (WrongPasswordException ex) {
            jR.setCodice(1);
            jR.setMessaggio("Nome utente o password errata!");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        }catch (GandalfException ex) {
            jR.setCodice(1);
            jR.setMessaggio("Utente non confermato!");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione: riprovare più tardi");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
            System.out.println(ex);
        }
        return jR;
    }
    
    @RequestMapping(value = "/registrazione", method = RequestMethod.GET)
    public String enterRegistrazione(@ModelAttribute("utenteRegistrati") Utente utente) {
        return "registrazione";
    }
    
    @RequestMapping(value = "/registrati")
    public @ResponseBody JsonResults registrazioneUtente(@RequestBody Utente utente) {
        logger.debug("Sono nel metodo di registrazione utente");
        JsonResults jR = new JsonResults();
        try {
            jR.setCodice(0);
            jR.setRisultato(servizi.creaUtente(utente));
            logger.debug("Utente registrato correttamente");
        } catch (ExistingUserException ex) {
            jR.setCodice(1);
            jR.setMessaggio("Utente già esistente");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
        } catch (Exception ex) {
            jR.setCodice(1);
            jR.setMessaggio("Sito in manutenzione: riprovare più tardi");
            logger.error("Eccezione: "+ex+"-"+ex.getMessage());
            System.out.println(ex);
        }        
        return jR;
    }

    @RequestMapping(value="/confermaMail/{username}",method = RequestMethod.GET)
    public String confermaMail(@PathVariable String username){
        logger.debug("Sono nel metodo di conferma utente");
        servizi.modificaConferma(username);
        return "redirect:/#/login";
    }
}
