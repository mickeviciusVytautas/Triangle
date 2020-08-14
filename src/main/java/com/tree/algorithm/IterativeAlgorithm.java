package com.tree.algorithm;

import java.util.List;

public class IterativeAlgorithm {

    public Integer findMaxSum(List<List<Integer>> triangle) {
        int[][] table = new int[triangle.size()][triangle.size()];
        putLastRowToTable(triangle, table);
        traverseTriangleAndSum(triangle, table);
        return table[0][0];
    }

    private void putLastRowToTable(List<List<Integer>> triangle, int[][] table) {
        int lastRowNum = triangle.size() - 1;
        List<Integer> lastRow = triangle.get(lastRowNum);
        for (int i = 0; i < lastRow.size() - 1; i++) {
            table[lastRowNum][i] = lastRow.get(i);
        }
    }

    private void traverseTriangleAndSum(List<List<Integer>> triangle, int[][] table) {
        for (int rowNum = triangle.size() - 2; rowNum > -1; rowNum--) {
            traverseRow(triangle, table, rowNum);
        }
    }

    private void traverseRow(List<List<Integer>> triangle, int[][] table, int rowNum) {
        List<Integer> row = triangle.get(rowNum);
        for (int index = 0; index < row.size(); index++) {
            int currentValue = row.get(index);
            int maxSumLeft = table[rowNum + 1][index];
            int maxSumRight = table[rowNum + 1][index + 1];
            boolean isLeftValueEven = triangle.get(rowNum + 1).get(index) % 2 == 0;
            boolean isRightValueEven = triangle.get(rowNum + 1).get(index + 1) % 2 == 0;
            if (currentValue % 2 != 0) {
                table[rowNum][index] = currentValue + getMaxValueIfEven(maxSumLeft, maxSumRight, isLeftValueEven, isRightValueEven);
            } else {
                table[rowNum][index] = currentValue + getMaxValueIfOdd(maxSumLeft, maxSumRight, isLeftValueEven, isRightValueEven);
            }
        }
    }

    protected static int getMaxValueIfOdd(int maxSumLeft, int maxSumRight, boolean isLeftValueEven, boolean isRightValueEven) {
        return getMaxValueIfEven(maxSumLeft, maxSumRight, !isLeftValueEven, !isRightValueEven);
    }

    protected static int getMaxValueIfEven(int maxSumLeft, int maxSumRight, boolean isLeftTrue, boolean isRightTrue) {
        if (isLeftTrue && isRightTrue) {
            return Math.max(maxSumLeft, maxSumRight);
        } else if (isLeftTrue) {
            return maxSumLeft;
        } else if (isRightTrue) {
            return maxSumRight;
        } else {
            return 0;
        }
    }

}
