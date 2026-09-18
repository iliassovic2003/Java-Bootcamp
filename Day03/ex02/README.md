# Exercise 02 — Real Multithreading

**Turn-in directory:** `ex02/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Object`, `Thread`, `Runnable`

**Keywords:** `Synchronized`

---

## Context

Use multithreading for its intended purpose: **distributing computation across threads**.

Given an array of integers, your goal is to calculate the total sum by splitting the work across several "summing" threads. Each thread is responsible for a contiguous section of the array. The section size is constant for all threads except possibly the last one, which may cover fewer or more elements.

The array is **randomly generated** each run. To verify correctness, the program must also compute the sum using a standard single-pass method and compare both results.

---

## Launch

Pass array size and thread count as command-line arguments:

```
$> java Program --arraySize=13 --threadsCount=3
```

---

## Constraints

| Parameter | Maximum |
|---|---|
| Array elements | 2,000,000 |
| Threads | ≤ number of array elements |
| Element value (absolute) | 1,000 |

All input data is guaranteed to be valid.

---

## Example Interaction

In this example every array element equals `1`, so the total sum equals the array size:

```
$> java Program --arraySize=13 --threadsCount=3
Sum: 13
Thread 1: from 0 to 4 sum is 5
Thread 2: from 5 to 9 sum is 5
Thread 3: from 10 to 12 sum is 3
Sum by threads: 13
$>
```

- **`Sum:`** — result from the standard (single-threaded) calculation, printed first.
- **`Thread N: from X to Y sum is Z`** — each thread reports its assigned range and partial sum. Threads may print in any order.
- **`Sum by threads:`** — the combined result from all threads, printed after all threads finish.

> The last thread's section (`10–12`) is smaller than the others (`0–4`, `5–9`) because 13 doesn't divide evenly by 3.

---

## Notes

- The section size per thread is `arraySize / threadsCount`. The last thread covers whatever remains.
- Use `synchronized` or `AtomicLong` to safely accumulate the partial sums into a shared total.
- Use `Thread.join()` to wait for all threads to complete before printing `Sum by threads:`.
- Thread output lines may appear in any order — this is expected and correct.
- Use `System.out` for all output.