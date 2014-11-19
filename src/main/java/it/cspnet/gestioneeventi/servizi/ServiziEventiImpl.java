package it.cspnet.gestioneeventi.servizi;

import it.cspnet.gestioneeventi.exception.IscrizioneGiaEffettuataException;
import it.cspnet.gestioneeventi.data.EventoDao;
import it.cspnet.gestioneeventi.data.RelatoreDao;
import it.cspnet.gestioneeventi.data.UtenteDao;
import it.cspnet.gestioneeventi.exception.ExistingUserException;
import it.cspnet.gestioneeventi.exception.GandalfException;
import it.cspnet.gestioneeventi.exception.UserNotFoundException;
import it.cspnet.gestioneeventi.exception.WrongPasswordException;
import it.cspnet.gestioneeventi.model.Evento;
import it.cspnet.gestioneeventi.model.Relatore;
import it.cspnet.gestioneeventi.model.Utente;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servizi")
@Transactional
public class ServiziEventiImpl implements ServiziEventi {

    @Autowired
    private EventoDao eventoDao;

    @Autowired
    private UtenteDao utenteDao;

    @Autowired
    private RelatoreDao relatoreDao;
    
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void setRelatoreDao(RelatoreDao relatoreDao) {
        this.relatoreDao = relatoreDao;
    }

    public void setEventoDao(EventoDao eventoDao) {
        this.eventoDao = eventoDao;
    }

    public void setUtenteDao(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    public Utente login(String userName, String password) throws
            UserNotFoundException, WrongPasswordException, GandalfException,Exception {
        Utente user = utenteDao.findOne(userName);
        if (user != null) {
            if(user.getConfermato().equals("SI")){
                if (password.equals(user.getPassword())) {
                    return user;
            }else{
                throw new WrongPasswordException();
            }
            }else{
              throw new GandalfException();
            }
            } else {
                throw new UserNotFoundException();
        }
    }
    
    @Override
    public Utente creaUtente(Utente utente) throws ExistingUserException, 
            Exception {
        if (utenteDao.exists(utente.getUsername()))
            throw new ExistingUserException();
        else{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(utente.getEmail());
            message.setSubject("Conferma registrazione");
            message.setText("Per completare la procedura di registrazione clicca sul seguente link: \n "
                    + "http://localhost:8084/gestioneEventiAngularJs/rest/confermaMail/"+utente.getUsername());
            mailSender.send(message);
            return utenteDao.save(utente);
        }
    }
    
    @Override
    public void modificaConferma(String username) {
        Utente user = utenteDao.findOne(username);
        user.setConfermato("SI");
        utenteDao.save(user);      
    }
    
    @Override
    public Collection<Evento> getListaEventiFuturi() throws Exception {
        return eventoDao.findByDataInizioAfter(new Date());
    }

    @Override
    public Collection<Evento> getEventiPassati() throws Exception {
        return eventoDao.findByDataInizioBefore(new Date());
    }

    @Override
    public Evento iscrizioneAdEvento(long idEvento, String username) throws IscrizioneGiaEffettuataException {
        Evento eventoDaPrenotare = eventoDao.findOne(idEvento);
        Utente utente = utenteDao.findOne(username);
        if (eventoDaPrenotare.getUtentiPartecipanti().contains(utente)) {
            throw new IscrizioneGiaEffettuataException();
        } else {
            eventoDaPrenotare.getUtentiPartecipanti().add(utente);
            return eventoDao.save(eventoDaPrenotare);
        }
    }

    

    @Override
    public Utente verificaUtente(Utente utente) {
        Utente utenteDb = this.utenteDao.findOne(utente.getUsername());
        if (utenteDb == null)
            return null;
        
        return utenteDb;
    }

    @Override
    public Relatore verificaRelatore(Relatore relatore) {
        Relatore relatoreDb = this.relatoreDao.findOne(relatore.getIdRelatore());
        if (relatoreDb == null) {
            return null;
        }
        return relatoreDb;
    }

    @Override
    public Relatore creaRelatore(Relatore relatore) {
        return relatoreDao.save(relatore);
    }

    @Override
    public Evento verificaEvento(Evento evento) {
        Evento eventoDb = this.eventoDao.findOne(evento.getIdEvento());
        if (eventoDb == null) {
            return null;
        }
        return eventoDb;
    }

    @Override
    public Collection<Evento> listaPrenotazioni(String username) {
        Utente utente = utenteDao.findOne(username);
        return eventoDao.findByutentiPartecipantiAndDataInizioAfter(utente, new Date());
    }

    @Override
    public void annullaIscrizione(long idEvento, String username) {
        Evento evento = eventoDao.findOne(idEvento);
        Utente u = utenteDao.findOne(username);
        evento.getUtentiPartecipanti().remove(u);
        eventoDao.save(evento);
    }

    @Override
    public Evento listaPartecipanti(long idEvento) {
        return eventoDao.findOne(idEvento);
    }

    @Override
    public Evento creaEvento(Evento evento) {
        return eventoDao.save(evento);
    }

    @Override
    public Collection<Relatore> listaRelatori() {
        return this.relatoreDao.findAll();
    }

    @Override
    public Relatore cercaRelatore(long parseLong) {
        return this.relatoreDao.findOne(parseLong);
    }



    

}
