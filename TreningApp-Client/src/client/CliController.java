package client;

import domm.*;
import comm.*;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class CliController {
    Socket socket;
    Sender sender;
    Receiver receiver;

    public CliController() throws IOException {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    private Object sendReqReceiveRes(Operation operation, Object argument) throws Exception {
        Request req = new Request(operation, argument);
        sender.send(req);
        Response res = (Response) receiver.receive();
        if (res.getException() == null)
            return res.getResult();
        throw res.getException();
    }

    public Trener login(Trener trener) throws Exception {
        return (Trener) sendReqReceiveRes(Operation.LOGIN, trener);
    }

    public String logout(Trener trener) throws Exception {
        return (String) sendReqReceiveRes(Operation.LOGOUT, trener);
    }

    public List<VrstaTreninga> ucitajVrsteTreninga() throws Exception {
        return (List<VrstaTreninga>) sendReqReceiveRes(Operation.UCITAJ_VRSTE_TRENINGA, null);
    }

    public Korisnik kreirajKorisnika() throws Exception {
        return (Korisnik) sendReqReceiveRes(Operation.KREIRAJ_KORISNIK, null);
    }

    public Korisnik sacuvajNovogKorisnika(Korisnik k) throws Exception {
        return (Korisnik) sendReqReceiveRes(Operation.SACUVAJ_NOVOG_KORISNIKA, k);
    }

    public List<Korisnik> pretraziKorisnike(Korisnik kriterijum) throws Exception {
        return (List<Korisnik>) sendReqReceiveRes(Operation.PRETRAZI_KORISNIKE, kriterijum);
    }

    public Korisnik nadjiKorisnika(int id) throws Exception {
        return (Korisnik) sendReqReceiveRes(Operation.NADJI_KORISNIKA, id);
    }

    public void izmeniKorisnika(Korisnik k) throws Exception {
        sendReqReceiveRes(Operation.IZMENI_KORISNIKA, k);
    }

    public void obrisiKorisnika(int id) throws Exception {
        sendReqReceiveRes(Operation.OBRISI_KORISNIKA, id);
    }

    public TrenerLicenca dodajLicencu(TrenerLicenca tl) throws Exception {
        return (TrenerLicenca) sendReqReceiveRes(Operation.DODAJ_LICENCU, tl);
    }

    public List<TrenerLicenca> licenceZaTrenera(Trener trener) throws Exception {
        return (List<TrenerLicenca>) sendReqReceiveRes(Operation.GET_LICENCE, trener);
    }
    
    public List<Trening> ucitajTreninge() throws Exception {
        return (List<Trening>) sendReqReceiveRes(Operation.UCITAJ_TRENINZI, null);
    }
 
    public Priznanica kreirajPriznanicu(Priznanica p) throws Exception {
        return (Priznanica) sendReqReceiveRes(Operation.PRIZNANICA_KREIRAJ, p);
    }
 
    public List<Priznanica> pretraziPriznanice(Priznanica kriterijum) throws Exception {
        return (List<Priznanica>) sendReqReceiveRes(Operation.PRIZNANICA_PRETRAZI, kriterijum);
    }
 
    public void izmeniPriznanicu(Priznanica p) throws Exception {
        sendReqReceiveRes(Operation.PRIZNANICA_IZMENI, p);
    }
    
    public Priznanica getPriznanicaById(int id) throws Exception {
        return (Priznanica) sendReqReceiveRes(Operation.PRIZNANICA_GET_ONE, id);
    }
 
}
