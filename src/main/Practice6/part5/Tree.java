package main.Practice6.part5;

public class Tree<E extends Comparable<E>>{
    private Node<E> root;
    private int capacity;

    public boolean add(E element) {
        int oldCapacity = capacity;
        root = addRecursive(root, element);
        return capacity != oldCapacity;
    }

    public Node<E> addRecursive(Node<E> current, E element) {
        if(current == null) {
            capacity++;
            return new Node<>(element, null, null);
        }
        int comp = element.compareTo(current.item);
        if(comp > 0) {
            current.left = addRecursive(current.left, element);
        } else if(comp < 0) {
            current.right = addRecursive(current.right, element);
        }
        return current;
    }

    public Node<E> deleteRecursive(Node<E> current, E element) {
        if(current == null) return null;
        int comp = element.compareTo(current.item);
        if(comp == 0) {
            capacity--;
            if(current.left == null && current.right == null) {
                return null;
            }
            if(current.right == null) {
                return current.left;
            }
            if(current.left == null) {
                return current.right;
            }
            E smallestValue = findSmallestValue(current.left);
            current.item = smallestValue;
            current.left = deleteRecursive(current.left, smallestValue);
            return current;
        }
        if(comp > 0) {
            current.left = deleteRecursive(current.left, element);
        }
        if(comp < 0) {
            current.right = deleteRecursive(current.right, element);
        }
        return current;
    }

    private E findSmallestValue(Node<E> initial) {
        return root.right == null ? initial.item : findSmallestValue(initial.right);
    }

    public void add(E[] elements) {
        for(E el : elements) {
            addRecursive(root, el);
        }
    }

    public boolean remove(E element) {
        int oldCapacity = capacity;
        root = deleteRecursive(root, element);
        return capacity != oldCapacity;
    }

    public void print() {
        traverseInOrder(root, 0);
    }

    public void traverseInOrder(Node node, int count) {
        count += 2;
        if (node != null) {
            traverseInOrder(node.right, count);
            count -= 2;
            printSpaces(count);
            System.out.print(node.item + System.lineSeparator());
            count += 2;
            traverseInOrder(node.left, count);
        }
    }

    private void printSpaces(int count) {
        for(int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static final class Node<E> {
        private E item;
        private Node<E> left;
        private Node<E> right;

        public Node(E item, Node<E> left, Node<E> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

    }

}

