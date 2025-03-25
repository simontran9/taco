package com.simontran.datastructures.priorityqueue;

import java.util.ArrayList;

public class BinaryMaxHeapPriorityQueue<E extends Comparable<K>> implements PriorityQueue<E> {
    private ArrayList<E> data;

    public BinaryMaxHeapPriorityQueue() {
        this.data = new ArrayList<>();
        this.data.add(null);
    }

    public BinaryMaxHeapPriorityQueue(ArrayList<E> initialKeys) {
        this.data = new ArrayList<>();
        this.data.add(null);
        this.data.addAll(initialKeys);
        buildMaxHeap();
    }

    public int size() {
        return this.data.size() - 1;
    }

    public void add(E element) {
        this.data.add(element);
        siftUp(this.data.size() - 1);
    }

    public K top() {
        if (this.data.size() == 1) {
            return null;
        }
        return this.data.get(1);
    }

    public K removeTop() {
        E top = this.data.get(1);
        this.data.set(1, this.data.getLast());
        this.data.removeLast();
        siftDown(1);
        return top;
    }

    private void buildMaxHeap() {
        for (int i = parent(this.data.size() - 1); i >= 1; i -= 1) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        int largest = i;
        int left = left(i);
        int right = right(i);
        if (left <= this.data.size() - 1 && this.data.get(left).compareTo(this.data.get(largest)) > 0) {
            largest = left;
        }
        if (right <= this.data.size() - 1 && this.data.get(right).compareTo(this.data.get(largest)) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            siftDown(largest);
        }
    }

    private void siftUp(int i) {
        if (i > 1 && this.data.get(i).compareTo(this.data.get(parent(i))) > 0) {
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

