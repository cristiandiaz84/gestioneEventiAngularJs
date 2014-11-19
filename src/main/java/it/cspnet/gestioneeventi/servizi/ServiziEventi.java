package it.cspnet.gestioneeventi.servizi;

import it.cspnet.gestioneeventi.exception.ExistingUserException;
import it.cspnet.gestioneeventi.exception.GandalfException;
import it.cspnet.gestioneeventi.exception.IscrizioneGiaEffettuataException;
import it.cspnet.gestioneeventi.exception.UserNotFoundException;
import it.cspnet.gestioneeventi.exception.WrongPasswordException;
import it.cspnet.gestioneeventi.model.Evento;
import it.cspnet.gestioneeventi.model.Relatore;
import it.cspnet.gestioneeventi.model.Utente;
import java.util.Collection;

public interface ServiziEventi {

    public Collection<Evento> listaPrenotazioni(String username);

    public Collection<Evento> getEventiPassati() throws Exception;

    public Collection<Evento> getListaEventiFuturi() throws Exception;

    public Utente creaUtente(Utente utente) throws ExistingUserException, 
            Exception;

    public Utente verificaUtente(Utente utente);

    public void annullaIscrizione(long idEvento, String username);

    public Relatore verificaRelatore(Relatore relatore);

    
    public Relatore creaRelatore(Relatore relatore);

    public Evento iscrizioneAdEvento(long idEvento, String username) throws
            IscrizioneGiaEffettuataException;
    
    public Evento listaPartecipanti(long idEvento);

    public Utente login(String username, String password) throws
            UserNotFoundException, WrongPasswordException, GandalfException,Exception;

    public Evento verificaEvento(Evento evento);
    
    public Evento creaEvento(Evento evento);


    public Collection<Relatore> listaRelatori();

    public Relatore cercaRelatore(long parseLong);

    public void modificaConferma(String username);

}

