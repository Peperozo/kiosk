package manager;

import kiosk.Menu;
import kiosk.Product;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCreation {
    private static String menuName; // 메뉴명
    private static String proName; // 상품명
    private static String proPrice; // 상품 가격
    private static String proDesc; // 상품 설명
    public static ArrayList<Menu> menuArrayList = new ArrayList<>(); // Menu 클래스를 저장
    static Scanner scan = new Scanner(System.in);

    static void createProduct() {
        AtomicInteger menuNum = new AtomicInteger(1);
        System.out.println();
        ManagerMain.mainMenu.forEach((key, value) -> { // 메인 메뉴 출력
            System.out.printf("%d. %-20s | %s\n", menuNum.getAndIncrement(), value[0], value[1]);
        });
        if(!ManagerMain.newMainMenu.isEmpty()) {
            ManagerMain.newMainMenu.forEach((key, value) -> { // 새로운 메인 메뉴 출력
                System.out.printf("%d. %-20s | %s\n", menuNum.getAndIncrement(), value[0], value[1]);
            });
        }
        System.out.println();

        System.out.println("새로운 상품정보 입력");
        System.out.print("메뉴명 : ");
        menuName = scan.nextLine();

        for (String[] values : ManagerMain.mainMenu.values()) {
            if (values[0].equals(menuName)) { // 기존 메뉴일 경우
                inputProduct(); // 상품 입력
                addProExisting(); // 기존 메뉴에 상품 추가
                return;
            }
        }

        if(!ManagerMain.newMainMenu.isEmpty()){ // 입력한 메뉴가 새메뉴에 이미 존재할 경우
            for (int i=0; i<menuArrayList.size(); i++){
                if(menuName.equals(menuArrayList.get(i).getName())){
                    inputProduct(); // 상품 입력
                    // 리스트 i번째 요소의 newProductMap 에 값 추가
                    menuArrayList.get(i).newProductMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
                    return;
                }
            }
        }

        System.out.print("새로운 메뉴명입니다. 메뉴 설명을 입력해주세요.\n: ");
        // 메뉴 설명
        String newMenuDesc = scan.nextLine();
        inputProduct(); // 상품 입력
        ManagerMain.newMainMenu.put( ManagerMain.menuId, new String[]{menuName, newMenuDesc}); // 새로운 메뉴 추가
        menuArrayList.add(new Menu(ManagerMain.menuId++, menuName, proName, proPrice, proDesc)); // menuArrayList에 새로운 Menu 인스턴스 추가
        // 리스트 마지막 요소의 newProductMap 에 값 추가
        menuArrayList.get(menuArrayList.size()-1).newProductMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
    }

    private static void inputProduct(){ // 상품 입력
        System.out.print("상품 이름 : ");
        proName = scan.nextLine();
        System.out.print("상품 가격 : ");
        do {
            proPrice = scan.nextLine();
        } while (!isDouble(proPrice));
        System.out.print("상품 설명 : ");
        proDesc = scan.nextLine();
    }

    private static boolean isDouble(String strValue) {
        try {
            Double.parseDouble(strValue);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("가격을 숫자로 입력하세요.");
            return false;
        }
    }

    private static void addProExisting(){
        switch (menuName){
            case "Burgers":
                ManagerMain.burgerMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
                break;
            case "Frozen Custard":
                ManagerMain.frozenCustardMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
                break;
            case "Drinks":
                ManagerMain.drinkMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
                break;
            case "Beer":
                ManagerMain.beerMap.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
                break;
            default: {
                System.out.println( "잘못된 메뉴명을 입력 하셨습니다." );
                break;
            }
        }
    }
}
