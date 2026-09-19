package client;

import client.models.KorisnikTableModel;
import domm.Korisnik;
import domm.Trener;
import domm.VrstaTreninga;
import java.util.List;
import javax.swing.JOptionPane;

public class KorisnikPretraga extends javax.swing.JDialog {
    Trener trener;
    CliController conn;
    
    KorisnikTableModel korisnikModel;
    List<Korisnik> listKorisnici;
    
    List<VrstaTreninga> vrste;
    
    public KorisnikPretraga(Trener trener, CliController conn) {
        this.trener = trener;
        this.conn = conn;
        
        initComponents();
        
        setModal(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Pretraga korisnika");
        
        fillTable();
        fillCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtIme = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnPretrazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKorisnici = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnDetalji = new javax.swing.JButton();
        cmbVrsta = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Ime");

        jLabel2.setText("Prezime");

        jLabel3.setText("Email");

        btnPretrazi.setText("Pretrazi korisnika");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        tblKorisnici.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKorisnici);

        btnBack.setText("Vrati se na glavnu formu");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnDetalji.setText("Detaljan prikaz korisnika");
        btnDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetaljiActionPerformed(evt);
            }
        });

        cmbVrsta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Vrsta treninga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbVrsta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPrezime, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addComponent(txtIme)
                                .addComponent(txtEmail))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDetalji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPretrazi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnPretrazi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnDetalji))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnBack))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbVrsta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        try {
            Korisnik korisnik = new Korisnik();

            korisnik.setIme(txtIme.getText().trim());
            korisnik.setPrez(txtPrezime.getText().trim());
            korisnik.setEmail(txtEmail.getText().trim());

            int index = cmbVrsta.getSelectedIndex();

            if (index > 0) {
                VrstaTreninga vrsta = vrste.get(index - 1);
                korisnik.setVrstaTreninga(vrsta);
            }
            
            listKorisnici = conn.pretraziKorisnike(korisnik);
            korisnikModel = new KorisnikTableModel(listKorisnici);
            tblKorisnici.setModel(korisnikModel);
            
            if (listKorisnici.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje korisnike po zadatim kriterijumima.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "Sistem je nasao korisnike po zadatim kriterijumima.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void btnDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetaljiActionPerformed
        try {
            int red = tblKorisnici.getSelectedRow();

            if (red == -1) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje korisnika po zadatom kriterijumu", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Korisnik korisnik = listKorisnici.get(red);
            KorisnikDetalji form = new KorisnikDetalji(trener, conn, korisnik);

            JOptionPane.showMessageDialog(this, "Sistem je nasao korisnika.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);

            form.setVisible(true);
            fillTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDetaljiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDetalji;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JComboBox<String> cmbVrsta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKorisnici;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        try {
            Korisnik korisnik = new Korisnik();
            
            listKorisnici = conn.pretraziKorisnike(korisnik);
            korisnikModel = new KorisnikTableModel(listKorisnici);            
            tblKorisnici.setModel(korisnikModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje korisnike po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillCombo() {
        try {
            vrste = conn.ucitajVrsteTreninga();
            cmbVrsta.removeAllItems();
            cmbVrsta.addItem("Vrsta treninga");
            
            for (VrstaTreninga v : vrste) {
                cmbVrsta.addItem(v.getOpis());
            }
            
            cmbVrsta.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita vrste treninga.", "Greska", JOptionPane.ERROR_MESSAGE);
        } 
    }
}
