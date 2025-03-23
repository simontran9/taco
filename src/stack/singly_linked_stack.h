// package com.simontran.datastructures.stack;

// public class SinglyLinkedListStack<E> implements Stack<E> {
//     private static class Node<E> {
//         private E data;
//         private Node<E> next;

//         public Node(E data) {
//             this.data = data;
//             this.next = null;
//         }
//     }

//     private Node<E> head;
//     private int size;

//     public SinglyLinkedListStack() {
//         this.head = null;
//         this.size = 0;
//     }

//     public int size() {
//         return this.size;
//     }

//     public E top() {
//         if (this.head == null) {
//             return null;
//         }
//         return this.head.data;
//     }

//     public void push(E element) {
//         Node<E> newNode = new Node<>(element);
//         newNode.next = this.head;
//         this.head = newNode;
//         this.size += 1;
//     }

//     public E pop() {
//         if (this.size == 0) {
//             throw new EmptyStackException();
//         }
//         Node<E> removeNode = this.head;
//         this.head = removeNode.next;
//         E removedElement = removeNode.data;
//         removeNode.next = null; // Help garbage collection
//         this.size -= 1;
//         return removedElement;
//     }

//     public static class EmptyStackException extends RuntimeException {
//         public EmptyStackException() {
//             super("Stack is empty");
//         }
//     }
// }
