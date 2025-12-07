package coziahr;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Simple generic singly-linked list implementation.
 * Provides add(T), size(), isEmpty(), get(index) and iterator.
 */
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add to tail (O(1))
    public void add(T value) {
        Objects.requireNonNull(value, "value cannot be null");
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Get element by index (0-based). Traversal is O(n), used only by tests or when necessary.
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.value;
    }

    // Allows foreach loops
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() { return current != null; }
            @Override
            public T next() {
                if (current == null) throw new NoSuchElementException();
                T val = current.value;
                current = current.next;
                return val;
            }
        };
    }

    // Convert to array of doubles (helper)
    public Double[] toDoubleArray() {
        Double[] arr = new Double[size];
        int i = 0;
        for (T t : this) {
            arr[i++] = (Double) t;
        }
        return arr;
    }
}