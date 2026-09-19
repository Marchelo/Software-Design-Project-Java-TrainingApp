package so.trener;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Trener;
import so.AbstractSO;

// Postavlja online=true/false(login/logout), ima generic update() koji ce zapisati SVA polja iz valuesForUpdate()
// trener mora vec imati kompletne podatke (ime, prez, email, sifra) pre poziva, osigurao si da to uvek radi tako lagan si
public class SOSetOnline extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
        }
        Trener t = (Trener) ado;
        if (t.getId() <= 0) {
            throw new Exception("Trener mora imati vazeci ID.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
