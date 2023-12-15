package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dto.PersonInfoDTO;
import db.util.DBConnectionManager;

public class PersonInfoDAO {

	//�ʵ庯��
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	//select...  find...  read...
	public List<PersonInfoDTO> findPersonInfoList(){
		//����� �Ѱ����� ������(List)����

		//DBConnectionManager ������� connection �� Ȱ��
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT * FROM t_person_info ORDER BY id " ;

		List<PersonInfoDTO> personInfoList = null;

		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü

			rs = psmt.executeQuery(); //�غ�� sql ������ ����!
			personInfoList = new ArrayList<PersonInfoDTO>();

			while(rs.next()) { 
				PersonInfoDTO personInfoDTO = new PersonInfoDTO(rs.getInt("id"), rs.getString("name"));

				personInfoList.add(personInfoDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		return personInfoList;
	}

	public PersonInfoDTO findPersonInfoById(int id) { //ex) 10
		
		conn = DBConnectionManager.connectDB();

		String sql =  " SELECT * FROM t_person_info " 
					+ " WHERE id = ? ";

		PersonInfoDTO personInfo = null;

		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setInt(1, id);

			rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			if(rs.next()) { 
				personInfo = new PersonInfoDTO(rs.getInt("id"), rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		return personInfo;
	}

	//����
	public int savePersonInfo(int id, String name) {
		
		conn = DBConnectionManager.connectDB();

		String sql =  " INSERT INTO t_person_info " 
					+ " VALUES ( ?, ? ) ";
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setInt(1, id);
			psmt.setString(2, name);

			result = psmt.executeUpdate(); //1 , 0 
			//rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			/*
			SELECT ���� : psmt.executeQuery(); -> ����� ResultSet
			INSERT, UPDATE, DELETE ���� : psmt.executeUpdate();
										-> ����� ����� ���� ����  
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		
		return result;
	}
	
	public int savePersonInfo(String name) {  // abbb221
		
		conn = DBConnectionManager.connectDB();

		String sql =  " INSERT INTO t_person_info " 
					+ " VALUES ( ( SELECT NVL(MAX(id), 0) + 1 FROM t_person_info ), ? ) ";
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setString(1, name);

			result = psmt.executeUpdate(); //1 , 0 
			//rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			/*
			SELECT ���� : psmt.executeQuery(); -> ����� ResultSet
			INSERT, UPDATE, DELETE ���� : psmt.executeUpdate();
										-> ����� ����� ���� ����  
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		
		return result;
	}
	
	
	public int savePersonInfo(PersonInfoDTO personInfo) {
		
		conn = DBConnectionManager.connectDB();

		String sql =  " INSERT INTO t_person_info " 
					+ " VALUES ( ?, ? ) ";
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setInt(1, personInfo.getId());
			psmt.setString(2, personInfo.getName());

			result = psmt.executeUpdate(); //1 , 0 
			//rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			/*
			SELECT ���� : psmt.executeQuery(); -> ����� ResultSet
			INSERT, UPDATE, DELETE ���� : psmt.executeUpdate();
										-> ����� ����� ���� ����  
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		
		return result;
	}
	
	
	/*
	select find
	insert save
	update modify
	delete remove
	 */
	//����
	public int modifyPersonInfo(PersonInfoDTO personInfo) { 
		//�ش� ���̵� �´� ����� �̸��� ����!
		
		conn = DBConnectionManager.connectDB();

		String sql =  " UPDATE t_person_info " 
					+ " SET name = ? "
					+ " WHERE id = ? ";
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setString(1, personInfo.getName());
			psmt.setInt(2, personInfo.getId());

			result = psmt.executeUpdate(); //1 , 0 
			//rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			/*
			SELECT ���� : psmt.executeQuery(); -> ����� ResultSet
			INSERT, UPDATE, DELETE ���� : psmt.executeUpdate();
										-> ����� ����� ���� ����  
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		
		return result;
	}

	//����
	public int removePersonInfoById(int id) { 
		//�ش� ���̵� �´� ����� ������ ����!!
		
		conn = DBConnectionManager.connectDB();

		String sql =  " DELETE FROM t_person_info " 
					+ " WHERE id = ? ";
		
		int result = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			//Connection Ȱ���ؼ� sql ����� �����ϴ� ��ü
			
			psmt.setInt(1, id);

			result = psmt.executeUpdate(); //1 , 0 
			//rs = psmt.executeQuery(); //�غ�� sql ������ ����!

			/*
			SELECT ���� : psmt.executeQuery(); -> ����� ResultSet
			INSERT, UPDATE, DELETE ���� : psmt.executeUpdate();
										-> ����� ����� ���� ����  
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionManager.closeDB(conn, psmt, rs);
		}

		
		return result;
	}
}

/*
 *  PersonInfoDAO �� �����ϰ�, �����͸� ��ȸ�ؼ� �������� �޼ҵ带 �ۼ��ϼ���.

1) t_person_info �� �ִ� ��ü ������ ��ȸ�ؿ��� �޼ҵ� ���� (return �ʼ�)

2) id ���� �������� �� ���� ��� ������ �������� �޼ҵ� ���� (return �ʼ�)

3) ���ο��� �ش� �޼ҵ带 Ȱ���Ͽ� �����͸� �о����, �ܼ� â�� ����ϼ���.

 */