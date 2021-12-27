package koperasisimpanpinjam;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NasabahFormController implements Initializable { 

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfNoRek;

    @FXML
    private TextField tfSaldo;

    @FXML
    private Button btnTambahNasabah;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnClear;

    @FXML
    private TextField tfNIK;

    @FXML
    private TextField tfNPWP;

    @FXML
    private TableView<Individu> tblNasabah;

    @FXML
    private TableColumn<Individu, Integer> colId;

    @FXML
    private TableColumn<Individu, String> colNama;

    @FXML
    private TableColumn<Individu, String> colAlamat;

    @FXML
    private TableColumn<Individu, Long> colNIK;

    @FXML
    private TableColumn<Individu, Long> colNPWP;

    @FXML
    private TableColumn<Individu, Integer> colJmlRek;

    @FXML
    private TableView<Rekening> tblRekening;

    @FXML
    private TableColumn<Rekening, Integer> colNoRek;

    @FXML
    private TableColumn<Rekening, Double> colSaldo;

    @FXML
    private TextField tfIdNasabah;

    @FXML
    private TextField tfNorekBaru;

    @FXML
    private TextField tfSaldoRekBaru;

    @FXML
    private Button btnTambahRekening;

    @FXML
    private TextField tfNoRekening;

    @FXML
    private Button btnTambahSaldo;

    @FXML
    private TextField tfTambahTarik;

    @FXML
    private Button btnTarikSaldo;

    @FXML
    private TextField tfIdP;

    @FXML
    private TextField tfNamaP;

    @FXML
    private TextField tfAlamatP;

    @FXML
    private TextField tfNoRekP;

    @FXML
    private TextField tfSaldoP;

    @FXML
    private Button btnTambahNasabahP;

    @FXML
    private Button btnReloadP;

    @FXML
    private Button btnClearP;

    @FXML
    private TextField tfNIB;

    @FXML
    private TableView<Perusahaan> tblNasabahP;

    @FXML
    private TableColumn<Perusahaan, Integer> colIdP;

    @FXML
    private TableColumn<Perusahaan, String> colNamaP;

    @FXML
    private TableColumn<Perusahaan, String> colAlamatP;

    @FXML
    private TableColumn<Perusahaan, String> colNIB;

    @FXML
    private TableColumn<Perusahaan, Integer> colJmlRekP;

    @FXML
    private TableView<Rekening> tblRekeningP;

    @FXML
    private TableColumn<Rekening, Integer> colNoRekP;

    @FXML
    private TableColumn<Rekening, Double> colSaldoP;

    @FXML
    private TextField tfIdNasabahP;

    @FXML
    private TextField tfNorekBaruP;

    @FXML
    private TextField tfSaldoRekBaruP;

    @FXML
    private Button btnTambahRekeningP;

    @FXML
    private TextField tfNoRekeningP;

    @FXML
    private Button btnTambahSaldoP;

    @FXML
    private TextField tfTambahTarikP;

    @FXML
    private Button btnTarikSaldoP;
    
    @FXML
    private Label lblStatusSimpan;
    
    @FXML
    private Label lblStatusSimpanP;
    
    private NasabahDataModel ndm;
    

    

    @FXML
    void handleClearButton(ActionEvent event) {
        try {
            tfId.setText(""+ndm.nextIdNasabah());
            tfNoRek.setText(tfId.getText()+"01");
            tfNama.setText("");
            tfAlamat.setText("");
            tfNIK.setText("");
            tfNPWP.setText("");
            tfSaldo.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleReloadButton(ActionEvent event) {
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        ObservableList<Individu> data = ndm.getIndividu();
        colId.setCellValueFactory(new PropertyValueFactory<>("idNasabah"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNIK.setCellValueFactory(new PropertyValueFactory<>("nik"));
        colNPWP.setCellValueFactory(new PropertyValueFactory<>("npwp"));
        colJmlRek.setCellValueFactory(new PropertyValueFactory<>("NumRek"));
        tblNasabah.setItems(null);
        tblNasabah.setItems(data);
        btnTambahRekening.setDisable(true);
    }

   
    @FXML
    void handleTambahNasabahButton(ActionEvent event) {
        Individu Individunew = new Individu(Integer.parseInt(tfId.getText()), 
                tfNama.getText(), 
                tfAlamat.getText(),
                new Rekening(Integer.parseInt(tfNoRek.getText()),
                Double.parseDouble(tfSaldo.getText())),
                Long.parseLong(tfNIK.getText()), 
                Long.parseLong(tfNPWP.getText()));
        try {
            ndm.tambahNasabah(Individunew);
            lblStatusSimpan.setText("Berhasil Ditambahkan!");
            btnReload.fire();
            btnClear.fire();
        } catch (SQLException ex) {
             lblStatusSimpan.setText("Gagal Ditambahkan!");
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleTambahRekeningButton(ActionEvent event) {
        try {
            Rekening rek =  new Rekening(Integer.parseInt(tfNorekBaru.getText()), 
                            Double.parseDouble(tfSaldoRekBaru.getText()));
            
            ndm.addRekening(Integer.parseInt(tfIdNasabah.getText()), rek); 
            tampilDataRekening(Integer.parseInt(tfIdNasabah.getText()));
            btnReload.fire();
            tfSaldoRekBaru.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleTambahSaldoButton(ActionEvent event) {
        try {
            ndm.tambahSaldo(Integer.parseInt(tfNoRekening.getText()), Integer.parseInt(tfTambahTarik.getText()));
            tampilDataRekening(Integer.parseInt(tfIdNasabah.getText()));
//            tfNoRekening.setText("");
            tfTambahTarik.setText("");   
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleTarikSaldoButton(ActionEvent event) {
         try {
            ndm.tarikSaldo(Integer.parseInt(tfNoRekening.getText()), Integer.parseInt(tfTambahTarik.getText()));
            tampilDataRekening(Integer.parseInt(tfIdNasabah.getText()));
//            tfNoRekening.setText("");
            tfTambahTarik.setText("");   
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    void handleClearButtonP(ActionEvent event) {
        try {
            tfIdP.setText(""+ndm.nextIdNasabah());
            tfNoRekP.setText(tfIdP.getText()+"01");
            tfNamaP.setText("");
            tfAlamatP.setText("");
            tfNIB.setText("");
            tfSaldoP.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleReloadButtonP(ActionEvent event) {
        colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        ObservableList<Perusahaan> data = ndm.getPerusahaan();
        colIdP.setCellValueFactory(new PropertyValueFactory<>("idNasabah"));
        colNamaP.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamatP.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colNIB.setCellValueFactory(new PropertyValueFactory<>("nib"));
        colJmlRekP.setCellValueFactory(new PropertyValueFactory<>("NumRek"));
        tblNasabahP.setItems(null);
        tblNasabahP.setItems(data);
        btnTambahRekeningP.setDisable(true);
    }

   
    @FXML
    void handleTambahNasabahButtonP(ActionEvent event) {
        Perusahaan Perusahaannew = new Perusahaan(Integer.parseInt(tfIdP.getText()), 
                tfNamaP.getText(), 
                tfAlamatP.getText(),
                new Rekening(Integer.parseInt(tfNoRekP.getText()),
                Double.parseDouble(tfSaldoP.getText())),
                tfNIB.getText());
       
        try {
            ndm.tambahNasabah(Perusahaannew);
            lblStatusSimpanP.setText("Berhasil Ditambahkan!");
            btnReloadP.fire();
            btnClearP.fire();
        } catch (SQLException ex) {
             lblStatusSimpanP.setText("Gagal Ditambahkan!");
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleTambahRekeningButtonP(ActionEvent event) {
        try {
            Rekening rek =  new Rekening(Integer.parseInt(tfNorekBaruP.getText()), 
                            Double.parseDouble(tfSaldoRekBaruP.getText()));
            
            ndm.addRekening(Integer.parseInt(tfIdNasabahP.getText()), rek); 
            tampilDataRekeningP(Integer.parseInt(tfIdNasabahP.getText()));
            btnReloadP.fire();
            tfSaldoRekBaruP.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void handleTambahSaldoButtonP(ActionEvent event) {
        try {
            ndm.tambahSaldo(Integer.parseInt(tfNoRekeningP.getText()), Integer.parseInt(tfTambahTarikP.getText()));
            tampilDataRekeningP(Integer.parseInt(tfIdNasabahP.getText()));
//            tfNoRekening.setText("");
            tfTambahTarikP.setText("");        
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void handleTarikSaldoButtonP(ActionEvent event) {
         try {
            ndm.tarikSaldo(Integer.parseInt(tfNoRekeningP.getText()), Integer.parseInt(tfTambahTarikP.getText()));
            tampilDataRekeningP(Integer.parseInt(tfIdNasabahP.getText()));
//            tfNoRekening.setText("");
            tfTambahTarikP.setText("");   
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
  
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ndm = new  NasabahDataModel();
            btnClear.fire();
            btnClearP.fire();
            btnReload.fire();
            btnReloadP.fire();
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        tblNasabah.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if(tblNasabah.getSelectionModel().getSelectedItem() != null){
                Individu NasabahI = tblNasabah.getSelectionModel().getSelectedItem();
                tampilDataRekening(NasabahI.getIdNasabah());
                btnTambahRekening.setDisable(false);
                tfIdNasabah.setText("" + NasabahI.getIdNasabah());
                try {
                    tfNorekBaru.setText("" + ndm.nextNoRekening(NasabahI.getIdNasabah()));
                } catch (SQLException ex) {
                    Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tblNasabahP.getSelectionModel().selectedIndexProperty().addListener(listener->{
            if(tblNasabahP.getSelectionModel().getSelectedItem() != null){
                Perusahaan NasabahP = tblNasabahP.getSelectionModel().getSelectedItem();
                tampilDataRekeningP(NasabahP.getIdNasabah());
                btnTambahRekeningP.setDisable(false);
                tfIdNasabahP.setText("" + NasabahP.getIdNasabah());
                try {
                    tfNorekBaruP.setText("" + ndm.nextNoRekening(NasabahP.getIdNasabah()));
                } catch (SQLException ex) {
                    Logger.getLogger(NasabahFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void tampilDataRekening(int idIndividu){
         ObservableList<Rekening> data = ndm.getRekenings(idIndividu);
         colNoRek.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
         colSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
         tblRekening.setItems(null);
         tblRekening.setItems(data);
    }
    
    public void tampilDataRekeningP(int idPerusahaan){
         ObservableList<Rekening> data = ndm.getRekenings(idPerusahaan);
         colNoRekP.setCellValueFactory(new PropertyValueFactory<>("noRekening"));
         colSaldoP.setCellValueFactory(new PropertyValueFactory<>("saldo"));
         tblRekeningP.setItems(null);
         tblRekeningP.setItems(data);
    }   
}