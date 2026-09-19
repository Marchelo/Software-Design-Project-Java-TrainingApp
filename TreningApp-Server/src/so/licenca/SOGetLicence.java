package so.licenca;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.TrenerLicenca;
import java.util.ArrayList;
import so.AbstractSO;

public class SOGetLicence extends AbstractSO {

    private ArrayList<TrenerLicenca> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TrenerLicenca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TrenerLicenca!");
        }
        TrenerLicenca tl = (TrenerLicenca) ado;
        if (tl.getTrener() == null || tl.getTrener().getId() <= 0) {
            throw new Exception("Trener je obavezan za pretragu licenci!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        lista = (ArrayList<TrenerLicenca>) (ArrayList<?>) rezultat;
    }

    public ArrayList<TrenerLicenca> getLista() {
        return lista;
    }
}
