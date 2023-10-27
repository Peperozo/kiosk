package manager;

import kiosk.Order;
import kiosk.Product;
import kiosk.ProductOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;


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
						o.setState( Order.orderState.ready );
						for( Product p : o.getOrderedProductList() ) {
							System.out.println(p.getName() + " " + p.getDescription());
						}
					}
					break;
				}
				case 2 : {
					for( Order o : orderList ) {

						o.setState( Order.orderState.complete );
						System.out.println("--------------------------------------------");
						System.out.println("[ 완료주문 데이터 ]");
						System.out.println("1. 대기 번호: ");
						System.out.println("2. 주문 상품 목록:");

//						for( Product p : o.getOrderedProductList() ) {
<<<<<<< HEAD
							for (Product product : o.getOrderedProductList()) {
								System.out.println("   " + product.getName() + " | " + product.getDescription());
							}
//						}}}}}
=======
						for (Product product : o.getOrderedProductList()) {
							System.out.println("   " + product.getName() + " | " + product.getDescription());
						}
//						}
>>>>>>> main
						System.out.println("3. 주문 총 가격: W " + o.getTotalPrice());
						System.out.println("4. 주문 일시: ");
						System.out.println("5. 요청 사항: " + o.orderReq());
						System.out.println("6. 완료주문 일시: " + java.time.Instant.now());
						System.out.println("--------------------------------------------");
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