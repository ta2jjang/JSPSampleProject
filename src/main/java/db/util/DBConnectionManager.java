package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionManager {
	
	public static Connection connectDB() {
		//DB ?ó∞Í≤? Í¥??†®?êú ?ùº?ù¥Î∏åÎü¨Î¶? Î°úÎî©
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String db_id = "scott";
			String db_pw = "tiger";

			conn = DriverManager.getConnection(db_url, db_id, db_pw);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeDB(Connection conn, PreparedStatement psmt, ResultSet rs) {
		try {
			if(conn != null) {
				conn.close();
			}

			if(psmt != null) {
				psmt.close();
			}

			if(rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //DB Connection ?ó∞Í≤? Ï¢ÖÎ£å 
	}
}
