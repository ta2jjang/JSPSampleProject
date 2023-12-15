package db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.HobbyDTO;
import db.util.DBConnectionManager;

public class HobbyDAO {

	//필드변수
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public List<HobbyDTO> selectHobbyList() {

		//DB연결코드
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT * FROM t_hobby_list "
				+ " ORDER BY id, no ";  //매개변수를 활용한 sql 쿼리문
		
		List<HobbyDTO> hobbyList =null;
		//if (hobbyList == null) 
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); //준비된 sql 쿼리문 실행!
			
			hobbyList = new ArrayList<HobbyDTO>();
			//if ( hobbyList.size() > 0 )
			
			while(rs.next()) { 
			
			//hobbyList = new ArrayList<HobbyDTO>();
			//if(hobbyList = null) { null이 없는거고 null 아니면 뭐라도 담아
				//hobbyList = new ArrayList<HobbyDTO>();
			//}
				
			HobbyDTO hobbyDTO = new HobbyDTO( rs.getInt("id"), rs.getInt("no"), rs.getString("hobby"), rs.getInt("prefer"));
			
			hobbyList.add(hobbyDTO);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}
	

	
		return hobbyList;	
	}

}
