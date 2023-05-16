package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int length;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        head = tail = null;
        length = 0;
    }

    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        StdOut.println("isEmpty should be true: " + dq.isEmpty());

        dq.addFirst("hello");
        dq.addFirst("world");
        dq.addFirst("foo");
        StdOut.println("Expect: foo -> world -> hello");
        for (String s : dq) {
            StdOut.print(s + " -> ");
        }
        StdOut.println();
        StdOut.println("Size should be 3: " + dq.size());

        dq.addLast("bar");
        dq.addLast("baz");
        StdOut.println("Expect: foo -> world -> hello -> bar -> baz");
        for (String s: dq) {
            StdOut.print(s + " -> ");
        }
        StdOut.println();

        StdOut.println("isEmpty should be false: " + dq.isEmpty());

        String curr = dq.removeFirst();
        StdOut.println("Remove first should return foo: " + curr);

        curr = dq.removeFirst();
        StdOut.println("Remove first should return world: " + curr);

        curr = dq.removeLast();
        StdOut.println("Remove last should return baz: " + curr);

        curr = dq.removeLast();
        StdOut.println("Remove last should return bar" + curr);

        StdOut.println("Only hello left");
        for (String s : dq) {
            StdOut.println(s);
        }

        curr = dq.removeFirst();
        StdOut.println("Remove first should return hello: " + curr);

        try {
            curr = dq.removeFirst();
        } catch (NoSuchElementException err) {
            StdOut.println("Remove first expect NoSuchElementException:  " + err);
        }

        try {
            curr = dq.removeLast();
        } catch (NoSuchElementException err) {
            StdOut.println("Remove last expect NoSuchElementException: " + err);
        }

        try {
            dq.addFirst(null);
        } catch (IllegalArgumentException err) {
            StdOut.println("Add first expect IllegalArgumentException: " + err);
        }

        try {
            dq.addLast(null);
        } catch (IllegalArgumentException err) {
            StdOut.println("Add last expect IllegalArgumentException: " + err);
        }

        Iterator<String> iterator = dq.iterator();

        try {
            iterator.next();
        } catch (NoSuchElementException err) {
            StdOut.println("Iterator next on empty list expect NoSuchElementException: " + err);
        }

        try {
            iterator.remove();
        } catch (UnsupportedOperationException err) {
            StdOut.println("Iterator remove expect UnsupportedOperationException: " + err);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return length;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        length++;
        Node node = new Node();
        node.item = item;
        node.next = head;
        if (head != null) {
            head.prev = node;
            head = node;
        } else {
            head = tail = node;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        length++;
        Node node = new Node();
        node.item = item;
        node.next = null;
        node.prev = tail;
        if (tail != null) {
            tail.next = node;
            tail = node;
        } else {
            head = tail = node;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        length--;
        Node curr = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        curr.next = null;
        return curr.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        length--;
        Node curr = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        curr.prev = null;
        return curr.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
