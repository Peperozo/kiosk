package kiosk;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Product extends Menu implements Cloneable {
	// 메인 제품들 ( 햄버거, 맥주, 음료수 등등 )

	private final int price;

	// 해당 제품에 선택 가능한 옵션들.
	private List< ProductOption > enable_options;

	// 사용자가 주문하면서 선택한 옵션들.
	private List< ProductOption > select_options;

	public Product( String name, int price, String description ) {
		super.name = name;
		this.price = price;
		super.description = description;

		this.enable_options = new ArrayList<>();
		this.select_options = new ArrayList<>();
	}

	public Product addEnableOption( ProductOption po ) {
		enable_options.add( po );

		return this;
	}

	public int getTotalPrice() {
		// 제품과 옵션들의 가격 총 합.
		return price + select_options.stream().mapToInt( ProductOption::getPrice ).sum();
	}

	public boolean selectProduct() {

		boolean finishedSelectOption = false;
		while( true ) {
			try {
				System.out.printf( "\"%-20s | W %4.1f | %s\"\n", this.name, this.getTotalPrice() / 1000.0, this.description );

				if( select_options.isEmpty() == false ) {
					for( int i = 0; i < select_options.size(); i++ ) {
						ProductOption po = select_options.get( i );
						System.out.printf( "ㄴ %-17s | %s\n", po.getName(), po.getDescription() );
					}

				} else {
					System.out.println( "ㄴ 선택된 옵션 없음." );
				}

				System.out.println();

				if( enable_options.isEmpty() == false ) {
					System.out.println( "위 메뉴에 어떤 옵션을 추가하시겠습니까?" );
					for( int i = 0; i < enable_options.size(); i++ ) {
						ProductOption po = enable_options.get( i );
						System.out.printf( "%d. %s( W %.1f )\n", i + 1, po.getName(), po.getPrice() / 1000.0 );
					}
					System.out.printf( "%d. 옵션 선택하지 않기.\n", enable_options.size() + 1 );
					System.out.print( "메뉴를 선택해 주세요. : " );

					int selectNum = KioskMain.input.nextInt();
					System.out.println();
					if( 0 < selectNum && selectNum <= enable_options.size() ) {
						select_options.add( enable_options.get( selectNum - 1 ).clone() );
						continue;
					} else if( selectNum == enable_options.size() + 1 ) {
						finishedSelectOption = true;
					} else {
						System.out.println( "잘못된 입력입니다. 다시 선택해 주세요." );
					}
				} else {
					finishedSelectOption = true;
				}

				if( finishedSelectOption ) {
					System.out.println( "위 메뉴를 장바구니에 추가하시겠습니까?" );
					System.out.println( "1. 확인     2. 취소" );
					System.out.print( "번호를 선택해 주세요 : " );
					int selectNum = KioskMain.input.nextInt();
					System.out.println();
					return selectNum == 1;
				}
			}
			catch( Exception ex ) {
				System.out.printf( "옵션 선택 중 오류가 발생하였습니다." );
			}
		}
	}

	@Override
	public Product clone() {
		try {
			Product clone = ( Product ) super.clone();
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			clone.enable_options = new ArrayList<>( this.enable_options );
			clone.select_options = new ArrayList<>();

			return clone;
		}
		catch( CloneNotSupportedException e ) {
			throw new AssertionError();
		}
	}
}
