package controller;

import domm.Korisnik;
import domm.Priznanica;
import domm.Trener;
import domm.TrenerLicenca;
import domm.Trening;
import domm.VrstaTreninga;
import java.util.ArrayList;

import so.trener.SOLogin;
import so.trener.SOSetOnline;

import so.korisnik.SOSacuvajNovogKorisnika;
import so.korisnik.SOPretraziKorisnike;
import so.korisnik.SONadjiKorisnika;
import so.korisnik.SOIzmeniKorisnika;
import so.korisnik.SOObrisiKorisnika;

import so.vrstatreninga.SOUcitajVrsteTreninga;

import so.licenca.SODodajLicencu;
import so.licenca.SOGetLicence;
import so.priznanica.SOAddPriznanica;
import so.priznanica.SOGetPriznanica;
import so.priznanica.SOUpdatePriznanica;
import so.trening.SOGetAllTrening;

public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static synchronized ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    // ---------- TRENER - LOGIN / LOGOUT - SK8 ----------

    public Trener login(Trener kredencijali) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(kredencijali);
        return so.getUlogovaniTrener();
    }

    public void setOnline(boolean online, Trener trener) throws Exception {
        trener.setOnline(online);
        new SOSetOnline().templateExecute(trener);
    }

    public void logout(Trener trener) throws Exception {
        setOnline(false, trener);
    }

    // ---------- VRSTA TRENINGA ----------

    public ArrayList<VrstaTreninga> ucitajVrsteTreninga() throws Exception {
        SOUcitajVrsteTreninga so = new SOUcitajVrsteTreninga();
        so.templateExecute(new VrstaTreninga());
        return so.getLista();
    }

    // ---------- KORISNIK (SK4, SK5, SK6, SK7) ----------

    public Korisnik kreirajKorisnika() {
        // nema DB poziva - samo pravi prazan objekat za formu
        return new Korisnik();
    }

    public Korisnik sacuvajNovogKorisnika(Korisnik korisnik) throws Exception {
        new SOSacuvajNovogKorisnika().templateExecute(korisnik);
        return korisnik;
    }

    public ArrayList<Korisnik> pretraziKorisnike(Korisnik kriterijum) throws Exception {
        SOPretraziKorisnike so = new SOPretraziKorisnike();
        so.templateExecute(kriterijum != null ? kriterijum : new Korisnik());
        return so.getLista();
    }

    public Korisnik nadjiKorisnika(int id) throws Exception {
        SONadjiKorisnika so = new SONadjiKorisnika();
        Korisnik k = new Korisnik();
        k.setIdKorisnik(id);
        so.templateExecute(k);
        return so.getKorisnik();
    }

    public void izmeniKorisnika(Korisnik korisnik) throws Exception {
        new SOIzmeniKorisnika().templateExecute(korisnik);
    }

    public void obrisiKorisnika(int id) throws Exception {
        Korisnik k = new Korisnik();
        k.setIdKorisnik(id);
        new SOObrisiKorisnika().templateExecute(k);
    }

    // ---------- LICENCA - SK21 ----------

    public TrenerLicenca dodajLicencu(TrenerLicenca tl) throws Exception {
        new SODodajLicencu().templateExecute(tl);
        return tl;
    }

    public ArrayList<TrenerLicenca> licenceZaTrenera(Trener trener) throws Exception {
        SOGetLicence so = new SOGetLicence();
        TrenerLicenca filter = new TrenerLicenca();
        filter.setTrener(trener);
        so.templateExecute(filter);
        return so.getLista();
    }
    
    // ---------- TRENING ----------
 
    public ArrayList<Trening> ucitajTreninge() throws Exception {
        SOGetAllTrening so = new SOGetAllTrening();
        so.templateExecute(new Trening());
        return so.getLista();
    }
 
    // ---------- PRIZNANICA (SK1, SK2, SK3) ----------
 
    public Priznanica kreirajPriznanicu(Priznanica p) throws Exception {
        new SOAddPriznanica().templateExecute(p);
        return p;
    }
 
    public ArrayList<Priznanica> pretraziPriznanice(Priznanica filter) throws Exception {
        SOGetPriznanica so = new SOGetPriznanica();
        so.templateExecute(filter != null ? filter : new Priznanica());
        return so.getLista();
    }
 
    public void izmeniPriznanicu(Priznanica p) throws Exception {
        new SOUpdatePriznanica().templateExecute(p);
    }
    
    public Priznanica getPriznanicaById(int id) throws Exception {
        SOGetPriznanica so = new SOGetPriznanica();
        Priznanica filter = new Priznanica();
        filter.setIdPriznanica(id);
        so.templateExecute(filter);
        ArrayList<Priznanica> lista = so.getLista();
        return lista.isEmpty() ? null : lista.get(0);
    }
}
