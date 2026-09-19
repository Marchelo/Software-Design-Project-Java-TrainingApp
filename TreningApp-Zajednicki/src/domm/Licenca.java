package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Licenca extends AbstractDomainObject {
    private int id;
    private String naziv;
    private String opis;

    public Licenca() {}
    public Licenca(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }
    public Licenca(int id, String naziv, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String tableName() {
        return "licenca";
    }

    @Override
    public String alias() {
        return "l";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idLicenca");
            String naziv = rs.getString("naziv");
            String opis = rs.getString("opis");
            lista.add(new Licenca(id, naziv, opis));
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(naziv,opis)";
    }

    @Override
    public String requirement() {
        return "idLicenca=" + id;
    }

    @Override
    public String valuesForInsert() {
        return "'" + naziv + "','" + opis + "'";
    }

    @Override
    public String valuesForUpdate() {
        return "naziv='" + naziv + "', opis='" + opis + "'";
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Licenca)) return "";
        Licenca lic = (Licenca) o;

        if (lic.getId() > 0) {
            return " WHERE l.idLicenca = " + lic.getId();
        }
        if (lic.getNaziv() != null && !lic.getNaziv().isBlank()) {
            return " WHERE l.naziv LIKE '" + lic.getNaziv().trim().replace("'", "''") + "%'";
        }
        return "";
    }
}