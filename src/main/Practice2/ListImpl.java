package main.Practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private int size = 0;
    private Node head;
    private Node tail;
    private Node search;

    private static class Node {
        private Object object;
        private Node next;

        public Node(Object object, Node next) {
            this.object = object;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "object=" + object + ", next=" + next + '}';
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        Node node = head;
        Node result = null;

        @Override
        public boolean hasNext() {
            if(node == null) return false;
            if(node.next == null) {
                return node == tail;
            }
            return true;
        }

        @Override
        public Object next() {
            if(node == null) throw new NoSuchElementException();
            result = node;
            node = node.next;
            return result.object;
        }

        @Override
        public void remove() {
            ListImpl.this.remove(result.object);
        }
    }

    @Override
    public void addFirst(Object element) {
        if (head == null) {
            head = new Node(element, null);
            tail = head;
        } else {
            head = new Node(element, head);
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (tail == null) {
            head = new Node(element, null);
            tail = head;
        } else {
            Node node = new Node(element, null);
            tail.next = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if(head != null) {
            if(head.next != null) {
                head = head.next;
            } else {
                head = null;
                tail = null;
            }
            size--;
        }
    }

    @Override
    public void removeLast() {
        if(size == 1) {
            head = null;
            tail = null;
        } else if(tail != null) {
            search = null;
            findParentNode(head, tail.object);
            tail = search;
            tail.next = null;
        } else return;
        size--;
    }

    @Override
    public Object getFirst() {
        return head != null ? head.object : null;
    }

    @Override
    public Object getLast() {
        return head != null ? tail.object : null;
    }

    @Override
    public Object search(Object element) {
        search = null;
        findNode(head, element);
        return search != null ? search.object : null;
    }

    private void findNode(Node comparable, Object element) {
        if(comparable != null && comparable.object != null) {
            if (!comparable.object.equals(element)) findNode(comparable.next, element);
            else search = comparable;
        } else {
            if (element != null && comparable != null) findNode(comparable.next, element);
            else search = comparable;
        }
    }

    private void findParentNode(Node comparable, Object element) {
        if(comparable != null && comparable.next != null && comparable.next.object != null) {
            if (!comparable.next.object.equals(element)) findParentNode(comparable.next, element);
            else search = comparable;
        } else {
            if (element != null && comparable != null) findParentNode(comparable.next, element);
            else search = comparable;
        }
    }

    @Override
    public boolean remove(Object element) {
        if(size > 0 && headTailChecking(element)) return true;
        search = null;
        Node linkTo = null;
        Node necNode = null;
        findNode(head, element);
        if (search != null) {
            necNode = search;
            linkTo = search.next;
        }
        search = null;
        findParentNode(head, element);
        if (search != null) search.next = linkTo;
        else if (linkTo != null) head = linkTo;
        return necNode != null;
    }

    private boolean headTailChecking(Object element) {
        if (element != null) {
            if (element.equals(head.object)) {
                removeFirst();
                return true;
            }
            if (element.equals(tail.object)) {
                removeLast();
                return true;
            }
        } else {
            if (head.object == null) {
                removeFirst();
                return true;
            }
            if (tail.object == null) {
                removeLast();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if(head != null) {
            sb.append(head.object);
            if (head.next != null) {
                sb.append(", ");
                recursionToString(sb, head.next);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void recursionToString(StringBuilder sb, Node node) {
        sb.append(node.object);
        if(node.next != null) {
            sb.append(", ");
            recursionToString(sb, node.next);
        }
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            Object each = iterator.next();
            System.out.println(each);
        }
    }
}

