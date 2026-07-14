/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Win X
 */
public class Prodavnica extends AbstractDomainObject {

    private Long prodavnicaID;
    private String naziv;

    public Prodavnica(Long prodavnicaID, String naziv) {
        this.prodavnicaID = prodavnicaID;
        this.naziv = naziv;
    }

    public Prodavnica() {
    }

    @Override
    public String nazivTabele() {
        return " Prodavnica ";
    }

    @Override
    public String alijas() {
        return " pr ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavnica p = new Prodavnica(rs.getLong("ProdavnicaID"),
                    rs.getString("pr.naziv"));

            lista.add(p);
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
        return " ProdavnicaID = " + prodavnicaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getProdavnicaID() {
        return prodavnicaID;
    }

    public void setProdavnicaID(Long prodavnicaID) {
        this.prodavnicaID = prodavnicaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


  
}
