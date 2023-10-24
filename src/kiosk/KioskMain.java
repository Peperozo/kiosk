package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KioskMain {
	static OrderHistory orderHistory = new OrderHistory();
	static Order order = new Order();
	public static List< Product > burgerList = new ArrayList<>();
	static List< Product > frozenCustardList = new ArrayList<>();
	static List< Product > drinkList = new ArrayList<>();
	static List< Product > beerList = new ArrayList<>();
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
		System.out.printf( "5. %-20s | %s", "kiosk.Order", "장바구니를 확인 후 주문합니다.\n" );
		System.out.printf( "6. %-20s | %s", "Cancel", "진행중인 주문을 취소합니다.\n" );
		System.out.println( "7. 종료" );
		System.out.print( "메뉴를 선택해 주세요 : " );
	}

	public static void prepareBurgerList() {
		ProductOption beefPatty = new ProductOption( "패티 추가", 500, "소고기 패티 추가 1장" );
		ProductOption mozzarellaCheese = new ProductOption( "모짜렐라 치즈 추가", 400, "모짜렐라 치즈 추가 1장" );
		ProductOption bacon = new ProductOption( "베이컨 추가", 1000, "베이컨 추가 2장" );
		ProductOption chedaCheese = new ProductOption( "체다 치즈 추가", 600, "고소한 체다 치즈 1장" );
		ProductOption potatoBurn = new ProductOption( "포테이토 번", 1200, "감자 맛있겠다." );
		ProductOption largeVegiterian = new ProductOption( "야채야채", 300, "야채 많이 주세요~" );

		burgerList.add( new Product( "Shack Burger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거" )
				.addEnableOption( beefPatty )
				.addEnableOption( mozzarellaCheese )
		);
		burgerList.add( new Product( "SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거" )
				.addEnableOption( bacon )
		);
		burgerList.add( new Product( "Shroom Burger", 9400, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거" )
				.addEnableOption( chedaCheese )
				.addEnableOption( potatoBurn )
		);
		burgerList.add( new Product( "Cheese Burger", 6900, "치즈 1장만 있는 전통 치즈 버거" )
				.addEnableOption( mozzarellaCheese )
				.addEnableOption( chedaCheese )
				.addEnableOption( bacon )
				.addEnableOption( largeVegiterian )
		);
		burgerList.add( new Product( "Hamburger", 5400, "소고기 패티만 들어간 기본 버거!" )
				.addEnableOption( beefPatty )
				.addEnableOption( mozzarellaCheese )
				.addEnableOption( bacon )
				.addEnableOption( chedaCheese )
				.addEnableOption( potatoBurn )
				.addEnableOption( largeVegiterian )
		);
	}

	public static void prepareFrozenCustard() {
		frozenCustardList.add( new Product( "Anthracite Shake", 6501, "로컬 로스팅 브랜드 앤트러사이트와 협업한 기간 한정 콜라보레이션 커피 쉐이크" ) );
		frozenCustardList.add( new Product( "Classic Shakes", 6500, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크" ) );
		frozenCustardList.add( new Product( "Floats", 6500, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료" ) );
	}

	public static void prepareDrinks() {
		ProductOption largeSizeUp = new ProductOption( "Large", 700, "큰걸로 마시고 싶다." );

		drinkList.add( new Product( "Raspberry Lemonade", 4800, "쉐이크쉑 시그니처 레몬에이드에 상큼 달콤한 라즈베리가 더해진 시즌 한정 레몬에이드" )
				.addEnableOption( largeSizeUp )
		);
		drinkList.add( new Product( "Lemonade", 4300, "매장에서 직접 만드는 상큼한 레몬에이드" )
				.addEnableOption( largeSizeUp )
		);
		drinkList.add( new Product( "Brewed Iced Tea", 3500, "직접 유기농 홍차를 우려낸 아이스 티" )
				.addEnableOption( largeSizeUp )
		);
	}

	public static void prepareBeer() {
		beerList.add( new Product( "Abita Root Beer", 4800, "마시면 물파스 맛이 나요!!" ) );
	}

	static void print_product_list( String title, List< Product > pl ) {
		while( true ) {
			try {
				System.out.println( "\"SHAKESHACK BURGER 에 오신걸 환영합니다.\"" );
				System.out.println( "아래 상품메뉴판을 보시고 상품을 골라 입력해주세요." );
				System.out.println();
				System.out.println( title );
				for( int i = 0; i < pl.size(); i++ ) {
					Product p = pl.get( i );
					System.out.printf( "%d. %-20s | W %4.1f | %s\n", i + 1, p.getName(), p.getPrice() / 1000.0, p.getDescription() );
				}
				System.out.printf( "%d. %-20s\n", pl.size() + 1, "돌아가기" );
				System.out.print( "상품을 선택해 주세요 : " );
				int selectNum = input.nextInt();
				System.out.println();
				if( 0 < selectNum && selectNum <= pl.size() ) {
					Product p = pl.get( selectNum - 1 ).clone();
					boolean addCart = p.selectProduct();
					if( addCart ) {
						order.addCart( p );
					}
				} else if( selectNum == pl.size() + 1 ) {
					break;
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

		prepareBurgerList();
		prepareFrozenCustard();
		prepareDrinks();
		prepareBeer();

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
						print_product_list( "[ Burger MENU ]", burgerList );
						break;
					}
					case 2: {
						print_product_list( "[ Frozen Custard MENU ]", frozenCustardList );
						break;
					}
					case 3: {
						print_product_list( "[ Drinks MENU ]", drinkList );
						break;
					}
					case 4: {
						print_product_list( "[ Beer MENU ]", beerList );
						break;
					}
					case 5: {
						boolean orderOK = order.showOrderList();
						if( orderOK ) {
							orderHistory.addOrder( order.clone() );
							order.clearAllCart();

							System.out.println( "주문이 완료되었습니다!" );
							System.out.println();
							System.out.println( "대기번호는 [ 1 ] 번 입니다." );
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
					case 7: {
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