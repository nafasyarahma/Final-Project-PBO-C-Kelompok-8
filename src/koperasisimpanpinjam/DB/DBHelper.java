package koperasisimpanpinjam.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class DBHelper {

    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "koperasi";
    private static final String MYCONN = "jdbc:mysql://localhost/" + DB;

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(MYCONN, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Library tidak ada");
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void createTable(Connection conn) throws SQLException {
        String sqlCreate = "";
        sqlCreate = "CREATE TABLE IF NOT EXISTS `nasabah` ("
                + "  `idNasabah` int(10) NOT NULL,"
                + "  `nama` varchar(100) DEFAULT NULL,"
                + "  `alamat` varchar(100) DEFAULT NULL,"
                + "  PRIMARY KEY (`idNasabah`)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
                
                + "CREATE TABLE IF NOT EXISTS `individu` ("
                + "  `idNasabah` int(10) NOT NULL,"
                + "  `nik` bigint(255) DEFAULT NULL,"
                + "  `npwp` bigint(255) DEFAULT NULL,"
                + "  PRIMARY KEY (`idNasabah`),"
                + "FOREIGN KEY (`idNasabah`) REFERENCES `nasabah` (`idNasabah`) ON UPDATE CASCADE"
                + ")ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
                
                + "CREATE TABLE IF NOT EXISTS `perusahaan` ("
                + "  `idNasabah` int(10) NOT NULL,"
                + "  `nib` varchar(100) DEFAULT NULL,"
                + "  PRIMARY KEY (`idNasabah`),"
                + "FOREIGN KEY (`idNasabah`) REFERENCES `nasabah` (`idNasabah`) ON UPDATE CASCADE"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;"
                
                
                + "CREATE TABLE IF NOT EXISTS `rekening` ("
                + "  `noRekening` int(10) NOT NULL,"
                + "  `saldo` double(16,2) DEFAULT NULL,"
                + "  `idNasabah` int(10) DEFAULT NULL,"
                + "  PRIMARY KEY (`noRekening`),"
                + "  KEY `idNasabah` (`idNasabah`),"
                + "  FOREIGN KEY (`idNasabah`) REFERENCES `nasabah` (`idNasabah`) ON UPDATE CASCADE)"
                + "  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        
        String sqls[] = sqlCreate.split(";");
            for (String sql : sqls) {
                PreparedStatement stet = conn.prepareStatement(sql);
                stet.execute();
            }
        }
}
    
    
