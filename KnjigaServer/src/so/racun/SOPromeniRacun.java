/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import so.AbstractSO;

/**
 *
 * @author jovan
 */
public class SOPromeniRacun extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
         if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
         Racun r=(Racun) ado;
         
         if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem jednu stavku!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
         DBBroker.getInstance().update(ado);
         
         Racun r=(Racun) ado;
         StavkaRacuna s=new StavkaRacuna();
         s.setRacun(r);
         DBBroker.getInstance().delete(s);
         
         for (StavkaRacuna stavkaRacuna : r.getStavkeRacuna()) {
             stavkaRacuna.setRacun(r);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }
    
}
