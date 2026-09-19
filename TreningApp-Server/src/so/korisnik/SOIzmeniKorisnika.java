package so.korisnik;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Korisnik;
import so.AbstractSO;

public class SOIzmeniKorisnika extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Korisnik k = (Korisnik) ado;
        if (k.getIdKorisnik() <= 0) {
            throw new Exception("Korisnik mora imati vazeci ID za izmenu.");
        }
        if (k.getEmail() == null || !k.getEmail().contains("@")) {
            throw new Exception("Neispravan format email-a!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
