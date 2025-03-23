package com.simontran.datastructures.priorityqueue;

import java.util.ArrayList;

public class BinaryMinHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    private ArrayList<E> data;

    public BinaryMinHeapPriorityQueue() {
        this.data = new ArrayList<>();
        this.data.add(null);
    }

    public BinaryMinHeapPriorityQueue(ArrayList<E> initialElements) {
        this.data = new ArrayList<>();
        this.data.add(null);
        this.data.addAll(initialElements);
        buildMinHeap();
    }

    public int size() {
        return this.data.size() - 1;
    }

    public void add(E element) {
        this.data.add(element);
        siftUp(this.data.size() - 1);
    }

    public E top() {
        if (this.data.size() == 1) {
            return null;
        }
        return this.data.get(1);
    }

    public E removeTop() {
        E top = this.data.get(1);
        this.data.set(1, this.data.getLast());
        this.data.removeLast();
        siftDown(1);
        return top;
    }

    private void buildMinHeap() {
        for (int i = parent(this.data.size() - 1); i >= 1; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        int smallest = i;
        int left = left(i);
        int right = right(i);
        if (left <= this.data.size() - 1 && this.data.get(left).compareTo(this.data.get(smallest)) < 0) {
            smallest = left;
        }
        if (right <= this.data.size() - 1 && this.data.get(right).compareTo(this.data.get(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    private void siftUp(int i) {
        if (i > 1 && this.data.get(i).compareTo(this.data.get(parent(i))) < 0) {
            swap(i, parent(i));
            siftUp(parent(i));
        }
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void swap(int i, int j) {
        E temp = this.data.get(i);
        this.data.set(i, this.data.get(j));
        this.data.set(j, temp);
    }
}