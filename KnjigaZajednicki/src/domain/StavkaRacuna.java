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
public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Knjiga knjiga;

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, double iznos, Knjiga knjiga) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.knjiga = knjiga;
    }

    public StavkaRacuna() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaRacuna";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return "JOIN RACUN R ON(R.RACUNID=SR.RACUNID)\n"
                + "JOIN KUPAC K ON(K.KUPACID=R.KUPACID)\n"
                + "JOIN MESTO M ON(M.MESTOID=K.MESTOID)\n"
                + "JOIN PRODAVAC P ON(P.PRODAVACID=R.PRODAVACID)\n"
                + "JOIN KNJIGA KN ON(KN.KNJIGAID=SR.KNJIGAID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Prodavac p = new Prodavac(rs.getLong("prodavacID"),
                    rs.getString("p.ime"), rs.getString("p.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Mesto m = new Mesto(rs.getLong("MestoID"),
                    rs.getString("m.naziv"));

            Kupac k = new Kupac(rs.getLong("kupacID"), rs.getString("k.ime"),
                    rs.getString("k.prezime"), rs.getString("email"),
                    rs.getString("telefon"), m);

            Racun r = new Racun(rs.getLong("racunID"),
                    rs.getDate("datumIzdavanja"), rs.getDouble("r.ukupanIznos"), k, p, null);

            Knjiga knj = new Knjiga(rs.getLong("knjigaID"),
                    rs.getString("kn.naziv"), rs.getString("autor"), rs.getString("opis"),
                    rs.getDouble("kn.cena"));

            StavkaRacuna sr = new StavkaRacuna(r, rs.getInt("rb"),
                    rs.getInt("kolicina"), rs.getDouble("cena"), rs.getDouble("iznos"), knj);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (racunID, rb, kolicina, cena, iznos, knjigaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getRacunID() + ", " + rb + ", "
                + " " + kolicina + ", " + cena + ", " + iznos + ", " + knjiga.getKnjigaID();
    }

    @Override
    public String vrednostiZaUpdate() {
         return "";
    }

    @Override
    public String uslov() {
        return "racunID = "+racun.getRacunID();
    }

    @Override
    public String uslovZaSelect() {
        if (racun != null) {
            return " WHERE R.RACUNID = " + racun.getRacunID();
        } else {
            return " WHERE K.KNJIGAID = " + knjiga.getKnjigaID();
        }
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

}
