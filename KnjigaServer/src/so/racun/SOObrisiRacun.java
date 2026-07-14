package so.racun;


import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import so.AbstractSO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jovan
 */
public class SOObrisiRacun extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
          if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
