package niti;

import domain.Knjiga;
import domain.Kupac;
import domain.Prodavac;
import domain.Racun;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import kontroler.ServerKontroler;
import transfer.Odgovor;
import transfer.Zahtev;
import transfer.util.Operacije;
import transfer.util.ResponseStatus;

public class NitKlijent extends Thread {

    private Socket socket;

    public NitKlijent(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());

            ObjectOutputStream out =
                    new ObjectOutputStream(socket.getOutputStream());

            out.flush();

            while (!socket.isClosed()) {

                Zahtev request = (Zahtev) in.readObject();

                Odgovor response = handleRequest(request);

                out.writeObject(response);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Odgovor handleRequest(Zahtev request) {

        Odgovor response =
                new Odgovor(null, null, ResponseStatus.Success);

        try {

            switch (request.getOperation()) {

                case Operacije.login:
                    Prodavac prodavac =
                            (Prodavac) request.getData();

                    Prodavac p =
                            ServerKontroler.getInstance().login(prodavac);

                    response.setData(p);
                    break;

                case Operacije.getAllKupac:
                    response.setData(
                            ServerKontroler.getInstance().getAllKupac());
                    break;

                case Operacije.getAllKnjige:
                    response.setData(
                            ServerKontroler.getInstance().getAllKnjige());
                    break;

                case Operacije.sacuvajRacun:
                    ServerKontroler.getInstance()
                            .sacuvajRacun((Racun) request.getData());
                    break;

                case Operacije.vratiMesta:
                    response.setData(
                            ServerKontroler.getInstance().vratiMesta());
                    break;

                case Operacije.kreirajKupca:
                    ServerKontroler.getInstance()
                            .kreirajKupca((Kupac) request.getData());
                    break;

                case Operacije.obrisiKupca:
                    ServerKontroler.getInstance()
                            .obrisiKupca((Kupac) request.getData());
                    break;

                case Operacije.promeniKupac:
                    ServerKontroler.getInstance()
                            .promeniKupac((Kupac) request.getData());
                    break;

                case Operacije.dodajKnjigu:
                    ServerKontroler.getInstance()
                            .dodajKnjigu((Knjiga) request.getData());
                    break;

                case Operacije.obrisiKnjigu:
                    ServerKontroler.getInstance()
                            .obrisiKnjigu((Knjiga) request.getData());
                    break;

                case Operacije.promeniKnjiga:
                    ServerKontroler.getInstance()
                            .promeniKnjigu((Knjiga) request.getData());
                    break;

                case Operacije.odjava:
                    Prodavac ulogovani =
                            (Prodavac) request.getData();

                    ServerKontroler.getInstance()
                            .odjava(ulogovani);
                    break;

                case Operacije.getALlRacun:
                    response.setData(
                            ServerKontroler.getInstance()
                                    .getAllRacun(
                                            (Kupac) request.getData()));
                    break;

                case Operacije.obrisiRacun:
                    ServerKontroler.getInstance()
                            .obrisiRacun((Racun) request.getData());
                    break;

                case Operacije.promeniRacun:
                    ServerKontroler.getInstance()
                            .promeniRacun((Racun) request.getData());
                    break;

                default:
                    return null;
            }

        } catch (Exception ex) {

            response.setResponseStatus(ResponseStatus.Error);
            response.setExc(ex);
        }

        return response;
    }
}