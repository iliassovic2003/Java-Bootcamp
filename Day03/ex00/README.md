# Exercise 00 — Egg, Hen... or Human?

**Turn-in directory:** `ex00/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Object`, `Thread`, `Runnable`

---

## Context

The truth is born in a dispute — let's assume that each thread provides its own answer. The thread that has the last word is right.

You need to implement two competing threads, each printing its answer a set number of times. But the program also contains a **main thread** that must print all its responses **after** the two competing threads have finished.

---

## Launch

Pass the number of repetitions as a command-line argument:

```
$> java Program --count=50
```

---

## Expected Behavior

The `Egg` and `Hen` threads run concurrently and interleave unpredictably. Once they finish, the `main` thread prints `Human` exactly `--count` times at the end.

```
$> java Program --count=50
Egg
Hen
Hen
Hen
...
Egg
Hen
...
Human
...
...
Human
$>
```

- `Egg` and `Hen` each print their word `--count` times (e.g. 50).
- `Human` is printed `--count` times by the **main thread**, always after the other two threads complete.
- The interleaving of `Egg` and `Hen` is non-deterministic — either one may "win".

---

## Notes

- Explore **all ways** of creating threads in Java:
  - Extending `Thread`
  - Implementing `Runnable`
  - Using lambda expressions
- It would be best to solve this task using **each approach** separately.
- Use `Thread.join()` in the main thread to wait for both competing threads to finish before printing `Human`.
- Use `System.out` for all output.