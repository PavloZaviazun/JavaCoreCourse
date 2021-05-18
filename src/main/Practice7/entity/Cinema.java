package main.Practice7.entity;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List <Movie> movieList;

    public List <Movie> getMovieList() {
        if(movieList == null) movieList = new ArrayList <>();
        return movieList;
    }

    @Override
    public String toString() {
        return "Cinema{" + "cinema=" + movieList + '}';
    }
}
