package com.simontran.datastructures.sortedmap;

public class AVLTreeMap<K extends Comparable<K>, V> implements SortedMap<K, V> {
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;
        
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    private Node<K, V> root;
    private int size;

    public AVLTreeMap() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public K min() {
        if (this.root == null) {
            return null;
        }
        return minNode(this.root).key;
    }

    public K max() {
        if (this.root == null) {
            return null;
        }
        return maxNode(this.root).key;
    }

    public K successor(K key) {
        Node<K, V> successor = successorNode(this.root, key);
        if (successor == null) {
            return null;
        }
        return successor.key;
    }

    public K predecessor(K key) {
        Node<K, V> predecessor = predecessorNode(this.root, key);
        if (predecessor == null) {
            return null;
        }
        return predecessor.key;
    }

    public V get(K key) {
        Node<K, V> node = getNode(this.root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    public void add(K key, V value) {
        if (getNode(this.root, key) == null) {
            this.size += 1;
        }
        this.root = addNode(this.root, key, value);
    }

    public void remove(K key) {
        if (getNode(this.root, key) != null) {
            this.size -= 1;
        }
        this.root = removeNode(this.root, key);
    }

    private Node<K, V> minNode(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node<K, V> successorNode(Node<K, V> node, K key) {
        Node<K, V> successor = null;
        while (node != null) {
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
        Node<K, V> predecessor = null;
        while (node != null) {
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
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    private Node<K, V> addNode(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = addNode(node.left, key, value);
        } else if (cmp > 0) {
            node.right = addNode(node.right, key, value);
        } else {
            node.value = value;
        }
        updateHeight(node);
        return balance(node);
    }

    private Node<K, V> removeNode(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<K, V> successor = minNode(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = removeNode(node.right, successor.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private Node<K, V> balance(Node<K, V> node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) { // right heavy
            if (balanceFactor(node.right) < 0) { // right triangle
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (balanceFactor < -1) { // left heavy
            if (balanceFactor(node.left) > 0) { // left triangle
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }

    private int balanceFactor(Node<K, V> node) {
        return height(node.right) - height(node.left);
    }

    private Node<K, V> rotateLeft(Node<K, V> root) {
        Node<K, V> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node<K, V> rotateRight(Node<K, V> root) {
        Node<K, V> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        updateHeight(root);
        updateHeight(newRoot);
        return newRoot;
    }
}
