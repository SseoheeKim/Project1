package jdbc.pos.model.service;
import static jdbc.comm.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jdbc.pos.model.dao.MainDao;
import jdbc.pos.model.vo.Menu;

public class MainService {
	private MainDao dao = new MainDao();
	private Connection conn;
	
	
	/** 메뉴 중복 확인 서비스
	 * @param menuName
	 * @return result
	 * @throws Exception
	 */
	public int menuDupCheck(String menuName) throws Exception {
		conn = getConnection();
		
		int result = dao.menuDupCheck(conn, menuName);
		
		close(conn);
		return result;
	}



	/** 메뉴 추가 서비스
	 * @param menuName
	 * @param menuPrice
	 * @return result
	 * @throws Exception
	 */
	public int addMenu(Menu menu) throws Exception {
		conn = getConnection();
		
		int result = dao.addMenu(conn, menu);
		
		if(result>0) commit(conn);
		else 		rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 메뉴 목록 조회
	 * @return menuList
	 * @throws Exception
	 */
	public List<Menu> showMenuList() throws Exception {
		Connection conn = getConnection();
		
		List<Menu> menuList = dao.showMenuList(conn);
	
		close(conn);
		
		return menuList;
	}



	/** 메뉴 삭제 서비스
	 * @param input
	 * @return result
	 * @throws Exception
	 */
	public int deleteMenu(int input) throws Exception {
		
		Connection conn = getConnection();
		int result = dao.deleteMenu(conn, input);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		close(conn);
		
		return result;
	}



	/** 메뉴 주문 서비스
	 * @param menu
	 * @return result
	 * @throws Exception
	 */
	public int orderMenu(Menu menu) throws Exception {
		
		Connection conn = getConnection();
		
		int orderNo = dao.nextOrderNo(conn);
		menu.setMenuNo(orderNo);
		
		int result = dao.orderMenu(conn, menu);
		if(result > 0) {
			commit(conn);
			result = orderNo;
		}
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}



	/** 가격 변동 서비스
	 * @param menu
	 * @return result
	 * @throws Exception
	 */
	public int updatePrice(Menu menu) throws Exception {
		Connection conn =getConnection();
		
		int result = dao.updatePrice(conn, menu);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		return result;
	}


	
	
}
