package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VrstaTreninga extends AbstractDomainObject {
    private int id;
    private String opis;

    public VrstaTreninga() {}
    public VrstaTreninga(String opis) {
        this.opis = opis;
    }
    public VrstaTreninga(int id, String opis) {
        this.id = id;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;
    }

    @Override
    public String tableName() {
        return "vrstaTreninga";
    }

    @Override
    public String alias() {
        return "vt";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idVrstaTreninga");
            String opis = rs.getString("opis");
            lista.add(new VrstaTreninga(id, opis));
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(opis)";
    }

    @Override
    public String requirement() {
        return "idVrstaTreninga=" + id;
    }

    @Override
    public String valuesForInsert() {
        return "'" + opis + "'";
    }

    @Override
    public String valuesForUpdate() {
        return "opis='" + opis + "'";
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof VrstaTreninga)) return "";
        VrstaTreninga v = (VrstaTreninga) o;

        if (v.getId() > 0) {
            return " WHERE vt.idVrstaTreninga = " + v.getId();
        }
        if (v.getOpis() != null && !v.getOpis().isBlank()) {
            return " WHERE vt.opis LIKE '" + v.getOpis().trim().replace("'", "''") + "%'";
        }
        return "";
    }    
}