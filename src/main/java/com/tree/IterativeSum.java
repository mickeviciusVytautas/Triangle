package com.tree;

import java.util.List;

public class IterativeSum {

    public Integer findHighestValidSum(List<List<Integer>> triangle) {
        int[][] table = new int[triangle.size()][triangle.size()];
        putLastRowToTable(triangle, table);
        traverseTriangle(triangle, table);
        return table[0][0];
    }

    private void putLastRowToTable(List<List<Integer>> triangle, int[][] table) {
        int lastRowNumber = triangle.size() - 1;
        List<Integer> lastRow = triangle.get(lastRowNumber);
        for (int i = 0; i < lastRow.size() - 1; i++) {
            table[lastRowNumber][i] = lastRow.get(i);
        }
    }

    private void traverseTriangle(List<List<Integer>> triangle, int[][] table) {
        for (int i = triangle.size() - 2; i > -1; i--) {
            traverseRow(triangle, table, i);
        }
    }

    private void traverseRow(List<List<Integer>> triangle, int[][] table, int rowNum) {
        List<Integer> row = triangle.get(rowNum);
        for (int j = 0; j < row.size(); j++) {
            int currentValue = row.get(j);
            int maxSumLeft = table[rowNum + 1][j];
            int maxSumRight = table[rowNum + 1][j + 1];
            boolean isLeftValueEven = triangle.get(rowNum + 1).get(j) % 2 == 0;
            boolean isRightValueEven = triangle.get(rowNum + 1).get(j + 1) % 2 == 0;
            if (currentValue % 2 == 0) {
                table[rowNum][j] = currentValue + getMaxValueIfOdd(maxSumLeft, maxSumRight, isLeftValueEven, isRightValueEven);
            } else {
                table[rowNum][j] = currentValue + getMaxValueIfEven(maxSumLeft, maxSumRight, isLeftValueEven, isRightValueEven);
            }
        }
    }

    protected static int getMaxValueIfOdd(int maxSumLeft, int maxSumRight, boolean leftValueEven, boolean rightValueEven) {
        return getMaxValueIfEven(maxSumLeft, maxSumRight, !leftValueEven, !rightValueEven);
    }

    protected static int getMaxValueIfEven(int maxSumLeft, int maxSumRight, boolean leftValueEven, boolean rightValueEven) {
        if (leftValueEven && rightValueEven) {
            return Math.max(maxSumLeft, maxSumRight);
        } else if (leftValueEven) {
            return maxSumLeft;
        } else if (rightValueEven) {
            return maxSumRight;
        } else {
            return 0;
        }
    }

}
