package jdbc.pos.model.vo;

import java.util.List;

public class Menu {
	private int menuNo; // 메뉴 번호 
	private String menuName;// 메뉴명
	private int menuPrice; // 메뉴가격
	private String saleFlag; // 판매여부
	private int salesQuentity; // 판매수량
	private String salesDate; // 판매일
	private int orderNo; // 주문번호
	
	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	private List<Menu> orderList;
	
	public Menu() { }


	public Menu(String menuName, int menuPrice) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}


	public List<Menu> getOrderList() {
		return orderList;
	}
	
	public void setOrderList(List<Menu> orderList) {
		this.orderList = orderList;
	}
	
	public String getSaleFlag() {
		return saleFlag;
	}
	
	
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
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

	public int getSalesQuentity() {
		return salesQuentity;
	}

	public void setSalesQuentity(int salesQuentity) {
		this.salesQuentity = salesQuentity;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}
 
	
}
