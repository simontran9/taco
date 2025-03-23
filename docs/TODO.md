
- write docs
- bench step
- hash table
    - https://go.dev/blog/swisstable
    - Rust hashing framework (see matthieum's comment)
    - https://benhoyt.com/writings/hash-table-in-c/
    - https://craftinginterpreters.com/hash-tables.html
- optimize integer operations with bit operators
https://github.com/sharkdp/fd-benchmarks

- If have time (lock-free containers)
    - Facebook folly
    - [Java's std lib](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/package-summary.html)
	- https://github.com/rigtorp/awesome-lockfree, https://www.reddit.com/r/rust/comments/bzka7m/learning_resources_for_lockfree_and_waitfree_data/
	- https://stroustrup.com/lock-free-vector.pdf, https://www.cs.rochester.edu/~scott/papers/1996_PODC_queues.pdf`