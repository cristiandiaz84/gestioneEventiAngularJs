/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.cspnet.gestioneeventi.model;

/**
 *
 * @author corsojava
 */
public class JsonResults {
    private Object risultato;
    private String messaggio;
    private int codice;

    public Object getRisultato() {
        return risultato;
    }

    public void setRisultato(Object risultato) {
        this.risultato = risultato;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }
    
    
    
}
