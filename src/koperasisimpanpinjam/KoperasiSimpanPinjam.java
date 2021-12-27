/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasisimpanpinjam;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import koperasisimpanpinjam.DB.DBHelper;

/**
 *
 * @author ASUS
 */
public class KoperasiSimpanPinjam extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NasabahForm.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
//       try {
//            NasabahDataModel ndm = new NasabahDataModel();
//            Individu in = new Individu(1, "Yulia","Sukaraja",new Rekening(11,150.00), 123,345);
//            ndm.tambahNasabah(in);
//            System.out.println("sukses");
//            
//            //try {
//            if (null != DBHelper.getConnection()){
//            System.out.println("Koneksi Berhasil");
//            } else {
//            System.out.println("Koneksi gagal");
//            }
//            } catch (SQLException ex){
//   
//            System.out.println("gagal");
//            Logger.getLogger(KoperasiSimpanPinjam.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    launch(args);
    }
    
}
