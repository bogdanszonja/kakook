package com.codecool.kakook.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    private static String fileName = "/kakook/src/resources/test_questions";

    public static List<String> readFile() {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
            Stream <String> lines = br.lines();
            return lines.collect(Collectors.toList());
        } catch (IOException io) {
            io.printStackTrace();
        }
        return null;
    }
}
