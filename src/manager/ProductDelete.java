package manager;

import kiosk.Product;

import java.util.Map;
import java.util.Scanner;

public class ProductDelete {
    static Scanner scan = new Scanner(System.in);

    public static void deleteMenu() { // 메뉴 삭제
        ManagerMain.mainMenu.forEach((key, value) -> { // 메인 메뉴 출력
            System.out.printf("ID%d. %-20s | %s\n", key, value[0], value[1]);
        });
        if (!ManagerMain.newMainMenu.isEmpty()) {
            ManagerMain.newMainMenu.forEach((key, value) -> { // 새로운 메인 메뉴 출력
                System.out.printf("ID%d. %-20s | %s\n", key, value[0], value[1]);
            });
        }
        System.out.print("삭제할 메뉴의 ID를 입력하세요: ");
        while (true) {
            int menuToDelete = scan.nextInt();
            if (ManagerMain.mainMenu.keySet().stream().anyMatch(key -> key.equals(menuToDelete))) {
                ManagerMain.mainMenu.keySet().removeIf(key -> key == menuToDelete);
                System.out.println("메뉴가 삭제되었습니다.");
                break;
            } else if (!ManagerMain.newMainMenu.isEmpty()) {
                if (ManagerMain.newMainMenu.keySet().stream().anyMatch(key -> key.equals(menuToDelete))) {
                    ManagerMain.newMainMenu.keySet().removeIf(key -> key == menuToDelete);
                    System.out.println("메뉴가 삭제되었습니다.");
                    break;
                } else {
                    System.out.println("해당 메뉴가 존재하지 않습니다. 다시 입력해주세요: ");
                }
            } else {
                System.out.println("해당 메뉴가 존재하지 않습니다. 다시 입력해주세요: ");
            }
        }
    }

    public static void deleteProduct() { // 상품 삭제
        ManagerMain.mainMenu.forEach((key, value) -> { // 메인 메뉴 출력
            System.out.printf("ID%d. %-20s | %s\n", key, value[0], value[1]);
        });
        if (!ManagerMain.newMainMenu.isEmpty()) {
            ManagerMain.newMainMenu.forEach((key, value) -> { // 새로운 메인 메뉴 출력
                System.out.printf("ID%d. %-20s | %s\n", key, value[0], value[1]);
            });
        }
        System.out.print("삭제할 상품이 든 메뉴 ID를 선택하세요: ");
        while (true) {
            int menuToProduct = scan.nextInt();
            if (menuToProduct == 9000) { // 기존 메뉴 선택
                selectProduct(ManagerMain.burgerMap);
            } else if (menuToProduct == 9001) {
                selectProduct(ManagerMain.frozenCustardMap);
            } else if (menuToProduct == 9002) {
                selectProduct(ManagerMain.drinkMap);
            } else if (menuToProduct == 9003) {
                selectProduct(ManagerMain.beerMap);
            } else {
                for (int i = 0; i < ProductCreation.menuArrayList.size(); i++) {
                    if (ProductCreation.menuArrayList.get(i).getMenuID() == menuToProduct) { // 같은 id 값을 가지고 있는 menuArrayList의 newProductMap 출력
                        selectProduct(ProductCreation.menuArrayList.get(i).newProductMap);
                    }
                }
            }
            System.out.println("잘못된 메뉴 ID입니다. 다시 입력해주세요: ");
        }
    }

    private static void selectProduct(Map<Integer, Product> pl) { // 삭제할 상품 선택
        for (var entry : pl.entrySet()) {
            int key = entry.getKey();
            Product p = entry.getValue();
            System.out.printf("ID%d. %-20s | W %4.1f | %s\n", key, p.getName(), p.getPrice() / 1000.0, p.getDescription());
        }
        System.out.print("\n삭제할 상품 ID를 입력해 주세요 : ");
        while(true) {
            int productToDelete = scan.nextInt();
            System.out.println();
            if (pl.containsKey(productToDelete)) {
                pl.remove(productToDelete); // 해당 키값 골라서 삭제
                break;
            } else {
                System.out.println("잘못된 상품 ID입니다. 다시 입력해주세요: ");
            }
        }
    }
}