package jdbc.pos.model.vo;

public class Menu {
	private String menuNo; // 메뉴 번호 
	private String menuName;// 메뉴명
	private int menuPrice; // 메뉴가격
	private int salesQuentity; // 판매수량
	private String salesDate; // 판매일
	
	public Menu() { }
	

	public Menu(String menuNo, String menuName, int menuPrice) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}


	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
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
