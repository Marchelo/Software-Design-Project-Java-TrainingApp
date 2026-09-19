package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Korisnik extends AbstractDomainObject {
    private int idKorisnik;
    private String ime;
    private String prez;
    private String email;
    private VrstaTreninga vrstaTreninga;

    public Korisnik() {
    }

    public Korisnik(String ime, String prez, String email, VrstaTreninga vrstaTreninga) {
        this.ime = ime;
        this.prez = prez;
        this.email = email;
        this.vrstaTreninga = vrstaTreninga;
    }

    public Korisnik(String email) {
        this.email = email;
    }

    public Korisnik(int idKorisnik, String ime, String prez, String email, VrstaTreninga vrstaTreninga) {
        this.idKorisnik = idKorisnik;
        this.ime = ime;
        this.prez = prez;
        this.email = email;
        this.vrstaTreninga = vrstaTreninga;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrez() {
        return prez;
    }

    public void setPrez(String prez) {
        this.prez = prez;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VrstaTreninga getVrstaTreninga() {
        return vrstaTreninga;
    }

    public void setVrstaTreninga(VrstaTreninga vrstaTreninga) {
        this.vrstaTreninga = vrstaTreninga;
    }

    @Override
    public String toString() {
        return ime + " " + prez;
    }

    @Override
    public String tableName() {
        return "korisnik";
    }

    @Override
    public String alias() {
        return "k";
    }

    @Override
    public String join() {
        return "JOIN vrstaTreninga vt ON k.idVrstaTreninga = vt.idVrstaTreninga";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idVrstaTreninga = rs.getInt("vt.idVrstaTreninga");
            String opis = rs.getString("vt.opis");

            VrstaTreninga vt = new VrstaTreninga(
                    idVrstaTreninga,
                    opis
            );

            int idKorisnik = rs.getInt("k.idKorisnik");
            String ime = rs.getString("k.ime");
            String prez = rs.getString("k.prez");
            String email = rs.getString("k.email");

            Korisnik k = new Korisnik(
                    idKorisnik,
                    ime,
                    prez,
                    email,
                    vt
            );

            lista.add(k);
        }

        rs.close();

        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(ime,prez,email,idVrstaTreninga)";
    }

    @Override
    public String valuesForInsert() {
        return "'" + ime + "','" + prez + "','" + email + "',"
                + vrstaTreninga.getId();
    }

    @Override
    public String valuesForUpdate() {
        return "ime='" + ime
                + "', prez='" + prez
                + "', email='" + email
                + "', idVrstaTreninga=" + vrstaTreninga.getId();
    }

    @Override
    public String requirement() {
        return "idKorisnik=" + idKorisnik;
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Korisnik)) {
            return "";
        }

        Korisnik k = (Korisnik) o;

        if (k.getIdKorisnik() > 0) {
            return " WHERE k.idKorisnik = " + k.getIdKorisnik();
        }

        StringBuilder where = new StringBuilder();
        boolean hasCondition = false;

        // IME
        if (k.getIme() != null && !k.getIme().isBlank()) {

            String ime = k.getIme().trim().replace("'", "''");

            where.append(" WHERE k.ime LIKE '%")
                    .append(ime)
                    .append("%'");

            hasCondition = true;
        }

        // PREZIME
        if (k.getPrez() != null && !k.getPrez().isBlank()) {

            String prez = k.getPrez().trim().replace("'", "''");

            if (hasCondition) {
                where.append(" AND ");
            } else {
                where.append(" WHERE ");
            }

            where.append("k.prez LIKE '%")
                    .append(prez)
                    .append("%'");

            hasCondition = true;
        }

        // EMAIL
        if (k.getEmail() != null && !k.getEmail().isBlank()) {

            String email = k.getEmail().trim().replace("'", "''");

            if (hasCondition) {
                where.append(" AND ");
            } else {
                where.append(" WHERE ");
            }

            where.append("k.email LIKE '%")
                    .append(email)
                    .append("%'");

            hasCondition = true;
        }

        // VRSTA TRENINGA - COMBO BOX
        if (k.getVrstaTreninga() != null
                && k.getVrstaTreninga().getId() > 0) {

            if (hasCondition) {
                where.append(" AND ");
            } else {
                where.append(" WHERE ");
            }

            where.append("k.idVrstaTreninga = ")
                    .append(k.getVrstaTreninga().getId());

            hasCondition = true;
        }

        return where.toString(); 
    }
}