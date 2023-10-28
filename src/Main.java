import kiosk.KioskMain;
import manager.ManagerMain;

import java.util.Scanner;

public class Main {
	public static void main( String... args ) {
		Scanner sc = new Scanner( System.in );
		new ManagerMain(); // 메뉴 초기 설정

		loop:
		while( true ) {
			System.out.println();
			System.out.println( "무엇을 실행 하시겠습니까?" );
			System.out.println( "1. Kiosk" );
			System.out.println( "2. Manager" );
			System.out.print( "선택 : " );
			int selectNum = sc.nextInt();
			switch( selectNum ) {
				case 0 :
					break loop;
				case 1 : {
					KioskMain.kiosk_main_menu();
					break;
				}

				case 2 : {
					ManagerMain.manager_main_menu();
					break;
				}
			}
		}
	}
}
