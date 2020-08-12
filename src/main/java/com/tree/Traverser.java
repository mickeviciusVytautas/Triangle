package com.tree;

import java.util.Arrays;
import java.util.List;

public class Traverser {
    private Integer[][] memory = new Integer[1000][1000];

    public Integer findMaxPathDP(List<List<Integer>> triangle, int rowNumber, int index) {
        int[][] dp = new int[triangle.size()][triangle.size()];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 0; i < triangle.size(); i++) {

            List<Integer> row = triangle.get(i);

            for (int j = 0; j < row.size(); j++) {
                int currentValue = row.get(j);
                if (i != triangle.size() - 1) {
                    int leftValue = triangle.get(i + 1).get(j);
                    int rightValue = triangle.get(i + 1).get(j + 1);
                    int sumLeft = currentValue + leftValue;
                    int sumRight = currentValue + rightValue;
                    dp[i + 1][j] = sumLeft;
                    dp[i + 1][j + 1] = sumRight;
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public Integer calculateWithMemory(List<List<Integer>> triangle, int rowNumber, int index) {
        List<Integer> row = triangle.get(rowNumber);

        // last row - only one value available
        if (rowNumber == triangle.size() - 1) {
            return row.get(index);
        }

        Integer cachedValue = memory[rowNumber][index];
        if (cachedValue == null) {
            Integer currentValue = row.get(index);
            Integer leftValue = triangle.get(rowNumber + 1).get(index);
            Integer rightValue = triangle.get(rowNumber + 1).get(index + 1);
            Integer maxLeftValue = calculateWithMemory(triangle, rowNumber + 1, index);
            Integer maxRightValue = calculateWithMemory(triangle, rowNumber + 1, index + 1);
            if (currentValue % 2 == 0) {
                if (leftValue % 2 == 1 && rightValue % 2 == 1) {
                    return currentValue + Math.max(maxLeftValue, maxRightValue);
                } else if (leftValue % 2 == 1) {
                    return currentValue + maxLeftValue;
                } else if (rightValue % 2 == 1) {
                    return currentValue + maxRightValue;
                } else {
                    return 0;
                }
            } else {
                if (leftValue % 2 == 0 && rightValue % 2 == 0) {
                    return currentValue + Math.max(maxLeftValue, maxRightValue);
                } else if (leftValue % 2 == 0) {
                    return currentValue + maxLeftValue;
                } else if (rightValue % 2 == 0) {
                    return currentValue + rightValue;
                } else {
                    return 0;
                }
            }
        }
        return cachedValue;
    }

    public Integer calculateMaxSum(List<List<Integer>> triangle, int rowNumber, int index) {
        List<Integer> row = triangle.get(rowNumber);

        Integer currentValue = row.get(index);
        // last row - only one value available
        if (rowNumber == triangle.size() - 1) {
            return currentValue;
        }

        Integer leftValue = triangle.get(rowNumber + 1).get(index);
        Integer rightValue = triangle.get(rowNumber + 1).get(index + 1);
        Integer maxLeftValue = calculateMaxSum(triangle, rowNumber + 1, index);
        Integer maxRightValue = calculateMaxSum(triangle, rowNumber + 1, index + 1);
        if (currentValue % 2 == 0) {
            if (leftValue % 2 == 1 && rightValue % 2 == 1) {
                return currentValue + Math.max(maxLeftValue, maxRightValue);
            } else if (leftValue % 2 == 1) {
                return currentValue + maxLeftValue;
            } else if (rightValue % 2 == 1) {
                return currentValue + maxRightValue;
            } else {
                return 0;
            }
        } else {
            if (leftValue % 2 == 0 && rightValue % 2 == 0) {
                return currentValue + Math.max(maxLeftValue, maxRightValue);
            } else if (leftValue % 2 == 0) {
                return currentValue + maxLeftValue;
            } else if (rightValue % 2 == 0) {
                return currentValue + rightValue;
            } else {
                return 0;
            }
        }
    }
}
