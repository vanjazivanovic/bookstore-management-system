/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jovan
 */
public class ProdavacProdavnica extends AbstractDomainObject {

    private Prodavnica prodavnica;
    private Prodavac prodavac;
    private Date datum;
    private double satnica;

    public ProdavacProdavnica(Prodavnica prodavnica, Prodavac prodavac, Date datum, double satnica) {
        this.prodavnica = prodavnica;
        this.prodavac = prodavac;
        this.datum = datum;
        this.satnica = satnica;
    }

    public ProdavacProdavnica() {
    }

    @Override
    public String nazivTabele() {
        return " ProdavacProdavnica ";
    }

    @Override
    public String alijas() {
        return " ppr ";
    }

    @Override
    public String join() {
        return "JOIN PRODAVAC P ON(P.PRODAVACID=PPR.PRODAVACID)/n"
                + "JOIN PRODAVNICA PR ON(PR.PRODAVNICAID=PPR.PRODAVNICAID";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac prodavac = new Prodavac(rs.getLong("prodavacID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            Prodavnica prodavnica = new Prodavnica(rs.getLong("ProdavnicaID"),
                    rs.getString("naziv"));

            ProdavacProdavnica prodavacProdavnica = new ProdavacProdavnica(prodavnica, prodavac, rs.getDate("datum"),
                    rs.getDouble("satnica"));
            lista.add(prodavacProdavnica);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
         return " (ProdavnicaID, prodavacId, datum, satnica) ";
    }

    @Override
    public String vrednostiZaInsert() {
           return " " + prodavnica.getProdavnicaID() + ", " + prodavac.getProdavacID()+ ", "
                + "'" + new java.sql.Date(datum.getTime()) + "', "
                + " " + satnica + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " satnica = " + satnica + " ";
    }

    @Override
    public String uslov() {
        return " prodavacId = "+prodavac.getProdavacID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Prodavnica getProdavnica() {
        return prodavnica;
    }

    public void setProdavnica(Prodavnica prodavnica) {
        this.prodavnica = prodavnica;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getSatnica() {
        return satnica;
    }

    public void setSatnica(double satnica) {
        this.satnica = satnica;
    }

}
