package jdbc.pos.model.service;
import static jdbc.comm.JDBCTemplate.*;

import java.sql.Connection;

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

}
