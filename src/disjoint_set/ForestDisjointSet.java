package com.simontran.datastructures.disjointset;

public class ForestDisjointSet implements DisjointSet {
    private int[] parent;
    private int[] rank;

    public ForestDisjointSet(int initialCapacity) {
        this.parent = new int[initialCapacity];
        this.rank = new int[initialCapacity];
        for (int i = 0; i < initialCapacity; i += 1) {
            this.parent[i] = i;
            this.rank[i] = 0;
        }
    }

    public void union(int x, int y) {
        int xRep = find(x);
        int yRep = find(y);

        // apply union by rank
        if (this.rank[xRep] > this.rank[yRep]) {
            this.parent[yRep] = xRep;
        } else if (this.rank[xRep] < this.rank[yRep]) {
            this.parent[xRep] = yRep;
        } else {
            this.parent[xRep] = yRep;
            this.rank[yRep] += 1;
        }
    }

    public int find(int x) {
        if (x != this.parent[x]) {
            // apply path compression
            this.parent[x] = find(this.parent[x]);
        }
        return this.parent[x];
    }
}