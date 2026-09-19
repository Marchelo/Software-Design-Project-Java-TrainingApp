package client;

import client.models.PriznanicaTableModel;
import domm.Korisnik;
import domm.Priznanica;
import domm.Trener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class PriznanicaPretraga extends javax.swing.JDialog {
    Trener trener; 
    CliController conn;

    private static final SimpleDateFormat DATUM_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    
    private PriznanicaTableModel model;
    private List<Korisnik> listaKorisnika;
    private List<Priznanica> listaPriznanica;
    
    public PriznanicaPretraga(Trener trener, CliController conn) {
        this.trener = trener;
        this.conn = conn;
        
        DATUM_FORMAT.setLenient(false);
        
        initComponents();
        
        model = new PriznanicaTableModel(listaPriznanica);
        tblPriznanica.setModel(model);
            
        setModal(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Pretraga priznanica");
        
        fillKorisnici();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbKorisnik = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPriznanica = new javax.swing.JTable();
        btnPretrazi = new javax.swing.JButton();
        btnDetalji = new javax.swing.JButton();
        btnZatvori = new javax.swing.JButton();
        txtDatumIzdavanja = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Korisnik");

        cmbKorisnik.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblPriznanica.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPriznanica);

        btnPretrazi.setText("Pretrazi priznanicu");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        btnDetalji.setText("Detalji priznanice");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        btnZatvori.setText("Vrati se na glavnu formu");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        jLabel2.setText("Datum izdavanja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnZatvori)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDatumIzdavanja)
                            .addComponent(cmbKorisnik, 0, 200, Short.MAX_VALUE))
                        .addGap(262, 262, 262)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPretrazi)
                            .addComponent(btnDetalji, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretrazi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDetalji)
                    .addComponent(txtDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZatvori)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZatvoriActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnZatvoriActionPerformed

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        int row = tblPriznanica.getSelectedRow();
        if (row < 0) {
//            JOptionPane.showMessageDialog(this, "Izaberi priznanicu iz tabele.");
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje priznanicu.", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        Priznanica osnovna = model.getAt(row);
 
        try {
            Priznanica popunjenaPriz = conn.getPriznanicaById(osnovna.getIdPriznanica());
            if (popunjenaPriz == null) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje priznanicu.", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            PriznanicaDetalji form = new PriznanicaDetalji(trener, conn, popunjenaPriz);
            JOptionPane.showMessageDialog(this, "Sistem је našao priznanicu.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            
            form.setVisible(true);
            pretrazi(); // osvezi listu posle eventualne izmene
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje priznanicu.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDetaljiActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        pretrazi();
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void fillKorisnici() {
        try {
            listaKorisnika = conn.pretraziKorisnike(new Korisnik());
            cmbKorisnik.removeAllItems();
            cmbKorisnik.addItem("Svi korisnici"); 
            
            for (Korisnik k : listaKorisnika) {
                cmbKorisnik.addItem(k.getIme() + " " + k.getPrez());
            }
            cmbKorisnik.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita korisnike.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pretrazi() {
        try {
            Priznanica priznanica = new Priznanica();
            
            Korisnik k = null;
            int index = cmbKorisnik.getSelectedIndex();
            if(index > 0){
                k = listaKorisnika.get(index - 1);
                priznanica.setKorisnik(k);
            }
 
            String txtDatum = txtDatumIzdavanja.getText().trim();
            if (!txtDatum.isEmpty()) {
                Date datum;
                try {
                    datum = DATUM_FORMAT.parse(txtDatum);
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(this, "Datum izdavanja mora biti u formatu dd.MM.yyyy", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                priznanica.setDatumIzdavanja(datum);
            }            
            
            listaPriznanica = conn.pretraziPriznanice(priznanica);
 
            if (listaPriznanica.isEmpty()) {
                model.setData(new ArrayList<>());
                JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje priznanice po zadatim kriterijumima.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            }else{
                model.setData(listaPriznanica);
                JOptionPane.showMessageDialog(this, "Sistem je nasao priznanice po zadatim kriterijumima.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            } 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje priznanice.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JComboBox<String> cmbKorisnik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPriznanica;
    private javax.swing.JTextField txtDatumIzdavanja;
    // End of variables declaration//GEN-END:variables
}
