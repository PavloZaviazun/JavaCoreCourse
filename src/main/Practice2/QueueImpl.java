package main.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private Object[] arrayQueue;
    private int sizeQueue = 0;

    public QueueImpl() {
        this.arrayQueue = new Object[0];
    }

    @Override
    public void clear() {
        this.arrayQueue = new Object[0];
        sizeQueue = 0;
    }

    @Override
    public int size() {
        return sizeQueue;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < arrayQueue.length;
        }

        @Override
        public Object next() {
            if(arrayQueue.length <= currentIndex) throw new NoSuchElementException();
            return arrayQueue[currentIndex++];
        }

        @Override
        public void remove() {
            Object[] arrayUpdated = new Object[arrayQueue.length - 1];
            int j = 0;
            for(int i = 0; i < arrayQueue.length; i++) {
                if(i + 1 != currentIndex) {
                    arrayUpdated[j] = arrayQueue[i];
                    j++;
                }
            }
            arrayQueue = arrayUpdated;
            sizeQueue--;
        }
    }

    @Override
    public void enqueue(Object element) {
        Object[] arrayUpdated = new Object[arrayQueue.length + 1];
        System.arraycopy(arrayQueue, 0, arrayUpdated, 0, arrayQueue.length);
        arrayUpdated[arrayQueue.length] = element;
        arrayQueue = arrayUpdated;
        sizeQueue++;
    }

    @Override
    public Object dequeue() {
        if(sizeQueue > 0) {
            Object top = top();
            Object[] arrayUpdated = new Object[arrayQueue.length - 1];
            for(int i = 1; i < arrayQueue.length; i++) {
                arrayUpdated[i - 1] = arrayQueue[i];
            }
            arrayQueue = arrayUpdated;
            sizeQueue--;
            return top;
        }
        return null;
    }

    @Override
    public Object top() {
        if(sizeQueue > 0) return arrayQueue[0];
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < arrayQueue.length; i++) {
            sb.append(arrayQueue[i]);
            if(i < arrayQueue.length - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
    }
}

