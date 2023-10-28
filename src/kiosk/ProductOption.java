package kiosk;

public class ProductOption implements Cloneable {
	// 패티, 머스타드, 치즈 등등 제품에 추가 할 수 있는 옵션.
	private final int price;
	public int getPrice() {
		return price;
	}
	private String name;
	public String getName() {
		return name;
	}
	private String description;
	public String getDescription() {
		return description;
	}

	public ProductOption( String name, int price, String description ) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	@Override
	public ProductOption clone() {
		try {
			ProductOption clone = ( ProductOption ) super.clone();
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return clone;
		}
		catch( CloneNotSupportedException e ) {
			throw new AssertionError();
		}
	}
}
