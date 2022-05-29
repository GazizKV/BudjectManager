package budget;

import java.io.IOException;
import java.util.Map;

import static budget.Constants.reader;
import static budget.Constants.totalPurchases;

public class SystemInOutUtil {
    public static String readInputLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void printAllPurchase() {
        double sum = 0;
        for (Map<String, Double> nextMap : totalPurchases.values()) {
            for (Map.Entry<String, Double> nextPurchase : nextMap.entrySet()) {
                System.out.printf("%s $%.2f\n", nextPurchase.getKey(), nextPurchase.getValue());
                sum += nextPurchase.getValue();
            }
        }
        System.out.printf("Total sum: $%.2f\n", sum);
    }

    static void printListWithSum(String key) {
        double sum = 0;
        Map<String, Double> mapToOutput = totalPurchases.get(key);
        if (mapToOutput.isEmpty()) {
            System.out.println("The purchase empty!");
        } else {
            for (Map.Entry<String, Double> nextPurchase : totalPurchases.get(key).entrySet()) {
                System.out.printf("%s $%.2f\n", nextPurchase.getKey(), nextPurchase.getValue());
                sum += nextPurchase.getValue();
            }
            System.out.printf("Total sum: $%.2f\n", sum);
        }
    }
}
