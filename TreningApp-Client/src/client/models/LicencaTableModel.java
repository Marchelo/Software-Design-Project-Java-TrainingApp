package client.models;


import domm.TrenerLicenca;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LicencaTableModel extends AbstractTableModel{
    List<TrenerLicenca> licence = new ArrayList<>();
    String[] cols = {"Ime trenera","Prezime trenera","Naziv licence","Datum sticanja"};

    public LicencaTableModel(List<TrenerLicenca> licence) {
        this.licence = licence != null ? licence : new ArrayList<>();
    }

    public List<TrenerLicenca> getLicence() {
        return licence;
    }

    public void setLicence(List<TrenerLicenca> licence) {
        this.licence = licence != null ? licence : new ArrayList<>();
    }
 
    @Override
    public int getRowCount() {
        return licence.size();
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
        TrenerLicenca tl = licence.get(rowIndex);
        switch (columnIndex) {
            case 0: return tl.getTrener().getIme();
            case 1: return tl.getTrener().getPrezime();
            case 2: return tl.getLicenca().getNaziv();
            case 3: return tl.getDatum();
                
            default:
                throw new AssertionError();
        }
    }
}
