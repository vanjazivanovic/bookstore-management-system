/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author jovan
 */
public class SOKreirajKupac extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }
        Kupac k = (Kupac) ado;

        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Kupac kupac : kupci) {
            if (kupac.getEmail().equals(k.getEmail())) {
                throw new Exception("Kupac sa tim emailom vec postoji!");
            }
            if (kupac.getTelefon().equals(k.getTelefon())) {
                throw new Exception("Kupac sa tim telefonom vec postoji!");
            }
           
    }
             if(k.getTelefon().length()!=10){
            throw new Exception("Format telefona nije ispravan, mora da sadrzi tacno 10 cifara");
            }
            if (!k.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        throw new Exception("Email format nije ispravan! Primer: korisnik@gmail.com");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
         DBBroker.getInstance().insert(ado);
    }
    
}
