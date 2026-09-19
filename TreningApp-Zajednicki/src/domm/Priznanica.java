package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Priznanica extends AbstractDomainObject {
    private int idPriznanica;
    private int ukupanIznos;
    private String mestoIzdavanja;
    private Date datumIzdavanja;
    private Trener trener;
    private Korisnik korisnik;
    private ArrayList<StavkaPriznanice> stavke = new ArrayList<>();

    public Priznanica() {}
    public Priznanica(Trener trener, Korisnik korisnik) {
        this.trener = trener;
        this.korisnik = korisnik;
    }
    public Priznanica(int idPriznanica, int ukupanIznos,
            String mestoIzdavanja, Date datumIzdavanja,
            Trener trener, Korisnik korisnik) {

        this.idPriznanica = idPriznanica;
        this.ukupanIznos = ukupanIznos;
        this.mestoIzdavanja = mestoIzdavanja;
        this.datumIzdavanja = datumIzdavanja;
        this.trener = trener;
        this.korisnik = korisnik;
    }

    public int getIdPriznanica() {
        return idPriznanica;
    }

    public void setIdPriznanica(int idPriznanica) {
        this.idPriznanica = idPriznanica;
    }

    public int getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(int ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public String getMestoIzdavanja() {
        return mestoIzdavanja;
    }

    public void setMestoIzdavanja(String mestoIzdavanja) {
        this.mestoIzdavanja = mestoIzdavanja;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    public ArrayList<StavkaPriznanice> getStavke() {
        return stavke;
    }

    public void setStavke(ArrayList<StavkaPriznanice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Priznanica br. " + idPriznanica + " - " + korisnik;
    }

    @Override
    public String tableName() {
        return "priznanica";
    }

    @Override
    public String alias() {
        return "p";
    }

    @Override
    public String join() {
        return "JOIN trener t ON p.idTrener = t.idTrener "
                + "JOIN korisnik k ON p.idKorisnik = k.idKorisnik";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            // TRENER - uvek citas iz tabele trener (alias t)
            Trener t = new Trener(
                    rs.getInt("t.idTrener"),
                    rs.getString("t.ime"),
                    rs.getString("t.prez"),
                    rs.getString("t.email"),
                    rs.getString("t.sifra"),
                    rs.getBoolean("t.online")
            );

            // KORISNIK - uvek citas iz tabele korisnik (alias k)
            Korisnik k = new Korisnik(
                    rs.getInt("k.idKorisnik"),
                    rs.getString("k.ime"),
                    rs.getString("k.prez"),
                    rs.getString("k.email"),
                    null
            );

            // PRIZNANICA - citamo iz tabele priznanica (alias p)
            int idPriznanica = rs.getInt("p.idPriznanica");
            int ukupanIznos = rs.getInt("p.ukupanIznos");
            String mestoIzdavanja = rs.getString("p.mestoIzdavanja");
            Date datumIzdavanja = rs.getDate("p.datumIzdavanja");

            lista.add(new Priznanica(
                    idPriznanica,
                    ukupanIznos,
                    mestoIzdavanja,
                    datumIzdavanja,
                    t,
                    k
            ));
        }

        rs.close();
        return lista;
    }
    
    @Override
    public String columnsForInsert() {
        return "(ukupanIznos,mestoIzdavanja,datumIzdavanja,idTrener,idKorisnik)";
    }

    @Override
    public String requirement() {
        return "idPriznanica=" + idPriznanica;
    }

    @Override
    public String valuesForInsert() {
        return ukupanIznos
                + ",'" + mestoIzdavanja + "','"
                + new java.sql.Date(datumIzdavanja.getTime())
                + "'," + trener.getId()
                + "," + korisnik.getIdKorisnik();
    }

    @Override
    public String valuesForUpdate() {
        return "ukupanIznos=" + ukupanIznos
                + ", mestoIzdavanja='" + mestoIzdavanja
                + "', datumIzdavanja='"
                + new java.sql.Date(datumIzdavanja.getTime())
                + "', idTrener=" + trener.getId()
                + ", idKorisnik=" + korisnik.getIdKorisnik();
    }

    @Override
    public String requirementForSelect(Object o) {
        if (!(o instanceof Priznanica)) {
            return "";
        }

        Priznanica p = (Priznanica) o;

        if (p.getIdPriznanica() > 0) {
            return " WHERE p.idPriznanica = " + p.getIdPriznanica();
        }

        StringBuilder where = new StringBuilder();
        boolean hasCondition = false;

        if (p.getKorisnik() != null && p.getKorisnik().getIdKorisnik() > 0) {
            where.append(" WHERE p.idKorisnik = ").append(p.getKorisnik().getIdKorisnik());
            hasCondition = true;
        }

        if (p.getTrener() != null && p.getTrener().getId() > 0) {
            where.append(hasCondition ? " AND " : " WHERE")
                    .append(" p.idTrener = ").append(p.getTrener().getId());
            hasCondition = true;
        }

        if (p.getDatumIzdavanja() != null) {
            where.append(hasCondition ? " AND " : " WHERE")
                    .append(" p.datumIzdavanja = '")
                    .append(new java.sql.Date(p.getDatumIzdavanja().getTime()))
                    .append("'");
            hasCondition = true;
        }

        return where.toString();
        
//        if (!(o instanceof Priznanica)) {
//            return "";
//        }
//
//        Priznanica p = (Priznanica) o;
//
//        if (p.getIdPriznanica() > 0) {
//            return " WHERE p.idPriznanica = "
//                    + p.getIdPriznanica();
//        }
//
//        if (p.getKorisnik() != null
//                && p.getKorisnik().getIdKorisnik() > 0) {
//
//            return " WHERE p.idKorisnik = "
//                    + p.getKorisnik().getIdKorisnik();
//        }
//
//        if (p.getTrener() != null
//                && p.getTrener().getId() > 0) {
//
//            return " WHERE p.idTrener = "
//                    + p.getTrener().getId();
//        }
//
//        return "";
    }
}