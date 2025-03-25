package com.simontran.datastructures.sortedmap;

public class RedBlackTreeMap<K extends Comparable<K>, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private boolean color;

        private Node(K key, V value, Node<K, V> left, Node<K, V> right, Node<K, V> parent, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }
    }

    private Node<K, V> root;
    private Node<K, V> NIL; // red-black trees have NIL as their leaves
    private int size;

    public RedBlackTreeMap() {
        this.NIL = new Node<>(null, null, null, null, null, BLACK);
        this.root = NIL;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public K min() {
        if (this.root == NIL) {
            return null;
        }
        return minNode(this.root).key;
    }

    public K max() {
        if (this.root == NIL) {
            return null;
        }
        return maxNode(this.root).key;
    }

    public K successor(K key) {
        Node<K, V> successor = successorNode(this.root, key);
        if (successor == NIL) {
            return null;
        }
        return successor.key;
    }

    public K predecessor(K key) {
        Node<K, V> predecessor = predecessorNode(this.root, key);
        if (predecessor == NIL) {
            return null;
        }
        return predecessor.key;
    }

    public V get(K key) {
        Node<K, V> node = getNode(this.root, key);
        if (node == NIL) {
            return null;
        }
        return node.value;
    }

    public void add(K key, V value) {
        Node<K, V> current = this.root;
        Node<K, V> parent = NIL;
        Node<K, V> node = new Node<>(key, value, NIL, NIL, NIL, RED);
        while (current != NIL) {
            parent = current;
            int cmp = node.key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.value = value;
                return;
            }
        }
        node.parent = parent;
        if (parent == NIL) {
            this.root = node;
        } else if (node.key.compareTo(parent.key) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        this.size += 1;
        insertFixup(node);
    }

    public void remove(K key) {
        Node<K, V> delete = getNode(this.root, key);
        if (delete == NIL) {
            return;
        }
        this.size -= 1;
        Node<K, V> child;
        boolean originalColor = delete.color;
        if (delete.left == NIL) {
            child = delete.right;
            transplant(delete, child);
        } else if (delete.right == NIL) {
            child = delete.left;
            transplant(delete, child);
        } else {
            Node<K, V> successor = minNode(delete.right);
            originalColor = successor.color;
            child = successor.right;
            if (successor != delete.right) {
                transplant(successor, child);
                successor.right = delete.right;
                successor.right.parent = successor;
            } else {
                child.parent = successor;
            }
            transplant(delete, successor);
            successor.left = delete.left;
            successor.left.parent = successor;
            successor.color = delete.color;
        }
        if (originalColor == BLACK) {
            deleteFixup(child);
        }
    }

    private Node<K, V> minNode(Node<K, V> node) {
        while (node.left != NIL) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        while (node.right != NIL) {
            node = node.right;
        }
        return node;
    }

    private Node<K, V> successorNode(Node<K, V> node, K key) {
        Node<K, V> successor = NIL;
        while (node != NIL) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                successor = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return successor;
    }

    private Node<K, V> predecessorNode(Node<K, V> node, K key) {
        Node<K, V> predecessor = NIL;
        while (node != NIL) {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) {
                predecessor = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return predecessor;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        while (node != NIL) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return NIL;
    }

    private void insertFixup(Node<K, V> node) {
        while (node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) { // node's parent is a left child
                Node<K, V> uncle = node.parent.parent.right;
                if (uncle.color == RED) { // case 1: uncle is red
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) { // case 2: uncle is black, and node is a right child
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = BLACK; // case 3: uncle is black, and node is a left child
                    node.parent.parent.color = RED;
                    rotateRight(node.parent.parent);
                }
            } else { // node's parent is a right child
                Node<K, V> uncle = node.parent.parent.left;
                if (uncle.color == RED) {
                    node.parent.color = BLACK; // case 1: uncle is red
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) { // case 2: uncle is black, and node is a left child
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = BLACK;  // case 3: uncle is black, and node is a right child
                    node.parent.parent.color = RED;
                    rotateLeft(node.parent.parent);
                }
            }
        }
        this.root.color = BLACK;
    }

    private void deleteFixup(Node<K, V> node) {
        while (node != this.root && node.color == BLACK) {
            if (node == node.parent.left) { // node is a left child
                Node<K, V> sibling = node.parent.right;
                if (sibling.color == RED) { // case 1: sibling is red
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }
                if (sibling.left.color == BLACK && sibling.right.color == BLACK) { // case 2: sibling is black, and its children are black
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.right.color == BLACK) { // case 3: sibling is black, and its left child is red and its right child is black
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.color = node.parent.color; // case 4: sibling is black, and its right child is red
                    node.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = this.root;
                }
            } else { // node is a right child
                Node<K, V> sibling = node.parent.left;
                if (sibling.color == RED) { // case 1: sibling is red
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }
                if (sibling.right.color == BLACK && sibling.left.color == BLACK) { // case 2: sibling is black, and its children are black
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.left.color == BLACK) { // case 3: sibling is black, and its right child is red and its left child is black
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.color = node.parent.color; // case 4: sibling is black, and its left child is red
                    node.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(node.parent);
                    node = this.root;
                }
            }
        }
        node.color = BLACK;
    }

    private void transplant(Node<K, V> delete, Node<K, V> replace) {
        if (delete.parent == NIL) {
            this.root = replace;
        } else if (delete == delete.parent.left) {
            delete.parent.left = replace;
        } else {
            delete.parent.right = replace;
        }
        replace.parent = delete.parent;
    }

    private void rotateLeft(Node<K, V> root) {
        Node<K, V> newRoot = root.right;
        root.right = newRoot.left;
        if (newRoot.left != NIL) {
            newRoot.left.parent = root;
        }
        newRoot.parent = root.parent;
        if (root.parent == NIL) {
            this.root = newRoot;
        } else if (root == root.parent.left) {
            root.parent.left = newRoot;
        } else {
            root.parent.right = newRoot;
        }
        newRoot.left = root;
        root.parent = newRoot;
    }

    private void rotateRight(Node<K, V> root) {
        Node<K, V> newRoot = root.left;
        root.left = newRoot.right;
        if (newRoot.right != NIL) {
            newRoot.right.parent = root;
        }
        newRoot.parent = root.parent;
        if (root.parent == NIL) {
            this.root = newRoot;
        } else if (root == root.parent.right) {
            root.parent.right = newRoot;
        } else {
            root.parent.left = newRoot;
        }
        newRoot.right = root;
        root.parent = newRoot;
    }
}
