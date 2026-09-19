package client.models;

import domm.Priznanica;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PriznanicaTableModel extends AbstractTableModel {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    List<Priznanica> priznanice = new ArrayList<>();
    String[] cols = {"ID", "Korisnik", "Mesto izdavanja", "Datum izdavanja", "Ukupan iznos"};

    public PriznanicaTableModel(List<Priznanica> priznanice) {
        this.priznanice = priznanice != null ? priznanice : new ArrayList<>();
    }

    public void setData(List<Priznanica> lista) {
        this.priznanice = lista != null ? lista : new ArrayList<>();
        fireTableDataChanged();
    }

    public Priznanica getAt(int row) {
        return priznanice.get(row);
    }

    @Override
    public int getRowCount() {
        return priznanice.size();
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
        Priznanica p = priznanice.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getIdPriznanica();
            case 1: return p.getKorisnik() != null ? p.getKorisnik().getIme() + " " + p.getKorisnik().getPrez() : "";
            case 2: return p.getMestoIzdavanja();
            case 3: return p.getDatumIzdavanja() != null ? FORMAT.format(p.getDatumIzdavanja()) : "";
            case 4: return p.getUkupanIznos();
            default:
                throw new AssertionError();
        }
    }
}