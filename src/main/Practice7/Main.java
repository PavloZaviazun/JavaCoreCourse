package main.Practice7;

import main.Practice7.controller.DomController;
import main.Practice7.controller.SaxController;
import main.Practice7.controller.StaxController;
import main.Practice7.entity.Movie;
import main.Practice7.util.Sorter;

import java.util.List;

public final class Main {

    public static void main(final String[] args) {
        SaxController saxController = new SaxController();
        saxController.saxReader(args);
        List<Movie> sortedList1 = Sorter.sortList(saxController.getList());
        SaxController.saxWriter(sortedList1, "output.sax.xml");
        DomController domController = new DomController();
        domController.domReader(args);
        List<Movie> sortedList2 = Sorter.sortList(domController.getList());
        SaxController.saxWriter(sortedList2, "output.dom.xml");
        StaxController staxController = new StaxController();
        staxController.staxReader(args);
        List<Movie> sortedList3 = Sorter.sortList(staxController.getList());
        SaxController.saxWriter(sortedList3, "output.stax.xml");
    }

}
