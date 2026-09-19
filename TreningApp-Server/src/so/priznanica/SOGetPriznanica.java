package so.priznanica;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Priznanica;
import domm.StavkaPriznanice;
import java.util.ArrayList;
import so.AbstractSO;

// SK2 - Pretrazi priznanica.
// Nadje priznanice po kriterijumima (id, korisnik, trener, datum izdavanja), pa za svaku dovuce i njene stavke 
public class SOGetPriznanica extends AbstractSO {

    private ArrayList<Priznanica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Priznanica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Priznanica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> rezultat = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Priznanica>) (ArrayList<?>) rezultat;

        for (Priznanica p : lista) {
            StavkaPriznanice filter = new StavkaPriznanice();
            filter.setIdPriznanica(p.getIdPriznanica());

            ArrayList<AbstractDomainObject> stavkeADO = DBBroker.getInstance().select(filter);
            ArrayList<StavkaPriznanice> stavke = (ArrayList<StavkaPriznanice>) (ArrayList<?>) stavkeADO;
            p.setStavke(stavke);
        }
    }

    public ArrayList<Priznanica> getLista() {
        return lista;
    }
}