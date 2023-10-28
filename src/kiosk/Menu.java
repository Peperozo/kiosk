package kiosk;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	String name;
	String description;

	public List< Product > productList = new ArrayList<>();

	public String getName() { return name; }
	public String getDescription() { return description; }
}
