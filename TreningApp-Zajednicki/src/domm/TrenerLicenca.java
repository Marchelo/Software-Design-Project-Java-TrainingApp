package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TrenerLicenca extends AbstractDomainObject {
    private int idTrenerLicenca;
    private Trener trener;
    private Licenca licenca;
    private Date datum;

    public TrenerLicenca() {}
    public TrenerLicenca(Trener trener, Licenca licenca, Date datum) {
        this.trener = trener;
        this.licenca = licenca;
        this.datum = datum;
    }
    public TrenerLicenca(int idTrenerLicenca, Trener trener, Licenca licenca, Date datum) {
        this.idTrenerLicenca = idTrenerLicenca;
        this.trener = trener;
        this.licenca = licenca;
        this.datum = datum;
    }

    public int getIdTrenerLicenca() {
        return idTrenerLicenca;
    }

    public void setIdTrenerLicenca(int idTrenerLicenca) {
        this.idTrenerLicenca = idTrenerLicenca;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return trener + " - " + licenca + " (" + datum + ")";
    }

    @Override
    public String tableName() {
        return "trener_licenca";
    }

    @Override
    public String alias() {
        return "tl";
    }

    @Override
    public String join() {
        return "JOIN trener t ON tl.idTrener = t.idTrener "
                + "JOIN licenca l ON tl.idLicenca = l.idLicenca";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trener t = new Trener(
                    rs.getInt("t.idTrener"),
                    rs.getString("t.ime"),
                    rs.getString("t.prez"),
                    rs.getString("t.email"),
                    rs.getString("t.sifra"),
                    rs.getBoolean("t.online")
            );
            Licenca l = new Licenca(
                    rs.getInt("l.idLicenca"),
                    rs.getString("l.naziv"),
                    rs.getString("l.opis")
            );
            int idTrenerLicenca = rs.getInt("tl.idTrenerLicenca");
            Date datum = rs.getDate("tl.datum");
            
            lista.add(new TrenerLicenca(
                    idTrenerLicenca,
                    t,
                    l,
                    datum
            ));
        }
        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(idTrener,idLicenca,datum)";
    }

    @Override
    public String requirement() {
        return "idTrenerLicenca=" + idTrenerLicenca;
    }

    @Override
    public String valuesForInsert() {
        return trener.getId()
                + "," + licenca.getId()
                + ",'" + new java.sql.Date(datum.getTime()) + "'";
    }

    @Override
    public String valuesForUpdate() {
        return "idTrener=" + trener.getId()
                + ", idLicenca=" + licenca.getId()
                + ", datum='" + new java.sql.Date(datum.getTime()) + "'";
    }

    @Override
    public String requirementForSelect(Object o) {

        if (o == null) {
            return "";
        }

        if (o instanceof Integer) {
            return " WHERE tl.idTrener = " + (Integer) o;
        }

        if (o instanceof Trener) {
            return " WHERE tl.idTrener = " + ((Trener) o).getId();
        }

        if (o instanceof TrenerLicenca) {

            TrenerLicenca tl = (TrenerLicenca) o;

            if (tl.getIdTrenerLicenca() > 0) {
                return " WHERE tl.idTrenerLicenca = "
                        + tl.getIdTrenerLicenca();
            }

            if (tl.getTrener() != null
                    && tl.getTrener().getId() > 0) {

                return " WHERE tl.idTrener = "
                        + tl.getTrener().getId();
            }
        }

        return "";
    }
}