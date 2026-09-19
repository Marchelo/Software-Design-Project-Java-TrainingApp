package client;

import domm.Trener;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {
    Trener trener;
    CliController conn;
    
    public Main(Trener trener, CliController conn) {        
        this.trener = trener;
        this.conn = conn;
        initComponents();
        setResizable(false);
        setLocationRelativeTo(this);
        setTitle("GLAVNA FORMA");
        lblUlogovan.setText("Ulogovani trener: " + trener.getEmail());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogout = new javax.swing.JButton();
        btnDodaj = new javax.swing.JButton();
        lblUlogovan = new javax.swing.JLabel();
        btnPronadji = new javax.swing.JButton();
        btnAddLicencu = new javax.swing.JButton();
        btnKreirajPriznanicu = new javax.swing.JButton();
        btnPretraziPriznanicu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnLogout.setText("Odjavi se");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnDodaj.setText("Dodaj novog korisnika");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnPronadji.setText("Pronadji korisnika");
        btnPronadji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPronadjiActionPerformed(evt);
            }
        });

        btnAddLicencu.setText("Dodaj licencu");
        btnAddLicencu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLicencuActionPerformed(evt);
            }
        });

        btnKreirajPriznanicu.setText("Kreiraj priznanicu");
        btnKreirajPriznanicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajPriznanicuActionPerformed(evt);
            }
        });

        btnPretraziPriznanicu.setText("Pretrazi priznanicu");
        btnPretraziPriznanicu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziPriznanicuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUlogovan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnPronadji, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDodaj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddLicencu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnKreirajPriznanicu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPretraziPriznanicu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblUlogovan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDodaj)
                    .addComponent(btnKreirajPriznanicu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPronadji)
                    .addComponent(btnPretraziPriznanicu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddLicencu)
                    .addComponent(btnLogout))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            conn.logout(trener);
            System.out.println("Trener je uspesno izlogovan!");
            JOptionPane.showMessageDialog(this, "Trener je uspesno izlogovan!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da izloguje trenera.", "Greska", JOptionPane.ERROR_MESSAGE);
            System.out.println("Main btnLogout(): " + e.getMessage());
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        KorisnikDodavanje form = new KorisnikDodavanje(trener, conn);
        form.setVisible(true);
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPronadjiActionPerformed
        KorisnikPretraga form = new KorisnikPretraga(trener, conn);
        form.setVisible(true);
    }//GEN-LAST:event_btnPronadjiActionPerformed

    private void btnAddLicencuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLicencuActionPerformed
        DodajLicencu form = new DodajLicencu(trener, conn);
        form.setVisible(true);
    }//GEN-LAST:event_btnAddLicencuActionPerformed

    private void btnKreirajPriznanicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajPriznanicuActionPerformed
        PriznanicaKreiranje form = new PriznanicaKreiranje(trener, conn);
        form.setVisible(true);
    }//GEN-LAST:event_btnKreirajPriznanicuActionPerformed

    private void btnPretraziPriznanicuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziPriznanicuActionPerformed
        PriznanicaPretraga form = new PriznanicaPretraga(trener, conn);
        form.setVisible(true);
    }//GEN-LAST:event_btnPretraziPriznanicuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLicencu;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnKreirajPriznanicu;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPretraziPriznanicu;
    private javax.swing.JButton btnPronadji;
    private javax.swing.JLabel lblUlogovan;
    // End of variables declaration//GEN-END:variables
}
