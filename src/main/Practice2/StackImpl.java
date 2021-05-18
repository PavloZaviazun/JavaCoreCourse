package main.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private Object[] arrayStack;
    private int sizeStack = 0;

    public StackImpl() {
        this.arrayStack = new Object[0];
    }

    @Override
    public void clear() {
        this.arrayStack = new Object[0];
        sizeStack = 0;
    }

    @Override
    public int size() {
        return sizeStack;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < arrayStack.length;
        }

        @Override
        public Object next() {
            if(arrayStack.length <= currentIndex) throw new NoSuchElementException();
            return arrayStack[currentIndex++];
        }

        @Override
        public void remove() {
            Object[] arrayUpdated = new Object[arrayStack.length - 1];
            int j = 0;
            for(int i = 0; i < arrayStack.length; i++) {
                if(i + 1 != currentIndex) {
                    arrayUpdated[j] = arrayStack[i];
                    j++;
                }
            }
            arrayStack = arrayUpdated;
            sizeStack--;
        }
    }

    @Override
    public void push(Object element) {
        Object[] arrayUpdated = new Object[arrayStack.length + 1];
        System.arraycopy(arrayStack, 0, arrayUpdated, 1, arrayStack.length);
        arrayUpdated[0] = element;
        arrayStack = arrayUpdated;
        sizeStack++;
    }

    @Override
    public Object pop() {
        if(sizeStack > 0) {
            Object top = top();
            Object[] arrayUpdated = new Object[arrayStack.length - 1];
            for(int i = 1; i < arrayStack.length; i++) {
                arrayUpdated[i - 1] = arrayStack[i];
            }
            arrayStack = arrayUpdated;
            sizeStack--;
            return top;
        }
        return null;
    }

    @Override
    public Object top() {
        if(sizeStack > 0) return arrayStack[0];
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = arrayStack.length - 1; i >= 0 ; i--) {
            sb.append(arrayStack[i]);
            if(i > 0) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
    }
}

