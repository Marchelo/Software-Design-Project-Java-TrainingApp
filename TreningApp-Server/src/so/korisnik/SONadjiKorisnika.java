package so.korisnik;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

public class SONadjiKorisnika extends AbstractSO {

    private Korisnik korisnik;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
        Korisnik k = (Korisnik) ado;
        if (k.getIdKorisnik() <= 0) {
            throw new Exception("ID korisnika mora biti validan!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        if (rezultat.isEmpty()) {
            throw new Exception("Sistem ne moze da nadje korisnika");
        }
        korisnik = (Korisnik) rezultat.get(0);
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }
}
