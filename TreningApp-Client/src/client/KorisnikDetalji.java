package client;

import domm.Korisnik;
import domm.Trener;
import domm.VrstaTreninga;
import java.util.List;
import javax.swing.JOptionPane;

public class KorisnikDetalji extends javax.swing.JDialog {
    Trener trener;
    CliController conn;

    Korisnik korisnik;
    List<VrstaTreninga> vrste;

    public KorisnikDetalji(Trener trener, CliController conn, Korisnik korisnik) {
        this.trener = trener;
        this.conn = conn;
        this.korisnik = korisnik;
        
        initComponents();
        
        setModal(true);  // dok je ovaj dialog aktivan ne mozes da klikces po KorisnikPretraga, nakon sto se ovaj dialog zatvori onda ce se promene azurirati u KorisnikPretraga i mozes opet dalje raditi sa tim dialogom
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Detalji korisnika");
        
        txtId.setEditable(false);
        fillCombo();
        ucitajKorisnika();
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
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbVrsta = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnNazad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Ime");

        jLabel2.setText("Prezime");

        jLabel3.setText("Email");

        jLabel4.setText("ID");

        cmbVrsta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Vrsta treninga");

        btnIzmeni.setText("Izmeni korisnika");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi korisnika");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnNazad.setText("Nazad");
        btnNazad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNazadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbVrsta, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPrezime)
                            .addComponent(txtIme)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIzmeni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnObrisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNazad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbVrsta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzmeni)
                    .addComponent(btnObrisi)
                    .addComponent(btnNazad))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try {
            String ime = txtIme.getText().trim();
            String prezime = txtPrezime.getText().trim();
            String email = txtEmail.getText().trim();

            int index = cmbVrsta.getSelectedIndex();

            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Morate odabrati vrstu treninga.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }

            VrstaTreninga vrsta = vrste.get(index);

            // kontrola podataka
            if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // objekat sa izmenjenim podacima
            korisnik.setIme(ime);
            korisnik.setPrez(prezime);
            korisnik.setEmail(email);
            korisnik.setVrstaTreninga(vrsta);

            conn.izmeniKorisnika(korisnik); // update

            JOptionPane.showMessageDialog(this, "Sistem je zapamtio korisnika.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da obrišete korisnika?", "Brisanje korisnika", JOptionPane.YES_NO_OPTION);

        if (odgovor != JOptionPane.YES_OPTION) return;

        try {
            conn.obrisiKorisnika(korisnik.getIdKorisnik());
            JOptionPane.showMessageDialog(this, "Sistem je obrisao korisnika.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnNazadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNazadActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnNazadActionPerformed

    private void fillCombo() {
        try {
            vrste = conn.ucitajVrsteTreninga();
            cmbVrsta.removeAllItems();

            for (VrstaTreninga v : vrste) {
                cmbVrsta.addItem(v.getOpis());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita vrste treninga.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ucitajKorisnika() {
        try {
            korisnik = conn.nadjiKorisnika(korisnik.getIdKorisnik());

            txtId.setText(String.valueOf(korisnik.getIdKorisnik()));
            txtIme.setText(korisnik.getIme());
            txtPrezime.setText(korisnik.getPrez());
            txtEmail.setText(korisnik.getEmail());

            if (korisnik.getVrstaTreninga() != null) {
                cmbVrsta.setSelectedItem(korisnik.getVrstaTreninga().getOpis());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe korisnika.", "Greska", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnNazad;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JComboBox<String> cmbVrsta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables
}
