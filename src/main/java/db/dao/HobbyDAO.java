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

	//�ʵ庯��
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public List<HobbyDTO> selectHobbyList() {

		//DB�����ڵ�
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT * FROM t_hobby_list "
				+ " ORDER BY id, no ";  //�Ű������� Ȱ���� sql ������
		
		List<HobbyDTO> hobbyList =null;
		//if (hobbyList == null) 
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); //�غ�� sql ������ ����!
			
			hobbyList = new ArrayList<HobbyDTO>();
			//if ( hobbyList.size() > 0 )
			
			while(rs.next()) { 
			
			//hobbyList = new ArrayList<HobbyDTO>();
			//if(hobbyList = null) { null�� ���°Ű� null �ƴϸ� ���� ���
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
