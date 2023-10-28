package kiosk;

import manager.ManagerMain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class KioskMain {
	static OrderHistory orderHistory = new OrderHistory();
	static Order order = new Order();
	public static Scanner input = new Scanner( System.in );

	static int menuNum;
	static AtomicInteger menuNumber = new AtomicInteger(1);
	static AtomicInteger productNumber = new AtomicInteger(1);
	static ArrayList<Integer> menuIdList = new ArrayList<Integer>();

	static void print_main_menu() {
		System.out.println();
		System.out.println( "\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"" );
		System.out.println( "아래 메뉴판을 보시고 메뉴를 골라 입력해주세요." );
		System.out.println();
		System.out.println( "[ SHAKESHACK MENU ]" );
		System.out.println();
		ManagerMain.mainMenu.forEach((key, value) -> { // 메인 메뉴 출력
			System.out.printf("%d. %-20s | %s\n", menuNumber.getAndIncrement(), value[0], value[1]);
			menuIdList.add(key); // 리스트에 순서대로 키값(메뉴 ID) 저장
		});
		System.out.println();
		System.out.println( "[ ORDER MENU ]" );
		System.out.printf( "%d. %-20s | %s", menuNumber.get(), "Order", "장바구니를 확인 후 주문합니다.\n" );
		System.out.printf( "%d. %-20s | %s", menuNumber.get() + 1, "Cancel", "진행중인 주문을 취소합니다.\n" );
		System.out.printf( "%d. 주문 현황 확인\n", menuNumber.get() + 2 );
		System.out.printf( "%d. 종료\n", menuNumber.get() + 3 );
		System.out.print( "메뉴를 선택해 주세요 : " );

		menuNum = menuNumber.get(); // menuNumber 값 따로 저장 후
		menuNumber = new AtomicInteger(1); // menuNumber 1로 초기화
	}

	static void print_product_list( String title, Map<Integer, Product > pl ) {
		while( true ) {
			productNumber = new AtomicInteger(1); // productNumber 1로 초기화
			try {
				System.out.println( "\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"" );
				System.out.println( "아래 상품메뉴판을 보시고 상품을 골라 입력해주세요." );
				System.out.println();
				System.out.println( title );
				System.out.printf( "%2d. %-20s\n", 0, "돌아가기" );
				for( var entry : pl.entrySet() ) {
					int key = entry.getKey();
					Product p = entry.getValue();
					System.out.printf("%2d. ID%d %-20s | W %4.1f | %s\n", productNumber.getAndIncrement(), key, p.getName(), p.getPrice() / 1000.0, p.getDescription());
				}
				System.out.print( "상품 ID를 입력해 주세요 : " );
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
				}  else {
					System.out.println( "상품을 잘못 선택하셨습니다. 다시 확인해 주세요." );
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

				if(selectNum == 0){
					orderHistory.showOrderedHistory();
					break;
				} else if(selectNum == menuNum){ // 확인 및 주문
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
				} else if(selectNum == menuNum + 1){ // 주문 취소
					System.out.println( "진행하던 주문을 취소하시겠습니까?" );
					System.out.println( "1. 확인     2. 취소" );
					System.out.print( "동작을 선택해 주세요. : " );
					int orderCancelNum = input.nextInt();
					if( orderCancelNum == 1 ) {
						order.clearAllCart();
						System.out.println( "진행하던 주문이 취소되었습니다." );
					}
					break;
				} else if(selectNum == menuNum + 2){ // 주문 현황
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
				} else if(selectNum == menuNum + 3){ // 종료
					System.out.println( "프로그램을 종료 합니다." );
					//input.close();
					running = false;
					break;
				} else if(menuIdList.get(selectNum-1) == 9000) { // 메뉴 선택
					print_product_list("[ Burger MENU ]", ManagerMain.burgerMap);
					break;
				} else if(menuIdList.get(selectNum-1) == 9001){
					print_product_list( "[ Frozen Custard MENU ]", ManagerMain.frozenCustardMap );
					break;
				} else if(menuIdList.get(selectNum-1) == 9002){
					print_product_list( "[ Drinks MENU ]", ManagerMain.drinkMap );
					break;
				} else if(menuIdList.get(selectNum-1) == 9003){
					print_product_list( "[ Beer MENU ]", ManagerMain.beerMap );
					break;
				} //else if(){
					// 신메뉴
					//break;
				//}
			}
			catch( Exception ex ) {
				System.out.println( "오류가 발생하였습니다. 다시 시도해 주세요." );
			}
		}
	}
}