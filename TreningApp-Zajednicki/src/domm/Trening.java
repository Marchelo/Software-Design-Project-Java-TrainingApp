package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Trening extends AbstractDomainObject {
    private int id;
    private TipTreninga tip;
    private int trajanje;
    private int cena;

    public Trening() {}
    public Trening(TipTreninga tip, int trajanje, int cena) {
        this.tip = tip;
        this.trajanje = trajanje;
        this.cena = cena;
    }
    public Trening(int id, TipTreninga tip, int trajanje, int cena) {
        this.id = id;
        this.tip = tip;
        this.trajanje = trajanje;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipTreninga getTip() {
        return tip;
    }

    public void setTip(TipTreninga tip) {
        this.tip = tip;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return tip.toString();
    }

    @Override
    public String tableName() {
        return "trening";
    }

    @Override
    public String alias() {
        return "tr";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idTrening");
            TipTreninga tip = TipTreninga.valueOf(rs.getString("tip"));
            int trajanje = rs.getInt("trajanje");
            int cena = rs.getInt("cena");
            lista.add(new Trening(id, tip, trajanje, cena));
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(tip,trajanje,cena)";
    }

    @Override
    public String requirement() {
        return "idTrening=" + id;
    }

    @Override
    public String valuesForInsert() {
        return "'" + tip.name() + "'," + trajanje + "," + cena;
    }

    @Override
    public String valuesForUpdate() {
        return "tip='" + tip.name() + "', trajanje=" + trajanje + ", cena=" + cena;
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Trening)) return "";
        Trening t = (Trening) o;

        if (t.getId() > 0) {
            return " WHERE tr.idTrening = " + t.getId();
        }
        if (t.getTip() != null) {
            return " WHERE tr.tip = '" + t.getTip().name() + "'";
        }
        return "";
    }
}