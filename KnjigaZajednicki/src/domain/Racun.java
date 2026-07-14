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
public class Racun extends AbstractDomainObject {

    private long racunID;
    private Date datumIzdavanja;
    private double ukupanIznos;
    private Kupac kupac;
    private Prodavac prodavac;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun(long racunID, Date datumIzdavanja, double ukupanIznos, Kupac kupac, Prodavac prodavac, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datumIzdavanja = datumIzdavanja;
        this.ukupanIznos = ukupanIznos;
        this.kupac = kupac;
        this.prodavac = prodavac;
        this.stavkeRacuna = stavkeRacuna;
    }

    public Racun() {
    }

    @Override
    public String nazivTabele() {
        return " Racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return "  JOIN KUPAC K ON (K.KUPACID = R.KUPACID)\n"
                + "JOIN MESTO M ON (M.MESTOID = K.MESTOID)\n"
                + "JOIN PRODAVAC P ON (P.PRODAVACID = R.PRODAVACID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Prodavac p = new Prodavac(rs.getLong("prodavacID"),
                    rs.getString("p.Ime"), rs.getString("p.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Mesto m = new Mesto(rs.getLong("MestoID"),
                    rs.getString("m.naziv"));

            Kupac k = new Kupac(rs.getLong("kupacID"), rs.getString("k.ime"),
                    rs.getString("k.prezime"), rs.getString("email"),
                    rs.getString("telefon"), m);

            Racun r = new Racun(rs.getLong("racunID"),
                    rs.getDate("datumIzdavanja"), rs.getDouble("ukupanIznos"), k, p, null);

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumIzdavanja, ukupanIznos, kupacID, prodavacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(datumIzdavanja.getTime()) + "', " + ukupanIznos + ", "
                + " " + kupac.getKupacID() + ", " + prodavac.getProdavacID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumIzdavanja = '" + new java.sql.Date(datumIzdavanja.getTime()) + "', "
                + "ukupanIznos = " + ukupanIznos + " ";
    }

    @Override
    public String uslov() {
        return " racunID = " + racunID;
    }

    @Override
    public String uslovZaSelect() {
        if (kupac != null) {
            return " WHERE K.KUPACID = " + kupac.getKupacID();
        }
        return "";
    }

    public long getRacunID() {
        return racunID;
    }

    public void setRacunID(long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

}
