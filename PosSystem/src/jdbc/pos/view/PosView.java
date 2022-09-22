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
	private Menu menu = null;
	
	public void mainMenu() {
		int input = -1;
		
		do {
			try {
				System.out.println("\n*****[POS MENU]*****\n");

				System.out.println("1. 메뉴 주문");
				System.out.println("2. 새 메뉴 등록");
				System.out.println("3. 메뉴 삭제");
				System.out.println("4. 메뉴 가격 변경");
				System.out.println("5. 일자별 판매내역 조회");
				System.out.println("6. 정산");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: break;
				case 2: addMenu(); break;
				case 3:  break;
				case 4:  break;
				case 5:  break;
				case 6:  break;
				case 0: System.out.println("\n프로그램을 종료합니다."); break;
				default : System.out.println("\n메뉴의 번호를 입력하세요.");
				}
				
			} catch(InputMismatchException e) {
				System.out.println("\n숫자만 입력해주세요.");
				sc.nextLine();
			}
		} while (input != 0);
	}

	
	/**
	 * 새로운 메뉴 추가
	 */
	private void addMenu() {
		System.out.println("\n*****[메뉴 추가]*****\n");
		String menuName = null;
		
		try {
			while(true) {
				System.out.print("추가할 메뉴 이름 : ");
				menuName = sc.nextLine();
				
				int result = service.menuDupCheck(menuName);
				
				if (result == 0 ) {
					System.out.println(menuName + "는/은 등록 가능한 메뉴입니다.");
					break;
				} else {
					System.out.println("이미 존재하는 메뉴입니다.");
				}
			}
			
			 
			System.out.print("메뉴 가격 : ");
			int menuPrice = sc.nextInt();
			
			int result = service.addMenu(menu);
			
			if(result > 0) {
				System.out.println("\n메뉴가 정상적으로 추가되었습니다.");
			} else {
				System.out.println("\n메뉴 추가 실패");
			}
			
		} catch(Exception e) {
			System.out.println("\n 메뉴 추가 중 예외 발생");
		}	
	}
}


