package so.korisnik;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

public class SOPretraziKorisnike extends AbstractSO {

    private ArrayList<Korisnik> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Korisnik>) (ArrayList<?>) rezultat;
    }

    public ArrayList<Korisnik> getLista() {
        return lista;
    }
}
