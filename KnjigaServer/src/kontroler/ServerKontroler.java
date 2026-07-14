/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domain.Knjiga;
import domain.Kupac;
import domain.Mesto;
import domain.Prodavac;
import domain.Racun;
import java.util.ArrayList;
import so.knjiga.SODodajKnjiga;
import so.knjiga.SOGetAllKnjiga;
import so.knjiga.SOObrisiKnjiga;
import so.knjiga.SOPromeniKnjiga;
import so.kupac.SOGetAllKupac;
import so.kupac.SOKreirajKupac;
import so.kupac.SOObrisiKupac;
import so.kupac.SOPromeniKupac;
import so.login.SOLogin;
import so.mesto.SOVratiMesta;
import so.racun.SOGetAllRacun;
import so.racun.SOObrisiRacun;
import so.racun.SOPromeniRacun;
import so.racun.SOSacuvajRacun;

/**
 *
 * @author jovan
 */
public class ServerKontroler {

    private static ServerKontroler instance;
    private ArrayList<Prodavac> ulogovaniZaposleni = new ArrayList<>();
    
    private ServerKontroler() {
    }
    
    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }
    
    public Prodavac login(Prodavac prodavac) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(prodavac);
        return so.getUlogovani();
    }
    
    public ArrayList<Prodavac> getUlogovaniZaposleni() {
        return ulogovaniZaposleni;
    }
    
    public void setUlogovaniZaposleni(ArrayList<Prodavac> ulogovaniZaposleni) {
        this.ulogovaniZaposleni = ulogovaniZaposleni;
    }
    
    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());        
        return so.getListaKupaca();
    }
    
    public ArrayList<Knjiga> getAllKnjige() throws Exception {
        SOGetAllKnjiga so = new SOGetAllKnjiga();
        so.templateExecute(new Knjiga());
        return so.getListaKnjiga();
    }
    
    public void sacuvajRacun(Racun racun) throws Exception {
        (new SOSacuvajRacun()).templateExecute(racun);
    }
    
    public ArrayList<Mesto> vratiMesta() throws Exception {
        SOVratiMesta so = new SOVratiMesta();
        so.templateExecute(new Mesto());
        return so.getLista();
    }

    public void kreirajKupca(Kupac kupac) throws Exception {
        new SOKreirajKupac().templateExecute(kupac);
    }

    public void obrisiKupca(Kupac kupac) throws Exception {
        new SOObrisiKupac().templateExecute(kupac);
    }

    public void promeniKupac(Kupac kupac) throws Exception {
        new SOPromeniKupac().templateExecute(kupac);
    }

    public void dodajKnjigu(Knjiga knjiga) throws Exception {
        new SODodajKnjiga().templateExecute(knjiga);
    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        new SOObrisiKnjiga().templateExecute(knjiga);
    }

    public void promeniKnjigu(Knjiga knjiga) throws Exception {
        new SOPromeniKnjiga().templateExecute(knjiga);
    }

    public void odjava(Prodavac ulogovani) {
        ulogovaniZaposleni.remove(ulogovani);
    }

    public ArrayList<Racun> getAllRacun(Kupac kupac) throws Exception {
         SOGetAllRacun so=new SOGetAllRacun();
         Racun r= new Racun();
         r.setKupac(kupac);
         
         so.templateExecute(r);
         return so.getLista();
    }

    public void obrisiRacun(Racun racun) throws Exception {
      new SOObrisiRacun().templateExecute(racun);
    }

    public void promeniRacun(Racun racun) throws Exception {
        new SOPromeniRacun().templateExecute(racun);
    }
    
}
