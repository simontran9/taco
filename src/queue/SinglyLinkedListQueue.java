package com.simontran.datastructures.queue;

public class SinglyLinkedListQueue<E> implements Queue<E> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public E front() {
        if (this.head == null) {
            return null;
        }
        return this.head.data;
    }

    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.size == 0) {
            this.head = newNode;
        } else {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size += 1;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new EmptyQueueException();
        }
        Node<E> removeNode = this.head;
        this.head = removeNode.next;
        E removedElement = removeNode.data;
        removeNode.next = null;
        this.size -= 1;
        if (this.size == 0) {
            this.tail = null;
        }
        return removedElement;
    }

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException() {
            super("Queue is empty");
        }
    }
}
