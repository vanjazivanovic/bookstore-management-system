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
public class SOPromeniKupac extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }

        Kupac k = (Kupac) ado;

        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Kupac kupac : kupci) {
            if (kupac.getKupacID()!=(k.getKupacID())) {
                if (kupac.getEmail().equals(k.getEmail())) {
                    throw new Exception("Kupac sa tim emailom vec postoji!");
                }
                if (kupac.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Kupac sa tim telefonom vec postoji!");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
    
}
