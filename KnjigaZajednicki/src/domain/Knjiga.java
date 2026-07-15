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
public class Knjiga extends AbstractDomainObject {

    private long knjigaID;
    private String naziv;
    private String autor;
    private String opis;
    private double cena;
    private String isbn;
    private String izdavackaKuca;

    public Knjiga(long knjigaID, String naziv, String autor, String opis, double cena, String isbn, String izdavackaKuca) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.autor = autor;
        this.opis = opis;
        this.cena = cena;
        this.isbn = isbn;
        this.izdavackaKuca = izdavackaKuca;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIzdavackaKuca() {
        return izdavackaKuca;
    }

    public void setIzdavackaKuca(String izdavackaKuca) {
        this.izdavackaKuca = izdavackaKuca;
    }

    public Knjiga() {
    }

    public String toString() {
        return naziv + " (Autor: " + autor + ")";
    }

    @Override
    public String nazivTabele() {
        return " Knjiga ";
    }

    @Override
    public String alijas() {
        return " kn ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Knjiga k = new Knjiga(
                    rs.getLong("knjigaID"),
                    rs.getString("naziv"),
                    rs.getString("autor"),
                    rs.getString("opis"),
                    rs.getDouble("cena"),
                    rs.getString("isbn"),
                    rs.getString("izdavackaKuca"));

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "(naziv, autor, opis, cena, isbn, izdavackaKuca)";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + autor + "', '" + opis + "', "
                + cena + ", '" + isbn + "', '" + izdavackaKuca + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv
                + "', autor = '" + autor
                + "', opis = '" + opis
                + "', cena = " + cena
                + ", isbn = '" + isbn
                + "', izdavackaKuca = '" + izdavackaKuca + "' ";
    }

    @Override
    public String uslov() {
        return " KnjigaId = " + knjigaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

}
