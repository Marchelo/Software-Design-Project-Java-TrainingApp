package client;

import client.models.LicencaTableModel;
import domm.Licenca;
import domm.Trener;
import domm.TrenerLicenca;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class DodajLicencu extends javax.swing.JDialog {
    Trener trener;
    CliController conn;
    private static final String FORMAT_DATUMA = "dd.MM.yyyy";
    
    LicencaTableModel licencaModel;
    List<TrenerLicenca> listTL;
    
    public DodajLicencu(Trener trener, CliController conn) {
        this.trener = trener;
        this.conn = conn;
        
        initComponents();
        
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(this);
        setTitle("Dodaj licencu");
        
        fillTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtOpis = new javax.swing.JTextField();
        txtDatum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnDodaj = new javax.swing.JButton();
        btnOtkazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLicence = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Naziv licence");

        jLabel2.setText("Opis");

        jLabel3.setText("Datum sticanja (dd.MM.yyyy)");

        btnDodaj.setText("Dodaj licencu");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnOtkazi.setText("Vrati se na glavnu formu");
        btnOtkazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtkaziActionPerformed(evt);
            }
        });

        tblLicence.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLicence);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDatum, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnDodaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOtkazi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtOpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnOtkazi))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        Date datum = validirajIProcitajDatum();
        if (datum == null) return;
 
        try {
            Licenca licenca = new Licenca();
            licenca.setNaziv(txtNaziv.getText().trim());
            licenca.setOpis(txtOpis.getText().trim());
            TrenerLicenca tl = new TrenerLicenca(trener, licenca, datum);
 
            conn.dodajLicencu(tl);
            
            fillTable();

            JOptionPane.showMessageDialog(this, "Sistem je zapamtio licencu.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            txtNaziv.setText("");
            txtOpis.setText("");
            txtDatum.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti licencu.", "Greska", JOptionPane.ERROR_MESSAGE);
            System.out.println("Sistem ne moze da zapamti licencu." + e.getMessage());
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnOtkaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtkaziActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnOtkaziActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnOtkazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLicence;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtOpis;
    // End of variables declaration//GEN-END:variables

    private Date validirajIProcitajDatum() {
        if (txtNaziv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti licencu. \n Naziv licence je obavezan.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (txtOpis.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti licencu. \n Opis licence je obavezan.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (txtDatum.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti licencu. \n Datum sticanja je obavezan.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return null;
        }
 
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATUMA);
            format.setLenient(false); // odbija datume koji ne postoje npr. 31.02.2024.
            return format.parse(txtDatum.getText().trim());
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Sistem ne moze da zapamti licencu. \n Datum nije ispravnog formata. Unesite ga kao " + FORMAT_DATUMA + " (npr. 15.03.2024.)", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    private void fillTable() {
        try {
            listTL = conn.licenceZaTrenera(trener);
            licencaModel = new LicencaTableModel(listTL);
            tblLicence.setModel(licencaModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje licence za ulogovanog trenera.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
}
