/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Mesto;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author jovan
 */
public class SOVratiMesta extends AbstractSO{
     private ArrayList<Mesto> lista;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
          if (!(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
         ArrayList<AbstractDomainObject> mesta = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Mesto>) (ArrayList<?>) mesta;
    }

    public ArrayList<Mesto> getLista() {
        return lista;
    }
    
}
