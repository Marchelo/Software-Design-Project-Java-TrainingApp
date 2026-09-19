package so.korisnik;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Korisnik;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

public class SOSacuvajNovogKorisnika extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Korisnik k = (Korisnik) ado;
        if (k.getIme() == null || k.getIme().isBlank()) {
            throw new Exception("Ime je obavezno!");
        }
        if (k.getPrez() == null || k.getPrez().isBlank()) {
            throw new Exception("Prezime je obavezno!");
        }
        if (k.getEmail() == null || !k.getEmail().contains("@")) {
            throw new Exception("Neispravan format email-a!");
        }
        if (k.getVrstaTreninga() == null || k.getVrstaTreninga().getId() <= 0) {
            throw new Exception("Vrsta treninga je obavezna!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Korisnik k = (Korisnik) ado;
        PreparedStatement ps = DBBroker.getInstance().insert(k);
        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            k.setIdKorisnik(keys.getInt(1));
        } else {
            throw new Exception("Sistem ne moze da zapamti korisnika");
        }
    }
}
