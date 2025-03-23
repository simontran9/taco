package com.simontran.datastructures.list;

public class SinglyLinkedList<E> implements List<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return this.head.data;
        }
        if (index == this.size - 1) {
            return this.tail.data;
        }
        return getNode(index).data;
    }

    public E getFirst() {
        if (this.head == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.head.data;
    }

    public E getLast() {
        if (this.tail == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.tail.data;
    }

    public E set(int index, E element) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            E oldElement = this.head.data;
            this.head.data = element;
            return oldElement;
        }
        if (index == size - 1) {
            E oldElement = this.tail.data;
            this.tail.data = element;
            return oldElement;
        }
        Node<E> node = getNode(index);
        E oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    public void add(int index, E element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else if (index == this.size) {
            this.tail.next = newNode;
            this.tail = newNode;
        } else {
            Node<E> prevNode = getNode(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }
        this.size += 1;
    }

    public void addFirst(E element) {
        add(0, element);
    }

    public void addLast(E element) {
        add(this.size, element);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> removeNode;
        if (index == 0) {
            removeNode = this.head;
            this.head = removeNode.next;
            if (this.size == 1) this.tail = null;
        } else {
            Node<E> prevNode = getNode(index - 1);
            removeNode = prevNode.next;
            prevNode.next = removeNode.next;
            if (index == this.size - 1) this.tail = prevNode;
        }
        E removedElement = removeNode.data;
        this.size -= 1;
        return removedElement;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(0);
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return remove(this.size - 1);
    }

    private Node<E> getNode(int index) {
        Node<E> current = this.head;
        for (int i = 0; i < index; i += 1) {
            current = current.next;
        }
        return current;
    }
}