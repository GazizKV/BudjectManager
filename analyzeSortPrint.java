package budget;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static budget.Constants.*;
import static budget.SystemInOutUtil.readInputLine;

public class analyzeSortPrint {
    static void analyze() {
        while (true) {
            System.out.println(Constants.sortMenu);
            String inputNumber = readInputLine();
            switch (inputNumber) {
                case "1":
                    sortAndPrintAll();
                    break;
                case "2":
                    sortByType();
                    break;
                case "3":
                    sortByCertainType();
                    break;
                case "4":
                    return;
                default:
                    break;
            }
        }
    }

    static void sortByCertainType() {
        System.out.println(Constants.menuSortByCertainType);
        String inputLine = readInputLine();
        Map<String, Double> sortMap = null;
        List<String> sortByCertainType = new ArrayList<>();
        Double totalSum = 0d;
        String name;
        switch (inputLine) {
            case "1":
                sortMap = totalPurchases.get("food");
                name = "\nFood";
                break;
            case "2":
                sortMap = totalPurchases.get("clothes");
                name = "\nClothes";
                break;
            case "3":
                sortMap = totalPurchases.get("entertainment");
                name = "\nEntertainment";
                break;
            case "4":
                sortMap = totalPurchases.get("other");
                name = "\nOther";
                break;
            default:
                return;
        }
        if (sortMap.size() == 0) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }
        Formatter formatter = new Formatter();
        for (Map.Entry<String, Double> nextPair : sortMap.entrySet()) {
            formatter = new Formatter();
            String nextFormattedValue = formatter.format(Locale.ENGLISH, "%.2f", nextPair.getValue()).toString();
            sortByCertainType.add(nextPair.getKey() + " $" + nextFormattedValue);
            totalSum += nextPair.getValue();
        }
        sortByCertainType.sort(new SortByDouble());
        System.out.println(name + ":");
        for (String next : sortByCertainType) {
            System.out.println(next);
        }
        formatter = new Formatter();
        String formattedTotalSum = formatter.format("%.2f", totalSum).toString().replace(",", ".");
        System.out.println("Total sum: $" + formattedTotalSum);
    }

    static void sortByType() {
        List<String> listSortByType = new ArrayList<>();
        double sumType;
        double totalSum = 0;
        for (Map.Entry<String, Map<String, Double>> pair : totalPurchases.entrySet()) {
            sumType = 0;
            String nextType = (char) (pair.getKey().charAt(0) - 32) +
                    pair.getKey().substring(1) + " - $";
            if (pair.getValue().size() == 0) {
                listSortByType.add(nextType + 0);
                continue;
            }
            for (Double nextDouble : pair.getValue().values()) {
                sumType += nextDouble;
            }
            Formatter formatter = new Formatter();
            String sumTypeString = formatter.format("%.2f", sumType).toString().replace(",", ".");
            listSortByType.add(nextType + sumTypeString);
            totalSum += sumType;
        }
        Collections.sort(listSortByType, new SortByDouble());
        System.out.println("\nTypes:");
        for (String next : listSortByType) {
            System.out.println(next);
        }
        Formatter formatter = new Formatter();
        String totalStringSum = formatter.format("%.2f", totalSum).toString().replace(",", ".");
        System.out.println("Total sum: $" + totalStringSum);
    }

    static void sortAndPrintAll() {
        listOfPurchases.clear();
        AtomicReference<Double> sumAmount = new AtomicReference<>(0d);
        if (totalPurchases.values().stream().allMatch(Map::isEmpty)) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }
        for (Map<String, Double> nextMap : totalPurchases.values()) {
            nextMap.forEach((key, value) -> {
                sumAmount.updateAndGet(v -> v + value);
                Formatter formatter = new Formatter();
                String cost = String.valueOf(formatter.format("%.2f", value)).replace(",", ".");
                listOfPurchases.add(key + " $" + cost);
            });
        }
        listOfPurchases.sort(new SortByDouble());
        System.out.println("\nAll:");
        for (String nextStr : listOfPurchases) {
            System.out.println(nextStr);
        }
        System.out.println("Total: $" + sumAmount);
    }

}
