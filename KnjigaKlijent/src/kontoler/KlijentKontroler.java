/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontoler;

import domain.Knjiga;
import domain.Kupac;
import domain.Mesto;
import domain.Prodavac;
import domain.Racun;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import sesija.Sesija;
import transfer.Odgovor;
import transfer.Zahtev;
import transfer.util.Operacije;
import transfer.util.ResponseStatus;

/**
 *
 * @author jovan
 */
public class KlijentKontroler {

    private static KlijentKontroler instance;

    private KlijentKontroler() {
    }

    public static KlijentKontroler getInstance() {
        if (instance == null) {
            instance = new KlijentKontroler();
        }
        return instance;
    }

    public Prodavac login(Prodavac p) throws Exception {
        return (Prodavac) sendRequest(Operacije.login, p);
    }
     public ArrayList<Kupac> getAllKupac() throws Exception {
        return (ArrayList<Kupac>) sendRequest(Operacije.getAllKupac, null);
        
    }
    private Object sendRequest(int operation, Object data) throws Exception {
        Zahtev request = new Zahtev(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        out.flush();
       

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        Odgovor response = (Odgovor) in.readObject();
        
        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getExc();
        } else {
            return response.getData();
        }

    }

    public ArrayList<Knjiga> getAllKnjige() throws Exception {
        return (ArrayList<Knjiga>) sendRequest(Operacije.getAllKnjige, null);
    }

    public void sacuvajRacun(Racun racun) throws Exception {
        sendRequest(Operacije.sacuvajRacun, racun);
    }

    public ArrayList<Mesto> vratiSvaMesta() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operacije.vratiMesta, null);
    }

    public void kreirajKupca(Kupac k) throws Exception {
         sendRequest(Operacije.kreirajKupca, k);
    }

    public void obrisiKupac(Kupac kupac) throws Exception {
        sendRequest(Operacije.obrisiKupca, kupac);
    }

    public void promeniKupac(Kupac kupac) throws Exception {
        sendRequest(Operacije.promeniKupac, kupac);
    }

    public void dodajKnjigu(Knjiga k) throws Exception {
        sendRequest(Operacije.dodajKnjigu, k);
    }

    public void obrisiKnjiga(Knjiga knjiga) throws Exception {
        sendRequest(Operacije.obrisiKnjigu, knjiga);
    }

    public void promeniKnjiga(Knjiga knjiga) throws Exception {
        sendRequest(Operacije.promeniKnjiga, knjiga);
    }

    public void odjava(Prodavac ulogovani) throws Exception {
        sendRequest(Operacije.odjava, ulogovani);
    }

    public ArrayList<Racun> getAllRacun(Kupac kupac) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operacije.getALlRacun, kupac);
    }

    public void obrisiRacun(Racun racun) throws Exception {
        sendRequest(Operacije.obrisiRacun, racun);
    }

    public void promeniRacun(Racun racun) throws Exception {
        sendRequest(Operacije.promeniRacun, racun);
    }

  
    
   
}
