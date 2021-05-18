package Practice6.part3;

import main.Practice6.part3.Parking;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ParkingTest {
    @Test
    public void main() {
        Parking parking = new Parking(4);
        PrintStream outInitial = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        assertTrue(parking.arrive(2));
        assertTrue(parking.arrive(3));
        assertTrue(parking.arrive(2));
        assertTrue(parking.arrive(2));
        assertFalse(parking.arrive(2));
        assertTrue(parking.depart(1));
        assertFalse(parking.depart(1));
        parking.print();
        assertEquals("1011" + System.lineSeparator(), baos.toString());
        System.setOut(outInitial);
    }
}
