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
public class SOPromeniRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
        Racun r = (Racun) ado;

        if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem jednu stavku!");
        }
    }

    @Override

    protected void execute(AbstractDomainObject ado) throws Exception {

        DBBroker.getInstance().update(ado);

        Racun r = (Racun) ado;

        for (StavkaRacuna sr : r.getStavkeRacuna()) {

            switch (sr.getStatus()) {

                case NOVA:
                    sr.setRacun(r);
                    DBBroker.getInstance().insert(sr);
                    break;

                case IZMENJENA:
                    DBBroker.getInstance().update(sr);
                    break;

                case OBRISANA:
                    DBBroker.getInstance().delete(sr);
                    break;

                case NEPROMENJENA:
                    break;
            }
        }
    }

}
