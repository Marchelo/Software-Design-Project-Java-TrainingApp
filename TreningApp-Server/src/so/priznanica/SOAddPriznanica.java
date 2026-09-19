package so.priznanica;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Priznanica;
import domm.StavkaPriznanice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

// SK1 - Kreiraj priznanica
// za svaku stavku racuna iznos racunas kao: trening.cena * brTreninga
// ukupanIznos priznanice = suma svih iznosa stavki
public class SOAddPriznanica extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Priznanica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Priznanica!");
        }
        Priznanica p = (Priznanica) ado;

        if (p.getTrener() == null || p.getTrener().getId() <= 0) {
            throw new Exception("Trener je obavezan.");
        }
        if (p.getKorisnik() == null || p.getKorisnik().getIdKorisnik() <= 0) {
            throw new Exception("Korisnik je obavezan.");
        }
        if (p.getMestoIzdavanja() == null || p.getMestoIzdavanja().isBlank()) {
            throw new Exception("Mesto izdavanja je obavezno.");
        }
        if (p.getDatumIzdavanja() == null) {
            throw new Exception("Datum izdavanja je obavezan.");
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

        PreparedStatement ps = DBBroker.getInstance().insert(p);
        ResultSet keys = ps.getGeneratedKeys();
        if (!keys.next()) {
            throw new Exception("Sistem ne moze da kreira priznanicu");
        }
        int idPriznanica = keys.getInt(1);
        p.setIdPriznanica(idPriznanica);

        int rb = 1;
        for (StavkaPriznanice s : p.getStavke()) {
            s.setIdPriznanica(idPriznanica);
            s.setRb(rb++);
            DBBroker.getInstance().insert(s);
        }
    }
}