package modeli;

import domain.Kupac;
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
public class ModelTabeleKupci extends AbstractTableModel implements Runnable {

    private ArrayList<Kupac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Mesto"};
    private String parametar = "";

    public ModelTabeleKupci() {
        try {
            lista = KlijentKontroler.getInstance().getAllKupac();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleKupci.class.getName()).log(Level.SEVERE, null, ex);
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
        Kupac k = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getKupacID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTelefon();
            case 5:
                return k.getMesto();
            default:
                return null;
        }
    }

    public ArrayList<Kupac> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Kupac> lista) {
        this.lista = lista;
    }

    public String getParametar() {
        return parametar;
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refresujTabelu();
    }

    @Override
    public void run() {
        
        try {
            while(!Thread.currentThread().isInterrupted()){
            Thread.sleep(10000);
            refresujTabelu();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelTabeleKupci.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void refresujTabelu() {
        try {
            lista = KlijentKontroler.getInstance().getAllKupac();
            if (!parametar.equals("")) {
                ArrayList<Kupac> novaLista = new ArrayList<>();
                for (Kupac k : lista) {
                    if (k.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || k.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
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

    public Kupac vratiIzabranogKupca(int row) {
        return lista.get(row);
    }

}
