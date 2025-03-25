package com.simontran.datastructures.list;

public class DoublyLinkedList<E> implements List<E> {
    private static class Node<E> {
        private Node<E> prev;
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.prev = null;
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> dummyHead;
    private Node<E> dummyTail;
    private int size;

    public DoublyLinkedList() {
        this.dummyHead = new Node<>(null);
        this.dummyTail = new Node<>(null);
        this.dummyHead.next = this.dummyTail;
        this.dummyTail.prev = this.dummyHead;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.getNode(index).data;
    }

    public E getFirst() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.dummyHead.next.data;
    }

    public E getLast() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.dummyTail.prev.data;
    }

    public E set(int index, E element) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = this.getNode(index);
        E oldElement = node.data;
        node.data = element;
        return oldElement;
    }

    public void add(int index, E element) {
        if (index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(element);
        Node<E> prevNode;
        Node<E> nextNode;
        if (this.size == 0) {
            prevNode = this.dummyHead;
            nextNode = this.dummyTail;
        } else if (index == 0) {
            prevNode = this.dummyHead;
            nextNode = this.dummyHead.next;
        } else if (index == this.size) {
            prevNode = this.dummyTail.prev;
            nextNode = this.dummyTail;
        } else {
            prevNode = this.getNode(index - 1);
            nextNode = prevNode.next;
        }
        prevNode.next = newNode;
        nextNode.prev = newNode;
        newNode.next = nextNode;
        newNode.prev = prevNode;
        this.size += 1;
    }

    public void addFirst(E element) {
        this.add(0, element);
    }

    public void addLast(E element) {
        this.add(this.size, element);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> removeNode;
        Node<E> prevNode;
        Node<E> nextNode;
        if (index == 0) {
            removeNode = this.dummyHead.next;
            prevNode = this.dummyHead;
            nextNode = removeNode.next;
        } else if (index == this.size - 1) {
            removeNode = this.dummyTail.prev;
            prevNode = removeNode.prev;
            nextNode = this.dummyTail;
        } else {
            prevNode = this.getNode(index - 1);
            removeNode = prevNode.next;
            nextNode = removeNode.next;
        }
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        E removedElement = removeNode.data;
        this.size -= 1;
        return removedElement;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.remove(0);
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.remove(this.size - 1);
    }

    private Node<E> getNode(int index) {
        Node<E> current;
        if (index < this.size / 2) {
            current = this.dummyHead.next;
            for (int i = 0; i < index; i += 1) {
                current = current.next;
            }
        } else {
            current = this.dummyTail;
            for (int i = this.size; i > index; i -= 1) {
                current = current.prev;
            }
        }
        return current;
    }
}