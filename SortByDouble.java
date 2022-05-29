package budget;

import java.util.Comparator;

public class SortByDouble implements Comparator<String>, Comparable<String> {

    @Override
    public int compare(String first, String second) {
        double firstNumber = 0, secondNumber = 0;
        try {
            firstNumber = Double.parseDouble(first.substring(first.lastIndexOf("$") + 1));
            secondNumber = Double.parseDouble(second.substring(second.lastIndexOf("$") + 1));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        double diff = firstNumber - secondNumber;
        if (diff > 0) {
            return -1;
        } else if (diff < 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int compareTo(String s) {
        return 0;
    }
}

