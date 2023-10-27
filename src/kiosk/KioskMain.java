package kiosk;

import manager.ManagerMain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class KioskMain {
	static OrderHistory orderHistory = new OrderHistory();
	static Order order = new Order();
	public static Scanner input = new Scanner( System.in );

	static void print_main_menu() {
		System.out.println();
		System.out.println( "\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"" );
		System.out.println( "아래 메뉴판을 보시고 메뉴를 골라 입력해주세요." );
		System.out.println();
		System.out.println( "[ SHAKESHACK MENU ]" );
		System.out.printf( "1. %-20s | %s", "Burgers", "앵거스 비프 통살을 다져만든 버거\n" );
		System.out.printf( "2. %-20s | %s", "Frozen Custard", "매장에서 신선하게 만드는 아이스크림\n" );
		System.out.printf( "3. %-20s | %s", "Drinks", "매장에서 직접 만드는 음료\n" );
		System.out.printf( "4. %-20s | %s", "Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주\n" );
		System.out.println();
		System.out.println( "[ ORDER MENU ]" );
		System.out.printf( "5. %-20s | %s", "Order", "장바구니를 확인 후 주문합니다.\n" );
		System.out.printf( "6. %-20s | %s", "Cancel", "진행중인 주문을 취소합니다.\n" );
		System.out.println( "7. 주문 현황 확인" );
		System.out.println( "8. 종료" );
		System.out.print( "메뉴를 선택해 주세요 : " );
	}

	static void print_product_list( String title, Map<Integer, Product > pl ) {
		while( true ) {
			try {
				System.out.println( "\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"" );
				System.out.println( "아래 상품메뉴판을 보시고 상품을 골라 입력해주세요." );
				System.out.println();
				System.out.println( title );
				System.out.printf( "%2d. %-20s\n", 0, "돌아가기" );
				for( var entry : pl.entrySet() ) {
					int key = entry.getKey();
					Product p = entry.getValue();
					System.out.printf( "%2d. %-20s | W %4.1f | %s\n", key, p.getName(), p.getPrice() / 1000.0, p.getDescription() );
				}
				System.out.print( "상품을 선택해 주세요 : " );
				int selectNum = input.nextInt();
				System.out.println();
				if( selectNum == 0 ) {
					break;
				}
				else if( pl.containsKey( selectNum ) ) {
					Product p = pl.get( selectNum ).clone();
					boolean addCart = p.selectProduct();
					if( addCart ) {
						order.addCart( p );
					}
				} else {
					System.out.println( "메뉴를 잘못 선택하셨습니다. 다시 확인해 주세요." );
				}
			}
			catch( Exception ex ) {
				System.out.println( "오류가 발생하였습니다. 입력을 다시 확인해 주세요." );
			}
		}
	}


	public static void kiosk_main_menu() {

		//prepareBurgerList();
		//prepareFrozenCustard();
		//prepareDrinks();
		//prepareBeer();

		boolean running = true;
		while( running ) {
			try {
				print_main_menu();
				int selectNum = input.nextInt();
				System.out.println();
				switch( selectNum ) {
					case 0: {
						orderHistory.showOrderedHistory();
						break;
					}
					case 1: {
						print_product_list( "[ Burger MENU ]", ManagerMain.burgerMap );
						break;
					}
					case 2: {
						print_product_list( "[ Frozen Custard MENU ]", ManagerMain.frozenCustardMap );
						break;
					}
					case 3: {
						print_product_list( "[ Drinks MENU ]", ManagerMain.drinkMap );
						break;
					}
					case 4: {
						print_product_list( "[ Beer MENU ]", ManagerMain.beerMap );
						break;
					}
					case 5: {
						boolean orderOK = order.showOrderList();
						if( orderOK ) {
							order.setOrderTime(LocalDateTime.now() );
							orderHistory.addOrder( order.clone() );
							ManagerMain.addOrder( order.clone() );
							order.clearAllCart();

							System.out.println( "주문이 완료되었습니다!" );
							System.out.println();
							System.out.printf( "대기번호는 [ %d ] 번 입니다.\n", ManagerMain.getLastOrderNumber() );
							System.out.println( "( 3초후 메뉴판으로 돌아갑니다. )" );
							Thread.sleep( 3000 );
						}
						break;
					}
					case 6: {
						System.out.println( "진행하던 주문을 취소하시겠습니까?" );
						System.out.println( "1. 확인     2. 취소" );
						System.out.print( "동작을 선택해 주세요. : " );
						int orderCancelNum = input.nextInt();
						if( orderCancelNum == 1 ) {
							order.clearAllCart();
							System.out.println( "진행하던 주문이 취소되었습니다." );
						}

						break;
					}
					case 7 : {

						List< Order > orderList = ManagerMain.orderList;
						var completeList = orderList.stream().filter( o->o.getState() == Order.orderState.complete ).toList();
						System.out.println( "[ 최근 완료된 주문 목록 ]" );
						for( int i = 0; i < completeList.size() && i < 3; i++ ) {
							Order o = completeList.get( i );
							System.out.println( "주문번호 : " + o.getOrderNumber() + " 요청사항 : " +o.getOrderReq() );
						}
						System.out.println();
						System.out.println( "[ 대기중인 주문 목록 ]" );
						var readyList = orderList.stream().filter( o->o.getState() == Order.orderState.ready ).toList();
						for( Order o : readyList ) {
							System.out.println( "주문번호 : " + o.getOrderNumber() + " 요청사항 : " + o.getOrderReq() );
						}
						System.out.println();
						System.out.println( "3초후 돌아갑니다." );
						Thread.sleep( 3000 );

						break;
					}
					case 8: {
						System.out.println( "프로그램을 종료 합니다." );
						//input.close();
						running = false;
						break;
					}
				}
			}
			catch( Exception ex ) {
				System.out.println( "오류가 발생하였습니다. 다시 시도해 주세요." );
			}
		}
	}
}