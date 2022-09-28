package jdbc.pos.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.pos.model.service.MainService;
import jdbc.pos.model.vo.Menu;

public class PosView {
	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	private Menu menu = new Menu();
	
	public void mainMenu() {
		
		int input = -1;
		
		do {
			try {
				System.out.println("\n---------------------------");
				System.out.println("---------[POS MENU]----------\n");
				System.out.println("1. 메뉴 주문");
				System.out.println("2. 새 메뉴 등록");
				System.out.println("3. 메뉴 삭제");
				System.out.println("4. 메뉴 가격 변경");
				System.out.println("5. 일자별 판매 금액 조회");
				System.out.println("6. 일자별 판매 내역 조회");
				System.out.println("7. 메뉴별 판매 내역 조회");
				
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: orderMenu(); break;
				case 2: addMenu(); break;
				case 3: deleteMenu(); break;
				case 4: updatePrice(); break;
				case 5: selectByDate(); break;
				case 0: System.out.println("\n프로그램을 종료합니다."); break;
				default : System.out.println("\n메뉴의 번호를 입력하세요.");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n입력 형식이 잘못되었습니다.");
				sc.nextLine();
			}
			
		} while (input != 0);
	}

	

	/**
	 * 메뉴 목록 조회
	 */
	private List<Menu> showMenuList() {
		List<Menu> menuList = null;
		
		try {
			
			menuList = service.showMenuList();
			
			if(menuList.isEmpty()) {
				System.out.println("\n판매 중인 메뉴가 없습니다.");
			} else {
				System.out.println("\n____________ 메뉴 목록 ___________\n");
				for(Menu menu : menuList) {
				System.out.printf("%d | %10s | %d \n" ,
								menu.getMenuNo(), menu.getMenuName(), menu.getMenuPrice()); 
				}
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println("\n[메뉴 리스트 확인 중 예외 발생]");
			e.printStackTrace();
		}
		
		return menuList;
	}



	/**
	 * 메뉴 주문
	 */
	private void orderMenu() {
				
		try {
					
			while(true) {
				
				System.out.println("\n*********** 메뉴 주문 **********");
				List<Menu> menuList = showMenuList();
				
				System.out.print("\n메뉴 선택 > ");
				int menuNo = sc.nextInt();
				sc.nextLine();
				
				System.out.print("수량 > ");
				int orderQuentity = sc.nextInt();
				sc.nextLine();
				
				Menu menu = new Menu();
				menu.setMenuNo(menuNo);
				menu.setOrderQuentity(orderQuentity);
				
				int result = service.orderMenu(menu);
				
				
				if(result > 0) {
					for(Menu m : menuList) {
						if(m.getMenuNo() == menuNo) {
							System.out.printf("\n%s %d잔, %d원입니다.", 
									m.getMenuName(), orderQuentity, m.getMenuPrice() * orderQuentity);
							break;
						}
					}
					System.out.println("\n주문이 완료되었습니다.");
					break;	
				} else {
					System.out.println("\n주문 실패");
				}
			} 
			
		} catch(Exception e) {
			System.out.println("\n메뉴 주문 중 예외 발생");
			e.printStackTrace();
		}
		
	}

	

	/**
	 * 새로운 메뉴 추가
	 */
	private void addMenu() {
		String menuName = null;
		
		try {
			System.out.println("\n*********** 새로운 메뉴 등록 ***********");
			
			while(true) {
				System.out.print("\n새로 추가할 메뉴 이름 : ");
				menuName = sc.nextLine();
				
				int result = service.menuDupCheck(menuName);
				
				if (result == 0) {
					Menu m = new Menu();
					m.setMenuName(menuName);
					System.out.println(m.getMenuName() + "는/은 등록 가능한 메뉴입니다. \n");
					break;
				} else {
					System.out.println("\n이미 존재하는 메뉴입니다.");
				}
			}
			
			 
			System.out.print("메뉴 가격 : ");
			int menuPrice = sc.nextInt();
			
			Menu menu = new Menu();
			menu.setMenuName(menuName);
			menu.setMenuPrice(menuPrice);
			
			int result = service.addMenu(menu);
			
			if(result > 0) {
				System.out.println( menuName +" 메뉴가 정상적으로 추가되었습니다.");
			} else {
				System.out.println("\n메뉴 추가 실패");
			}
			
		} catch(Exception e) {
			System.out.println("\n메뉴 추가 중 예외 발생");
			e.printStackTrace();
		}	
	}
	
	
	
	/**
	 * 메뉴 삭제
	 */
	private void deleteMenu() {
		try {
			System.out.println("\n*********** 메뉴 삭제 ***********");
			showMenuList();
			
			System.out.print("\n삭제할 메뉴 선택 : ");
			int input = sc.nextInt();
			sc.nextLine();
			
			int result = service.deleteMenu(input);
			
			
			System.out.print("\n정말로 삭제하시겠습니까?(Y/N)");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch == 'Y') {
				if(result > 0) {
					System.out.println("\n선택하신 메뉴가 삭제되었습니다.");
					System.out.print("기존 메뉴 삭제 후");
					showMenuList();
				} else {
					System.out.println("\n메뉴 삭제 실패");
				}
			} else if(ch == 'N') {
				System.out.println("\n메뉴 삭제를 취소합니다.");
			} else {
				System.out.println("\nY 또는 N를 입력하세요.");
			}
			
		} catch(Exception e) {
			System.out.println("\n메뉴 삭제 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 가격 변경하기
	 */
	private void updatePrice() {
		try {
			System.out.println("\n*********** 가격 변경 ***********");
			showMenuList();
			
			System.out.print("\n가격을 변경할 메뉴 선택 > ");
			int input = sc.nextInt();
			
			System.out.print("가격 : ");
			int price = sc.nextInt();
			
			Menu menu = new Menu();
			menu.setMenuNo(input);
			menu.setMenuPrice(price);
			
			int result = service.updatePrice(menu);
			
			if(result > 0) {
				Menu m = new Menu();
				m.setMenuPrice(price);
				System.out.println("\n가격을 " + m.getMenuPrice() + "원으로 변경합니다.");
				
			} else {
				System.out.println("\n가격 변동 실패");
			}
			
			
		} catch(Exception e) {
			System.out.println("\n가격 변경 중 예외 발생");
			e.printStackTrace();
		}
		
	}



	/**
	 * 일자별 판매금액 조회
	 */
	private void selectByDate() {
		
		try {
			System.out.println("\n*********** 일자별 판매 금액 조회 ***********");
			System.out.print("\n조회할 날짜 입력(YYYY-MM-DD) : ");
			String date = sc.nextLine();
			
			List<Menu> m =  service.selectByDate(date);
			
			if(m.isEmpty()) {
				System.out.println("\n조회되는 내역이 없습니다.");
			} else {
				
				for(Menu menu : m) {
					System.out.printf("\n%s 일자 판매 총액 : %d원\n", menu.getOrderDate(), menu.getMenuPrice());
				}
				
			}
			
		} catch(Exception e) {
			System.out.println("\n일자별 판매 금액 조회 중 예외 발생");
			
			
		}
		
	}



}


