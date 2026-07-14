/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domain.Prodavac;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jovan
 */
public class Sesija {
     private static Sesija instance;
    private Socket socket;
    Prodavac ulogovani;

    public Prodavac getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Prodavac ulogovani) {
        this.ulogovani = ulogovani;
    }
    

    private Sesija() {
         try {
             socket=new Socket("localhost", 9000);
         } catch (Exception ex) {
             ex.printStackTrace();
         }
    }
    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }
    
    
    
}
