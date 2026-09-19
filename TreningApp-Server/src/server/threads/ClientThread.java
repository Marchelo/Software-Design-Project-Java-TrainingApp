package server.threads;

import comm.Request;
import comm.Response;
import comm.Sender;
import comm.Receiver;
import controller.ServerController;
import domm.Korisnik;
import domm.Priznanica;
import domm.Trener;
import domm.TrenerLicenca;
import domm.Trening;
import domm.VrstaTreninga;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread {
    ServerThread server;
    Socket socket;
    Sender sender;
    Receiver receiver;
    Trener trener;

    public ClientThread(ServerThread server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                Request req = (Request) receiver.receive();
                Response res = handleReq(req);
                sender.send(res);
            }
        } catch (Exception e) {
            System.out.println("ClientThread run(): " + e.getMessage());
        }
    }

    private Response handleReq(Request req) {
        Response res = new Response();

        try {
            switch (req.getOperation()) {
                case LOGIN:
                    trener = handleLogin(req);
                    res.setResult(trener);
                    break;

                case LOGOUT:
                    String email = trener.getEmail();
                    handleLogout();
                    res.setResult("Trener uspesno izlogovan: " + email);
                    break;

                case UCITAJ_VRSTE_TRENINGA:
                    List<VrstaTreninga> list = ServerController.getInstance().ucitajVrsteTreninga();
                    res.setResult(list);
                    break;

                case KREIRAJ_KORISNIK:
                    Korisnik k = ServerController.getInstance().kreirajKorisnika();
                    res.setResult(k);
                    break;

                case SACUVAJ_NOVOG_KORISNIKA:
                    Korisnik novi = (Korisnik) req.getArgument();
                    res.setResult(ServerController.getInstance().sacuvajNovogKorisnika(novi));
                    break;

                case PRETRAZI_KORISNIKE:
                    Korisnik kriterijum = (Korisnik) req.getArgument();
                    List<Korisnik> pronadjeni = ServerController.getInstance().pretraziKorisnike(kriterijum);
                    res.setResult(pronadjeni);
                    break;

                case NADJI_KORISNIKA:
                    Integer id = (Integer) req.getArgument();
                    Korisnik korisnik = ServerController.getInstance().nadjiKorisnika(id);
                    res.setResult(korisnik);
                    break;

                case IZMENI_KORISNIKA:
                    Korisnik izmenjeni = (Korisnik) req.getArgument();
                    ServerController.getInstance().izmeniKorisnika(izmenjeni);
                    res.setResult(izmenjeni);
                    break;

                case OBRISI_KORISNIKA:
                    Integer idZaBrisanje = (Integer) req.getArgument();
                    ServerController.getInstance().obrisiKorisnika(idZaBrisanje);
                    res.setResult("Korisnik uspesno obrisan.");
                    break;

                case DODAJ_LICENCU:
                    TrenerLicenca tl = (TrenerLicenca) req.getArgument();
                    TrenerLicenca sacuvana = ServerController.getInstance().dodajLicencu(tl);
                    res.setResult(sacuvana);
                    break;

                case GET_LICENCE:
                    res.setResult(ServerController.getInstance().licenceZaTrenera((Trener) req.getArgument()));
                    break;

                case UCITAJ_TRENINZI:
                    List<Trening> treninzi = ServerController.getInstance().ucitajTreninge();
                    res.setResult(treninzi);
                    break;
 
                case PRIZNANICA_KREIRAJ:
                    Priznanica novaPriznanica = (Priznanica) req.getArgument();
                    res.setResult(ServerController.getInstance().kreirajPriznanicu(novaPriznanica));
                    break;
 
                case PRIZNANICA_PRETRAZI:
                    Priznanica kriterijumP = (Priznanica) req.getArgument();
                    res.setResult(ServerController.getInstance().pretraziPriznanice(kriterijumP));
                    break;
 
                case PRIZNANICA_IZMENI:
                    Priznanica izmenjenaPriznanica = (Priznanica) req.getArgument();
                    ServerController.getInstance().izmeniPriznanicu(izmenjenaPriznanica);
                    res.setResult(izmenjenaPriznanica);
                    break;
                    
                case PRIZNANICA_GET_ONE:
                    Integer idPriznanice = (Integer) req.getArgument();
                    res.setResult(ServerController.getInstance().getPriznanicaById(idPriznanice));
                    break;
                    
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.println("ClientThread handleReq(): " + e.getMessage());
            res.setException(e);
        }

        return res;
    }

    private Trener handleLogin(Request req) throws Exception {
        Trener t = (Trener) req.getArgument();

        // samo autentifikacija (SOLogin) - ne dira online status
        Trener logged = ServerController.getInstance().login(t);

        // dupli login provera
        if (server.isLogged(logged)) {
            throw new Exception("Trener je vec ulogovan!");
        }

        // tek sad stavljas online=true i cuvas ga u bazi
        ServerController.getInstance().setOnline(true, logged);

        this.trener = logged;
        System.out.println("Trener: " + logged.getEmail() + " je uspesno ulogovan!");
        return logged;
    }

    private void handleLogout() {
        if (trener == null) {
            return;
        }

        try {
            ServerController.getInstance().logout(trener);
        } catch (Exception e) {
            System.out.println("ClientThread handleLogout(): " + e.getMessage());
        }

        server.clients.remove(this);
        trener = null;
    }
}
