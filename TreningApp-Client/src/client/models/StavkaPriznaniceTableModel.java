package client.models;

import domm.StavkaPriznanice;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StavkaPriznaniceTableModel extends AbstractTableModel {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    List<StavkaPriznanice> stavke = new ArrayList<>();
    String[] cols = {"Rb", "Trening", "Br. treninga", "Pocetak", "Kraj", "Napomena", "Iznos"};

    public StavkaPriznaniceTableModel() {
    }

    public StavkaPriznaniceTableModel(List<StavkaPriznanice> stavke) {
        this.stavke = stavke != null ? new ArrayList<>(stavke) : new ArrayList<>();
    }

    public List<StavkaPriznanice> getStavke() {
        return stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
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
        StavkaPriznanice s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getRb();
            case 1: return s.getTrening() != null ? s.getTrening().toString() : "";
            case 2: return s.getBrTreninga();
            case 3: return s.getVremePocetka() != null ? FORMAT.format(s.getVremePocetka()) : "";
            case 4: return s.getVremeZavrsetka() != null ? FORMAT.format(s.getVremeZavrsetka()) : "";
            case 5: return s.getNapomena();
            case 6: return s.getIznos();
            default:
                throw new AssertionError();
        }
    }

    private void novIndex() {
        for (int i = 0; i < stavke.size(); i++) {
            stavke.get(i).setRb(i + 1);
        }
    }

    public void dodajStavku(StavkaPriznanice nova) {
        stavke.add(nova);
        novIndex();
        fireTableRowsInserted(stavke.size() - 1, stavke.size() - 1);
    }

    public void obrisiStavku(int row) {
        if (row < 0 || row >= stavke.size()) {
            return;
        }
        stavke.remove(row);
        novIndex();
        fireTableDataChanged();
    }

    public int vratiUkupanIznos() {
        int ukupno = 0;
        for (StavkaPriznanice s : stavke) {
            ukupno += s.getIznos();
        }
        return ukupno;
    }
}
/*

*/