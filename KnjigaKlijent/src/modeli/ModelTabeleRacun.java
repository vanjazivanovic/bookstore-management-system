package modeli;

import domain.Kupac;
import domain.Racun;
import java.text.SimpleDateFormat;
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
public class ModelTabeleRacun extends AbstractTableModel implements Runnable{

    private ArrayList<Racun> lista;
    private String[] kolone = {"ID", "Kupac", "Datum izdavanja", "Ukupan iznos"};
    private String parametar="";

    public ArrayList<Racun> getLista() {
        return lista;
    }

    public ModelTabeleRacun() {
        try {
            lista = KlijentKontroler.getInstance().getAllRacun(null);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleRacun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    public ModelTabeleRacun(Kupac kupac) {
        try {
            lista = KlijentKontroler.getInstance().getAllRacun(kupac);
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleRacun.class.getName()).log(Level.SEVERE, null, ex);
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
        Racun r = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0: return r.getRacunID();
            case 1: return r.getKupac();
            case 2: return sdf.format(r.getDatumIzdavanja());
            case 3: return r.getUkupanIznos();
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
            Logger.getLogger(ModelTabeleRacun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresujTabelu() {
        try {
            lista = KlijentKontroler.getInstance().getAllRacun(null);
            if (!parametar.equals("")) {
                ArrayList<Racun> novaLista = new ArrayList<>();
                for (Racun r : lista) {
                    if (r.getKupac().getIme().toLowerCase().contains(parametar.toLowerCase())
                            || r.getKupac().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(r);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public void setParametar(String parametar) {
        this.parametar = parametar;
        refresujTabelu();
    }

   

    public Racun vratiIzabranRacun(int row) {
        return lista.get(row);
    }
}
