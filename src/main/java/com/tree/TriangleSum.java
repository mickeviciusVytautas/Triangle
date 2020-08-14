package com.tree;

import com.tree.algorithm.IterativeAlgorithm;
import com.tree.algorithm.RecursiveAlgorithm;

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
        IterativeAlgorithm iterativeAlgorithm = new IterativeAlgorithm();
        int answer = iterativeAlgorithm.findMaxSum(triangle);
        System.out.println(answer);

        // Recursive solution
        RecursiveAlgorithm recursiveAlgorithm = new RecursiveAlgorithm(triangle);
        int recAnswer = recursiveAlgorithm.findMaxSum(0, 0);
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
