package so.priznanica;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Priznanica;
import domm.StavkaPriznanice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import so.AbstractSO;

// SK3 - Promeni priznanica.
// 1. Prvo azuriras osnovne podatke priznanice i izracunavas ukupan iznos -> UPDATE (npr. promenjena kolicina/vreme)
// 2. zatim poredis stavke koje trenutno postoje u bazi sa stavkama koje su prosleđene iz forme. -> INSERT (nova stavka)
// 3. postojece stavke azuriras, nove stavke unosis, a stavke koje su ranije postojale u bazi, 
    // ali ih vise nema u prosleđenoj listi, brises -> DELETE (korisnik ju je obrisao u formi)
public class SOUpdatePriznanica extends AbstractSO {
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Priznanica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Priznanica!");
        }
        Priznanica p = (Priznanica) ado;

        if (p.getIdPriznanica() <= 0) {
            throw new Exception("Priznanica mora imati vazeci ID za izmenu.");
        }
        if (p.getTrener() == null || p.getTrener().getId() <= 0) {
            throw new Exception("Trener je obavezan.");
        }
        if (p.getKorisnik() == null || p.getKorisnik().getIdKorisnik() <= 0) {
            throw new Exception("Korisnik je obavezan.");
        }
        if (p.getStavke() == null || p.getStavke().isEmpty()) {
            throw new Exception("Priznanica mora imati bar jednu stavku.");
        }
        for (StavkaPriznanice s : p.getStavke()) {
            if (s.getTrening() == null) {
                throw new Exception("Svaka stavka mora imati izabran trening.");
            }
            if (s.getBrTreninga() <= 0) {
                throw new Exception("Broj treninga u stavci mora biti veci od 0.");
            }
            if (s.getVremePocetka() == null || s.getVremeZavrsetka() == null) {
                throw new Exception("Vreme pocetka i zavrsetka su obavezni za svaku stavku.");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Priznanica p = (Priznanica) ado;

        // izracunaj iznos svake stavke i ukupan iznos priznanice
        int ukupno = 0;
        for (StavkaPriznanice s : p.getStavke()) {
            int iznos = s.getTrening().getCena() * s.getBrTreninga();
            s.setIznos(iznos);
            ukupno += iznos;
        }
        p.setUkupanIznos(ukupno);

        // azuriraj osnovne podatke priznanice
        DBBroker.getInstance().update(p);

        // ucitaj id-jeve stavki koje TRENUTNO postoje u bazi za ovu priznanicu
        StavkaPriznanice filter = new StavkaPriznanice();
        filter.setIdPriznanica(p.getIdPriznanica());
        ArrayList<AbstractDomainObject> postojeceADO = DBBroker.getInstance().select(filter);

        // pravis skup ID-jeva - iz svake stare stavke uzimaš ID 
        Set<Integer> postojeciIdovi = new HashSet<>(); 
        for (AbstractDomainObject a : postojeceADO) {
            postojeciIdovi.add(((StavkaPriznanice) a).getId());
        }

        // prodji kroz nove stavke: ako id postoji u bazi -> UPDATE, ako ne -> INSERT
        Set<Integer> zadrzaniIdovi = new HashSet<>(); // koje stare stavke trener NIJE obrisao na pocetku je prazna {}
        int rb = 1;
        for (StavkaPriznanice s : p.getStavke()) {
            s.setIdPriznanica(p.getIdPriznanica()); // prolazis kroz nove stavke i ubacujes njihove ID-jeve.
            s.setRb(rb++);

            if (s.getId() > 0 && postojeciIdovi.contains(s.getId())) { // da li stavka ima id? ima - okej to je postojeca stavka
                DBBroker.getInstance().update(s);                       // a ako joj je id = 0 onda je nova stavka
                zadrzaniIdovi.add(s.getId());                           // proveris id ako je stavka sa tim id-em i dalje u formi nemoj je brisati
            } else {
                DBBroker.getInstance().insert(s);
            }
        }

        // obrisi samo one stavke koje su postojale ranije, a korisnik ih je uklonio iz forme
        Connection conn = DBBroker.getInstance().getConnection();
        for (Integer idStavke : postojeciIdovi) {
            if (!zadrzaniIdovi.contains(idStavke)) {
                try (PreparedStatement del = conn.prepareStatement(
                        "DELETE FROM stavkaPriznanice WHERE idStavka = ?")) {
                    del.setInt(1, idStavke);
                    del.executeUpdate();
                }
            }
        }
        /*
        ova delete ti radi po principu :
        pocetno stanje baze: {10, 11, 12}
        korisnik je u formi ostavio samo: {10,11}
        znaci obrisao je: 12
        kako program zna da je 12 obrisan ? tako sto imas postojeciIdovi i zadrzaniIdovi
        u ovoj situaciji imas ovako nes:
            postojeciIdovi = {10, 11, 12}
            zadrzaniIdovi  = {10, 11}
        i onda imas proveru (!zadrzaniIdovi.contains(idStavke))
        ako id stare stavke nije medju zadrzanim stavkama onda je korisnik obrisao tu stavku
        */
    }
}