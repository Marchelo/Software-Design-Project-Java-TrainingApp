package so.trener;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Trener;
import java.util.ArrayList;
import so.AbstractSO;

// online=true radi ServerController nakon provere da trener nije vec ulogovan
public class SOLogin extends AbstractSO {
    private Trener ulogovaniTrener;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
        }
        Trener t = (Trener) ado;
        if (t.getEmail() == null || t.getEmail().isBlank()) {
            throw new Exception("Email je obavezan!");
        }
        if (t.getSifra() == null || t.getSifra().isBlank()) {
            throw new Exception("Sifra je obavezna!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Trener t = (Trener) ado;

        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(t);

        if (rezultat.isEmpty()) {
            throw new Exception("Ne postoji taj trener u bazi!");
        }

        ulogovaniTrener = (Trener) rezultat.get(0);
    }

    public Trener getUlogovaniTrener() {
        return ulogovaniTrener;
    }
}
