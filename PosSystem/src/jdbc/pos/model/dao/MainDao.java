package jdbc.pos.model.dao;
import static jdbc.comm.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getMenuPrice());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 메뉴 목록 조회(select)
	 * @param conn
	 * @return menuList
	 * @throws Exception
	 */
	public List<Menu> showMenuList(Connection conn) throws Exception {
		List<Menu> menuList = new ArrayList();
		
		try {
			String sql = prop.getProperty("showMenuList");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Menu menu = new Menu();
				menu.setMenuNo(rs.getInt(1));
				menu.setMenuName(rs.getString(2));
				menu.setMenuPrice(rs.getInt(3));
				menu.setSaleFlag(rs.getString(4)); 
				
				menuList.add(menu);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return menuList;
	}


	/** 메뉴 삭제
	 * @param conn
	 * @param input
	 * @return result
	 * @throws Exception
	 */
	public int deleteMenu(Connection conn, int input) throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteMenu");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	/** 메뉴 주문 
	 * @param conn
	 * @param menu
	 * @return
	 * @throws Exception
	 */
	public int orderMenu(Connection conn, Menu menu) throws Exception {
		int result = 0; 
		
		try {
			String sql = prop.getProperty("orderMenu");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menu.getOrderNo());
			pstmt.setInt(2, menu.getMenuNo());
			pstmt.setInt(3, menu.getOrderQuentity());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	

	/** 가격변동
	 * @param conn
	 * @param menu
	 * @return result
	 * @throws Exception
	 */
	public int updatePrice(Connection conn, Menu menu) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePrice");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menu.getMenuPrice());
			pstmt.setInt(2, menu.getMenuNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 다음 주문 번호 생성
	 * @param conn
	 * @return orderNo
	 * @throws Exception
	 */
	public int nextOrderNo(Connection conn) throws Exception {
		int orderNo = 0;
		
		try {
			String sql = prop.getProperty("nextOrderNo");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				orderNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		return orderNo;
	}

}
