package manager;

import kiosk.Order;
import kiosk.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerMain {

	public static List< Order > orderList = new ArrayList<>();

	public static void manager_main_menu() {
		Scanner sc = new Scanner( System.in );
		while( true ) {
			System.out.println();
			System.out.println( "kiosk manager에 오신것을 환영합니다." );
			System.out.println( "1. 대기주문 목록" );
			System.out.println( "2. 완료주문 목록" );
			System.out.println( "3. 상품 생성" );
			System.out.println( "4. 상품 삭제" );
			System.out.println( "5. 종료");
			System.out.print( "선택 : " );
			int selectNum = sc.nextInt();
			switch( selectNum )
			{
				case 1 : {
					for( Order o : orderList ) {
						for( Product p : o.getOrderedProductList() ) {
							System.out.println( p.getName() + " " + p.getDescription() );
						}
					}
					break;
				}
				case 5: {
					return;
				}
			}
		}
	}
}
