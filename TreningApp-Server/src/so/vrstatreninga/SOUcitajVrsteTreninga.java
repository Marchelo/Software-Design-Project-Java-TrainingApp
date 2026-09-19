package so.vrstatreninga;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.VrstaTreninga;
import java.util.ArrayList;
import so.AbstractSO;

public class SOUcitajVrsteTreninga extends AbstractSO {

    private ArrayList<VrstaTreninga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VrstaTreninga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaTreninga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaTreninga>) (ArrayList<?>) rezultat;
    }

    public ArrayList<VrstaTreninga> getLista() {
        return lista;
    }
}
