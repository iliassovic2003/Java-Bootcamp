# Exercise 03 — Too Many Threads...

**Turn-in directory:** `ex03/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Object`, `Thread`, `Runnable`

**Keywords:** `Synchronized`

---

## Context

Imagine downloading a list of files from the network, where some files are fast and others are slow. A naïve approach — one thread per file — breaks down when there are too many files: spawning and tearing down threads constantly is expensive, and running hundreds at once is impractical.

The solution is a **thread pool**: start exactly `N` threads upfront, and whenever a thread finishes downloading a file, it picks up the next one from the queue. Threads are reused, not recreated.

---

## Setup

Create a file named **`files_urls.txt`** (the filename must be hardcoded in your program) listing one URL per line:

```
$> cat files_urls.txt
https://i.pinimg.com/originals/11/19/2e/11192eba63f6f3aa591d3263fdb66bd5.jpg
https://pluspng.com/img-png/balloon-hd-png-balloons-png-hd-2750.png
https://i.pinimg.com/originals/db/a1/62/dba162603c71cac00d3548420c52bac6.png
https://pngimg.com/uploads/balloon/balloon_PNG4969.png
http://tldp.org/LDP/intro-linux/intro-linux.pdf
```

---

## Launch

Pass the number of worker threads as a command-line argument:

```
$> java Program.java --threadsCount=3
```

---

## Example Interaction

```
$> java Program.java --threadsCount=3
Thread-3 start download IZ_FILE_3.png
Thread-1 start download IZ_FILE_1.jpg
Thread-1 finish download IZ_FILE_1.jpg
Thread-3 finish download IZ_FILE_3.png
Thread-2 start download IZ_FILE_2.png
Thread-2 finish download IZ_FILE_2.png
Thread-3 start download IZ_FILE_5.pdf
Thread-3 finish download IZ_FILE_5.pdf
Thread-1 failed to download IZ_FILE_4.png. Retry 1
Thread-2 failed to download IZ_FILE_6.jpg. Retry 1
Thread-3 failed to download IZ_FILE_7.png. Retry 1
Thread-2 failed to download IZ_FILE_6.jpg. Retry 2
Thread-1 failed to download IZ_FILE_4.png. Retry 2
Thread-3 failed to download IZ_FILE_7.png. Retry 2
Thread-2 failed to download IZ_FILE_6.jpg. Retry 3
Thread-1 failed to download IZ_FILE_4.png. Retry 3
Thread-3 failed to download IZ_FILE_7.png. Retry 3
Thread-1 failed to download IZ_FILE_9.png. Retry 1
Thread-2 start download IZ_FILE_8.pdf
Thread-2 finish download IZ_FILE_8.pdf
Thread-1 failed to download IZ_FILE_9.png. Retry 2
Thread-1 failed to download IZ_FILE_9.png. Retry 3
Thread-1 failed to download IZ_FILE_12.svg. Retry 1
Thread-2 start download IZ_FILE_11.pdf
Thread-1 failed to download IZ_FILE_12.svg. Retry 2
Thread-1 failed to download IZ_FILE_12.svg. Retry 3
Thread-3 start download IZ_FILE_10.txt
Thread-3 finish download IZ_FILE_10.txt
Thread-2 finish download IZ_FILE_11.pdf
Thread-3 failed to download IZ_FILE_14.jpg. Retry 1
Thread-3 failed to download IZ_FILE_14.jpg. Retry 2
Thread-3 failed to download IZ_FILE_14.jpg. Retry 3
Thread-1 start download IZ_FILE_13.pdf
Thread-1 finish download IZ_FILE_13.pdf
$>
```

> Output order will vary between runs — threads pick up files as they become available.

---

## Notes

- Each file is downloaded **exactly once**, by a **single thread**.
- Threads are named `Thread-1`, `Thread-2`, … `Thread-N`.
- Use a **shared queue** (e.g. `LinkedList` or `ArrayDeque`) protected by `synchronized` to hand out file numbers to threads safely.
- The program may run in an **infinite loop** without an explicit exit condition — terminating the process (Ctrl+C) is acceptable behavior.
- Each "download" can be simulated with `Thread.sleep()` to mimic network latency.
- Use `System.out` for all output.