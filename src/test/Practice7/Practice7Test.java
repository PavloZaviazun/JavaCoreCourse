package Practice7;

import main.Practice7.controller.DomController;
import main.Practice7.controller.SaxController;
import main.Practice7.controller.StaxController;
import main.Practice7.entity.Movie;
import main.Practice7.util.Sorter;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Practice7Test {
    private String separator = System.lineSeparator();
    private String result = "[Movie{id=1, title='\"The Shawshank Redemption\"', year=1994, genre=HORROR}," +
            " Movie{id=2, title='\"The Godfather\"', year=1972, genre=COMEDY}, Movie{id=3, title='\"The Dark Knight\"'," +
            " year=2008, genre=THRILLER}]";
    private String sorted = "[Movie{id=3, title='\"The Dark Knight\"', year=2008, genre=THRILLER}," +
            " Movie{id=1, title='\"The Shawshank Redemption\"', year=1994, genre=HORROR}," +
            " Movie{id=2, title='\"The Godfather\"', year=1972, genre=COMEDY}]";

    @Test
    public void saxReaderTest() {
        SaxController saxController = new SaxController();
        saxController.saxReader(new String[] {"input.xml"});
        assertEquals(result, saxController.getList().toString());
    }

    @Test
    public void staxReaderTest() {
        StaxController staxController = new StaxController();
        staxController.staxReader(new String[] {"input.xml"});
        assertEquals(result, staxController.getList().toString());
    }

    @Test
    public void domReaderTest() {
        DomController domController = new DomController();
        domController.domReader(new String[] {"input.xml"});
        assertEquals(result, domController.getList().toString());
    }

    @Test
    public void sortList() {
        StaxController staxController = new StaxController();
        staxController.staxReader(new String[] {"input.xml"});
        List <Movie> sortedList = Sorter.sortList(staxController.getList());
        assertEquals(sorted, sortedList.toString());
    }

}
