package client.models;

import domm.Korisnik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class KorisnikTableModel extends AbstractTableModel{
    List<Korisnik> korisnici = new ArrayList<>();
    String[] cols = {"Ime","Prezime","Email","Vrsta treninga"};

    public KorisnikTableModel(List<Korisnik> korisnici) {
        this.korisnici = korisnici != null ? korisnici : new ArrayList<>(); // ako nije null, stavi tu listu; ako jeste null, napravi praznu listu
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici != null ? korisnici : new ArrayList<>(); // ista prica i ovde kao gore
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIme();
            case 1: return k.getPrez();
            case 2: return k.getEmail();
            case 3: 
                return k.getVrstaTreninga() != null
                ? k.getVrstaTreninga().getOpis()
                : "";
                
            default:
                throw new AssertionError();
        }
    }
}
