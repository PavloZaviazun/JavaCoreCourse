package main.Practice6.part2;

import java.util.*;

public class Part2 {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList <>();
        List<Integer> linkedList = new LinkedList <>();
        fillLists(arrayList, linkedList);
        System.out.println("ArrayList#Index: " + removeByIndex(arrayList, 4) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(linkedList, 4)  + " ms");
        fillLists(arrayList, linkedList);
        System.out.println("ArrayList#Iterator: " + removeByIterator(arrayList, 4) + " ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(linkedList, 4) + " ms");
    }

    public static void fillLists(List<Integer> arrayList, List<Integer> linkedList) {
        for(int i = 0; i < 10000; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long start = System.currentTimeMillis();
        List<Integer> copy = null;
        int j = 0;
        while(list.size() != 1) {
            if(list instanceof ArrayList) copy = new ArrayList<>(list);
            if(list instanceof LinkedList) copy = new LinkedList <>(list);
            for (int i = k - 1 + j; i < copy.size(); i += k - 1) {
                list.remove(i);
                if(list.size() < k) {
                    j = i - k;
                }
                if((i + k - 1) >= list.size() && list.size() >= k) {
                    j = i - list.size();
                    break;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        int count = 0;
        int step = k;
        while(list.size() != 1) {
            Iterator <Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (++count == step) {
                    iterator.remove();
                    step += k;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }
}

