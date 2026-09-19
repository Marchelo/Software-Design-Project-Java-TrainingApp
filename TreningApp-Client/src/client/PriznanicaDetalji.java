package client;

import client.models.StavkaPriznaniceTableModel;
import domm.Priznanica;
import domm.StavkaPriznanice;
import domm.Trener;
import domm.Trening;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class PriznanicaDetalji extends javax.swing.JDialog {
private static final SimpleDateFormat DATUM_FORMAT = new SimpleDateFormat("dd.MM.yyyy"); 
private static final SimpleDateFormat VREME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm"); 

private final Trener trener; 
private final CliController conn; 
private final Priznanica priznanica;

private List<Trening> listaTreninga; 
private StavkaPriznaniceTableModel model;

    public PriznanicaDetalji(Trener trener, CliController conn, Priznanica priznanica) {
        this.trener = trener; 
        this.conn = conn; 
        this.priznanica = priznanica;        
        
        DATUM_FORMAT.setLenient(false); // datum i vreme moraju da budu ispravnog formata, ne moze 31.02 ...
        VREME_FORMAT.setLenient(false); // ne moze 25:12 ...
        
        initComponents();

        tblStavke.getSelectionModel().addListSelectionListener(e -> { // selektujes u tabeli koju stavku zelis da izmenis ovo ti je forica za handlovanje toga
            if (!e.getValueIsAdjusting()) {
                ucitajStavkuIzTabele();
            }
        });
        
        cmbTrening.addActionListener(e -> osveziIznosPreview()); // ovo ti je forica za osvezavanje teksta
        
        txtBrTreninga.getDocument().addDocumentListener( // forica za osvezavanje iznosa stavke na svaku promenu broja treninga
            new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    osveziIznosPreview();
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    osveziIznosPreview();
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    osveziIznosPreview();
                }
            }
        );
        
        setModal(true);
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Detalji o priznanici");
        
        fillTreninzi(); 
        
        ArrayList<StavkaPriznanice> stavke = priznanica.getStavke();

        if (stavke == null) {
            stavke = new ArrayList<>();
        }

        model = new StavkaPriznaniceTableModel(stavke);
        tblStavke.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtDatumIzdavanja = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMesto = new javax.swing.JTextField();
        cmbTrening = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBrTreninga = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtVremePocetka = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtVremeZavrsetka = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        lblIznosPreview = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblKorisnik = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblUkupno = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnIzmeni = new javax.swing.JButton();
        btnObrisiStavku = new javax.swing.JButton();
        btnZatvori = new javax.swing.JButton();
        btnIzmeniStavku = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Datum (dd.MM.yyyy)");

        jLabel3.setText("Mesto izdavanja");

        cmbTrening.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Trening");

        jLabel6.setText("Broj treninga");

        jLabel7.setText("Vreme pocetka");

        jLabel8.setText("(dd.MM.yyyy HH:mm)");

        jLabel9.setText("Vreme zavrsetka");

        jLabel10.setText("(dd.MM.yyyy HH:mm)");

        jLabel12.setText("Napomena");

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane1.setViewportView(txtNapomena);

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
        jScrollPane2.setViewportView(tblStavke);

        jLabel1.setText("ID priznanice");

        lblID.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblKorisnik.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Korisnik");

        lblUkupno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setText("Ukupan iznos");

        btnIzmeni.setText("Izmeni priznanicu");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisiStavku.setText("Obrisi stavku");
        btnObrisiStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiStavkuActionPerformed(evt);
            }
        });

        btnZatvori.setText("Vrati se na glavnu formu");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        btnIzmeniStavku.setText("Izmeni stavku");
        btnIzmeniStavku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniStavkuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVremePocetka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtBrTreninga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(cmbTrening, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                                .addComponent(txtDatumIzdavanja, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(lblKorisnik, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtVremeZavrsetka, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUkupno, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1)
                                .addComponent(lblIznosPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnIzmeni)
                                .addGap(18, 18, 18)
                                .addComponent(btnIzmeniStavku)
                                .addGap(21, 21, 21)
                                .addComponent(btnObrisiStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnZatvori)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(lblKorisnik, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtDatumIzdavanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbTrening, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBrTreninga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtVremeZavrsetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lblIznosPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(lblUkupno, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIzmeni)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnZatvori)
                        .addComponent(btnObrisiStavku)
                        .addComponent(btnIzmeniStavku)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZatvoriActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnZatvoriActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        try { 
            String mesto = txtMesto.getText().trim(); 
            if (mesto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti priznanicu. \n Mesto izdavanja je obavezno.", "Upozorenje", JOptionPane.WARNING_MESSAGE);                
                return; 
            } 
            if (txtDatumIzdavanja.getText().trim().isEmpty()){ 
                JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti priznanicu. \n Datum izdavanja priznanice je obavezan.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return; 
            } 
            if (model.getStavke().isEmpty()) {  
                JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti priznanicu. \n Priznanica mora imati bar jednu stavku.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return; 
            } 
            Date datum = DATUM_FORMAT.parse(txtDatumIzdavanja .getText().trim()); 
            
            priznanica.setMestoIzdavanja(mesto); 
            priznanica.setDatumIzdavanja(datum); 
            priznanica.setStavke(new ArrayList<>(model.getStavke())); 
            priznanica.setUkupanIznos(model.vratiUkupanIznos()); 
            
            conn.izmeniPriznanicu(priznanica); 
            
            JOptionPane.showMessageDialog( this, "Sistem je zapamtio priznanicu.", "Uspesno", JOptionPane.INFORMATION_MESSAGE); 
            dispose();
            
        } catch (ParseException e) {  
            JOptionPane.showMessageDialog(this, "Sistem ne moze da zapamti priznanicu. \n Datum nije ispravnog formata. Unesite ga kao dd.MM.yyyy (npr. 15.03.2024)", "Upozorenje", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) { 
            e.printStackTrace(); 
            JOptionPane.showMessageDialog( this, "Sistem ne moze da zapamti priznanicu.", "Greska", JOptionPane.ERROR_MESSAGE); 
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    private void btnObrisiStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiStavkuActionPerformed
        int row = tblStavke.getSelectedRow(); 
        
        if (row < 0) { 
            JOptionPane.showMessageDialog(this, "Izaberi stavku za brisanje."); 
            return; 
        } 
        int odgovor = JOptionPane.showConfirmDialog(this, "Da li si siguran da zelis da obrises stavku?", "Potvrda", JOptionPane.YES_NO_OPTION); 
        
        if (odgovor != JOptionPane.YES_OPTION) { 
            return; 
        } 
        model.obrisiStavku(row); 
        osveziUkupanIznos();
    }//GEN-LAST:event_btnObrisiStavkuActionPerformed

    private void btnIzmeniStavkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniStavkuActionPerformed
        izmeniStavku();
    }//GEN-LAST:event_btnIzmeniStavkuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzmeniStavku;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JComboBox<String> cmbTrening;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIznosPreview;
    private javax.swing.JLabel lblKorisnik;
    private javax.swing.JLabel lblUkupno;
    private javax.swing.JTable tblStavke;
    private javax.swing.JTextField txtBrTreninga;
    private javax.swing.JTextField txtDatumIzdavanja;
    private javax.swing.JTextField txtMesto;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextField txtVremePocetka;
    private javax.swing.JTextField txtVremeZavrsetka;
    // End of variables declaration//GEN-END:variables

    private void dodajStavku() { 
        try {
            int index = cmbTrening.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this,"Izaberi trening.");
                return;
            }

            Trening trening = listaTreninga.get(index);

            int br = Integer.parseInt(txtBrTreninga.getText().trim());

            if (br <= 0) {
                JOptionPane.showMessageDialog(this,"Sistem ne moze da zapamti priznanicu. \n Broj treninga nije ispravan broj.");
                return;
            }

            Date pocetak = VREME_FORMAT.parse(txtVremePocetka.getText().trim());
            Date kraj = VREME_FORMAT.parse(txtVremeZavrsetka.getText().trim());

            if (!kraj.after(pocetak)) {
                JOptionPane.showMessageDialog(this,"Vreme zavrsetka mora biti nakon vremena pocetka.");
                return;
            }

            StavkaPriznanice stavka = new StavkaPriznanice();
            stavka.setIdPriznanica(priznanica.getIdPriznanica());
            stavka.setTrening(trening);
            stavka.setBrTreninga(br);
            stavka.setVremePocetka(pocetak);
            stavka.setVremeZavrsetka(kraj);
            stavka.setNapomena(txtNapomena.getText().trim());
            stavka.setIznos(trening.getCena() * br);

            model.dodajStavku(stavka);

            osveziUkupanIznos();

            txtBrTreninga.setText("");
            txtNapomena.setText("");
            txtVremePocetka.setText(VREME_FORMAT.format(new Date()));
            txtVremeZavrsetka.setText(VREME_FORMAT.format(new Date()));
            
            osveziIznosPreview();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Broj treninga nije ispravan broj.","Upozorenje",JOptionPane.WARNING_MESSAGE);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,"Vreme mora biti u formatu dd.MM.yyyy HH:mm.","Upozorenje",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void osveziUkupanIznos() {
        lblUkupno.setText("Ukupan iznos: " + model.vratiUkupanIznos());
    }
    
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
    
    private void fillTreninzi() {
        try {
            listaTreninga = conn.ucitajTreninge();
            cmbTrening.removeAllItems();

            for (Trening t : listaTreninga) {
                cmbTrening.addItem(t.getTip().getLabel());
            }
            osveziIznosPreview();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita treninge.","Greska",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // popunis postojecu priznanicu
    private void popuniPodatke() {
        lblID.setText(String.valueOf(priznanica.getIdPriznanica())); 
        
        if (priznanica.getKorisnik() != null) { 
            lblKorisnik.setText(priznanica.getKorisnik().getIme() + " " + priznanica.getKorisnik().getPrez()); 
        } else { 
            lblKorisnik.setText(""); 
        } 
        txtMesto.setText(priznanica.getMestoIzdavanja() != null 
                ? priznanica.getMestoIzdavanja() 
                : ""); 
        
        if (priznanica.getDatumIzdavanja() != null) { 
            txtDatumIzdavanja.setText(DATUM_FORMAT.format(priznanica.getDatumIzdavanja())); 
        } else { 
            txtDatumIzdavanja.setText(""); 
        } 
        
        ArrayList<StavkaPriznanice> stavke = priznanica.getStavke(); 
        if(stavke == null) { 
            stavke = new ArrayList<>(); 
        } 
        model = new StavkaPriznaniceTableModel(stavke); 
        tblStavke.setModel(model); 
        osveziUkupanIznos();
    }

    private void izmeniStavku() {
        try {
            int row = tblStavke.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this,"Izaberi stavku za izmenu.","Upozorenje",JOptionPane.WARNING_MESSAGE);
                return;
            }

            int index = cmbTrening.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(this,"Izaberi trening.","Upozorenje",JOptionPane.WARNING_MESSAGE);
                return;
            }
            Trening trening = listaTreninga.get(index);


            int br = Integer.parseInt(txtBrTreninga.getText().trim());

            if (br <= 0) {
                JOptionPane.showMessageDialog(this,"Sistem ne moze da zapamti priznanicu. \n Broj treninga nije ispravan broj.","Upozorenje",JOptionPane.WARNING_MESSAGE);
                return;
            }

            Date pocetak = VREME_FORMAT.parse(txtVremePocetka.getText().trim());
            Date kraj = VREME_FORMAT.parse(txtVremeZavrsetka.getText().trim());

            if (!kraj.after(pocetak)) {
                JOptionPane.showMessageDialog(this,"Sistem ne moze da zapamti priznanicu. \n Vreme zavrsetka mora biti nakon vremena pocetka.","Upozorenje",JOptionPane.WARNING_MESSAGE);
                return;
            }

            // uzimas postojecu stavku iz modela
            StavkaPriznanice stavka = model.getStavke().get(row);

            // menjas samo njene podatke
            stavka.setTrening(trening);
            stavka.setBrTreninga(br);
            stavka.setVremePocetka(pocetak);
            stavka.setVremeZavrsetka(kraj);
            stavka.setNapomena(txtNapomena.getText().trim());
            stavka.setIznos(trening.getCena() * br);

            // osvezi samo taj red u tabeli
            model.fireTableRowsUpdated(row, row);
            // osvezi ukupan iznos priznanice
            osveziUkupanIznos();
            // osvezi preview iznosa stavke
            osveziIznosPreview();
            
            JOptionPane.showMessageDialog(this,"Stavka je izmenjena.","Uspešno",JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Sistem ne moze da zapamti priznanicu. \n Broj treninga nije ispravan broj.","Upozorenje",JOptionPane.WARNING_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,"Sistem ne moze da zapamti priznanicu. \n Vreme mora biti u formatu dd.MM.yyyy HH:mm.","Upozorenje", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ucitajStavkuIzTabele() {
        int row = tblStavke.getSelectedRow();

        if (row < 0) {
            return;
        }

        StavkaPriznanice stavka = model.getStavke().get(row);
        lblID.setText(String.valueOf(priznanica.getIdPriznanica())); 

        if (priznanica.getKorisnik() != null) {
            lblKorisnik.setText(priznanica.getKorisnik().getIme() + " " + priznanica.getKorisnik().getPrez());
        } else {
            lblKorisnik.setText("");
        }

        txtMesto.setText(priznanica.getMestoIzdavanja() != null
                ? priznanica.getMestoIzdavanja()
                : ""
        );

        if (priznanica.getDatumIzdavanja() != null) {
            txtDatumIzdavanja.setText(
                DATUM_FORMAT.format(priznanica.getDatumIzdavanja())
            );
        } else {
            txtDatumIzdavanja.setText("");
        }        
        
        // Trening
        if (stavka.getTrening() != null) {
            for (int i = 0; i < listaTreninga.size(); i++) {
                if (listaTreninga.get(i).getId() == stavka.getTrening().getId()) {
                    cmbTrening.setSelectedIndex(i);
                    break;
                }
            }
        }

        // Broj treninga
        txtBrTreninga.setText(String.valueOf(stavka.getBrTreninga()));

        // Vreme pocetka
        if (stavka.getVremePocetka() != null) {
            txtVremePocetka.setText(VREME_FORMAT.format(stavka.getVremePocetka()));
        } else {
            txtVremePocetka.setText("");
        }

        // Vreme zavrsetka
        if (stavka.getVremeZavrsetka() != null) {
            txtVremeZavrsetka.setText(VREME_FORMAT.format(stavka.getVremeZavrsetka()));
        } else {
            txtVremeZavrsetka.setText("");
        }

        // Napomena
        txtNapomena.setText(stavka.getNapomena() != null ? stavka.getNapomena() : "");

        osveziIznosPreview();
    }



}
