package com.tree;

import java.util.List;

import static com.tree.IterativeSum.getMaxValueIfEven;
import static com.tree.IterativeSum.getMaxValueIfOdd;

public class Recursive {

    private final List<List<Integer>> triangle;
    private final Integer[][] memory;

    public Recursive(List<List<Integer>> triangle) {
        this.triangle = triangle;
        this.memory = new Integer[triangle.size()][triangle.size()];
    }

    public Integer findMaxSum(int rowNumber, int index) {
        List<Integer> row = triangle.get(rowNumber);

        if (rowNumber == triangle.size() - 1) {
            return row.get(index);
        }

        Integer cachedValue = memory[rowNumber][index];
        if (cachedValue != null) {
            return cachedValue;
        }

        Integer currentValue = row.get(index);
        Integer maxLeftValue = findMaxSum(rowNumber + 1, index);
        Integer maxRightValue = findMaxSum(rowNumber + 1, index + 1);

        boolean isLeftValueEven = triangle.get(rowNumber + 1).get(index) % 2 == 0;
        boolean isRightValueEven = triangle.get(rowNumber + 1).get(index + 1) % 2 == 0;

        if (currentValue % 2 == 0) {
            memory[rowNumber][index] = currentValue + getMaxValueIfOdd(maxLeftValue, maxRightValue, isLeftValueEven, isRightValueEven);
        } else {
            memory[rowNumber][index] = currentValue + getMaxValueIfEven(maxLeftValue, maxRightValue, isLeftValueEven, isRightValueEven);
        }
        return memory[rowNumber][index];
    }

}
