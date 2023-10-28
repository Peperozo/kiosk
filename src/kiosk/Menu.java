package kiosk;

import java.util.*;

public class Menu {
	int id; // 메뉴 id
	String name; // 메뉴 이름
	String proName, proPrice, proDesc; // 상품

	public Map< Integer, Product > newProductMap = new LinkedHashMap<>();

	public Menu( int id, String name, String proName, String proPrice, String proDesc ) {
		this.name = name;
		this.id = id;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDesc = proDesc;
	}

	public String getName(){
		return name;
	}

	public int getMenuID(){
		return id;
	}
}