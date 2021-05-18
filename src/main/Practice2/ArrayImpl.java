package main.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] array;
    private int size = 0;

    public ArrayImpl(int capacity) {
        this.array = new Object[capacity];
    }

    @Override
    public void clear() {
        this.array = new Object[0];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < array.length;
        }

        @Override
        public Object next() {
            if(array.length <= currentIndex) throw new NoSuchElementException();
            return array[currentIndex++];
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(--currentIndex);
        }

    }

    @Override
    public void add(Object element) {
        if(size == array.length) {
            Object[] arrayUpdated = new Object[array.length + 1];
            System.arraycopy(array, 0, arrayUpdated, 0, array.length);
            arrayUpdated[array.length] = element;
            array = arrayUpdated;
        } else {
            array[size] = element;
        }
        size++;
    }

    @Override
    public void set(int index, Object element) {
        array[index] = element;
    }

    @Override
    public Object get(int index) {
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        for(int i = 0; i < size; i++) {
            if(array[i] != null && array[i].equals(element)) return i;
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        Object[] arrayUpdated = new Object[array.length - 1];
        int j = 0;
        for(int i = 0; i < array.length; i++) {
            if(i != index) {
                arrayUpdated[j] = array[i];
                j++;
            }
        }
        array = arrayUpdated;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i < array.length - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayImpl arrayImpl = new ArrayImpl(5);
        arrayImpl.add("A");
    }
}

