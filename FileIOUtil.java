package budget;

import java.io.*;
import java.util.Map;

import static budget.Constants.*;
import static budget.Constants.file;

public class FileIOUtil {

    static void loadFromFile() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            String nextLine;
            String nextPurchase;
            String typeName;
            int numberOfPurchase;
            String namePurchase;
            double costPurchase;
            while (reader.ready()) {
                nextLine = reader.readLine();
                if (nextLine.contains("balance")) {
                    balance = Double.parseDouble(nextLine.split(" ")[1]);
                    break;
                }
                typeName = nextLine.substring(0, nextLine.lastIndexOf(" "));
                numberOfPurchase = Integer.parseInt(nextLine.substring(nextLine.lastIndexOf(" ") + 1));
                for (int i = 0; i < numberOfPurchase; i++) {
                    nextPurchase = reader.readLine();
                    namePurchase = nextPurchase.substring(0, nextPurchase.lastIndexOf(" "));
                    String[] names = nextPurchase.split(" ");
                    costPurchase = Double.parseDouble(names[names.length - 1]);
                    totalPurchases.get(typeName).put(namePurchase, costPurchase);
                }
            }
            System.out.println("\nPurchases were loaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void saveToFile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Map<String, Double>> nextPair : totalPurchases.entrySet()) {
            stringBuilder
                    .append(nextPair.getKey())
                    .append(" ")
                    .append(nextPair.getValue().size())
                    .append("\n");
            for (Map.Entry<String, Double> nextMap : nextPair.getValue().entrySet()) {
                stringBuilder
                        .append(nextMap.getKey())
                        .append(" ")
                        .append(nextMap.getValue())
                        .append("\n");
            }
        }
        stringBuilder
                .append("balance")
                .append(" ")
                .append(balance)
                .append("\n");
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(file))
        ) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nPurchase where saved!");
    }
}
