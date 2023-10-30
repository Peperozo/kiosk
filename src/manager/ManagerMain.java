package manager;

import kiosk.Order;
import kiosk.Product;
import java.time.LocalDateTime;

import java.util.*;

public class ManagerMain {
	public static List< Order > orderList = new ArrayList<>();
	public static Map<Integer, String[]> mainMenu = new LinkedHashMap<>(); // 메인 메뉴
	public static Map<Integer, String[]> newMainMenu = new LinkedHashMap<>(); // 신규 메뉴 추가 부분
	public static Map<Integer, Product> burgerMap = new LinkedHashMap<>(); // 상품
	public static Map<Integer, Product> frozenCustardMap = new LinkedHashMap<>();
	public static Map<Integer, Product> drinkMap = new LinkedHashMap<>();
	public static Map<Integer, Product> beerMap = new LinkedHashMap<>();
	public static int menuId = 1; // 메인 메뉴 ID
	public static int productID = 1; // 상품 ID
	private static int orderTotalNumber = 1;

	public ManagerMain() {
		// 메뉴 초기 설정
		mainMenu.put(9000, new String[]{"Burgers", "앵거스 비프 통살을 다져만든 버거"});
		mainMenu.put(9001, new String[]{"Frozen Custard", "매장에서 신선하게 만드는 아이스크림"});
		mainMenu.put(9002, new String[]{"Drinks", "매장에서 직접 만드는 음료"});
		mainMenu.put(9003, new String[]{"Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주"});

		burgerMap.put(productID++, new Product("Shack Burger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
		burgerMap.put(productID++, new Product("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
		burgerMap.put(productID++, new Product("Shroom Burger", 9400, "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"));
		burgerMap.put(productID++, new Product("Cheese Burger", 6900, "치즈 1장만 있는 전통 치즈 버거"));
		burgerMap.put(productID++, new Product("Hamburger", 5400, "소고기 패티만 들어간 기본 버거!"));

		frozenCustardMap.put(productID++, new Product( "Anthracite Shake", 6501, "로컬 로스팅 브랜드 앤트러사이트와 협업한 기간 한정 콜라보레이션 커피 쉐이크" ));
		frozenCustardMap.put(productID++, new Product( "Classic Shakes", 6500, "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크" ));
		frozenCustardMap.put(productID++, new Product( "Floats", 6500, "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료" ));

		drinkMap.put(productID++, new Product("Raspberry Lemonade", 4800, "쉐이크쉑 시그니처 레몬에이드에 상큼 달콤한 라즈베리가 더해진 시즌 한정 레몬에이드"));
		drinkMap.put(productID++, new Product("Lemonade", 4300, "매장에서 직접 만드는 상큼한 레몬에이드"));
		drinkMap.put(productID++, new Product("Brewed Iced Tea", 3500, "직접 유기농 홍차를 우려낸 아이스 티"));

		beerMap.put(productID++, new Product( "Abita Root Beer", 4800, "마시면 물파스 맛이 나요!!" ));
	}
	public static void addOrder( Order o ) {
		o.setOrderNumber( orderTotalNumber++ );
		orderList.add( o );
	}
	public static int getLastOrderNumber() {
		return orderList.get( orderList.size() - 1 ).getOrderNumber();
	}

	public static void completeOrders() {
		System.out.println("----------대기주문 목록---------- ");
		for (Order order : orderList) {
			if (order.getState() == Order.orderState.ready) {
				System.out.println("1. 대기 번호: " + order.getOrderNumber());
				System.out.println("2. 주문 상품 목록:");
				for (Product product : order.getOrderedProductList()) {
					System.out.println("   - " + product.getName() + ": " + product.getDescription());
				}
				System.out.println("3. 주문 총 가격: " + order.getTotalPrice());
				System.out.println("4. 요청 사항: " + order.getOrderReq());
				System.out.println("5. 주문 일시: " + order.getOrderTime());

				// Mark the order as complete
				order.setState(Order.orderState.complete);
				order.setCompleteTime();

				System.out.println("주문이 완료 처리되었습니다.");
				System.out.println("------------------------------ ");
			}
		}
	}

	public static void getCompletedOrders() {
		System.out.println("----------완료주문 목록---------- ");
		for (Order order : orderList) {
			if (order.getState() == Order.orderState.complete) {
				System.out.println("1. 대기 번호: " + order.getOrderNumber());
				System.out.println("2. 주문 상품 목록:");
				for (Product product : order.getOrderedProductList()) {
					System.out.println("   - " + product.getName() + ": " + product.getDescription());
				}
				System.out.println("3. 주문 총 가격: " + order.getTotalPrice());
				System.out.println("4. 요청 사항: " + order.getOrderReq());
				System.out.println("5. 완료주문 일시: " + order.getCompleteTime());
				System.out.println("------------------------------ ");
			}
		}
	}

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
				case 1: {
					completeOrders();
				}
				break;
				case 2 : {
					getCompletedOrders();
				}
				break;
				case 3 : {
					ProductCreation.createProduct();
					break;
				}
				case 4:
					System.out.println("삭제할 항목을 선택하세요.\n1. 메뉴\t2. 상품\t0. 돌아가기");
					int delete = sc.nextInt();
					if (delete == 1){
						ProductDelete.deleteMenu();
					} else if(delete == 2){
						ProductDelete.deleteProduct();
					} else if(delete == 0){
						break;
					} else{
						System.out.println("1 또는 2를 입력하세요.");
					}
				case 5: {
					return;
				}
			}
		}
	}
}