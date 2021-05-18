package main.Practice6.part4;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{
    private Integer[] array;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        array = new Integer[(secBound - firstBound + 1)];
        int count = 0;
        for(int i = firstBound; i <= secBound; i++) {
            array[count] = i;
            count++;
        }
        if(reversedOrder) {
            Collections.reverse(Arrays.asList(array));
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        @Override
        public Integer next() {
            if(array.length <= currentIndex) throw new NoSuchElementException();
            return array[currentIndex++];
        }

    }

}

