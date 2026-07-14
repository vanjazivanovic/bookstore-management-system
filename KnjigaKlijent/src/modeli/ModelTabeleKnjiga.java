package modeli;



import domain.Knjiga;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontoler.KlijentKontroler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jovan
 */
public class ModelTabeleKnjiga extends AbstractTableModel implements Runnable{
    private ArrayList<Knjiga> lista;
    private String[] kolone ={"ID","Naziv", "Autor"};
     private String parametar = "";

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresujTabelu();
    }
     
 
    public ModelTabeleKnjiga(){
        try {
            lista=KlijentKontroler.getInstance().getAllKnjige();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKnjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
          return kolone.length;
    }
public String getColumnName(int i) {
        return kolone[i];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Knjiga k = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return k.getKnjigaID();
            case 1: return k.getNaziv();
            case 2: return k.getAutor();
           

            default:
                return null;
        }
    }

    @Override
    public void run() {
         try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refresujTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleKnjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresujTabelu() {
         try {
            lista = KlijentKontroler.getInstance().getAllKnjige();
            if (!parametar.equals("")) {
                ArrayList<Knjiga> novaLista = new ArrayList<>();
                for (Knjiga k : lista) {
                    if (k.getNaziv().toLowerCase().contains(parametar.toLowerCase())
                            ) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Knjiga vratiKnjigu(int row) {
        return lista.get(row);
    }

    
    
    
}
