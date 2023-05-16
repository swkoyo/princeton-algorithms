package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int length;
    private int capacity;
    private Item[] data;
    private int head;
    private int tail;

    public RandomizedQueue() {
        length = head = tail = 0;
        capacity = 1;
        data = (Item[]) new Object[capacity];
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        StdOut.println("isEmpty should be true: " + rq.isEmpty());

        rq.enqueue("hello");
        rq.enqueue("world");

        StdOut.println("Data should contain [hello, world]");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("Capacity should be 2: " + rq.capacity);
        StdOut.println("Size should be 2: " + rq.size());
        StdOut.println("Head should be 0: " + rq.head);
        StdOut.println("Tail should be 1: " + rq.tail);

        rq.enqueue("foo");
        rq.enqueue("bar");
        rq.enqueue("baz");

        StdOut.println("Data should contain [hello, world, foo, bar, baz]");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println("Capacity should be 8: " + rq.capacity);
        StdOut.println("Size should be 5: " + rq.size());
        StdOut.println("Current head: " + rq.head);
        StdOut.println("Current tail: " + rq.tail);

        String item = rq.sample();
        StdOut.println("Sample item should be random: " + item);

        item = rq.sample();
        StdOut.println("Sample item should be random: " + item);

        item = rq.dequeue();
        StdOut.println("Removed item: " + item);
        StdOut.println("Size should be 4: " + rq.size());
        StdOut.println("Capacity should be 8: " + rq.capacity);
        StdOut.println("Current head: " + rq.head);
        StdOut.println("Current tail: " + rq.tail);

        item = rq.dequeue();
        StdOut.println("Removed item: " + item);
        StdOut.println("Size should be 3: " + rq.size());
        StdOut.println("Capacity should be 8: " + rq.capacity);
        StdOut.println("Current head: " + rq.head);
        StdOut.println("Current tail: " + rq.tail);

        item = rq.dequeue();
        StdOut.println("Removed item: " + item);
        StdOut.println("Size should be 2: " + rq.size());
        StdOut.println("Capacity should be 4: " + rq.capacity);
        StdOut.println("Current head: " + rq.head);
        StdOut.println("Current tail: " + rq.tail);

        StdOut.println("Iterator should be in random order and not have dequeued items: ");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        StdOut.println("Iterator should be in random order and not have dequeued items: ");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        StdOut.println("Iterator should be in random order and not have dequeued items: ");
        for (String s : rq) {
            StdOut.print(s + " ");
        }
        StdOut.println();

        item = rq.dequeue();
        item = rq.dequeue();

        StdOut.println("Empty queue should have size 0, capacity 2, head 0, tail 0: " + rq.size() + " " + rq.capacity + " " + rq.head + " " + rq.tail);

        try {
            rq.dequeue();
        } catch (NoSuchElementException err) {
            StdOut.println("Dequeue on empty queue should throw NoSuchElementException: " + err);
        }

        try {
            rq.enqueue(null);
        } catch (IllegalArgumentException err) {
            StdOut.println("Enqueue with null should throw IllegalArgumentException: " + err);
        }

        try {
            rq.sample();
        } catch (NoSuchElementException err) {
            StdOut.println("Sample on empty queue should throw NoSuchElementException: " + err);
        }

        Iterator<String> iter = rq.iterator();
        try {
            while (iter.hasNext()) {
                iter.next();
            }
            iter.next();
        } catch (NoSuchElementException err) {
            StdOut.println("Next call on iterator when hasNext is false should throw NoSuchElementException: " + err);
        }

        try {
            iter.remove();
        } catch (UnsupportedOperationException err) {
            StdOut.println("Calling remove on iterator should throw UnsupportedOperationException: " + err);
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (length == capacity) {
            increaseSize();
        }

        data[length] = item;
        tail = length;
        length++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int idx = 0;
        if (length > 1) {
            idx = getRandomIdx();
        }
        Item item = data[idx];
        length--;
        if (length == 0) {
            head = tail = 0;
        } else if (idx == head) {
            data[head++] = null;
        } else if (idx == tail) {
            data[tail--] = null;
        } else {
            for (int i = idx; i < tail; i++) {
                data[i] = data[i + 1];
            }
            data[tail--] = null;
        }
        if (capacity > 2 && length * 1.0 / capacity <= 0.25) {
            decreaseSize();
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[getRandomIdx()];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int currIdx;
        private int[] iterData;

        public RandomizedQueueIterator() {
            currIdx = 0;
            Item[] iterItems = (Item[]) new Object[length];
            iterData = new int[length];
            int i = 0;
            for (int j = head; j < length; j++) {
                iterItems[i++] = data[j];
            }
            for (int idx = 0; idx < length; idx++) {
                int randomIdx = StdRandom.uniformInt(length);
                while (iterItems[randomIdx] == null) {
                    randomIdx = StdRandom.uniformInt(length);
                }
                iterItems[randomIdx] = null;
                iterData[idx] = randomIdx;
            }
        }

        public boolean hasNext() {
            return currIdx < length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (currIdx >= length) {
                throw new NoSuchElementException();
            }
            Item item = data[iterData[currIdx++]];
            return item;
        }
    }

    private void increaseSize() {
        capacity *= 2;
        Item[] updatedData = (Item[]) new Object[capacity];
        int i = 0;
        for (int j = head; j <= tail; j++) {
            updatedData[i++] = data[j];
        }
        data = updatedData;
        head = 0;
        tail = i;
    }

    private void decreaseSize() {
        capacity /= 2;
        Item[] updatedData = (Item[]) new Object[capacity];
        int i = 0;
        for (int j = head; j <= tail; j++) {
            updatedData[i++] = data[j];
        }
        data = updatedData;
        head = 0;
        tail = i;
    }

    private int getRandomIdx() {
        return StdRandom.uniformInt(head, tail + 1);
    }
}
