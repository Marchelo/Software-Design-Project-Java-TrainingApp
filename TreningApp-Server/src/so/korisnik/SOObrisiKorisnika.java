package so.korisnik;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Korisnik;
import so.AbstractSO;

public class SOObrisiKorisnika extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Korisnik k = (Korisnik) ado;
        if (k.getIdKorisnik() <= 0) {
            throw new Exception("Korisnik mora imati vazeci ID za brisanje.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        try {
            DBBroker.getInstance().delete(ado);
        } catch (Exception e) {
            // ON DELETE RESTRICT - ima priznanice ne moze brisanje !
            throw new Exception("Sistem ne moze da obrise korisnika jer postoje priznanice vezane za njega");
        }
    }
}
