package jdbc.pos.model.vo;

import java.util.List;

public class Menu {
	private int menuNo; // 메뉴 번호 
	private String menuName;// 메뉴명
	private int menuPrice; // 메뉴가격
	private String saleFlag; // 판매여부
	private int orderQuentity; // 판매수량
	private String orderDate; // 판매일
	private int orderNo; // 주문번호
	
	public Menu() {	}
	
	
	public Menu(int menuNo, int orderNo) {
		super();
		this.menuNo = menuNo;
		this.orderNo = orderNo;
	}

	


	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getSaleFlag() {
		return saleFlag;
	}
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}
	public int getOrderQuentity() {
		return orderQuentity;
	}
	public void setOrderQuentity(int orderQuentity) {
		this.orderQuentity = orderQuentity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
	
	
}
