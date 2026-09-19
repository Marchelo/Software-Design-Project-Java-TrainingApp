package so.trening;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Trening;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetAllTrening extends AbstractSO {

    private ArrayList<Trening> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Trening>) (ArrayList<?>) rezultat;
    }

    public ArrayList<Trening> getLista() {
        return lista;
    }
}