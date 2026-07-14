package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.util.ArrayList;
import so.AbstractSO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jovan
 */
public class SOGetAllKupac extends AbstractSO{
    ArrayList<Kupac> listaKupaca;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
         ArrayList<AbstractDomainObject> kupci = DBBroker.getInstance().select(ado);
        listaKupaca = (ArrayList<Kupac>) (ArrayList<?>) kupci;
    }

    public ArrayList<Kupac> getListaKupaca() {
        return listaKupaca;
    }
    
}
