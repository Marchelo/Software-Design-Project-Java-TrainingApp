package so.licenca;

import db.DBBroker;
import domm.AbstractDomainObject;
import domm.Licenca;
import domm.TrenerLicenca;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import so.AbstractSO;

// dodas novu licencu i odmah je vezes za trenera
// oba inserta u cugu radis ako ne uspe vezivanje za trenera rollback sve ne moze cao
public class SODodajLicencu extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TrenerLicenca)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TrenerLicenca!");
        }
        TrenerLicenca tl = (TrenerLicenca) ado;
        if (tl.getTrener() == null || tl.getTrener().getId() <= 0) {
            throw new Exception("Trener je obavezan!");
        }
        if (tl.getLicenca() == null
                || tl.getLicenca().getNaziv() == null
                || tl.getLicenca().getNaziv().isBlank()) {
            throw new Exception("Naziv licence je obavezan!");
        }
        if (tl.getDatum() == null) {
            throw new Exception("Datum je obavezan!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        TrenerLicenca tl = (TrenerLicenca) ado;
        Licenca licenca = tl.getLicenca();

        // 1. INSERT u licenca
        PreparedStatement psLicenca = DBBroker.getInstance().insert(licenca);
        ResultSet keysLicenca = psLicenca.getGeneratedKeys();
        if (keysLicenca.next()) {
            licenca.setId(keysLicenca.getInt(1));
        } else {
            throw new Exception("Sistem ne moze da zapamti licencu");
        }

        // 2. INSERT u trener_licenca (koristis vec dobijeni idLicenca iznad)
        PreparedStatement psTL = DBBroker.getInstance().insert(tl);
        ResultSet keysTL = psTL.getGeneratedKeys();
        if (keysTL.next()) {
            tl.setIdTrenerLicenca(keysTL.getInt(1));
        } else {
            throw new Exception("Sistem ne moze da zapamti licencu");
        }
    }
}
