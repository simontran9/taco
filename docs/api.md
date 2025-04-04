# API

> [!note]
> API design is inspired from [klib](https://github.com/attractivechaos/klib) and [OpenBSD](https://github.com/openbsd/src/blob/c1d6f13173b788e34852a303bda0c5a53861979e/sys/sys/tree.h)

## List

### `ArrayList`

### `SinglyLinkedList`

### `DoublyLinkedList`

## Stack

### `ArrayStack`

`define GENERATE_ARRAYSTACK(<name>, <type>)`: define a name and the type you want your ArrayStack to be.

`ARRAYSTACK_CREATE(name)`:

`ARRAYSTACK_DESTROY(name, stack)`:

`ARRAYSTACK_LEN(name, stack)`:

`ARRAYSTACK_IS_EMPTY(name, stack)`:

`ARRAYSTACK_TOP(name, stack, out_element)`:

`ARRAYSTACK_PUSH(name, stack, element)`:

`ARRAYSTACK_POP(name, stack, out_element)`:

### `LinkedStack`

`define GENERATE_LINKEDSTACK(<name>, <type>)`: define a name and the type you want your ArrayStack to be.

`LINKEDSTACK_CREATE(name)`:

`LINKEDSTACK_DESTROY(name, stack)`:

`LINKEDSTACK_LEN(name, stack)`:

`LINKEDSTACK_IS_EMPTY(name, stack)`:

`LINKEDSTACK_TOP(name, stack, out_element)`:

`LINKEDSTACK_PUSH(name, stack, element)`:

`LINKEDSTACK_POP(name, stack, out_element)`:

## Queue

### `ArrayQueue`

### `LinkedQueue`

## PriorityQueue

### `MaxHeapPriorityQueue`

### `MinHeapPriorityQueue`

## Ordered Map

### `AVLTreeMap`

### `RBTreeMap`

### `BTreeMap`

## Ordered Set

### `AVLTreeSet`

### `RBTreeSet`

### `BTreeSet`

## Map

### `OAHashMap`

### `ChainingHashMap`

### `TrieMap`

## Set

### `OAHashSet`

### `ChainingHashSet`

### `TrieSet`

## Disjoint Set (`ForestDisjointSet`)

## Graph

### `AdjacencyListGraph`

### `AdjacencyMatrixGraph`