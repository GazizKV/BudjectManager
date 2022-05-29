package budget;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    static Double balance = 0d;
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static final Map<String, Double> food = new HashMap<>();
    static final Map<String, Double> clothes = new HashMap<>();
    static final Map<String, Double> entertainment = new HashMap<>();
    static final Map<String, Double> other = new HashMap<>();
    static List<String> listOfPurchases = new ArrayList<>();
    static final Map<String, Map<String, Double>> totalPurchases = new HashMap<>();
    static final File file = new File("purchases.txt");

    static {
        totalPurchases.put("food", food);
        totalPurchases.put("clothes", clothes);
        totalPurchases.put("entertainment", entertainment);
        totalPurchases.put("other", other);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final String menuSortByCertainType = "\nChoose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other";

    static final String menu = "\nChoose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "5) Save\n" +
            "6) Load\n" +
            "7) Analyze (Sort)\n" +
            "0) Exit";

    static final String showPurchaseMenu = "\nChoose the type of purchases\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) All\n" +
            "6) Back";

    static final String foodMenu = "\nChoose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) Back";

    static final String sortMenu = "\nHow do you want to sort?\n" +
            "1) Sort all purchases\n" +
            "2) Sort by type\n" +
            "3) Sort certain type\n" +
            "4) Back";


}
