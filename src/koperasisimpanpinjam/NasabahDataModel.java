package koperasisimpanpinjam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import koperasisimpanpinjam.DB.DBHelper;

public class NasabahDataModel {
    public final Connection conn   ;

    public NasabahDataModel() throws SQLException {
        this.conn = DBHelper.getConnection();
    }
    
    public void tambahNasabah (Individu rek) throws SQLException{
        String insertNasabah = "INSERT INTO nasabah (idNasabah,nama, alamat)"
                + " VALUES (?,?,?)";
        String insertIndividu = "INSERT INTO individu (idNasabah,nik, npwp)"
                + " VALUES (?,?,?)";
        String insertRekening = "INSERT INTO rekening (noRekening, saldo, idNasabah)"
                + " VALUES (?,?,?)";
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, rek.getIdNasabah());
        stmtNasabah.setString(2, rek.getNama());
        stmtNasabah.setString(3, rek.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtindividu = conn.prepareStatement(insertIndividu);
        stmtindividu.setInt(1,  rek.getIdNasabah());
        stmtindividu.setLong(2, rek.getNik());
        stmtindividu.setLong(3, rek.getNpwp());
        stmtindividu.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, rek.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, rek.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, rek.getIdNasabah());
        stmtRekening.execute();
        
    }
    
    public void tambahNasabah (Perusahaan rek) throws SQLException{
        String insertNasabah = "INSERT INTO nasabah (idNasabah,nama, alamat)"
                + " VALUES (?,?,?)";
        String insertPerusahaan = "INSERT INTO perusahaan (idNasabah,nib)"
                + " VALUES (?,?)";
        String insertRekening = "INSERT INTO rekening (noRekening, saldo, idNasabah)"
                + " VALUES (?,?,?)";
        PreparedStatement stmtNasabah = conn.prepareStatement(insertNasabah);
        stmtNasabah.setInt(1, rek.getIdNasabah());
        stmtNasabah.setString(2, rek.getNama());
        stmtNasabah.setString(3, rek.getAlamat());
        stmtNasabah.execute();
        
        PreparedStatement stmtperusahaan = conn.prepareStatement(insertPerusahaan);
        stmtperusahaan.setInt(1,  rek.getIdNasabah());
        stmtperusahaan.setString(2, rek.getNib());
        stmtperusahaan.execute();
        
        PreparedStatement stmtRekening = conn.prepareStatement(insertRekening);
        stmtRekening.setInt(1, rek.getRekening().get(0).getNoRekening());
        stmtRekening.setDouble(2, rek.getRekening().get(0).getSaldo());
        stmtRekening.setInt(3, rek.getIdNasabah());
        stmtRekening.execute();
    }
    
    public ObservableList<Individu> getIndividu(){
        ObservableList<Individu> data = FXCollections.observableArrayList();
        String sql="SELECT `idNasabah`, `nama`,`alamat`, `nik`, `npwp` "
                + "FROM `nasabah` NATURAL JOIN `individu` "
                + "ORDER BY idNasabah";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT noRekening, saldo "
                    + "FROM rekening WHERE idNasabah="+rs.getInt(1);
                ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                while (rsRekening.next()){
                    dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                data.add(new Individu(rs.getInt(1),rs.getString(2),rs.getString(3), dataRekening,rs.getLong(4),rs.getLong(5)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    
    public ObservableList<Perusahaan> getPerusahaan(){
        ObservableList<Perusahaan> data = FXCollections.observableArrayList();
        String sql="SELECT `idNasabah`, `nama`,`alamat`, `nib` "
                + "FROM `nasabah` NATURAL JOIN `perusahaan` "
                + "ORDER BY idNasabah";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT noRekening, saldo "
                    + "FROM rekening WHERE idNasabah="+rs.getInt(1);
                ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                while (rsRekening.next()){
                    dataRekening.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                data.add(new Perusahaan(rs.getInt(1),rs.getString(2),rs.getString(3), dataRekening,rs.getString(4)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    
    public ObservableList<Rekening> getRekeningI(){
        ObservableList<Rekening> data2 = FXCollections.observableArrayList();
        String sql="SELECT `idNasabah`, `nik`,`npwp` "
                + "FROM `individu` "
                + "ORDER BY idNasabah";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT noRekening, saldo "
                    + "FROM rekening WHERE idNasabah="+rs.getInt(1);
                ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                while (rsRekening.next()){
                    data2.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data2;
    }
    public ObservableList<Rekening> getRekeningP(){
        ObservableList<Rekening> data3 = FXCollections.observableArrayList();
        String sql="SELECT `idNasabah`, `nama`,`alamat`, `nib` "
                + "FROM `Nasabah` NATURAL JOIN `perusahaan` "
                + "ORDER BY idNasabah";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlRekening = "SELECT noRekening, saldo "
                    + "FROM rekening WHERE idNasabah="+rs.getInt(1);
                ResultSet rsRekening = conn.createStatement().executeQuery(sqlRekening);
                ArrayList<Rekening> dataRekening = new ArrayList<>();
                while (rsRekening.next()){
                    data3.add(new Rekening(rsRekening.getInt(1),rsRekening.getDouble(2)));
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data3;
    }
    
    public ObservableList<Rekening> getRekenings(int idNasabah){
        ObservableList<Rekening> data = FXCollections.observableArrayList();
        String sql="SELECT `noRekening`, `saldo` "
                + "FROM `rekening` "
                + "WHERE idNasabah="+idNasabah;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                data.add(new Rekening(rs.getInt(1),rs.getDouble(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NasabahDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
    public int nextIdNasabah() throws SQLException{
        String sql="SELECT MAX(idNasabah) from nasabah";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)==0?100001:rs.getInt(1)+1;
            }
        return 100001;
    }
    
    public int nextNoRekening(int idNasabah) throws SQLException{
        String sql="SELECT MAX(noRekening) FROM rekening WHERE idNasabah="+idNasabah;
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)+1;
            }
        return 0;
     
    }
    
    public void addRekening(int idNasabah, Rekening acc) throws SQLException{
        String insertRek = "INSERT INTO rekening (noRekening, saldo, idNasabah)"
                + " VALUES (?,?,?)";
  
        PreparedStatement stmtRek = conn.prepareStatement(insertRek);
        stmtRek.setInt(1, acc.getNoRekening());
        stmtRek.setDouble(2, acc.getSaldo());
        stmtRek.setInt(3, idNasabah);
        stmtRek.execute();
        
    }
    public void tambahSaldo(int noRekening, int saldo) throws SQLException{
        String insertRek = "UPDATE rekening SET saldo = saldo + ? where noRekening = ?";
  
        PreparedStatement stmtRek = conn.prepareStatement(insertRek);
        stmtRek.setInt(1, saldo);
        stmtRek.setInt(2, noRekening);
        stmtRek.execute();
        
    }
        public void tarikSaldo(int noRekening, int saldo) throws SQLException{
        String insertRek = "UPDATE rekening SET saldo = saldo - ? where noRekening = ?";
  
        PreparedStatement stmtRek = conn.prepareStatement(insertRek);
        stmtRek.setInt(1, saldo);
        stmtRek.setInt(2, noRekening);
        stmtRek.execute();
        
    }
  
    
}