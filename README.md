<h1><img src="docs/logo.svg" width="50px"> berry</h1>

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
| Priority Queue            | Binary Heap (Max and Min)                                      |
| Map and Set               | Hash Table (Closed Chaining and Open Addressing), Trie         |
| Ordered Map and Ordered Set | AVL Tree, Red-Black Tree, Splay Tree, In-Memory B-Tree       |
| Disjoint Set              | Forest with Path Compression and Union by Rank Heuristic       |
| Graph                     | Adjacency List, Adjacency Matrix                               |

> [!NOTE]
> Containers are not thread-safe.

> [!WARNING]
> Despite the popularity of intrusive data structures (e.g. [intrusive linked list](https://www.data-structures-in-practice.com/intrusive-linked-lists/)) in C code, the containers in this library are **not** intrusive.

## Installation

#### Core dependencies

- [C Compiler (C23 or later)](https://gcc.gnu.org/)
- [GNU Make](https://www.gnu.org/software/make/)
- [musl libc](https://musl.libc.org/)
- [jemalloc](https://jemalloc.net/)

#### Dev dependencies

 - [ClangFormat](https://clang.llvm.org/docs/ClangFormat.html)
 - [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
 - [Valgrind](https://valgrind.org/)
 - [perf](https://www.swift.org/documentation/server/guides/linux-perf.html)
 - [hyperfine](https://github.com/sharkdp/hyperfine)

#### Option 1: Static library installation

```sh
git clone git@github.com:simontran9/berry.git
make
```

#### Option 2: Dynamic library installation

```sh
```

## Usage

Head over to `docs/api.md` to learn more about available containers and its methods.

> [!WARNING]
> You will probably encounter issues if you're going use this library through a FFI

## Contributing

For developers interested in contributing, see `docs/CONTRIBUTING.md` for more information.

## License

[MIT](https://opensource.org/license/mit)