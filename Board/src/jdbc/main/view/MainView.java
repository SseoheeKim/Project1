package jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.main.model.service.MainService;
import jdbc.main.model.vo.Member;

public class MainView {
	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	
	public static Member loginMember = null;
	
	public void mainMenu() {
		int input = -1;
		
		
		do {
			
			try {
				
				if(loginMember == null) {
					System.out.println("\n***** 회원제 게시판 프로그램 *****\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
					System.out.println("0. 프로그램 종료");
	
					System.out.print("\n메뉴 선택 : ");
	
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거
					System.out.println();
	
					switch (input) {
					case 1: break; // 로그인
					case 2: break; // 회원 가입
					case 0: System.out.println("\n프로그램 종료"); break;
					default: System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
					
				} else {
				
					System.out.println("***** 로그인 메뉴 *****");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.print("\n메뉴 선택 : ");
					input = sc.nextInt();
					
					System.out.println();
					
					switch (input) {
					case 1: break;
					case 2: break;
					case 0: break;
					case 99: break;
					default : 
					
						
				
					}
				}	
			} catch(InputMismatchException e) {
				System.out.println("\n[입력 형식이 올바르지 않습니다.]");
				sc.nextLine();
			}
			
		} while (input != 0);
	}
	
	
}
