package com.tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecursiveTriangleSum {

    public static void main(String[] args) throws IOException {
        long before = System.currentTimeMillis();
        List<List<Integer>> triangle = Files.lines(Paths.get("src/main/resources/triangle.txt"))
                .map(line -> Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(new Traverser().findMaxPathDP(triangle, 0, 0));
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

}
