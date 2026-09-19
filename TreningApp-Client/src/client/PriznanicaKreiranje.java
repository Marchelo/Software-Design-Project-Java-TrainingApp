package client;

import client.models.StavkaPriznaniceTableModel;
import domm.Korisnik;
import domm.Priznanica;
import domm.StavkaPriznanice;
import domm.Trener;
import domm.Trening;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


// lblIznosPreview  - koliko kosta stavka koju trenutno pravis = t.getCena() * br  -> trening + broj treninga = 1500 + 3
// lblUkupno  - ukupan iznos svih stavki koje si dodao u tabelu stavka1, stavka2...suma svih  
public class PriznanicaKreiranje extends javax.swing.JFrame {
    private static final SimpleDateFormat DATUM_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat VREME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
 
    private final Trener trener;
    private final CliController conn;
 
    private List<Korisnik> listaKorisnika;
    private List<Trening> listaTreninga;
    private StavkaPriznaniceTableModel model;
    
    public PriznanicaKreiranje(Trener trener, CliController conn) {
        this.trener = trener;
        this.conn = conn;
        
        setResizable(false);
        setLocationRelativeTo(this);
        setTitle("Kreiraj priznanicu");
 
        initComponents();
 
        model = new StavkaPriznaniceTableModel();
        tblStavke.setModel(model);
        
        fillKorisnici();
        fillTreninzi();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cmbKorisnik = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDatumIzdavanja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMesto = new javax.swing.JTextField();
        cmbTrening = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtBrTreninga = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtVremePocetka = new javax.swing.JTextField();
        txtVremeZavrsetka = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        btnDodaj = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblUkupno = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblIznosPreview = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnKreiraj = new javax.swing.JButton();
        btnZatvori = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbKorisnik.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Korisnik");

        jLabel2.setText("Datum (dd.MM.yyyy)");

        jLabel3.setText("Mesto izdavanja");

        cmbTrening.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Trening");

        jLabel5.setText("STAVKE PRIZNANICE");

        jLabel6.setText("Broj treninga");

        jLabel7.setText("Vreme pocetka");

        jLabel8.setText("(dd.MM.yyyy HH:mm)");

        jLabel9.setText("Vreme zavrsetka");

        jLabel10.setText("(dd.MM.yyyy HH:mm)");

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane1.setViewportView(txtNapomena);

        btnDodaj.setText("Dodaj  stavku");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        btnObrisi.setText("Ukloni stavku");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        jLabel11.setText("Ukupan iznos");

        lblUkupno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("Napomena");

        jLabel13.setText("Iznos stavke");

        lblIznosPreview.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblStavke);

        btnKreiraj.setText("Kreiraj priznanicu");
        btnKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajActionPerformed(evt);
            }
        });

        btnZatvori.setText("Vrati se na glavnu formu");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDatumIzdavanja)
                    .addComponent(txtMesto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(lblUkupno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cmbKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKreiraj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnZatvori))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(6, 6, 6)))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtBrTreninga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbTrening, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtVremeZavrsetka, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIznosPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel11))
                    .addComponent(lblUkupno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbTrening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBrTreninga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDodaj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisi))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtVremeZavrsetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblIznosPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKreiraj)
                    .addComponent(btnZatvori))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        try {
            int index = cmbTrening.getSelectedIndex();
            
            if(index == -1){
                JOptionPane.showMessageDialog(this, "Izaberi trening.");
                return;
            }
            
            Trening trening = listaTreninga.get(index);
 
            int br = Integer.parseInt(txtBrTreninga.getText().trim());
            if (br <= 0) {
                JOptionPane.showMessageDialog(this, "Broj treninga mora biti veci od 0.");
                return;
            }
 
            Date pocetak = VREME_FORMAT.parse(txtVremePocetka.getText().trim());
            Date kraj = VREME_FORMAT.parse(txtVremeZavrsetka.getText().trim());
 
            StavkaPriznanice stavka = new StavkaPriznanice();
            stavka.setTrening(trening);
            stavka.setBrTreninga(br);
            stavka.setVremePocetka(pocetak);
            stavka.setVremeZavrsetka(kraj);
            stavka.setNapomena(txtNapomena.getText().trim());
            stavka.setIznos(trening.getCena() * br);            // server ce ponovo izracunati pri cuvanju
 
            model.dodajStavku(stavka);
            lblUkupno.setText("Ukupan iznos: " + model.vratiUkupanIznos());
 
            osveziIznosPreview();
            txtBrTreninga.setText("");
            txtNapomena.setText("");
 
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Broj treninga nije ispravan broj.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "Vreme mora biti u formatu dd.MM.yyyy HH:mm.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da doda priznanicu. ", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        int row = tblStavke.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Izaberi stavku za brisanje.");
            return;
        }
        model.obrisiStavku(row);
        lblUkupno.setText("Ukupan iznos: " + model.vratiUkupanIznos());
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajActionPerformed
        try {
            int index = cmbKorisnik.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(this, "Izaberi korisnika.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Korisnik korisnik = listaKorisnika.get(index);
            
            String mesto = txtMesto.getText().trim();
            if (mesto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mesto izdavanja je obavezno.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (model.getStavke().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Priznanica mora imati bar jednu stavku.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
 
            Date datum = DATUM_FORMAT.parse(txtDatumIzdavanja.getText().trim());
 
            Priznanica priznanica = new Priznanica();
            priznanica.setTrener(trener);
            priznanica.setKorisnik(korisnik);
            priznanica.setMestoIzdavanja(mesto);
            priznanica.setDatumIzdavanja(datum);
            priznanica.setStavke(new java.util.ArrayList<>(model.getStavke()));
 
            conn.kreirajPriznanicu(priznanica);
 
            JOptionPane.showMessageDialog(this, "Sistem je kreirao priznanicu.", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sistem ne moze da kreira priznanicu.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnKreirajActionPerformed

    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZatvoriActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnZatvoriActionPerformed


    private void fillKorisnici() {
        try {
            listaKorisnika = conn.pretraziKorisnike(new Korisnik());
            cmbKorisnik.removeAllItems();
            for (Korisnik k : listaKorisnika) {
                cmbKorisnik.addItem(k.getIme() + " " + k.getPrez());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita korisnike.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillTreninzi() {
        try {
            listaTreninga = conn.ucitajTreninge();
            cmbTrening.removeAllItems();
            for (Trening t : listaTreninga) {
                cmbTrening.addItem(t.getTip().getLabel());
            }
            osveziIznosPreview();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita treninge.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnKreiraj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JComboBox<String> cmbKorisnik;
    private javax.swing.JComboBox<String> cmbTrening;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblIznosPreview;
    private javax.swing.JLabel lblUkupno;
    private javax.swing.JTable tblStavke;
    private javax.swing.JTextField txtBrTreninga;
    private javax.swing.JTextField txtDatumIzdavanja;
    private javax.swing.JTextField txtMesto;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextField txtVremePocetka;
    private javax.swing.JTextField txtVremeZavrsetka;
    // End of variables declaration//GEN-END:variables

    private void osveziIznosPreview() {
        try {
            int index = cmbTrening.getSelectedIndex();
            int br = Integer.parseInt(txtBrTreninga.getText().trim());

            if (index >= 0 && br > 0) {
                Trening t = listaTreninga.get(index);
                lblIznosPreview.setText(String.valueOf(t.getCena() * br));
                return;
            }
        } catch (NumberFormatException ignored) {
        }
        lblIznosPreview.setText("0");
    }
}
