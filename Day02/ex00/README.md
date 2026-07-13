# Exercise 00 — File Signatures

**Turn-in directory:** `ex00/`

**Files to turn in:** `*.java`, `signatures.txt`

**Allowed:** All standard Java functions

**Recommended types:** `Java Collections API` (`List`, `Map`, etc.), `InputStream`, `OutputStream`, `FileInputStream`, `FileOutputStream`

---

## Context

Java's I/O system is built on a broad hierarchy of classes. At its core, the abstract classes `InputStream` and `OutputStream` define byte-level I/O behavior and delegate the actual implementation to concrete subclasses like `FileInputStream` and `FileOutputStream`.

To practice this, you will build an application that identifies arbitrary files by analyzing their **file signatures** — sequences of "magic bytes" typically found at the start of a file that reveal its true content type, regardless of the file extension.

For example, every valid PNG image begins with these exact 8 bytes:

```
89 50 4E 47 0D 0A 1A 0A
```

---

## `signatures.txt`

You must create this file yourself and hard-code its name in your program. It maps file type labels to their HEX signatures, one entry per line.

**Required format:**

```
PNG, 89 50 4E 47 0D 0A 1A 0A
GIF, 47 49 46 38 37 61
```

> The file must contain **at least 10 different formats** for analysis.

---

## Behavior

1. On startup, the program reads `signatures.txt` and loads all known signatures into memory.
2. It then enters a loop, accepting **full file paths** from standard input.
3. For each path, it reads the beginning of the file and compares the bytes against the known signatures.
4. Results are written to `result.txt`.
   - If a match is found → write the file type label (e.g. `PNG`).
   - If no signature matches → write nothing for that file (`UNDEFINED`, do not write to file).
5. The loop ends when the user inputs a number (e.g. `42`) instead of a path.

---

## Example Interaction

```
$> java Program
-> /Users/Admin/images.png
PROCESSED
-> /Users/Admin/Games/WoW.iso
PROCESSED
-> 42
$>
```

**Resulting `result.txt`:**

```
PNG
GIF
```

> Note: `result.txt` is not submitted — only your `.java` sources and `signatures.txt` are turned in.

---

## Notes

- A file's extension (e.g. `.jpg`) can be freely renamed and is not a reliable indicator of content type — only the signature bytes matter.
- Read only as many bytes from the file as needed to match the longest signature in `signatures.txt`.
- Use `FileInputStream` (not higher-level readers) to access raw bytes.
- Keep your signature-loading and matching logic cleanly separated.