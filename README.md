<div align="center">
    <img src="docs/logo.png" width="150px">
    <h1>taco</h1>
</div>

C header-only generic containers library

## Motivation

By rule 5 of [*Rob Pike's 5 Rules of Programming*](https://users.ece.utexas.edu/~adnan/pike.html):
> Data dominates. If you've chosen the right data structures and organized things well, the algorithms will almost always be self-evident. **Data structures, not algorithms, are central to programming**.

## Features

| Abstract Data Type        | Container(s)                                                   |
| ------------------------- | -------------------------------------------------------------- |
| List                      | Dynamic Array, Linked List (Singly and Doubly)                 |
| Stack                     | Dynamic Array, Linked List (Singly)                            |
| Queue                     | Dynamic Circular Array, Linked List (Singly)                   |
| Deque                     | Dynamic Circular Array, Linked List (Doubly)                                      |
| Priority Queue            | Binary Heap (Max and Min)                                      |
| Map and Set               | Hash Table (Closed Chaining and Open Addressing), Trie         |
| Ordered Map and Ordered Set | AVL Tree, Red-Black Tree, Splay Tree, In-Memory B-Tree       |
| Disjoint Set              | Forest with Path Compression and Union by Rank Heuristic       |
| Graph                     | Adjacency List, Adjacency Matrix                               |

> [!NOTE]
> Containers are not thread-safe. Instead, please check out [liblfds](https://www.liblfds.org/): "a portable, license-free, lock-free data structure library written in C".

> [!WARNING]
> Despite the popularity of intrusive data structures (e.g. [intrusive linked list](https://www.data-structures-in-practice.com/intrusive-linked-lists/)) in C code, the containers in this library are **not** intrusive.

## Installation

#### Dependencies

- [C Compiler (C23 or later)](https://gcc.gnu.org/)
- [GNU Make](https://www.gnu.org/software/make/)

#### Setup

```sh
git clone git@github.com:simontran9/taco.git
make
```

## Usage

```c
```

Head over to `docs/api.md` to learn more about available containers and its methods.
