package domm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
// ne pravis punu referencu na priznanicu da bi izbegao kruznu zavisnost Priznanica <-> StavkaPriznanice
// cuvas samo idPriznanica(FK) a punis objekat Trening jer se njegova cena koristi za racunanje iznosa stavke

public class StavkaPriznanice extends AbstractDomainObject {
    private int id;
    private int rb;
    private int brTreninga;
    private int iznos;
    private Date vremePocetka;
    private Date vremeZavrsetka;
    private String napomena;
    private int idPriznanica;
    private Trening trening;

    public StavkaPriznanice() {}
    public StavkaPriznanice(int idPriznanica, Trening trening) {
        this.idPriznanica = idPriznanica;
        this.trening = trening;
    }
    public StavkaPriznanice(int id, int rb, int brTreninga, int iznos, 
            Date vremePocetka, Date vremeZavrsetka,
            String napomena, int idPriznanica, Trening trening) {
        this.id = id;
        this.rb = rb;
        this.brTreninga = brTreninga;
        this.iznos = iznos;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.napomena = napomena;
        this.idPriznanica = idPriznanica;
        this.trening = trening;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getBrTreninga() {
        return brTreninga;
    }

    public void setBrTreninga(int brTreninga) {
        this.brTreninga = brTreninga;
    }

    public int getIznos() {
        return iznos;
    }

    public void setIznos(int iznos) {
        this.iznos = iznos;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Date getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    public void setVremeZavrsetka(Date vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public int getIdPriznanica() {
        return idPriznanica;
    }

    public void setIdPriznanica(int idPriznanica) {
        this.idPriznanica = idPriznanica;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    @Override
    public String toString() {
        return "Stavka " + rb + " - " + trening;
    }

    @Override
    public String tableName() {
        return "stavkaPriznanice";
    }

    @Override
    public String alias() {
        return "sp";
    }

    @Override
    public String join() {
        return "JOIN trening tr ON sp.idTrening = tr.idTrening";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trening t = new Trening(
                    rs.getInt("tr.idTrening"),
                    TipTreninga.valueOf(rs.getString("tr.tip")),
                    rs.getInt("tr.trajanje"),
                    rs.getInt("tr.cena")
            );

            StavkaPriznanice sp = new StavkaPriznanice(
                    rs.getInt("sp.idStavka"),
                    rs.getInt("sp.rb"),
                    rs.getInt("sp.brTreninga"),
                    rs.getInt("sp.iznos"),
                    rs.getTimestamp("sp.vremePocetka"),
                    rs.getTimestamp("sp.vremeZavrsetka"),
                    rs.getString("sp.napomena"),
                    rs.getInt("sp.idPriznanica"),
                    t
            );
            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String columnsForInsert() {
        return "(rb,brTreninga,iznos,vremePocetka,vremeZavrsetka,napomena,idPriznanica,idTrening)";
    }

    @Override
    public String requirement() {
        return "idStavka=" + id;
    }

    @Override
    public String valuesForInsert() {
        return rb + "," + brTreninga + "," + iznos
                + ",'" + new Timestamp(vremePocetka.getTime()) + "'"
                + ",'" + new Timestamp(vremeZavrsetka.getTime()) + "'"
                + ",'" + napomena.replace("'", "''") + "'"
                + "," + idPriznanica + "," + trening.getId();
    }

    @Override
    public String valuesForUpdate() {
        return "rb=" + rb
                + ", brTreninga=" + brTreninga
                + ", iznos=" + iznos
                + ", vremePocetka='" + new Timestamp(vremePocetka.getTime()) + "'"
                + ", vremeZavrsetka='" + new Timestamp(vremeZavrsetka.getTime()) + "'"
                + ", napomena='" + napomena.replace("'", "''") + "'"
                + ", idPriznanica=" + idPriznanica
                + ", idTrening=" + trening.getId();
    }

    @Override
    public String requirementForSelect(Object o) {
        Integer idFiltera = null;

        if (o instanceof Integer) {
            idFiltera = (Integer) o;
        } else if (o instanceof Priznanica) {
            idFiltera = ((Priznanica) o).getIdPriznanica();
        } else if (o instanceof StavkaPriznanice) {
            idFiltera = ((StavkaPriznanice) o).getIdPriznanica();
        }

        if (idFiltera != null && idFiltera > 0) {
            return " WHERE sp.idPriznanica = " + idFiltera;
        }
        return "";
    }
}