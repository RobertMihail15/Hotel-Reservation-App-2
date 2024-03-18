package aplicatia3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
*
* @author vali
*/
public class DBOperations {
 String error;
 Connection con;
 // Conectarea la baza de date
 public DBOperations() {
 }
 public void connect() throws ClassNotFoundException, SQLException, Exception {
 try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/robert?useSSL=false", "root", "1234");
 } catch (ClassNotFoundException cnfe) {
 error = "ClassNotFoundException: Nu s-a gasit driverul bazei de date.";
 throw new ClassNotFoundException(error);
 } catch (SQLException cnfe) {
 error = "SQLException: Nu se poate conecta la baza de date.";
 throw new SQLException(error);
 } catch (Exception e) {
 error = "Exception: A aparut o exceptie neprevazuta in timp ce se stabilea legatura la baza de date.";
 throw new Exception(error);
 }
 }
 // end connect()
 public ResultSet vedeTabel(String tabel) throws SQLException, Exception {
 ResultSet rs = null;
 try {
 String queryString = ("select * from `robert`.`" + tabel + "`;");
 Statement stmt = con.createStatement();
 rs = stmt.executeQuery(queryString);
 } catch (SQLException sqle) {
 error = "SQLException: Interogarea nu a fost posibila.";
 throw new SQLException(error);
 } catch (Exception e) {
 error = "A aparut o exceptie in timp ce se extrageau datele.";
 throw new Exception(error);
 }
 return rs;
 }
 public ResultSet vedeRezervare() throws SQLException, Exception {
		ResultSet rs = null;
		try {
			String queryString = ("select h.nume_hotel, h.adresa, h.numar_stele, h.spatii_cazare, c.tip_camera, c.dotari, c.numar_camera, r.idrezervare, r.idhotel idhotel_rez, r.idcamera idcamera_rez, r.data_rezervare, r.suma_rezervare, r.perioada_rezervata from hoteluri h, camere c, rezervari r where h.idhotel = r.idhotel and c.idcamera = r.idcamera;");
			Statement stmt = con.createStatement(/* ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY */);
			rs = stmt.executeQuery(queryString);
		} catch (SQLException sqle) {
			error = "SQLException: Interogarea nu a fost posibila.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "A aparut o exceptie in timp ce se extrageau datele.";
			throw new Exception(error);
		}
		return rs;
	} // vedeFurnizor()
 // end vedeTabel()
 public void disconnect() throws SQLException {
 try {
 if (con != null) {
 con.close();
 }
 } catch (SQLException sqle) {
 error = ("SQLException: Nu se poate inchide conexiunea la baza de date.");
 throw new SQLException(error);
 }
 } // disconnect()
 public void adaugaCamera(String tip_camera, String dotari, String numar_camera) throws SQLException {
 try {
 // create a prepared SQL statement
 Statement stmt;
 stmt = con.createStatement();
 stmt.executeUpdate("insert into `robert`.`camere`(tip_camera, dotari, numar_camera) values('" + tip_camera + "', '" + dotari +
"', '" + numar_camera + "');");
 } catch (SQLException sqle) {
 error = "ExceptieSQL: Reactualizare nereusita; este posibil sa existe duplicate.";
 throw new SQLException(error);
 }
 }
 // end adaugaClient()

 public void adaugaHotel(String nume_hotel, String adresa, String numar_stele, String spatii_cazare) throws SQLException {
 try {
 // create a prepared SQL statement
 Statement stmt;
 stmt = con.createStatement();
 stmt.executeUpdate("insert into `robert`.`hoteluri`(nume_hotel, adresa, numar_stele, spatii_cazare) values('" + nume_hotel + "', '" + adresa + "','" + numar_stele + "', '" + spatii_cazare + "');");
 } catch (SQLException sqle) {
 error = "ExceptieSQL: Reactualizare nereusita; este posibil sa existe duplicate.";
 throw new SQLException(error);
 }
 }
 public void adaugaRezervare(int idhotel, int idcamera, String data_rezervare, String suma_rezervare, String perioada_rezervata) throws SQLException {
	    try {
	        // Create a prepared SQL statement with placeholders
	        String sql = "INSERT INTO `robert`.`rezervari` (idhotel, idcamera, data_rezervare, suma_rezervare, perioada_rezervata) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            // Set parameter values
	            pstmt.setInt(1, idhotel);
	            pstmt.setInt(2, idcamera);
	            pstmt.setString(3, data_rezervare);
	            pstmt.setString(4, suma_rezervare);
	            pstmt.setString(5, perioada_rezervata);

	            // Execute the update
	            pstmt.executeUpdate();
	        }
	    } catch (SQLException sqle) {
	        error = "ExceptieSQL: Reactualizare nereusita; este posibil sa existe duplicate.";
	        throw new SQLException(error);
	    }
	}

 public void stergeDateTabela(String tabela, String dupaID, String ID)
	        throws SQLException, Exception {
	    if (con != null) {
	        try {
	            PreparedStatement delete;
	            delete = con.prepareStatement("DELETE FROM " + tabela + " WHERE " + dupaID + "= " + ID + ";");
	            delete.execute();
	        } catch (SQLException sqle) {
	            error = "ExceptieSQL: Reactualizare nereusita; este posibil sa existe duplicate.";
	            throw new SQLException(error);
	        } catch (Exception e) {
	            error = "A aparut o exceptie in timp ce erau sterse inregistrarile.";
	            throw new Exception(error);
	        }
	    } else {
	        error = "Exceptie: Conexiunea cu baza de date a fost pierduta.";
	        throw new Exception(error);
	    }
	}

	public void stergeTabela(String tabela) throws SQLException, Exception {
		if (con != null) {
			try {
//creaza un "prepared SQL statement"
				Statement stmt;
				stmt = con.createStatement();
				stmt.executeUpdate("delete from " + tabela + ";");
			} catch (SQLException sqle) {
				error = "ExceptieSQL: Stergere nereusita; este posibil sa existe duplicate.";
				throw new SQLException(error);
			}
		} else {
			error = "Exceptie: Conexiunea cu baza de date a fost pierduta.";
			throw new Exception(error);
		}
	} // end of stergeTabela()
  
 public void modificaTabela(String tabela, String primarykey, long ID, String[] campuri, String[] valori)
 throws SQLException, Exception {
 String update = "update " + tabela + " set ";
 String temp = "";
 if (con != null) {
 try {
 for (int i = 0; i < campuri.length; i++) {
 if (i != (campuri.length - 1)) {
 temp = temp + campuri[i] + "='" + valori[i] + "', ";
 } else {
 temp = temp + campuri[i] + "='" + valori[i] + "' where " + primarykey + " = '" + ID + "';";
 }
 }
 update = update + temp;
 // create a prepared SQL statement
 Statement stmt;
 stmt = con.createStatement();
 stmt.executeUpdate(update);
 } catch (SQLException sqle) {
 error = "ExceptieSQL: Reactualizare nereusita; este posibil sa existe duplicate.";
 throw new SQLException(error);
 }
 } else {
 error = "Exceptie: Conexiunea cu baza de date a fost pierduta.";
 throw new Exception(error);
 }
 } // end of modificaTabela()
 public ResultSet intoarceLinie(String tabela, int ID) throws SQLException, Exception {
		ResultSet rs = null;
		try {
//Executa interogarea
			String queryString = ("SELECT * FROM " + tabela + " where idprodus=" + ID + ";");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(queryString); // sql exception
		} catch (SQLException sqle) {
			error = "SQLException: Interogarea nu a fost posibila.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "A aparut o exceptie in timp ce se extrageau datele.";
			throw new Exception(error);
		}
		return rs;
	} // end of intoarceLinie()

	public ResultSet intoarceLinieDupaId(String tabela, String denumireId, int ID) throws SQLException, Exception {
		ResultSet rs = null;
		try {
//Executa interogarea
			String queryString = ("SELECT * FROM " + tabela + " where " + denumireId + "=" + ID + ";");
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(queryString); // sql exception
		} catch (SQLException sqle) {
			error = "SQLException: Interogarea nu a fost posibila.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "A aparut o exceptie in timp ce se extrageau datele.";
			throw new Exception(error);
		}
		return rs;
	} // end of intoarceLinieDupaId()
	public ResultSet intoarceRezervareId(int ID) throws SQLException, Exception {
		ResultSet rs = null;
		try {
// Executa interogarea
			String queryString = ("SELECT h.nume_hotel, h.adresa, h.numar_stele, h.spatii_cazare, c.tip_camera, c.dotari, c.numar_camera, r.idrezervare, r.idhotel idhotel_rez, r.idcamera idcamera_rez, r.data_rezervare, r.suma_rezervare, r.perioada_rezervata FROM hoteluri h, camere c, rezervari r WHERE h.idhotel = r.idhotel and c.idcamera = r.idcamera and idrezervare ='"+ ID + "';");
					
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(queryString); // sql exception
		} catch (SQLException sqle) {
			error = "SQLException: Interogarea nu a fost posibila.";
			throw new SQLException(error);
		} catch (Exception e) {
			error = "A aparut o exceptie in timp ce se extrageau datele.";
			throw new Exception(error);
		}
		return rs;
	} // end of intoarceLinieDupaId()
 
 public void afisare(ResultSet rs) throws ClassNotFoundException, SQLException, Exception{
int idcamera;
String tip_camera, dotari, numar_camera;
while(rs.next()){
idcamera = rs.getInt("idcamera");
tip_camera = rs.getString("tip_camera");
dotari = rs.getString("dotari");
numar_camera = rs.getString("numar_camera");
 System.out.println("idcamera = " + idcamera + ", tip_camera = " + tip_camera + ", dotari = " + dotari + ",numar_camera = " + numar_camera);
}
}
}