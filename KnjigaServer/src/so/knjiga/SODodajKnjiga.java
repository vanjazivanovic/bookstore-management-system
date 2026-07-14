/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author jovan
 */
public class SODodajKnjiga extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Knjiga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Knjiga!");
        }

        Knjiga k = (Knjiga) ado;

        ArrayList<Knjiga> knjige = (ArrayList<Knjiga>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Knjiga knjiga : knjige) {
            if (knjiga.getNaziv().equals(k.getNaziv())&&knjiga.getAutor().equals(k.getAutor())) {
                throw new Exception("Knjiga sa tim nazivom i autorom vec postoji!");
            }
            if (knjiga.getCena()<=0) {
                throw new Exception("Cena mora biti veca od nule");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
          DBBroker.getInstance().insert(ado);
    }
    
}
