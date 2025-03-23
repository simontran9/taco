package com.simontran.datastructures.queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private E[] data;
    private int front;
    private int back;
    private int size;

    public ArrayQueue() {
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.back = 0;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public E front() {
        if (this.size == 0) {
            return null;
        }
        return this.data[this.front];
    }

    public void enqueue(E element) {
        if (this.size == this.data.length) {
            this.resize((int) Math.round(this.data.length * GROWTH_FACTOR));
        }
        this.data[this.back] = element;
        this.back = (this.back + 1) % this.data.length;
        this.size += 1;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        E oldElement = this.data[this.front];
        this.data[this.front] = null;
        this.front = (this.front + 1) % this.data.length;
        this.size -= 1;
        return oldElement;
    }

    private void resize(int newCapacity) {
        E[] biggerArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i += 1) {
            biggerArray[i] = this.data[(this.front + i) % this.data.length];
        }
        this.data = biggerArray;
        this.front = 0;
        this.back = this.size;
    }
}
