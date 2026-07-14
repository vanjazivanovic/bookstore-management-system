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
public class Prodavac extends AbstractDomainObject{
    private long prodavacID;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    
    public String toString() {
        return ime + " " + prezime;
    }

    public Prodavac(long prodavacID, String ime, String prezime, String username, String password) {
        this.prodavacID = prodavacID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Prodavac() {
    }
    
    @Override
    public String nazivTabele() {
        return " Prodavac ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
         ArrayList<AbstractDomainObject> lista = new ArrayList<>();
         while(rs.next()){
         Prodavac prodavac=new Prodavac(rs.getLong("prodavacID"), rs.getString("ime"), rs.getString("prezime"),
                 rs.getString("username"), rs.getString("password"));
         lista.add(prodavac);
         }
         rs.close();
         return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'"+ime+"' , '"+prezime+"' , '"+username+"' , '"+password+"'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslov() {
        return " prodavacID = " + prodavacID;
        }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public long getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(long prodavacID) {
        this.prodavacID = prodavacID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
