package com.tree.algorithm;

import java.util.List;


public class RecursiveAlgorithm {

    private final List<List<Integer>> triangle;
    private final Integer[][] memory;

    public RecursiveAlgorithm(List<List<Integer>> triangle) {
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
        boolean isLeftValueEven = triangle.get(rowNumber + 1).get(index) % 2 == 0;
        boolean isRightValueEven = triangle.get(rowNumber + 1).get(index + 1) % 2 == 0;
        Integer nextMaxValue;

        if (currentValue % 2 != 0) {
            nextMaxValue = getMaxValueIfEven(rowNumber, index, isLeftValueEven, isRightValueEven);
        } else {
            nextMaxValue = getMaxValueIfOdd(rowNumber, index, isLeftValueEven, isRightValueEven);
        }
        memory[rowNumber][index] = currentValue + nextMaxValue;
        return memory[rowNumber][index];
    }

    private Integer getMaxValueIfOdd(int rowNumber, int index, boolean isLeftValueEven, boolean isRightValueEven) {
        return getMaxValueIfEven(rowNumber, index, !isLeftValueEven, !isRightValueEven);
    }

    private Integer getMaxValueIfEven(int rowNumber, int index, boolean isLeftTrue, boolean isRightTrue) {
        if (isLeftTrue && isRightTrue) {
            return Math.max(findMaxSum(rowNumber + 1, index), findMaxSum(rowNumber + 1, index + 1));
        } else if (isLeftTrue) {
            return findMaxSum(rowNumber + 1, index);
        } else if (isRightTrue) {
            return findMaxSum(rowNumber + 1, index + 1);
        } else {
            return 0;
        }
    }

}
