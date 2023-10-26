package kiosk;

import java.util.ArrayList;
import java.util.List;


public class Order implements Cloneable {

	private List< Product > orderedProductList = new ArrayList<>();
	public List< Product > getOrderedProductList() {
		return orderedProductList;
	}

	public void addCart( Product p ) {
		orderedProductList.add( p );
	}

	public void clearAllCart() {
		orderedProductList.clear();
	}

	private int getTotalPrice() {
		return orderedProductList.stream().mapToInt( Product::getTotalPrice ).sum();
	}

	private String orderReq;
	public void setOrderReq( String req ) {
		this.orderReq = req;
	}
	public String getOrderReq() {
		return orderReq;
	}

	private int orderNumber;
	public void setOrderNumber( int n ) {
		this.orderNumber = n;
	}
	public int getOrderNumber() {
		return orderNumber;
	}

	public enum orderState {
		ready,
		complete,
	};
	private orderState state = orderState.ready;
	public orderState getState() {
		return state;
	}
	public void setState( orderState state ) {
		this.state = state;
	}

	public boolean showOrderList() throws InterruptedException {
		if( orderedProductList.isEmpty() ) {
			System.out.println( "현재 장바구니에 아무것도 없습니다." );
			Thread.sleep( 2000 );
			return false;
		}

		System.out.println( "아래와 같이 주문 하시겠습니까?" );
		System.out.println();
		System.out.println( "[ Orders ]" );
		for( int i = 0; i < orderedProductList.size(); i++ ) {
			Product p = orderedProductList.get( i );
			System.out.printf( "%d. %-20s | W %4.1f | %s\n", i + 1, p.getName(), p.getTotalPrice() / 1000.0, p.getDescription() );
		}
		System.out.println();
		System.out.println( "[ Total ]" );
		System.out.printf( "W %d\n", getTotalPrice() );
		System.out.println();
		System.out.println( "주문 요청사항(20자 제한) : " );
		orderReq = KioskMain.input.nextLine();
		orderReq = KioskMain.input.nextLine();
		System.out.println( "1. 주문     2. 메뉴판" );
		System.out.print( "동작을 선택해 주세요. : " );
		int selectNum = KioskMain.input.nextInt();
		return selectNum == 1;
	}

	@Override
	public Order clone() {
		try {
			Order clone = ( Order ) super.clone();
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			clone.orderedProductList = new ArrayList<>( this.orderedProductList );

			return clone;
		}
		catch( CloneNotSupportedException e ) {
			throw new AssertionError();
		}
	}
}
