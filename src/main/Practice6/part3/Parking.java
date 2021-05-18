package main.Practice6.part3;

public class Parking {
    int[] array;
    public Parking(int capacity) {
        array = new int[capacity];
    }

    public boolean arrive(int k) {
        if(k > array.length) throw new IllegalArgumentException();
        for(int i = k; i < array.length; i++) {
            if(array[k] == 0) {
                array[k] = 1;
                return true;
            }
            if(array[i] == 0) {
                array[i] = 1;
                return true;
            }
        }
        for(int j = 0; j < array.length; j++) {
            if(array[j] == 0) {
                array[j] = 1;
                return true;
            }
        }
        return false;
    }

    public boolean depart(int k) {
        if(k > array.length) throw new IllegalArgumentException();
        if(array[k] == 1) {
            array[k] = 0;
            return true;
        }
        return false;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for(int el : array) {
            sb.append(el);
        }
        System.out.println(sb);
    }
}

