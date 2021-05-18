package Practice7;

import main.Practice7.entity.Cinema;
import main.Practice7.entity.Movie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CinemaTest {

    @Test
    public void cinemaObject() {
        Cinema cinema = new Cinema();
        assertEquals("Cinema{cinema=null}", cinema.toString());
    }

    @Test
    public void cinemaGetMovieList() {
        Cinema cinema = new Cinema();
        cinema.getMovieList().add(new Movie(1, "Test", 2021, Movie.Genre.HORROR));
        assertEquals("[Movie{id=1, title='Test', year=2021, genre=HORROR}]", cinema.getMovieList().toString());
    }
}
