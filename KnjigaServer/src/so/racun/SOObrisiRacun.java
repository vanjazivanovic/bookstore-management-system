package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import so.AbstractSO;

public class SOObrisiRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        Racun racun = (Racun) ado;
        
        DBBroker.getInstance().deleteStavkeRacuna(racun.getRacunID());
        
        DBBroker.getInstance().delete(racun);
    }
}