package kiosk;

import java.util.*;

public class Menu {
	String name;
	String description;

	public Map< Integer, Product > productList = new LinkedHashMap<>();

	public Menu( String name, String desc ) {
		this.name = name;
		this.description = desc;
	}

	public String getName() { return name; }
	public String getDescription() { return description; }
}
