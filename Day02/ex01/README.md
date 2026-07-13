# Exercise 01 — Words

**Turn-in directory:** `ex01/`

**Files to turn in:** `*.java`

**Allowed:** All standard Java functions

**Recommended types:** `Java Collections API`, `Java IO`

---

## Context

Beyond raw byte streams, Java provides character-oriented I/O classes like the abstract `Reader`/`Writer` and their concrete implementations (`FileReader`, `FileWriter`, etc.). Of particular interest are `BufferedReader` and `BufferedWriter`, which improve performance through internal buffering — ideal for reading large text files line by line.

In this exercise you will measure the **textual similarity** between two files using **cosine similarity** on word-frequency vectors.

---

## How It Works

### 1. Build a dictionary

Read both files and collect every unique word into a shared dictionary (sorted or insertion-ordered). For example, given:

- File A: `aaa bba bba a ccc`
- File B: `bba a a a bb xxx`

The dictionary is: `a, aaa, bb, bba, ccc, xxx`

### 2. Build frequency vectors

For each file, create a vector of length equal to the dictionary size. Position `i` holds the count of how many times the `i`-th dictionary word appears in that file:

```
A = (1, 1, 0, 2, 1, 0)
B = (3, 0, 1, 1, 0, 1)
```

### 3. Compute cosine similarity

Apply the following formula:

```
similarity(A, B) = (A · B) / (||A|| × ||B||)
```

Where:
- `A · B` is the dot product: sum of `A[i] * B[i]` for all `i`
- `||A||` is the magnitude of A: `sqrt(sum of A[i]²)`
- `||B||` is the magnitude of B: `sqrt(sum of B[i]²)`

**Example calculation:**

```
A · B  = (1×3 + 1×0 + 0×1 + 2×1 + 1×0 + 0×1) = 5

||A||  = sqrt(1 + 1 + 0 + 4 + 1 + 0) = sqrt(7)  ≈ 2.64
||B||  = sqrt(9 + 0 + 1 + 1 + 0 + 1) = sqrt(12) ≈ 3.46

similarity = 5 / (2.64 × 3.46) ≈ 0.54
```

---

## Output

Print the similarity score to standard output and write the full dictionary to `dictionary.txt`.

```
$> java Program inputA.txt inputB.txt
Similarity = 0.54
$>
```

**`dictionary.txt`** should contain one word per line covering all unique words from both files combined.

---

## Notes

- Both input files are passed as **command-line arguments**.
- Maximum file size is **10 MB** each.
- Files may contain **non-letter characters** — strip or ignore punctuation when tokenizing words.
- Comparison should be **case-insensitive** (normalize to lowercase before building the dictionary).
- Use `BufferedReader` for efficient file reading.
- `dictionary.txt` is a side effect of the program — it is not submitted, only your `.java` files are.