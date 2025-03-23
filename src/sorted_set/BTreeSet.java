package com.simontran.datastructures.sortedset;

import java.util.ArrayList;

public class BTreeSet<K extends Comparable<K>> {
    private static class Node<K> {
        private ArrayList<K> keys;
        private ArrayList<Node<K>> children;
        private boolean leaf;

        private Node(boolean leaf) {
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.leaf = leaf;
        }
    }

    private Node<K> root;
    private int minDegree;
    private int size;

    public BTreeSet(int minDegree) {
        if (minDegree < 2) {
            throw new IllegalArgumentException("B-Tree minimum degree must be at least 2.");
        }
        this.minDegree = minDegree;
        this.root = new Node<>(true);
    }

    public int size() {
        return this.size;
    }

    public boolean contains(K key) {
        return getNode(this.root, key) != null;
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

    public void add(K key) {
        if (this.root.keys.size() == 2 * this.minDegree - 1) {
            Node<K> newRoot = splitRoot();
            addNonfull(newRoot, key);
        } else {
            addNonfull(this.root, key);
        }
    }

    public void remove(K key) {

    }

    private Object[] getNode(Node<K> node, K key) {
        int i = 0;
        while (i < node.keys.size() && key.compareTo(node.keys.get(i)) > 0) {
            i += 1;
        }
        if (i < node.keys.size() && key.compareTo(node.keys.get(i)) == 0) {
            return new Object[]{node, i};
        }
        if (node.leaf) {
            return null;
        }
        return getNode(node.children.get(i), key);
    }

    private Node<K> splitRoot() {
        Node<K> newRoot = new Node<>(false);
        newRoot.children.set(0, this.root);
        this.root = newRoot;
        splitChild(newRoot, 0);
        return newRoot;
    }

    private void splitChild(Node<K> parentNode, int fullChildIndex) {
        Node<K> fullChildNode = parentNode.children.get(fullChildIndex);

        Node<K> newSiblingNode = new Node<>(fullChildNode.leaf);
        parentNode.children.add(fullChildIndex + 1, newSiblingNode);

        parentNode.keys.add(fullChildIndex, fullChildNode.keys.get(this.minDegree - 1));

        newSiblingNode.keys = new ArrayList<>(fullChildNode.keys.subList(this.minDegree, 2 * this.minDegree - 1));
        fullChildNode.keys = new ArrayList<>(fullChildNode.keys.subList(0, this.minDegree - 1));

        if (!fullChildNode.leaf) {
            newSiblingNode.children = new ArrayList<>(fullChildNode.children.subList(this.minDegree, 2 * this.minDegree));
            fullChildNode.children = new ArrayList<>(fullChildNode.children.subList(0, this.minDegree));
        }
    }

    private void addNonfull(Node<K> node, K key) {
        int i = node.keys.size() - 1;
        if (node.leaf) {
            node.keys.addLast(null);
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                node.keys.set(i + 1, node.keys.get(i));
                i -= 1;
            }
            node.keys.set(i + 1, key);
            this.size += 1;
        } else {
            while (i >= 0 && key.compareTo(node.keys.get(i)) < 0) {
                i -= 1;
            }
            i += 1;
            if (node.children.size() == 2 * this.minDegree - 1) {
                splitChild(node, i);
                if (key.compareTo(node.keys.get(i)) > 0) {
                    i += 1;
                }
            }
            addNonfull(node.children.get(i), key);
        }
    }
}
