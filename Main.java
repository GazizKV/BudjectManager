package budget;

import java.io.IOException;
import java.util.Map;

import static budget.Constants.*;
import static budget.FileIOUtil.loadFromFile;
import static budget.FileIOUtil.saveToFile;
import static budget.SystemInOutUtil.*;
import static budget.analyzeSortPrint.analyze;

public class Main {

    public static void main(String[] args) {

        String inputLine;

        while (true) {
            System.out.println(menu);
            inputLine = readInputLine();
            switch (inputLine) {
                case "1":
                    income();
                    break;
                case "2":
                    addPurchase();
                    break;
                case "3":
                    showPurchaseList();
                    break;
                case "4":
                    balance();
                    break;
                case "5":
                    saveToFile();
                    break;
                case "6":
                    loadFromFile();
                    break;
                case "7":
                    analyze();
                    break;
                case "0":
                    exitProgram();
            }
        }
    }


    private static void exitProgram() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        totalPurchases.clear();
        System.out.println("\nBye!");
        System.exit(0);
    }

    private static void balance() {
        System.out.printf("\nBalance: $%.2f\n", balance);
    }

    private static void showPurchaseList() {
        if (totalPurchases.values().stream().allMatch(Map::isEmpty)) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }
        while (true) {
            System.out.println(showPurchaseMenu);
            String inputFoodLine = readInputLine();
            switch (inputFoodLine) {
                case "1":
                    System.out.println("\nFood:");
                    printListWithSum("food");
                    break;
                case "2":
                    System.out.println("\nClothes");
                    printListWithSum("clothes");
                    break;
                case "3":
                    System.out.println("\nEntertainment:");
                    printListWithSum("entertainment");
                    break;
                case "4":
                    System.out.println("\nOther");
                    printListWithSum("other");
                    break;
                case "5":
                    System.out.println("\nAll:");
                    printAllPurchase();
                    break;
                case "6":
                default:
                    return;
            }
        }
    }


    private static void addPurchase() {
        while (true) {
            System.out.println(foodMenu);
            String typePurchase = readInputLine();
            Map<String, Double> purchases = null;
            switch (typePurchase) {
                case "1":
                    purchases = totalPurchases.get("food");
                    break;
                case "2":
                    purchases = totalPurchases.get("clothes");
                    break;
                case "3":
                    purchases = totalPurchases.get("entertainment");
                    break;
                case "4":
                    purchases = totalPurchases.get("other");
                    break;
                case "5":
                    return;
                default:
                    break;
            }
            String name;
            double cost;
            System.out.println("\nEnter purchase name:");
            name = readInputLine();
            System.out.println("Enter its price:");
            cost = Double.parseDouble(readInputLine());
            balance -= cost;
            assert purchases != null;
            purchases.put(name, cost);
            System.out.println("\nPurchase was added!");
        }
    }

    private static void income() {
        System.out.println("\nEnter income");
        double income = Double.parseDouble(readInputLine());
        balance += income;
        System.out.println("Income was added!");
    }
}
