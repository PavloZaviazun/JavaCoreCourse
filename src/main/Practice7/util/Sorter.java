package main.Practice7.util;

import main.Practice7.entity.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class Sorter {

    private Sorter() {
        throw new UnsupportedOperationException();
    }

    public static List<Movie> sortList(List<Movie> list) {
        return list.stream().sorted((a, b) -> b.getYear() - a.getYear()).collect(Collectors.toList());
    }
}
