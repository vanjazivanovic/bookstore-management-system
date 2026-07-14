/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prodavac;
import java.util.ArrayList;
import kontroler.ServerKontroler;
import so.AbstractSO;

/**
 *
 * @author jovan
 */
public class SOLogin extends AbstractSO{
    Prodavac ulogovani;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Prodavac)){
            throw new Exception("Proslednjeni objekat mora biti instanca glase Prodavac ");
        
        }
        Prodavac p=(Prodavac) ado;
        for (Prodavac prodavac : ServerKontroler.getInstance().getUlogovaniZaposleni()) {
            if(prodavac.getUsername().equals(p.getUsername())){
             throw new Exception("Ovaj zaposleni je vec ulogovan na sistem!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Prodavac p=(Prodavac) ado;
        
       ArrayList<Prodavac> listaProdavaca = (ArrayList<Prodavac>) (ArrayList<?>) DBBroker.getInstance().select(ado);
       
        for (Prodavac prodavac : listaProdavaca) {
            if(prodavac.getUsername().equals(p.getUsername())&&prodavac.getPassword().equals(p.getPassword())){
            ulogovani=prodavac;
            ServerKontroler.getInstance().getUlogovaniZaposleni().add(prodavac);
            return;
            
            }
        }
        throw new Exception("Korisnicko ime i sifra nisu ispravni.");
    }

    public Prodavac getUlogovani() {
        return ulogovani;
    }
    
}
