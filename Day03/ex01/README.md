# Exercise 01 — Egg, Hen, Egg, Hen...

**Turn-in directory:** `ex01/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Object`, `Thread`, `Runnable`

**Keywords:** `Synchronized`

---

## Context

Let's orchestrate the argument. Now, each thread can provide its answer **only after the other thread has done so**. The `Egg` thread always answers first.

This exercise introduces thread synchronization: the two threads must alternate in strict order rather than running freely.

---

## Launch

Pass the number of repetitions as a command-line argument:

```
$> java Program --count=50
```

---

## Expected Output

The output must strictly alternate between `Egg` and `Hen`, starting with `Egg`:

```
$> java Program --count=50
Egg
Hen
Egg
Hen
Egg
Hen
...
$>
```

- `Egg` always prints first.
- `Hen` prints only after `Egg` has printed.
- They alternate for exactly `--count` iterations each.
- The sequence is **deterministic** — no random interleaving.

---

## Notes

- Study the **Producer-Consumer model** — it's the recommended design pattern for this exercise.
- Use `synchronized`, `wait()`, and `notify()` / `notifyAll()` to coordinate the two threads.
- A shared flag or state variable (e.g. `boolean eggTurn = true`) can signal whose turn it is.
- Use `System.out` for all output.