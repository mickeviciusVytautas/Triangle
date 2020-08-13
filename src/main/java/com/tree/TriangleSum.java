package com.tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleSum {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> triangle = buildTriangle();

        // Iterative solution
        IterativeSum iterativeSum = new IterativeSum();
        int answer = iterativeSum.findHighestValidSum(triangle);
        System.out.println(answer);

        // Recursive solution
        Recursive recursive = new Recursive(triangle);
        int recAnswer = recursive.findMaxSum(0, 0);
        System.out.println(recAnswer);
    }

    private static List<List<Integer>> buildTriangle() throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/triangle.txt"))) {
            return lines
                    .map(line -> Arrays
                            .stream(line.split(" "))
                            .mapToInt(Integer::parseInt)
                            .boxed()
                            .collect(Collectors.toList()))
                    .collect(Collectors.toCollection(LinkedList::new));
        }
    }

}
