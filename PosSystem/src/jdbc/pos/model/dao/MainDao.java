package jdbc.pos.model.dao;
import static jdbc.comm.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import jdbc.pos.model.vo.Menu;

public class MainDao {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	
	/**
	 * 기본 생성자 --> Properties 객체를 통해 xml파일 사용 가능
	 */
	public MainDao() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("sql-query.xml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 메뉴 중복 확인 DAO
	 * @param conn
	 * @param menuName
	 * @return result
	 * @throws Exception
	 */
	public int menuDupCheck(Connection conn, String menuName) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("menuDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 새로운 메뉴 추가
	 * @param conn
	 * @param menu
	 * @return result
	 * @throws Exception
	 */
	public int addMenu(Connection conn, Menu menu) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("addMenu");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu.getMenuNo());
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(3, menu.getMenuPrice());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
