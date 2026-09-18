# Java Piscine — Module 03
## Threads

An introduction to Java's multithreading model — creating threads, synchronizing them, coordinating execution order, and distributing work across a thread pool.

---

## 📋 Project Structure

```
.
├── ex00
│   ├── Program.java
│   └── README.md
├── ex01
│   ├── Program.java
│   └── README.md
├── ex02
│   ├── Program.java
│   └── README.md
└── ex03
    ├── files_urls.txt
    ├── Program.java
    └── README.md

4 directories, 9 files
```

---

## Module 03 — Threads

A step-by-step exploration of Java's concurrency primitives. Each exercise targets a different layer of multithreading, from basic thread creation to synchronization patterns to a full thread-pool implementation.

| Exercise | Key concept | Description |
|----------|-------------|-------------|
| [ex00](ex00/README.md) | Thread creation | Launch competing `Egg` and `Hen` threads freely; main thread always prints last |
| [ex01](ex01/README.md) | Synchronized alternation | Force strict `Egg → Hen → Egg → Hen…` ordering using `wait` / `notify` |
| [ex02](ex02/README.md) | Parallel computation | Split array summation across N threads and verify against a single-threaded reference |
| [ex03](ex03/README.md) | Thread pool | Download a URL list with a fixed pool of reusable worker threads |