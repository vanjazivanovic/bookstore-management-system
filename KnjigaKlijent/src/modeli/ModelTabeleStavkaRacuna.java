package modeli;

import domain.Racun;
import domain.StatusStavke;
import domain.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jovan
 */
public class ModelTabeleStavkaRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private ArrayList<StavkaRacuna> prikaz;
    private String[] kolone = {"Rb", "Knjiga", "Kolicina", "Cena"};
    int rb = 0;

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

    public ModelTabeleStavkaRacuna() {
        lista = new ArrayList<>();
        prikaz = new ArrayList<>();
    }

    public ModelTabeleStavkaRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        lista = stavkeRacuna;
        osveziPrikaz();
    }

    @Override
    public int getRowCount() {
        return prikaz.size();
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
        StavkaRacuna sr = prikaz.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getKnjiga();
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getCena() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {
        for (StavkaRacuna stavkaRacuna : lista) {
            if (stavkaRacuna.getKnjiga().getKnjigaID() == sr.getKnjiga().getKnjigaID()) {
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setIznos(stavkaRacuna.getIznos() + sr.getIznos());
                osveziPrikaz();
                return;

            }
        }
        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        osveziPrikaz();
    }

    public void obrisiStavku(int row) {

        StavkaRacuna sr = prikaz.get(row);

        sr.setStatus(StatusStavke.OBRISANA);

        rb = 0;

        for (StavkaRacuna s : lista) {
            if (s.getStatus() != StatusStavke.OBRISANA) {
                s.setRb(++rb);
            }
        }

        osveziPrikaz();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaRacuna stavkaRacuna : prikaz) {
            ukupanIznos += stavkaRacuna.getIznos();
        }

        return ukupanIznos;
    }

    private void osveziPrikaz() {

        prikaz = new ArrayList<>();

        for (StavkaRacuna sr : lista) {

            if (sr.getStatus() != StatusStavke.OBRISANA) {
                prikaz.add(sr);
            }

        }

        fireTableDataChanged();
    }

}
