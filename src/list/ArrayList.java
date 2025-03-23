package com.simontran.datastructures.list;

public class ArrayList<E> implements List<E> {
    private static int INITIAL_CAPACITY = 10;
    private static double GROWTH_FACTOR = 1.5;

    private E[] data;
    private int size;

    public ArrayList() {
        this.data = (E[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[index];
    }

    public E getFirst() throws IndexOutOfBoundsException {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[0];
    }

    public E getLast() throws IndexOutOfBoundsException {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[this.size - 1];
    }

    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = this.data[index];
        this.data[index] = element;
        return oldElement;
    }

    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == this.data.length) {
            resize((int) Math.round(this.data.length * GROWTH_FACTOR));
        }
        if (index < this.size) {
            System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        }
        this.data[index] = element;
        this.size += 1;
    }

    public void addFirst(E element) {
        add(0, element);
    }

    public void addLast(E element) {
        add(this.size, element);
    }

    public E remove(int index) throws IndexOutOfBoundsException {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = this.data[index];
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.data[this.size - 1] = null;
        this.size -= 1;
        return oldElement;
    }

    public E removeFirst() throws IndexOutOfBoundsException {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(0);
    }

    public E removeLast() throws IndexOutOfBoundsException {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(this.size - 1);
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(this.data, 0, newData, 0, this.size);
        this.data = newData;
    }
}
