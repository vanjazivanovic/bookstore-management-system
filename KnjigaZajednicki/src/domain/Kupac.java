/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jovan
 */
public class Kupac extends AbstractDomainObject {

    private long kupacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Mesto mesto;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Kupac(long kupacID, String ime, String prezime, String email, String telefon, Mesto mesto) {
        this.kupacID = kupacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    public Kupac() {
    }

    @Override
    public String nazivTabele() {
        return " Kupac ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return " JOIN MESTO M ON (M.MESTOID = K.MESTOID)  ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto(rs.getLong("MestoID"),
                    rs.getString("m.naziv"));

            Kupac k = new Kupac(rs.getLong("kupacID"), rs.getString("ime"),
                    rs.getString("prezime"), rs.getString("email"),
                    rs.getString("telefon"), m);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, email, telefon, mestoID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', " + mesto.getMestoId();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "', "
                + "mestoID = " + mesto.getMestoId() + " ";
    }

    @Override
    public String uslov() {
        return " kupacID = " + kupacID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public long getKupacID() {
        return kupacID;
    }

    public void setKupacId(long kupacId) {
        this.kupacID = kupacId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

}
