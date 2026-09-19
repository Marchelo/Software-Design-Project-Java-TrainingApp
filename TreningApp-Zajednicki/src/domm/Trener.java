package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Trener extends AbstractDomainObject {
    private int id;
    private String ime;
    private String prezime;
    private String email;
    private String sifra;
    private boolean online;

    public Trener() {}
    public Trener(String email, String sifra) {
        this.email = email;
        this.sifra = sifra;
    }
    public Trener(int id, String ime, String prezime, String email, String sifra, boolean online) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sifra = sifra;
        this.online = online;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String tableName() {
        return "trener";
    }

    @Override
    public String alias() {
        return "t";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idTrener");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prez");
            String email = rs.getString("email");
            String sifra = rs.getString("sifra");
            boolean online = rs.getBoolean("online");
            lista.add(new Trener(id, ime, prezime, email, sifra, online));
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(ime,prez,email,sifra,online)";
    }

    @Override
    public String requirement() {
        return "idTrener=" + id;
    }

    @Override
    public String valuesForInsert() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + sifra + "'," + (online ? 1 : 0);
    }

    @Override
    public String valuesForUpdate() {
        return "ime='" + ime + "', prez='" + prezime + "', email='" + email
                + "', sifra='" + sifra + "', online=" + (online ? 1 : 0);
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Trener)) return "";
        Trener t = (Trener) o;

        if (t.getId() > 0) {
            return " WHERE t.idTrener = " + t.getId();
        }

        // koristi se za login: email + sifra
        if (t.getEmail() != null && !t.getEmail().isBlank() && t.getSifra() != null && !t.getSifra().isBlank()) {
            return " WHERE t.email = '" + t.getEmail().trim().replace("'", "''")
                    + "' AND t.sifra = '" + t.getSifra().trim().replace("'", "''") + "'";
        }

        if (t.getEmail() != null && !t.getEmail().isBlank()) {
            return " WHERE t.email = '" + t.getEmail().trim().replace("'", "''") + "'";
        }

        String ime = t.getIme() == null ? "" : t.getIme().trim().replace("'", "''");
        String prezime = t.getPrezime() == null ? "" : t.getPrezime().trim().replace("'", "''");

        if (!ime.isBlank() || !prezime.isBlank()) {
            StringBuilder where = new StringBuilder(" WHERE ");
            boolean has = false;
            if (!ime.isBlank()) {
                where.append("t.ime LIKE '").append(ime).append("%'");
                has = true;
            }
            if (!prezime.isBlank()) {
                where.append(has ? " AND " : "").append("t.prez LIKE '").append(prezime).append("%'");
            }
            return where.toString();
        }

        return "";
    }
}