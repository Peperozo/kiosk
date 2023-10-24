import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
	private List< Order > orderHistory = new ArrayList<>();

	void addOrder( Order o ) {
		orderHistory.add( o );
	}

	int getTotalPrice() {
		return orderHistory
				.stream()
				.mapToInt(
						o -> o.getOrderedProductList()
								.stream()
								.mapToInt( Product::getTotalPrice )
								.sum()
				)
				.sum();
	}

	void showOrderedHistory() {
		while( true ) {
			System.out.println( "[ 총 판매상품 목록 현황 ]" );
			System.out.println( "현재까지 총 판매된 상품 목록은 아래와 같습니다." );
			System.out.println();
			for( Order o : orderHistory ) {
				for( Product p : o.getOrderedProductList() ) {
					System.out.printf( "- %-21s | W %4.1f\n", p.getName(), p.getTotalPrice() / 1000.0 );
				}
			}
			System.out.println();
			System.out.println( "[ 총 판매금액 현황 ]" );
			System.out.printf( "현재까지 총 판매된 금액은 [ W %.1f ] 입니다.\n", this.getTotalPrice() / 1000.0 );
			System.out.println();
			System.out.println( "1. 돌아가기" );
			System.out.print( "동작을 입력해 주세요. : " );
			int selectNum = Main.input.nextInt();
			if( selectNum == 1 ) {
				return;
			}
		}
	}
}
