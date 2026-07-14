/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jovan
 */
public class Mesto extends AbstractDomainObject{
    private long mestoID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Mesto(long mestoID, String naziv) {
        this.mestoID = mestoID;
        this.naziv = naziv;
    }

    public Mesto() {
    }

    public long getMestoId() {
        return mestoID;
    }

    public void setMestoId(long mestoId) {
        this.mestoID = mestoID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
   public String nazivTabele() {
        return " Mesto ";
    }

    @Override
    public String alijas() {
        return " m ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto(rs.getLong("MestoID"),
                    rs.getString("m.naziv"));

            lista.add(m);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " MestoID = " + mestoID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    
}
