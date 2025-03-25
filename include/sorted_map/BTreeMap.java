package com.simontran.datastructures.sortedmap;

public class BTreeMap<K, V> implements SortedMap<K, V> {
    private static class Node<K, V> {
        private int number_keys;
        private K[] keys;
        private Node<K, V>[] children;
        private boolean leaf;

        private Node(boolean leaf, int min_degree) {
            this.number_keys = 0;
            this.keys = (K[]) new Object[2 * min_degree - 1];
            this.children = (Node<K, V>[]) new Object[2 * min_degree];
            this.leaf = leaf;
        }
    }

    private Node<K, V> root;
    private int min_degree;
    private int size;

    public BTreeMap(int min_degree) {
        if (min_degree < 2) {
            throw new IllegalArgumentException("B-Tree minimum degree must be at least 2.");
        }
        this.min_degree = min_degree;
        this.root = new Node<>(true, this.min_degree);
    }

    public int size() {
        return this.size;
    }

    public V get(K key) {
        return null;
    }

    public void add(K key, V value) {

    }

    public void remove(K key) {

    }

    public K min() {
        return null;
    }

    public K max() {
        return null;
    }

    public K successor(K key) {
        return null;
    }

    public K predecessor(K key) {
        return null;
    }
}
