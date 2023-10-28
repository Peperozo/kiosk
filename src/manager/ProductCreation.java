package manager;

import kiosk.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ProductCreation {
    private String mName; // 메뉴명
    private String newMenuDesc; // 메뉴 설명
    private String proName; // 상품명
    private String proPrice; // 상품 가격
    private String proDesc; // 상품 설명

    HashMap<Integer, Product> newProductMap = new HashMap<>();
    List<HashMap<Integer, Product>> newProductList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    void createProduct() {
        System.out.println("새로운 상품정보 입력");
        System.out.print("메뉴명 : ");
        mName = scan.nextLine();

        for (String[] values : ManagerMain.mainMenu.values()) {
            if (values[0].equals(mName)) { // 기존 메뉴일 경우
                inputProduct(); // 상품 입력
                addProExisting(); // 기존 메뉴에 상품 추가
                return;
            }
        }

        System.out.print("새로운 메뉴명입니다. 메뉴 설명을 입력해주세요.\n: ");
        newMenuDesc = scan.nextLine();
        inputProduct(); // 상품 입력
        addProNew(); // 신규 메뉴에 상품 추가
    }

    private void inputProduct(){ // 상품 입력
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

    private void addProExisting(){
        switch (mName){
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

    private void addProNew(){
        kiosk.Menu newMenu = new kiosk.Menu( mName, newMenuDesc );
        newMenu.productList.put(ManagerMain.productID++, new Product(proName, Integer.parseInt(proPrice), proDesc));
        ManagerMain.newMainMenu.put( ManagerMain.menuId, newMenu );
    }
}
